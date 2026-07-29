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
          <v-chip
            color="indigo"
            variant="tonal"
            prepend-icon="mdi-account-plus-outline"
            class="mb-5"
          >
            Create your workspace
          </v-chip>

          <h1 class="register-title text-indigo-darken-3 mb-5">
            Start managing support tickets today
          </h1>

          <p class="text-body-1 text-blue-grey-darken-1 mb-7">
            Create your company workspace, invite your team and manage customer support requests in
            one place.
          </p>

          <v-list bg-color="transparent" class="pa-0">
            <v-list-item class="px-0 mb-2">
              <template #prepend>
                <v-avatar color="green-lighten-5" size="42" class="me-3">
                  <v-icon color="green">mdi-check</v-icon>
                </v-avatar>
              </template>

              <v-list-item-title class="font-weight-medium"> 14-day free trial </v-list-item-title>

              <v-list-item-subtitle> No payment details required </v-list-item-subtitle>
            </v-list-item>

            <v-list-item class="px-0 mb-2">
              <template #prepend>
                <v-avatar color="blue-lighten-5" size="42" class="me-3">
                  <v-icon color="blue">mdi-domain</v-icon>
                </v-avatar>
              </template>

              <v-list-item-title class="font-weight-medium">
                Private company workspace
              </v-list-item-title>

              <v-list-item-subtitle> Your company data remains separate </v-list-item-subtitle>
            </v-list-item>

            <v-list-item class="px-0 mb-2">
              <template #prepend>
                <v-avatar color="purple-lighten-5" size="42" class="me-3">
                  <v-icon color="purple">mdi-account-group-outline</v-icon>
                </v-avatar>
              </template>

              <v-list-item-title class="font-weight-medium">
                Invite your support team
              </v-list-item-title>

              <v-list-item-subtitle> Manage roles and permissions </v-list-item-subtitle>
            </v-list-item>

            <v-list-item class="px-0">
              <template #prepend>
                <v-avatar color="orange-lighten-5" size="42" class="me-3">
                  <v-icon color="orange">mdi-shield-check-outline</v-icon>
                </v-avatar>
              </template>

              <v-list-item-title class="font-weight-medium">
                Secure and reliable
              </v-list-item-title>

              <v-list-item-subtitle> Built for growing support teams </v-list-item-subtitle>
            </v-list-item>
          </v-list>
        </v-card>
      </v-col>

      <!-- Register card -->
      <v-col cols="12" md="7" lg="5">
        <v-card color="white" elevation="2" class="pa-5 pa-sm-7 rounded-xl">
          <div class="text-center mb-7">
            <v-avatar color="indigo" size="58" rounded="lg" class="mb-4">
              <v-icon color="white" size="32"> mdi-shield-check </v-icon>
            </v-avatar>

            <v-card-title class="pa-0 text-h4 font-weight-bold text-indigo-darken-4 mb-2">
              Create your account
            </v-card-title>

            <v-card-subtitle class="pa-0 text-body-2 text-blue-grey-darken-1">
              Set up your company workspace in a few simple steps.
            </v-card-subtitle>
          </div>

          <v-form>
            <v-text-field
              label="Full name"
              prepend-inner-icon="mdi-account-outline"
              variant="outlined"
              color="indigo"
              class="mb-2"
            />

            <v-text-field
              label="Company name"
              prepend-inner-icon="mdi-domain"
              variant="outlined"
              color="indigo"
              class="mb-2"
            />

            <v-text-field
              label="Work email address"
              prepend-inner-icon="mdi-email-outline"
              variant="outlined"
              color="indigo"
              type="email"
              class="mb-2"
            />

            <v-text-field
              label="Password"
              prepend-inner-icon="mdi-lock-outline"
              append-inner-icon="mdi-eye-outline"
              variant="outlined"
              color="indigo"
              type="password"
              hint="Use at least 8 characters."
              persistent-hint
              class="mb-2"
            />

            <v-text-field
              label="Confirm password"
              prepend-inner-icon="mdi-lock-check-outline"
              append-inner-icon="mdi-eye-outline"
              variant="outlined"
              color="indigo"
              type="password"
              class="mb-2"
            />

            <v-checkbox color="indigo" class="mb-4">
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
            >
              Create Account
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

<style scoped>
.register-title {
  font-size: clamp(2.4rem, 4.5vw, 4.5rem);
  font-weight: 800;
  line-height: 1.08;
  letter-spacing: -2px;
}
</style>
