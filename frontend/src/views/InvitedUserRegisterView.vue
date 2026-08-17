<script setup>
import { computed, onMounted, reactive, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'

import { useInvitationStore } from '@/stores/invitationStore'
import { useDepartmentStore } from '@/stores/departmentStore'

// Store instances
const invitationStore = useInvitationStore()
const departmentStore = useDepartmentStore()

// Router instances
const route = useRoute()
const router = useRouter()

// Vuetify form reference
// This allows us to use formRef.value.validate() and formRef.value.reset()
const formRef = ref(null)

// Get invitation token from URL
// Example:
// /register-employee?token=abc123
const token = route.query.token

// Snackbar state
const snackbar = ref(false)
const snackbarMessage = ref('')
const snackbarColor = ref('success')

// Register form data
const form = reactive({
  fullName: '',
  email: '',
  password: '',
  confirmPassword: '',
  phone: '',
  jobTitle: '',
  departmentId: null,
})

// Required field validation
const requiredRule = (value) => {
  return Boolean(value?.trim()) || 'This field is required.'
}

// Department validation
// Department value is an ID, so we do not use trim()
const departmentRule = (value) => {
  return Boolean(value) || 'Please select a department.'
}

// Email validation
const emailRule = (value) => {
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/

  return emailPattern.test(value) || 'Enter a valid email address.'
}

// Password validation
const passwordRule = (value) => {
  if (!value || value.length < 8) {
    return 'Password must contain at least 8 characters.'
  }

  if (!/[A-Z]/.test(value)) {
    return 'Password must contain at least one uppercase letter.'
  }

  if (!/[a-z]/.test(value)) {
    return 'Password must contain at least one lowercase letter.'
  }

  if (!/[0-9]/.test(value)) {
    return 'Password must contain at least one number.'
  }

  if (!/[!@#$%^&*(),.?":{}|<>]/.test(value)) {
    return 'Password must contain at least one special character.'
  }

  return true
}

// Confirm password validation
const confirmPasswordRule = (value) => {
  return value === form.password || 'Passwords do not match.'
}

// Format role for display
// SUPPORT_AGENT -> Support Agent
// EMPLOYEE -> Employee
const formattedRole = computed(() => {
  const role = invitationStore.invitation?.role

  if (!role) {
    return ''
  }

  return role
    .toLowerCase()
    .replaceAll('_', ' ')
    .split(' ')
    .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
    .join(' ')
})

// Change page title based on invitation role
const invitationTitle = computed(() => {
  if (invitationStore.invitation?.role === 'SUPPORT_AGENT') {
    return 'Create Support Agent Account'
  }

  return 'Create Employee Account'
})

// When page opens:
// 1. Check token exists
// 2. Validate token
// 3. Get company departments
onMounted(async () => {
  if (!token) {
    return
  }

  const valid = await invitationStore.validateInvitation(token)

  if (valid) {
    await departmentStore.getDepartmentsByCompany(invitationStore.invitation.companyId)
  }
})

// Submit register form
const handleRegister = async () => {
  // Run Vuetify form validation first
  const validationResult = await formRef.value.validate()

  if (!validationResult.valid) {
    return
  }

  // Prepare data for backend
  // companyId and role are NOT sent.
  // Backend gets them from invitation token.
  const registerData = {
    token: token,
    fullName: form.fullName.trim(),
    email: form.email.trim().toLowerCase(),
    password: form.password,
    phone: form.phone.trim(),
    jobTitle: form.jobTitle.trim(),
    departmentId: form.departmentId,
  }

  // Send register request through Pinia store
  const success = await invitationStore.registerInvitedUser(registerData)

  if (success) {
    // Show backend success message
    snackbarMessage.value = invitationStore.successMessage

    snackbarColor.value = 'success'

    // Reset Vuetify form
    formRef.value.reset()

    // Show snackbar
    snackbar.value = true

    // Redirect to login after a short delay
    setTimeout(() => {
      router.push('/login')
    }, 1500)
  } else {
    // Show backend error message
    snackbarMessage.value = invitationStore.errorMessage

    snackbarColor.value = 'error'
    snackbar.value = true
  }
}
</script>

<template>
  <v-container class="py-10">
    <!-- Success / Error Snackbar -->
    <v-snackbar
      v-model="snackbar"
      :color="snackbarColor"
      timeout="5000"
      location="top right"
      rounded="lg"
    >
      {{ snackbarMessage }}
    </v-snackbar>

    <v-row justify="center">
      <v-col cols="12" sm="10" md="7" lg="5">
        <!-- Loading invitation -->
        <v-card v-if="invitationStore.loading" class="pa-6 rounded-xl text-center" elevation="2">
          <v-progress-circular indeterminate color="indigo" />
        </v-card>

        <!-- Token missing -->
        <v-alert v-else-if="!token" type="error" variant="tonal">
          Invitation token is missing.
        </v-alert>

        <!-- Invitation validation error -->
        <v-alert v-else-if="invitationStore.errorMessage" type="error" variant="tonal">
          {{ invitationStore.errorMessage }}
        </v-alert>

        <!-- Valid invitation -->
        <v-card v-else-if="invitationStore.invitation" class="pa-6 rounded-xl" elevation="2">
          <!-- Page title -->
          <h1 class="text-h4 font-weight-bold mb-2">
            {{ invitationTitle }}
          </h1>

          <!-- Company information -->
          <p class="text-body-2 text-grey-darken-1 mb-2">
            You have been invited to join

            <strong>
              {{ invitationStore.invitation.companyName }}
            </strong>
          </p>

          <!-- Role information -->
          <p class="text-body-2 text-grey-darken-1 mb-6">
            Role:
            <strong>
              {{ formattedRole }}
            </strong>
          </p>

          <!-- Register Form -->
          <v-form ref="formRef" @submit.prevent="handleRegister">
            <!-- Full Name -->
            <v-text-field
              v-model="form.fullName"
              label="Full Name"
              variant="outlined"
              :rules="[requiredRule]"
              class="mb-2"
            />

            <!-- Email -->
            <v-text-field
              v-model="form.email"
              label="Email"
              type="email"
              variant="outlined"
              :rules="[requiredRule, emailRule]"
              class="mb-2"
            />

            <!-- Job Title -->
            <v-text-field
              v-model="form.jobTitle"
              label="Job Title"
              variant="outlined"
              :rules="[requiredRule]"
              class="mb-2"
            />

            <!-- Phone -->
            <v-text-field v-model="form.phone" label="Phone" variant="outlined" class="mb-2" />

            <!-- Department -->
            <v-select
              v-model="form.departmentId"
              :items="departmentStore.departments"
              item-title="name"
              item-value="id"
              label="Department"
              variant="outlined"
              :rules="[departmentRule]"
              :loading="departmentStore.loading"
              class="mb-2"
            />

            <!-- Password -->
            <v-text-field
              v-model="form.password"
              label="Password"
              type="password"
              variant="outlined"
              :rules="[passwordRule]"
              class="mb-2"
            />

            <!-- Confirm Password -->
            <v-text-field
              v-model="form.confirmPassword"
              label="Confirm Password"
              type="password"
              variant="outlined"
              :rules="[confirmPasswordRule]"
              class="mb-4"
            />

            <!-- Submit -->
            <v-btn
              type="submit"
              color="indigo"
              size="large"
              block
              class="text-none"
              :loading="invitationStore.loading"
            >
              Create Account
            </v-btn>
          </v-form>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
