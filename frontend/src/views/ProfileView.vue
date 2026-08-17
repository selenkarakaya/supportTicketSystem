<script setup>
import { computed } from 'vue'
import { useAuthStore } from '@/stores/authStore'
import { useInvitationStore } from '@/stores/invitationStore'
import { useRouter } from 'vue-router'
import { ref } from 'vue'

const authStore = useAuthStore()
const router = useRouter()

const invitationStore = useInvitationStore()
const snackbar = ref(false)
const snackbarMessage = ref('')

const formattedUser = computed(() => {
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

  return {
    fullName,
    role,
  }
})

const isAdmin = computed(() => {
  return authStore.user?.role === 'COMPANY_ADMIN'
})

const createEmployeeInvite = async () => {
  const success = await invitationStore.createEmployeeInvitation()

  if (success) {
    snackbarMessage.value = invitationStore.invitationLink
    snackbar.value = true
  }
}

const createSupportAgentInvite = async () => {
  const success = await invitationStore.createSupportAgentInvitation()

  if (success) {
    snackbarMessage.value = invitationStore.invitationLink
    snackbar.value = true
  }
}

const copyInvitationLink = async () => {
  try {
    await navigator.clipboard.writeText(snackbarMessage.value)
    console.log('Copied')
  } catch (error) {
    console.error('Copy failed:', error)
  }
}
</script>

<template>
  <v-snackbar v-model="snackbar" timeout="10000" location="top right" color="success" rounded="lg">
    {{ snackbarMessage }}

    <template #actions>
      <v-btn variant="text" @click="copyInvitationLink"> Copy </v-btn>
    </template>
  </v-snackbar>
  <v-container class="py-8">
    <v-card class="pa-6 rounded-xl" elevation="1">
      <h1 class="text-h4 font-weight-bold mb-6">Profile</h1>

      <v-row>
        <v-col cols="12" md="6">
          <p class="text-caption text-grey-darken-1 mb-1">Full Name</p>

          <p class="text-body-1 font-weight-medium">
            {{ formattedUser.fullName }}
          </p>
        </v-col>

        <v-col cols="12" md="6">
          <p class="text-caption text-grey-darken-1 mb-1">Email</p>

          <p class="text-body-1 font-weight-medium">
            {{ authStore.user?.email }}
          </p>
        </v-col>

        <v-col cols="12" md="6">
          <p class="text-caption text-grey-darken-1 mb-1">Role</p>

          <p class="text-body-1 font-weight-medium">
            {{ formattedUser.role }}
          </p>
        </v-col>

        <v-col cols="12" md="6">
          <p class="text-caption text-grey-darken-1 mb-1">Company</p>

          <p class="text-body-1 font-weight-medium">
            {{ authStore.user?.companyName }}
          </p>
        </v-col>
      </v-row>

      <template v-if="isAdmin">
        <v-divider class="my-6" />

        <h2 class="text-h6 font-weight-bold mb-4">Company Administration</h2>

        <div class="d-flex flex-wrap ga-3">
          <v-btn
            color="indigo"
            prepend-icon="mdi-domain"
            class="text-none"
            @click="router.push('/company/settings')"
          >
            Edit Company Information
          </v-btn>

          <v-btn
            color="indigo"
            variant="outlined"
            prepend-icon="mdi-office-building-outline"
            class="text-none"
            @click="router.push('/company/departments')"
          >
            Manage Departments
          </v-btn>

          <v-btn
            color="indigo"
            variant="outlined"
            prepend-icon="mdi-account-plus"
            class="text-none"
            @click="createEmployeeInvite"
          >
            Invite Employee
          </v-btn>

          <v-btn
            color="indigo"
            variant="outlined"
            prepend-icon="mdi-headset"
            class="text-none"
            @click="createSupportAgentInvite"
          >
            Invite Support Agent
          </v-btn>
          <v-list-item
            :to="{ name: 'employees' }"
            prepend-icon="mdi-account-group-outline"
            title="Employees"
          />
        </div>
      </template>
    </v-card>
  </v-container>
</template>
