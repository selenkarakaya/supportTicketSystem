<script setup>
import supportDashboard from '@/assets/support-dashboard.png'

const highlights = [
  {
    title: 'Fast ticket resolution',
    description: 'Streamlined workflows help your team solve issues quickly.',
    icon: 'mdi-lightning-bolt-outline',
    color: 'blue',
    background: 'blue-lighten-5',
  },
  {
    title: 'Knowledge base access',
    description: 'Empower users with self-service articles and guides.',
    icon: 'mdi-book-open-page-variant-outline',
    color: 'green',
    background: 'green-lighten-5',
  },
  {
    title: 'Email notifications',
    description: 'Stay informed with automatic updates and alerts.',
    icon: 'mdi-email-outline',
    color: 'purple',
    background: 'purple-lighten-5',
  },
  {
    title: 'SLA tracking',
    description: 'Monitor response times and meet service goals.',
    icon: 'mdi-chart-bar',
    color: 'orange',
    background: 'orange-lighten-5',
  },
]

const features = [
  {
    title: 'Create Ticket',
    description: 'Submit tickets easily with a simple, intuitive form.',
    icon: 'mdi-ticket-confirmation-outline',
    color: 'blue',
    background: 'blue-lighten-5',
  },
  {
    title: 'Track Status',
    description: 'Check real-time status and receive updates.',
    icon: 'mdi-magnify',
    color: 'green',
    background: 'green-lighten-5',
  },
  {
    title: 'Team Collaboration',
    description: 'Work together with notes and assignment tools.',
    icon: 'mdi-account-group-outline',
    color: 'purple',
    background: 'purple-lighten-5',
  },
  {
    title: 'Knowledge Base',
    description: 'Browse helpful articles and find answers quickly.',
    icon: 'mdi-book-open-page-variant-outline',
    color: 'orange',
    background: 'orange-lighten-5',
  },
]

const steps = [
  {
    number: 1,
    title: 'Submit a ticket',
    description: 'Tell us what you need help with using our simple form.',
  },
  {
    number: 2,
    title: 'We work on it',
    description: 'Our support team reviews and resolves your request.',
  },
  {
    number: 3,
    title: 'Get notified',
    description: 'Receive updates until your ticket is resolved.',
  },
]

const statistics = [
  {
    value: '120K+',
    label: 'Tickets Resolved',
    icon: 'mdi-check-circle-outline',
    color: 'green',
    background: 'green-lighten-5',
  },
  {
    value: '8K+',
    label: 'Happy Teams',
    icon: 'mdi-account-group-outline',
    color: 'purple',
    background: 'purple-lighten-5',
  },
  {
    value: '99.9%',
    label: 'Uptime Guarantee',
    icon: 'mdi-shield-check-outline',
    color: 'blue',
    background: 'blue-lighten-5',
  },
]

const testimonials = [
  {
    comment:
      'TicketSystem has transformed the way we handle support. It is fast, reliable, and incredibly easy to use.',
    name: 'Sarah Johnson',
    role: 'Support Manager, TechCorp',
  },
  {
    comment:
      'Our response times improved significantly after switching to TicketSystem. The dashboard is simple and clear.',
    name: 'Michael Brown',
    role: 'Operations Lead, Bright Solutions',
  },
  {
    comment:
      'The ticket tracking and notification features help our team stay organised and keep customers informed.',
    name: 'Emily Wilson',
    role: 'Customer Success Manager, Nova Digital',
  },
  {
    comment:
      'TicketSystem makes collaboration between our support agents much easier. We can resolve issues faster.',
    name: 'Daniel Smith',
    role: 'IT Support Lead, CloudWorks',
  },
]
</script>

<template>
  <v-container
    fluid
    class="pa-4 pa-md-6"
    style="background: linear-gradient(135deg, #fcfcfd 0%, #f4f8fd 50%, #fcfcfd 100%)"
  >
    <!-- Hero Section -->
    <v-card color="transparent" elevation="0" class="hero-card rounded-xl overflow-hidden mb-8">
      <v-row no-gutters align="center">
        <v-col cols="12" md="5">
          <div class="pa-6 pa-md-8">
            <h1 class="hero-title text-indigo-darken-3 mb-5">
              Manage support<br />
              tickets with ease
            </h1>

            <v-card-text class="pa-0 text-grey-darken-1 mb-6">
              <p class="mb-1">TicketSystem helps teams deliver exceptional support.</p>

              <p class="mb-0">Create, track, and resolve tickets faster—all in one place.</p>
            </v-card-text>

            <v-card-actions class="pa-0 d-flex flex-column flex-sm-row ga-4 mb-6">
              <v-btn
                color="indigo"
                size="large"
                append-icon="mdi-arrow-right"
                class="text-none px-6"
              >
                Get Started
              </v-btn>

              <v-btn variant="outlined" size="large" class="text-none px-6">
                Learn More

                <v-icon color="indigo" class="ms-2"> mdi-chevron-right </v-icon>
              </v-btn>
            </v-card-actions>

            <div class="d-flex align-center ga-2">
              <v-icon color="indigo" size="small"> mdi-shield-check-outline </v-icon>

              <span class="text-body-2 text-blue-grey-darken-1">
                Secure. Reliable. Built for teams of all sizes.
              </span>
            </div>
          </div>
        </v-col>

        <v-col cols="12" md="7">
          <v-img
            :src="supportDashboard"
            alt="Support ticket dashboard"
            contain
            class="hero-image"
          />
        </v-col>
      </v-row>
    </v-card>

    <!-- Highlights -->
    <v-card class="rounded-xl mb-8 bg-white">
      <v-row no-gutters align="stretch">
        <v-col v-for="(item, index) in highlights" :key="item.title" cols="12" sm="6" md="3">
          <div
            class="highlight-item d-flex align-center ga-4 px-5 py-4 h-100"
            :class="{ 'highlight-border': index < highlights.length - 1 }"
          >
            <v-avatar :color="item.background" size="64" class="flex-shrink-0">
              <v-icon :color="item.color" size="34">
                {{ item.icon }}
              </v-icon>
            </v-avatar>

            <div>
              <h3 class="text-subtitle-2 font-weight-bold text-indigo-darken-4 mb-1">
                {{ item.title }}
              </h3>

              <p class="text-body-2 text-blue-grey-darken-1 mb-0">
                {{ item.description }}
              </p>
            </div>
          </div>
        </v-col>
      </v-row>
    </v-card>

    <!-- Features -->
    <v-card id="features" color="transparent" elevation="0" class="mb-8">
      <v-card-title
        class="d-flex justify-center text-h4 font-weight-bold text-indigo-darken-4 mb-5"
      >
        Powerful features for better support
      </v-card-title>

      <v-row class="mb-4 mx-2">
        <v-col v-for="feature in features" :key="feature.title" cols="12" sm="6" md="3">
          <v-card elevation="1" class="pa-5 rounded-xl h-100 bg-white">
            <div class="d-flex align-center ga-4">
              <v-avatar :color="feature.background" size="64">
                <v-icon :color="feature.color" size="34">
                  {{ feature.icon }}
                </v-icon>
              </v-avatar>

              <div>
                <v-card-title class="pa-0 text-subtitle-1 font-weight-bold">
                  {{ feature.title }}
                </v-card-title>

                <v-card-text class="pa-0 text-body-2 text-blue-grey-darken-1">
                  {{ feature.description }}
                </v-card-text>
              </div>
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-card>

    <!-- How It Works -->
    <v-card color="transparent" elevation="0" class="mb-8">
      <v-card-title
        class="d-flex justify-center text-h4 font-weight-bold text-indigo-darken-4 mb-5"
      >
        How it works
      </v-card-title>

      <v-row class="mb-4 mx-2" align="center">
        <template v-for="(step, index) in steps" :key="step.number">
          <!-- Step card -->
          <v-col cols="12" md="3">
            <v-card elevation="1" class="pa-5 rounded-xl h-100 bg-white">
              <div class="d-flex align-center ga-4">
                <v-avatar color="indigo" size="50">
                  <span class="text-h6 font-weight-bold text-white">
                    {{ step.number }}
                  </span>
                </v-avatar>

                <div>
                  <v-card-title class="pa-0 text-subtitle-1 font-weight-bold">
                    {{ step.title }}
                  </v-card-title>

                  <v-card-text class="pa-0 text-body-2 text-blue-grey-darken-1">
                    {{ step.description }}
                  </v-card-text>
                </div>
              </div>
            </v-card>
          </v-col>

          <!-- Dotted arrow between cards -->
          <v-col
            v-if="index < steps.length - 1"
            cols="12"
            md="1"
            class="d-none d-md-flex align-center px-1"
          >
            <v-divider
              color="indigo-lighten-3"
              opacity="1"
              class="flex-grow-1"
              style="border-style: dashed"
            />

            <v-icon color="indigo-lighten-2" size="22"> mdi-chevron-right </v-icon>
          </v-col>
        </template>
      </v-row>
    </v-card>

    <!-- Statistics and Testimonial -->
    <v-card color="indigo-lighten-5" variant="outlined" class="pa-4 pa-md-5 rounded-xl">
      <v-row align="stretch">
        <v-col cols="12" md="7">
          <v-row>
            <v-col v-for="stat in statistics" :key="stat.label" cols="12" sm="4">
              <v-card color="white" elevation="0" class="pa-4 rounded-lg h-100">
                <div class="d-flex align-center ga-4">
                  <v-avatar :color="stat.background" size="54">
                    <v-icon :color="stat.color">
                      {{ stat.icon }}
                    </v-icon>
                  </v-avatar>

                  <div>
                    <v-card-title class="pa-0 text-h5 font-weight-bold text-indigo">
                      {{ stat.value }}
                    </v-card-title>

                    <v-card-text class="pa-0 text-body-2 font-weight-medium">
                      {{ stat.label }}
                    </v-card-text>
                  </div>
                </div>
              </v-card>
            </v-col>
          </v-row>

          <v-card-text class="text-center text-blue-grey-darken-1 pb-0">
            Trusted by teams worldwide to deliver outstanding support.
          </v-card-text>
        </v-col>

        <v-col cols="12" md="5">
          <v-carousel
            height="100%"
            min-height="260"
            hide-delimiter-background
            show-arrows="hover"
            cycle
            interval="5000"
            hide-delimiters
            class="rounded-lg"
          >
            <v-carousel-item v-for="testimonial in testimonials" :key="testimonial.name">
              <v-card color="white" elevation="0" class="pa-5 rounded-lg h-100">
                <div class="d-flex align-start ga-3">
                  <v-icon color="indigo" size="38"> mdi-format-quote-open </v-icon>

                  <div class="d-flex flex-column justify-space-between h-100">
                    <v-card-text class="pa-0 text-body-1 text-blue-grey-darken-2 mb-6">
                      {{ testimonial.comment }}
                    </v-card-text>

                    <div class="d-flex align-center ga-3">
                      <v-avatar color="indigo-lighten-4" size="44">
                        <v-icon color="indigo"> mdi-account </v-icon>
                      </v-avatar>

                      <div>
                        <v-card-title class="pa-0 text-subtitle-2 font-weight-bold">
                          {{ testimonial.name }}
                        </v-card-title>

                        <v-card-subtitle class="pa-0">
                          {{ testimonial.role }}
                        </v-card-subtitle>
                      </div>
                    </div>
                  </div>
                </div>
              </v-card>
            </v-carousel-item>
          </v-carousel>
        </v-col>
      </v-row>
    </v-card>
  </v-container>
</template>

<style scoped>
.hero-title {
  font-size: clamp(2.3rem, 4.5vw, 4.8rem);
  font-weight: 800;
  line-height: 1.05;
  letter-spacing: -2px;
}

.highlight-item {
  position: relative;
}

@media (min-width: 960px) {
  .highlight-border::after {
    content: '';
    position: absolute;
    top: 20%;
    right: 0;
    width: 1px;
    height: 60%;
    background-color: #dfe4ec;
  }
}

@media (max-width: 600px) {
  .hero-title {
    letter-spacing: -1px;
  }
}
</style>
