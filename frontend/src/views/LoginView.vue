<script setup>
import { reactive, ref } from 'vue'
import { useAuthStore } from '@/stores/authStore'
import { useRouter } from 'vue-router'

const formRef = ref(null)
const authStore = useAuthStore()
const router = useRouter()

const form = reactive({
  email: '',
  password: '',
  rememberMe: false,
})

const requiredRule = (value) => {
  return Boolean(value?.trim()) || 'This field is required.'
}

const loginUser = async () => {
  const validationResult = await formRef.value.validate()

  if (!validationResult.valid) {
    return
  }

  const loginData = {
    email: form.email.trim().toLowerCase(),
    password: form.password,
  }

  const success = await authStore.loginUser(loginData)

  if (success) {
    formRef.value.reset()
    router.push({ name: 'dashboard' })
  }
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
          <v-chip color="indigo" variant="tonal" prepend-icon="mdi-login" class="mb-5">
            Welcome back
          </v-chip>

          <h1 class="login-title text-indigo-darken-3 mb-5">Manage your support workspace</h1>

          <p class="text-body-1 text-blue-grey-darken-1 mb-7">
            Sign in to manage tickets, collaborate with your team and keep customer requests
            organised.
          </p>

          <v-list bg-color="transparent" class="pa-0">
            <v-list-item class="px-0 mb-2">
              <template #prepend>
                <v-avatar color="blue-lighten-5" size="42" class="me-3">
                  <v-icon color="blue"> mdi-ticket-confirmation-outline </v-icon>
                </v-avatar>
              </template>

              <v-list-item-title class="font-weight-medium"> Manage tickets </v-list-item-title>

              <v-list-item-subtitle> Create, assign and update requests </v-list-item-subtitle>
            </v-list-item>

            <v-list-item class="px-0 mb-2">
              <template #prepend>
                <v-avatar color="green-lighten-5" size="42" class="me-3">
                  <v-icon color="green"> mdi-chart-timeline-variant </v-icon>
                </v-avatar>
              </template>

              <v-list-item-title class="font-weight-medium"> Track progress </v-list-item-title>

              <v-list-item-subtitle> Monitor status, priority and SLA times </v-list-item-subtitle>
            </v-list-item>

            <v-list-item class="px-0 mb-2">
              <template #prepend>
                <v-avatar color="purple-lighten-5" size="42" class="me-3">
                  <v-icon color="purple"> mdi-account-group-outline </v-icon>
                </v-avatar>
              </template>

              <v-list-item-title class="font-weight-medium">
                Work with your team
              </v-list-item-title>

              <v-list-item-subtitle> Assign tickets and share internal notes </v-list-item-subtitle>
            </v-list-item>

            <v-list-item class="px-0">
              <template #prepend>
                <v-avatar color="orange-lighten-5" size="42" class="me-3">
                  <v-icon color="orange"> mdi-shield-check-outline </v-icon>
                </v-avatar>
              </template>

              <v-list-item-title class="font-weight-medium"> Secure access </v-list-item-title>

              <v-list-item-subtitle> Your company workspace remains private </v-list-item-subtitle>
            </v-list-item>
          </v-list>
        </v-card>
      </v-col>

      <!-- Login card -->
      <v-col cols="12" md="7" lg="5">
        <v-card color="white" elevation="2" class="pa-5 pa-sm-7 rounded-xl">
          <div class="text-center mb-7">
            <v-avatar color="indigo" size="58" rounded="lg" class="mb-4">
              <v-icon color="white" size="32"> mdi-shield-check </v-icon>
            </v-avatar>

            <v-card-title class="pa-0 text-h4 font-weight-bold text-indigo-darken-4 mb-2">
              Sign in to your account
            </v-card-title>

            <v-card-subtitle class="pa-0 text-body-2 text-blue-grey-darken-1">
              Enter your details to access your company workspace.
            </v-card-subtitle>
          </div>

          <v-form ref="formRef" @submit.prevent="loginUser">
            <v-text-field
              v-model="form.email"
              label="Email address"
              prepend-inner-icon="mdi-email-outline"
              variant="outlined"
              color="indigo"
              type="email"
              autocomplete="email"
              class="mb-2"
              :rules="[requiredRule]"
            />

            <v-text-field
              v-model="form.password"
              label="Password"
              prepend-inner-icon="mdi-lock-outline"
              append-inner-icon="mdi-eye-outline"
              variant="outlined"
              color="indigo"
              type="password"
              autocomplete="current-password"
              class="mb-1"
              :rules="[requiredRule]"
            />

            <div class="d-flex flex-column flex-sm-row align-sm-center justify-space-between mb-5">
              <v-checkbox
                label="Remember me"
                color="indigo"
                density="compact"
                hide-details
                v-model="form.rememberMe"
              />

              <v-btn variant="text" color="indigo" size="small" class="text-none px-0">
                Forgot password?
              </v-btn>
            </div>

            <v-btn
              color="indigo"
              size="large"
              block
              class="text-none mb-5"
              append-icon="mdi-arrow-right"
              type="submit"
            >
              Sign In
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
            Do not have an account?

            <v-btn
              :to="{ name: 'register' }"
              variant="text"
              color="indigo"
              size="small"
              class="text-none font-weight-bold px-1"
            >
              Create account
            </v-btn>
          </p>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.login-title {
  font-size: clamp(2.4rem, 4.5vw, 4.5rem);
  font-weight: 800;
  line-height: 1.08;
  letter-spacing: -2px;
}
</style>
