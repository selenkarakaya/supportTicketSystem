<script setup>
import { reactive, ref, onMounted, computed } from 'vue'
import { useAuthStore } from '@/stores/authStore'
import { useDepartmentStore } from '@/stores/departmentStore'
import { useTicketCategoryStore } from '@/stores/ticketCategoryStore'
import { useRouter } from 'vue-router'

const authStore = useAuthStore()
const departmentStore = useDepartmentStore()
const ticketCategoryStore = useTicketCategoryStore()
const formRef = ref(null)

const snackbar = ref(false)
const snackbarMessage = ref('')
const snackbarColor = ref('success')

onMounted(async () => {
  const authenticated = await authStore.checkAuth()

  if (!authenticated || !authStore.user?.companyId) {
    return
  }

  await departmentStore.getDepartmentsByCompany(authStore.user.companyId)
  await ticketCategoryStore.getTicketCategories()
  console.log('Departments:', JSON.stringify(ticketCategoryStore.categories, null, 2))
})

const currentUser = computed(() => {
  const fullName = authStore.user?.fullName
    ? authStore.user.fullName
        .toLowerCase()
        .split(' ')
        .map((name) => name.charAt(0).toUpperCase() + name.slice(1))
        .join(' ')
    : ''

  const role = authStore.user?.role
    ? authStore.user.role
        .toLowerCase()
        .replaceAll('_', ' ')
        .split(' ')
        .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
        .join(' ')
    : ''
  const departmentName = authStore.user?.departmentName
  return { fullName, role, departmentName }
})
const requiredTextRule = (value) => {
  return Boolean(value?.trim()) || 'This field is required.'
}

const subjectLengthRule = (value) => {
  return !value || value.length <= 200 || 'Subject must not exceed 200 characters.'
}

const requiredSelectRule = (value) => {
  return (value !== null && value !== undefined && value !== '') || 'Please select an option.'
}
const form = reactive({
  subject: '',
  description: '',
  priorityId: null,
  categoryId: null,
  departmentId: null,
})

const createTicket = async () => {
  const validationResult = await formRef.value.validate()

  if (!validationResult.valid) {
    return
  }
  const ticketData = {
    subject: form.subject.trim(),
    description: form.description.trim(),
    priorityId: form.priorityId,
    categoryId: form.categoryId,
    departmentId: form.departmentId,
  }

  console.log(ticketData)
}
</script>
<template>
  <v-container class="py-8">
    <v-row justify="center">
      <v-col cols="12" md="11" lg="10" xl="9">
        <v-card border elevation="0" rounded="xl">
          <v-card-title class="d-flex align-center ga-3 pa-6">
            <v-avatar color="indigo-lighten-5" rounded="lg" size="48">
              <v-icon color="indigo" icon="mdi-ticket-confirmation-outline" />
            </v-avatar>

            <div>
              <div class="text-h5 font-weight-bold">Create a Ticket</div>

              <div class="text-body-2 text-grey-darken-1">
                Provide the details of your issue below.
              </div>
            </div>
          </v-card-title>

          <v-divider />

          <v-card-text class="pa-6">
            <v-card class="mb-6" color="indigo-lighten-5" elevation="0" rounded="lg">
              <v-card-title class="text-subtitle-1 font-weight-bold">
                <v-icon class="mr-2" color="indigo" icon="mdi-account-circle-outline" />

                Requester Information
              </v-card-title>
              <v-card-text>
                <v-row>
                  <v-col cols="12" md="4">
                    <v-text-field
                      :model-value="currentUser.fullName"
                      bg-color="white"
                      color="indigo"
                      label="Created By"
                      prepend-inner-icon="mdi-account-outline"
                      readonly
                      variant="outlined"
                    />
                  </v-col>

                  <v-col cols="12" md="4">
                    <v-text-field
                      :model-value="currentUser.role"
                      class="text-caption"
                      bg-color="white"
                      color="indigo"
                      label="Role"
                      prepend-inner-icon="mdi-shield-account-outline"
                      readonly
                      variant="outlined"
                    />
                  </v-col>

                  <v-col cols="12" md="4">
                    <v-text-field
                      :model-value="currentUser.departmentName || 'Not assigned'"
                      class="text-caption"
                      bg-color="white"
                      color="indigo"
                      label="Department"
                      prepend-inner-icon="mdi-domain"
                      readonly
                      variant="outlined"
                    />
                  </v-col>
                </v-row>
              </v-card-text>
            </v-card>
            <v-form ref="formRef" @submit.prevent="createTicket">
              <v-row>
                <v-col cols="12">
                  <v-text-field
                    v-model="form.subject"
                    :rules="[requiredTextRule, subjectLengthRule]"
                    color="indigo"
                    label="Subject"
                    placeholder="Enter a short summary"
                    prepend-inner-icon="mdi-format-title"
                    variant="outlined"
                  />
                </v-col>

                <v-col cols="12" md="6">
                  <v-select
                    v-model="form.categoryId"
                    :items="ticketCategoryStore.categories"
                    :loading="ticketCategoryStore.loading"
                    :rules="[requiredSelectRule]"
                    color="indigo"
                    item-title="label"
                    item-value="id"
                    label="Category"
                    placeholder="Select a category"
                    prepend-inner-icon="mdi-shape-outline"
                    variant="outlined"
                  />
                </v-col>

                <v-col cols="12" md="6">
                  <v-select
                    v-model="form.priorityId"
                    :rules="[requiredSelectRule]"
                    color="indigo"
                    label="Priority"
                    placeholder="Select a priority"
                    prepend-inner-icon="mdi-flag-outline"
                    variant="outlined"
                  />
                </v-col>

                <v-col cols="12" md="6">
                  <v-select
                    v-model="form.departmentId"
                    :items="departmentStore.departments"
                    :loading="departmentStore.loading"
                    clearable
                    color="indigo"
                    item-title="name"
                    item-value="id"
                    label="Department (optional)"
                    no-data-text="No departments available"
                    placeholder="Select a department"
                    prepend-inner-icon="mdi-domain"
                    variant="outlined"
                  />
                </v-col>

                <v-col cols="12" md="6">
                  <v-select
                    chips
                    clearable
                    closable-chips
                    color="indigo"
                    label="Assign To (optional)"
                    multiple
                    placeholder="Select team members"
                    prepend-inner-icon="mdi-account-multiple-plus-outline"
                    variant="outlined"
                  />
                </v-col>

                <v-col cols="12">
                  <v-textarea
                    v-model="form.description"
                    :rules="[requiredTextRule]"
                    auto-grow
                    color="indigo"
                    label="Description"
                    placeholder="Describe the issue and any steps you have already tried"
                    prepend-inner-icon="mdi-text-box-outline"
                    rows="6"
                    variant="outlined"
                  />
                </v-col>
              </v-row>
            </v-form>
          </v-card-text>

          <v-divider />

          <v-card-actions class="justify-end pa-6">
            <v-btn color="grey-darken-1" prepend-icon="mdi-close" variant="text"> Cancel </v-btn>

            <v-btn
              color="indigo"
              prepend-icon="mdi-send-outline"
              size="large"
              variant="flat"
              @click="createTicket"
            >
              Create Ticket
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
