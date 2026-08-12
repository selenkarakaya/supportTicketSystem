<script setup>
import { reactive, ref } from 'vue'
import { useAuthStore } from '@/stores/authStore'

const formRef = ref(null)
const authStore = useAuthStore()

const form = reactive({
  companyName: '',
  adminFullName: '',
  adminEmail: '',
  adminPassword: '',
  adminConfirmPassword: '',
  termsAccepted: false,
})

const requiredRule = (value) => {
  return Boolean(value?.trim()) || 'This field is required.'
}
const emailRule = (value) => {
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  return emailPattern.test(value) || 'Enter a valid email address.'
}

const passwordRule = (value) => {
  if (!value || value.length < 8) {
    return 'Password must contain at least 8 characters.'
  }

  if (!/[A-Z]/.test(value)) {
    return 'Password must contain at least one uppercase letter.'
  }
  if (!/[0-9]/.test(value)) {
    return 'Password must contain at least one number.'
  }
  if (!/[a-z]/.test(value)) {
    return 'Password must contain at least one lowercase letter.'
  }
  if (!/[!@#$%^&*(),.?":{}|<>]/.test(value)) {
    return 'Password must contain at least one special character.'
  }

  return true
}

const confirmPasswordRule = (value) => {
  return value === form.adminPassword || 'Passwords do not match.'
}

const termsRule = (value) => {
  return value === true || 'You must accept the terms.'
}

const registerCompany = async () => {
  const validationResult = await formRef.value.validate()

  if (!validationResult.valid) {
    return
  }

  const registerData = {
    companyName: form.companyName.trim(),
    adminFullName: form.adminFullName.trim(),
    adminEmail: form.adminEmail.trim().toLowerCase(),
    password: form.adminPassword,
  }

  await authStore.registerCompany(registerData)
}
</script>

<template>
  <v-container
    fluid
    class="pa-4 pa-md-6"
    style="
      min-height: calc(100vh - 64px);
      background: linear-gradient(135deg, #fcfcfd 0%, #f4f8fd 50%, #fcfcfd 100%);
    "
  >
    <v-row align="center" justify="center" class="py-6 py-md-10">
      <!-- Left side -->

      <v-col cols="12" md="5" lg="4">
        <v-card color="transparent" elevation="0" class="pa-4 pa-md-6">
          <v-chip color="indigo" variant="tonal" prepend-icon="mdi-domain-plus" class="mb-5">
            Create your company workspace
          </v-chip>

          <h1 class="register-title text-indigo-darken-3 mb-5">Build your support workspace</h1>

          <p class="text-body-1 text-blue-grey-darken-1 mb-7">
            Register your company and create the first administrator account. After registration,
            you can add offices, departments and invite your team.
          </p>

          <v-list bg-color="transparent" class="pa-0">
            <v-list-item class="px-0 mb-2">
              <template #prepend>
                <v-avatar color="green-lighten-5" size="42" class="me-3">
                  <v-icon color="green">mdi-domain</v-icon>
                </v-avatar>
              </template>

              <v-list-item-title class="font-weight-medium">
                Private company workspace
              </v-list-item-title>

              <v-list-item-subtitle>
                Keep your company data separate and secure
              </v-list-item-subtitle>
            </v-list-item>

            <v-list-item class="px-0 mb-2">
              <template #prepend>
                <v-avatar color="blue-lighten-5" size="42" class="me-3">
                  <v-icon color="blue">mdi-account-key-outline</v-icon>
                </v-avatar>
              </template>

              <v-list-item-title class="font-weight-medium">
                First administrator account
              </v-list-item-title>

              <v-list-item-subtitle>
                The first user will manage the company workspace
              </v-list-item-subtitle>
            </v-list-item>

            <v-list-item class="px-0 mb-2">
              <template #prepend>
                <v-avatar color="purple-lighten-5" size="42" class="me-3">
                  <v-icon color="purple">mdi-account-multiple-plus-outline</v-icon>
                </v-avatar>
              </template>

              <v-list-item-title class="font-weight-medium">
                Invite your employees
              </v-list-item-title>

              <v-list-item-subtitle>
                Add support managers, agents and employees later
              </v-list-item-subtitle>
            </v-list-item>

            <v-list-item class="px-0">
              <template #prepend>
                <v-avatar color="orange-lighten-5" size="42" class="me-3">
                  <v-icon color="orange">mdi-office-building-outline</v-icon>
                </v-avatar>
              </template>

              <v-list-item-title class="font-weight-medium">
                Organise your company
              </v-list-item-title>

              <v-list-item-subtitle>
                Add offices and departments after registration
              </v-list-item-subtitle>
            </v-list-item>
          </v-list>
        </v-card>
      </v-col>

      <!-- Registration card -->
      <v-col cols="12" md="7" lg="5">
        <v-card color="white" elevation="2" class="pa-5 pa-sm-7 rounded-xl">
          <div class="text-center mb-7">
            <v-avatar color="indigo" size="58" rounded="lg" class="mb-4">
              <v-icon color="white" size="32">mdi-domain-plus</v-icon>
            </v-avatar>

            <v-card-title class="pa-0 text-h4 font-weight-bold text-indigo-darken-4 mb-2">
              Create your workspace
            </v-card-title>

            <v-card-subtitle class="pa-0 text-body-2 text-blue-grey-darken-1">
              Register your company and its first administrator.
            </v-card-subtitle>
          </div>

          <v-alert color="indigo" variant="tonal" icon="mdi-information-outline" class="mb-6">
            The administrator account will be used to sign in and manage the company workspace.
          </v-alert>

          <v-form ref="formRef" @submit.prevent="registerCompany">
            <p class="text-subtitle-1 font-weight-bold text-indigo-darken-3 mb-3">
              Company details
            </p>

            <v-text-field
              v-model="form.companyName"
              label="Company name"
              prepend-inner-icon="mdi-domain"
              variant="outlined"
              color="indigo"
              class="mb-3"
              required
              :rules="[requiredRule]"
            />

            <p class="text-subtitle-1 font-weight-bold text-indigo-darken-3 mb-3 mt-2">
              Administrator details
            </p>

            <v-text-field
              v-model="form.adminFullName"
              label="Administrator full name"
              prepend-inner-icon="mdi-account-outline"
              variant="outlined"
              color="indigo"
              class="mb-3"
              required
              :rules="[requiredRule]"
            />

            <v-text-field
              v-model="form.adminEmail"
              label="Administrator work email"
              prepend-inner-icon="mdi-email-outline"
              variant="outlined"
              color="indigo"
              type="email"
              class="mb-3"
              required
              :rules="[requiredRule, emailRule]"
            />

            <v-text-field
              v-model="form.adminPassword"
              label="Password"
              prepend-inner-icon="mdi-lock-outline"
              append-inner-icon="mdi-eye-outline"
              variant="outlined"
              color="indigo"
              type="password"
              hint="Use at least 8 characters."
              persistent-hint
              class="mb-3"
              required
              :rules="[requiredRule, passwordRule]"
            />

            <v-text-field
              v-model="form.adminConfirmPassword"
              label="Confirm password"
              prepend-inner-icon="mdi-lock-check-outline"
              append-inner-icon="mdi-eye-outline"
              variant="outlined"
              color="indigo"
              type="password"
              class="mb-2"
              required
              :rules="[requiredRule, confirmPasswordRule]"
            />

            <v-checkbox
              v-model="form.termsAccepted"
              color="indigo"
              class="mb-4"
              required
              :rules="[termsRule]"
            >
              <template #label>
                <span class="text-body-2">
                  I agree to the
                  <span class="text-indigo font-weight-medium"> Terms of Service </span>
                  and
                  <span class="text-indigo font-weight-medium"> Privacy Policy </span>
                </span>
              </template>
            </v-checkbox>

            <v-btn
              color="indigo"
              size="large"
              block
              class="text-none mb-5"
              append-icon="mdi-arrow-right"
              type="submit"
            >
              Create Company Workspace
            </v-btn>
          </v-form>

          <div class="d-flex align-center ga-3 mb-5">
            <v-divider />

            <span class="text-caption text-blue-grey-darken-1"> OR </span>

            <v-divider />
          </div>

          <v-btn
            variant="outlined"
            size="large"
            block
            class="text-none mb-6"
            prepend-icon="mdi-google"
          >
            Continue with Google
          </v-btn>

          <p class="text-center text-body-2 text-blue-grey-darken-1 mb-0">
            Already have an account?

            <v-btn
              variant="text"
              color="indigo"
              size="small"
              class="text-none font-weight-bold px-1"
            >
              Sign in
            </v-btn>
          </p>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
