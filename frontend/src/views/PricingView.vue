<script setup>
const plans = [
  {
    name: 'Starter',
    description: 'For small teams getting started with customer support.',
    price: '£0',
    period: '/month',
    buttonText: 'Get Started',
    popular: false,
    features: [
      'Up to 3 team members',
      '100 tickets per month',
      'Basic ticket management',
      'Email notifications',
      'Knowledge base access',
    ],
  },
  {
    name: 'Professional',
    description: 'For growing teams that need better collaboration tools.',
    price: '£29',
    period: '/month',
    buttonText: 'Start Free Trial',
    popular: true,
    features: [
      'Up to 15 team members',
      'Unlimited tickets',
      'Ticket assignment',
      'Priority and status management',
      'Internal comments',
      'File attachments',
      'SLA tracking',
    ],
  },
  {
    name: 'Business',
    description: 'For larger organisations with advanced support needs.',
    price: '£79',
    period: '/month',
    buttonText: 'Choose Business',
    popular: false,
    features: [
      'Unlimited team members',
      'Unlimited tickets',
      'Advanced reporting',
      'Custom roles and permissions',
      'Audit logs',
      'Priority support',
      'Advanced SLA rules',
    ],
  },
]

const comparisonFeatures = [
  {
    name: 'Team members',
    starter: '3',
    professional: '15',
    business: 'Unlimited',
  },
  {
    name: 'Monthly tickets',
    starter: '100',
    professional: 'Unlimited',
    business: 'Unlimited',
  },
  {
    name: 'File attachments',
    starter: false,
    professional: true,
    business: true,
  },
  {
    name: 'Ticket assignment',
    starter: false,
    professional: true,
    business: true,
  },
  {
    name: 'SLA tracking',
    starter: false,
    professional: true,
    business: true,
  },
  {
    name: 'Advanced reports',
    starter: false,
    professional: false,
    business: true,
  },
  {
    name: 'Priority support',
    starter: false,
    professional: false,
    business: true,
  },
]

const faqs = [
  {
    question: 'Can I change my plan later?',
    answer: 'Yes. You can upgrade or downgrade your plan at any time from your company settings.',
  },
  {
    question: 'Is there a free trial?',
    answer:
      'The Professional and Business plans include a 14-day free trial. No payment is required to start.',
  },
  {
    question: 'Can multiple companies use the system?',
    answer: 'Yes. Each company has its own separate workspace, users, tickets and settings.',
  },
  {
    question: 'Are payments charged per user?',
    answer:
      'The prices shown are for the complete company workspace and include the stated number of team members.',
  },
]
</script>

<template>
  <v-container
    fluid
    class="pa-4 pa-md-6"
    style="background: linear-gradient(135deg, #fcfcfd 0%, #f4f8fd 50%, #fcfcfd 100%)"
  >
    <!-- Header -->
    <v-card color="transparent" elevation="0" class="text-center py-8 py-md-12 mb-6">
      <v-chip color="indigo" variant="tonal" prepend-icon="mdi-tag-outline" class="mb-4">
        Simple pricing
      </v-chip>

      <h1 class="pricing-title text-indigo-darken-3 mb-4">Choose the right plan for your team</h1>

      <p class="text-body-1 text-blue-grey-darken-1 mx-auto mb-0" style="max-width: 680px">
        Start for free and upgrade as your support team grows. All plans include secure company
        workspaces and essential ticket management.
      </p>
    </v-card>

    <!-- Pricing cards -->
    <v-row align="stretch" class="mb-10">
      <v-col v-for="plan in plans" :key="plan.name" cols="12" md="4">
        <v-card
          color="white"
          :elevation="plan.popular ? 5 : 1"
          class="pa-5 pa-md-7 rounded-xl h-100 d-flex flex-column"
          :class="{ 'border-indigo': plan.popular }"
        >
          <div class="d-flex justify-space-between align-start mb-4">
            <div>
              <v-card-title class="pa-0 text-h5 font-weight-bold text-indigo-darken-4">
                {{ plan.name }}
              </v-card-title>

              <v-card-subtitle class="pa-0 mt-2 text-body-2 text-blue-grey-darken-1">
                {{ plan.description }}
              </v-card-subtitle>
            </div>

            <v-chip v-if="plan.popular" color="indigo" size="small"> Most Popular </v-chip>
          </div>

          <div class="d-flex align-end mb-6">
            <span class="text-h2 font-weight-bold text-indigo-darken-3">
              {{ plan.price }}
            </span>

            <span class="text-body-2 text-blue-grey-darken-1 mb-2 ms-2">
              {{ plan.period }}
            </span>
          </div>

          <v-divider class="mb-5" />

          <v-list density="compact" bg-color="transparent" class="pa-0 mb-6 flex-grow-1">
            <v-list-item v-for="feature in plan.features" :key="feature" class="px-0">
              <template #prepend>
                <v-icon color="green" size="20" class="me-3"> mdi-check-circle-outline </v-icon>
              </template>

              <v-list-item-title class="text-body-2">
                {{ feature }}
              </v-list-item-title>
            </v-list-item>
          </v-list>

          <v-btn
            :color="plan.popular ? 'indigo' : undefined"
            :variant="plan.popular ? 'flat' : 'outlined'"
            size="large"
            block
            class="text-none"
            append-icon="mdi-arrow-right"
          >
            {{ plan.buttonText }}
          </v-btn>
        </v-card>
      </v-col>
    </v-row>

    <!-- Trust message -->
    <v-card color="indigo-lighten-5" elevation="0" class="rounded-xl pa-5 mb-10">
      <v-row align="center">
        <v-col cols="12" md="4">
          <div class="d-flex align-center ga-3">
            <v-avatar color="white" size="52">
              <v-icon color="indigo"> mdi-credit-card-off-outline </v-icon>
            </v-avatar>

            <div>
              <p class="font-weight-bold mb-0">No card required</p>
              <p class="text-body-2 text-blue-grey-darken-1 mb-0">
                Start your trial without payment details.
              </p>
            </div>
          </div>
        </v-col>

        <v-col cols="12" md="4">
          <div class="d-flex align-center ga-3">
            <v-avatar color="white" size="52">
              <v-icon color="indigo"> mdi-calendar-refresh-outline </v-icon>
            </v-avatar>

            <div>
              <p class="font-weight-bold mb-0">Cancel anytime</p>
              <p class="text-body-2 text-blue-grey-darken-1 mb-0">
                No long-term contract or hidden charges.
              </p>
            </div>
          </div>
        </v-col>

        <v-col cols="12" md="4">
          <div class="d-flex align-center ga-3">
            <v-avatar color="white" size="52">
              <v-icon color="indigo"> mdi-shield-check-outline </v-icon>
            </v-avatar>

            <div>
              <p class="font-weight-bold mb-0">Secure workspaces</p>
              <p class="text-body-2 text-blue-grey-darken-1 mb-0">
                Every company’s data is kept separate.
              </p>
            </div>
          </div>
        </v-col>
      </v-row>
    </v-card>

    <!-- Comparison table -->
    <v-card color="white" elevation="1" class="rounded-xl mb-10 overflow-hidden">
      <v-card-title
        class="d-flex justify-center text-h4 font-weight-bold text-indigo-darken-4 py-6"
      >
        Compare plans
      </v-card-title>

      <v-table>
        <thead>
          <tr>
            <th class="text-left">Feature</th>
            <th class="text-center">Starter</th>
            <th class="text-center text-indigo">Professional</th>
            <th class="text-center">Business</th>
          </tr>
        </thead>

        <tbody>
          <tr v-for="feature in comparisonFeatures" :key="feature.name">
            <td class="font-weight-medium">
              {{ feature.name }}
            </td>

            <td class="text-center">
              <v-icon
                v-if="typeof feature.starter === 'boolean'"
                :color="feature.starter ? 'green' : 'grey-lighten-1'"
              >
                {{ feature.starter ? 'mdi-check-circle-outline' : 'mdi-close-circle-outline' }}
              </v-icon>

              <span v-else>{{ feature.starter }}</span>
            </td>

            <td class="text-center">
              <v-icon
                v-if="typeof feature.professional === 'boolean'"
                :color="feature.professional ? 'green' : 'grey-lighten-1'"
              >
                {{ feature.professional ? 'mdi-check-circle-outline' : 'mdi-close-circle-outline' }}
              </v-icon>

              <span v-else>{{ feature.professional }}</span>
            </td>

            <td class="text-center">
              <v-icon
                v-if="typeof feature.business === 'boolean'"
                :color="feature.business ? 'green' : 'grey-lighten-1'"
              >
                {{ feature.business ? 'mdi-check-circle-outline' : 'mdi-close-circle-outline' }}
              </v-icon>

              <span v-else>{{ feature.business }}</span>
            </td>
          </tr>
        </tbody>
      </v-table>
    </v-card>

    <!-- FAQ -->
    <v-card color="transparent" elevation="0" class="mb-8">
      <v-card-title
        class="d-flex justify-center text-h4 font-weight-bold text-indigo-darken-4 mb-5"
      >
        Frequently asked questions
      </v-card-title>

      <v-expansion-panels variant="accordion">
        <v-expansion-panel
          v-for="faq in faqs"
          :key="faq.question"
          elevation="1"
          class="mb-3 rounded-lg bg-white"
        >
          <v-expansion-panel-title class="font-weight-bold">
            {{ faq.question }}
          </v-expansion-panel-title>

          <v-expansion-panel-text class="text-blue-grey-darken-1">
            {{ faq.answer }}
          </v-expansion-panel-text>
        </v-expansion-panel>
      </v-expansion-panels>
    </v-card>

    <!-- CTA -->
    <v-card color="indigo" class="rounded-xl pa-6 pa-md-10 text-center">
      <v-icon color="white" size="48" class="mb-3"> mdi-headset </v-icon>

      <h2 class="text-h4 font-weight-bold text-white mb-3">
        Ready to improve your support workflow?
      </h2>

      <p class="text-body-1 text-indigo-lighten-4 mb-6">
        Create your company workspace and start managing tickets today.
      </p>

      <v-btn
        color="white"
        size="large"
        class="text-none text-indigo px-8"
        append-icon="mdi-arrow-right"
      >
        Start for Free
      </v-btn>
    </v-card>
  </v-container>
</template>

<style scoped>
.pricing-title {
  font-size: clamp(2.3rem, 4.5vw, 4.5rem);
  font-weight: 800;
  line-height: 1.08;
  letter-spacing: -2px;
}

.border-indigo {
  border: 2px solid #3f51b5;
}

@media (max-width: 600px) {
  .pricing-title {
    letter-spacing: -1px;
  }
}
</style>
