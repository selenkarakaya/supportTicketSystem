<script setup>
import { ref } from 'vue'

const form = ref(null)
const loading = ref(false)
const snackbar = ref(false)

const contactForm = ref({
  name: '',
  email: '',
  company: '',
  subject: '',
  message: '',
})

const subjects = [
  'General question',
  'Technical support',
  'Pricing and plans',
  'Account assistance',
  'Partnership enquiry',
  'Other',
]

const requiredRule = (value) => {
  return Boolean(value?.trim()) || 'This field is required.'
}

const emailRule = (value) => {
  const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/

  return pattern.test(value) || 'Enter a valid email address.'
}

const submitForm = async () => {
  const result = await form.value?.validate()

  if (!result?.valid) {
    return
  }

  loading.value = true

  // Backend eklendiğinde burada API isteği gönderilecek.
  await new Promise((resolve) => setTimeout(resolve, 800))

  loading.value = false
  snackbar.value = true

  contactForm.value = {
    name: '',
    email: '',
    company: '',
    subject: '',
    message: '',
  }

  form.value?.resetValidation()
}
</script>

<template>
  <v-container
    fluid
    class="pa-4 pa-md-6"
    style="background: linear-gradient(135deg, #fcfcfd 0%, #f4f8fd 50%, #fcfcfd 100%)"
  >
    <!-- Hero -->
    <v-card color="transparent" elevation="0" class="text-center py-8 py-md-12 mb-6">
      <v-chip color="indigo" variant="tonal" prepend-icon="mdi-message-text-outline" class="mb-4">
        Contact Us
      </v-chip>

      <h1 class="contact-title text-indigo-darken-3 mb-4">We would love to hear from you</h1>

      <p class="text-body-1 text-blue-grey-darken-1 mx-auto mb-0" style="max-width: 680px">
        Have a question about TicketSystem? Send us a message and our team will get back to you as
        soon as possible.
      </p>
    </v-card>

    <!-- Contact information -->
    <v-row class="mb-8">
      <v-col cols="12" sm="6" md="3">
        <v-card color="white" elevation="1" class="pa-5 rounded-xl h-100 text-center">
          <v-avatar color="blue-lighten-5" size="60" class="mb-4">
            <v-icon color="blue" size="30"> mdi-email-outline </v-icon>
          </v-avatar>

          <v-card-title class="pa-0 text-subtitle-1 font-weight-bold mb-2"> Email us </v-card-title>

          <v-card-text class="pa-0 text-body-2 text-blue-grey-darken-1 mb-3">
            Send us your questions anytime.
          </v-card-text>

          <a
            href="mailto:support@ticketsystem.com"
            class="text-indigo text-decoration-none text-body-2 font-weight-medium"
          >
            support@ticketsystem.com
          </a>
        </v-card>
      </v-col>

      <v-col cols="12" sm="6" md="3">
        <v-card color="white" elevation="1" class="pa-5 rounded-xl h-100 text-center">
          <v-avatar color="green-lighten-5" size="60" class="mb-4">
            <v-icon color="green" size="30"> mdi-phone-outline </v-icon>
          </v-avatar>

          <v-card-title class="pa-0 text-subtitle-1 font-weight-bold mb-2"> Call us </v-card-title>

          <v-card-text class="pa-0 text-body-2 text-blue-grey-darken-1 mb-3">
            Monday to Friday, 9:00–17:00.
          </v-card-text>

          <a
            href="tel:+441234567890"
            class="text-indigo text-decoration-none text-body-2 font-weight-medium"
          >
            +44 1234 567890
          </a>
        </v-card>
      </v-col>

      <v-col cols="12" sm="6" md="3">
        <v-card color="white" elevation="1" class="pa-5 rounded-xl h-100 text-center">
          <v-avatar color="purple-lighten-5" size="60" class="mb-4">
            <v-icon color="purple" size="30"> mdi-map-marker-outline </v-icon>
          </v-avatar>

          <v-card-title class="pa-0 text-subtitle-1 font-weight-bold mb-2"> Visit us </v-card-title>

          <v-card-text class="pa-0 text-body-2 text-blue-grey-darken-1 mb-3">
            Our team is based in the UK.
          </v-card-text>

          <span class="text-indigo text-body-2 font-weight-medium">
            Southampton, United Kingdom
          </span>
        </v-card>
      </v-col>

      <v-col cols="12" sm="6" md="3">
        <v-card color="white" elevation="1" class="pa-5 rounded-xl h-100 text-center">
          <v-avatar color="orange-lighten-5" size="60" class="mb-4">
            <v-icon color="orange" size="30"> mdi-lifebuoy </v-icon>
          </v-avatar>

          <v-card-title class="pa-0 text-subtitle-1 font-weight-bold mb-2">
            Help Center
          </v-card-title>

          <v-card-text class="pa-0 text-body-2 text-blue-grey-darken-1 mb-3">
            Find quick answers and helpful guides.
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>

    <!-- Contact form -->
    <v-row align="stretch" class="mb-10">
      <v-col cols="12" md="7">
        <v-card color="white" elevation="1" class="pa-5 pa-md-7 rounded-xl h-100">
          <v-card-title class="pa-0 text-h5 font-weight-bold text-indigo-darken-4 mb-2">
            Send us a message
          </v-card-title>

          <v-card-text class="pa-0 text-body-2 text-blue-grey-darken-1 mb-6">
            Complete the form below and we will respond within one business day.
          </v-card-text>

          <v-form ref="form" @submit.prevent="submitForm">
            <v-row>
              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="contactForm.name"
                  label="Full name"
                  prepend-inner-icon="mdi-account-outline"
                  variant="outlined"
                  color="indigo"
                  :rules="[requiredRule]"
                  required
                />
              </v-col>

              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="contactForm.email"
                  label="Email address"
                  prepend-inner-icon="mdi-email-outline"
                  variant="outlined"
                  color="indigo"
                  type="email"
                  :rules="[requiredRule, emailRule]"
                  required
                />
              </v-col>

              <v-col cols="12" sm="6">
                <v-text-field
                  v-model="contactForm.company"
                  label="Company name"
                  prepend-inner-icon="mdi-domain"
                  variant="outlined"
                  color="indigo"
                />
              </v-col>

              <v-col cols="12" sm="6">
                <v-select
                  v-model="contactForm.subject"
                  :items="subjects"
                  label="Subject"
                  prepend-inner-icon="mdi-tag-outline"
                  variant="outlined"
                  color="indigo"
                  :rules="[requiredRule]"
                  required
                />
              </v-col>

              <v-col cols="12">
                <v-textarea
                  v-model="contactForm.message"
                  label="How can we help?"
                  prepend-inner-icon="mdi-message-text-outline"
                  variant="outlined"
                  color="indigo"
                  rows="6"
                  counter="1000"
                  maxlength="1000"
                  :rules="[requiredRule]"
                  required
                />
              </v-col>

              <v-col cols="12">
                <div
                  class="d-flex flex-column flex-sm-row align-sm-center justify-space-between ga-4"
                >
                  <div class="d-flex align-center ga-2">
                    <v-icon color="indigo" size="small"> mdi-shield-check-outline </v-icon>

                    <span class="text-caption text-blue-grey-darken-1">
                      Your information will be kept private and secure.
                    </span>
                  </div>

                  <v-btn
                    type="submit"
                    color="indigo"
                    size="large"
                    class="text-none px-7"
                    append-icon="mdi-send-outline"
                    :loading="loading"
                  >
                    Send Message
                  </v-btn>
                </div>
              </v-col>
            </v-row>
          </v-form>
        </v-card>
      </v-col>

      <!-- Side information -->
      <v-col cols="12" md="5">
        <v-card color="indigo-lighten-5" elevation="0" class="pa-5 pa-md-7 rounded-xl h-100">
          <v-avatar color="white" size="64" class="mb-5">
            <v-icon color="indigo" size="34"> mdi-headset </v-icon>
          </v-avatar>

          <v-card-title class="pa-0 text-h5 font-weight-bold text-indigo-darken-4 mb-3">
            Need technical support?
          </v-card-title>

          <v-card-text class="pa-0 text-body-1 text-blue-grey-darken-1 mb-6">
            Existing customers can create a ticket to track their request and receive updates from
            the support team.
          </v-card-text>

          <v-divider class="mb-6" />

          <h3 class="text-subtitle-1 font-weight-bold mb-4">Response times</h3>

          <v-list bg-color="transparent" class="pa-0" density="compact">
            <v-list-item class="px-0">
              <template #prepend>
                <v-icon color="green" class="me-3"> mdi-check-circle-outline </v-icon>
              </template>

              <v-list-item-title> General enquiries </v-list-item-title>

              <v-list-item-subtitle> Within one business day </v-list-item-subtitle>
            </v-list-item>

            <v-list-item class="px-0">
              <template #prepend>
                <v-icon color="green" class="me-3"> mdi-check-circle-outline </v-icon>
              </template>

              <v-list-item-title> Technical support </v-list-item-title>

              <v-list-item-subtitle> Based on your SLA plan </v-list-item-subtitle>
            </v-list-item>

            <v-list-item class="px-0">
              <template #prepend>
                <v-icon color="green" class="me-3"> mdi-check-circle-outline </v-icon>
              </template>

              <v-list-item-title> Sales enquiries </v-list-item-title>

              <v-list-item-subtitle> Within 24 hours </v-list-item-subtitle>
            </v-list-item>
          </v-list>
        </v-card>
      </v-col>
    </v-row>

    <!-- Bottom CTA -->
    <v-card color="indigo" elevation="1" class="rounded-xl pa-6 pa-md-9">
      <v-row align="center">
        <v-col cols="12" md="8">
          <h2 class="text-h4 font-weight-bold text-white mb-2">
            Ready to simplify your support workflow?
          </h2>

          <p class="text-body-1 text-indigo-lighten-4 mb-0">
            Create your company workspace and start managing support tickets.
          </p>
        </v-col>

        <v-col cols="12" md="4" class="d-flex justify-md-end">
          <v-btn
            :to="{ name: 'pricing' }"
            color="white"
            size="large"
            class="text-none text-indigo px-7"
            append-icon="mdi-arrow-right"
          >
            View Pricing
          </v-btn>
        </v-col>
      </v-row>
    </v-card>

    <v-snackbar v-model="snackbar" color="success" location="top" timeout="4000">
      Your message has been sent successfully.

      <template #actions>
        <v-btn variant="text" @click="snackbar = false"> Close </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>

<style scoped>
.contact-title {
  font-size: clamp(2.3rem, 4.5vw, 4.5rem);
  font-weight: 800;
  line-height: 1.08;
  letter-spacing: -2px;
}
</style>
