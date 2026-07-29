<script setup>
import { computed, ref } from 'vue'

const search = ref('')

const categories = [
  {
    title: 'Getting Started',
    description: 'Learn how to create your account and set up your workspace.',
    icon: 'mdi-rocket-launch-outline',
    color: 'blue',
    background: 'blue-lighten-5',
    articleCount: 6,
  },
  {
    title: 'Ticket Management',
    description: 'Create, update, assign and manage support tickets.',
    icon: 'mdi-ticket-confirmation-outline',
    color: 'green',
    background: 'green-lighten-5',
    articleCount: 12,
  },
  {
    title: 'Account & Profile',
    description: 'Manage your personal information, password and preferences.',
    icon: 'mdi-account-circle-outline',
    color: 'purple',
    background: 'purple-lighten-5',
    articleCount: 8,
  },
  {
    title: 'Team Management',
    description: 'Invite employees and manage roles and permissions.',
    icon: 'mdi-account-group-outline',
    color: 'orange',
    background: 'orange-lighten-5',
    articleCount: 9,
  },
  {
    title: 'Notifications',
    description: 'Configure email notifications and ticket updates.',
    icon: 'mdi-bell-outline',
    color: 'red',
    background: 'red-lighten-5',
    articleCount: 5,
  },
  {
    title: 'SLA & Reporting',
    description: 'Understand SLA targets, reports and team performance.',
    icon: 'mdi-chart-box-outline',
    color: 'indigo',
    background: 'indigo-lighten-5',
    articleCount: 7,
  },
]

const articles = [
  {
    title: 'How to create your first support ticket',
    description: 'Follow these steps to submit a new support request.',
    category: 'Ticket Management',
    icon: 'mdi-ticket-outline',
    views: '2.4K',
  },
  {
    title: 'How to invite team members',
    description: 'Add employees to your company workspace securely.',
    category: 'Team Management',
    icon: 'mdi-account-plus-outline',
    views: '1.9K',
  },
  {
    title: 'Understanding ticket priorities',
    description: 'Learn when to use low, medium, high and urgent priority.',
    category: 'Ticket Management',
    icon: 'mdi-flag-outline',
    views: '1.6K',
  },
  {
    title: 'How email notifications work',
    description: 'See when users and support agents receive email updates.',
    category: 'Notifications',
    icon: 'mdi-email-outline',
    views: '1.3K',
  },
  {
    title: 'How to reset your password',
    description: 'Recover access to your account using your email address.',
    category: 'Account & Profile',
    icon: 'mdi-lock-reset',
    views: '980',
  },
]

const recentArticles = [
  {
    title: 'Setting up SLA response times',
    category: 'SLA & Reporting',
    date: 'July 28, 2026',
  },
  {
    title: 'Assigning tickets to support agents',
    category: 'Ticket Management',
    date: 'July 25, 2026',
  },
  {
    title: 'Managing company roles and permissions',
    category: 'Team Management',
    date: 'July 21, 2026',
  },
]

const filteredArticles = computed(() => {
  const query = search.value?.trim().toLowerCase() || ''

  if (!query) {
    return articles
  }

  return articles.filter((article) => {
    return (
      article.title.toLowerCase().includes(query) ||
      article.description.toLowerCase().includes(query) ||
      article.category.toLowerCase().includes(query)
    )
  })
})
</script>

<template>
  <v-container
    fluid
    class="pa-4 pa-md-6"
    style="background: linear-gradient(135deg, #fcfcfd 0%, #f4f8fd 50%, #fcfcfd 100%)"
  >
    <!-- Hero -->
    <v-card color="transparent" elevation="0" class="text-center py-8 py-md-12 mb-6">
      <v-chip
        color="indigo"
        variant="tonal"
        prepend-icon="mdi-book-open-page-variant-outline"
        class="mb-4"
      >
        Help Center
      </v-chip>

      <h1 class="knowledge-title text-indigo-darken-3 mb-4">How can we help you?</h1>

      <p class="text-body-1 text-blue-grey-darken-1 mb-7">
        Search our knowledge base or browse a category to find answers.
      </p>

      <v-text-field
        v-model="search"
        prepend-inner-icon="mdi-magnify"
        placeholder="Search articles, guides and answers..."
        variant="solo"
        bg-color="white"
        hide-details
        clearable
        rounded="lg"
        class="mx-auto"
        style="max-width: 720px"
      />
    </v-card>

    <!-- Categories -->
    <section class="mb-10">
      <h2 class="text-h4 text-center font-weight-bold text-indigo-darken-4 mb-6">
        Browse by category
      </h2>

      <v-row>
        <v-col v-for="category in categories" :key="category.title" cols="12" sm="6" md="4">
          <v-card elevation="1" class="pa-5 rounded-xl h-100 bg-white" hover>
            <div class="d-flex align-start ga-4">
              <v-avatar :color="category.background" size="60" class="flex-shrink-0">
                <v-icon :color="category.color" size="32">
                  {{ category.icon }}
                </v-icon>
              </v-avatar>

              <div>
                <v-card-title class="pa-0 text-subtitle-1 font-weight-bold mb-1">
                  {{ category.title }}
                </v-card-title>

                <v-card-text class="pa-0 text-body-2 text-blue-grey-darken-1 mb-3">
                  {{ category.description }}
                </v-card-text>

                <span class="text-caption text-indigo font-weight-medium">
                  {{ category.articleCount }} articles
                </span>
              </div>
            </div>
          </v-card>
        </v-col>
      </v-row>
    </section>

    <!-- Articles -->
    <v-row class="mb-10">
      <!-- Popular articles -->
      <v-col cols="12" md="8">
        <v-card color="white" elevation="1" class="rounded-xl h-100 overflow-hidden">
          <v-card-title
            class="d-flex align-center ga-2 text-h5 font-weight-bold text-indigo-darken-4 pa-5"
          >
            <v-icon color="indigo"> mdi-fire </v-icon>

            Popular articles
          </v-card-title>

          <v-divider />

          <v-list v-if="filteredArticles.length" bg-color="transparent" class="py-0">
            <template v-for="(article, index) in filteredArticles" :key="article.title">
              <v-list-item class="pa-5">
                <template #prepend>
                  <v-avatar color="indigo-lighten-5" size="46" class="me-4">
                    <v-icon color="indigo">
                      {{ article.icon }}
                    </v-icon>
                  </v-avatar>
                </template>

                <v-list-item-title class="font-weight-bold mb-1">
                  {{ article.title }}
                </v-list-item-title>

                <v-list-item-subtitle class="mb-2">
                  {{ article.description }}
                </v-list-item-subtitle>

                <div class="d-flex flex-wrap align-center ga-3">
                  <v-chip color="indigo" variant="tonal" size="x-small">
                    {{ article.category }}
                  </v-chip>

                  <span class="d-flex align-center ga-1 text-caption text-blue-grey-darken-1">
                    <v-icon size="small"> mdi-eye-outline </v-icon>

                    {{ article.views }}
                  </span>
                </div>

                <template #append>
                  <v-icon color="indigo-lighten-2"> mdi-chevron-right </v-icon>
                </template>
              </v-list-item>

              <v-divider v-if="index < filteredArticles.length - 1" />
            </template>
          </v-list>

          <div v-else class="text-center pa-10">
            <v-icon color="blue-grey-lighten-2" size="52" class="mb-3">
              mdi-file-search-outline
            </v-icon>

            <p class="font-weight-bold mb-1">No articles found</p>

            <p class="text-body-2 text-blue-grey-darken-1 mb-4">
              Try searching with a different keyword.
            </p>

            <v-btn color="indigo" variant="tonal" class="text-none" @click="search = ''">
              Clear Search
            </v-btn>
          </div>
        </v-card>
      </v-col>

      <!-- Recent articles -->
      <v-col cols="12" md="4">
        <v-card color="indigo-lighten-5" elevation="0" class="rounded-xl h-100 pa-5">
          <v-card-title class="pa-0 text-h5 font-weight-bold text-indigo-darken-4 mb-5">
            Recently added
          </v-card-title>

          <template v-for="(article, index) in recentArticles" :key="article.title">
            <div class="py-3">
              <p class="font-weight-bold mb-1">
                {{ article.title }}
              </p>

              <p class="text-caption text-indigo mb-1">
                {{ article.category }}
              </p>

              <p class="text-caption text-blue-grey-darken-1 mb-0">
                {{ article.date }}
              </p>
            </div>

            <v-divider v-if="index < recentArticles.length - 1" />
          </template>

          <v-btn
            variant="text"
            color="indigo"
            block
            class="text-none mt-4"
            append-icon="mdi-arrow-right"
          >
            View All Articles
          </v-btn>
        </v-card>
      </v-col>
    </v-row>

    <!-- Support CTA -->
    <v-card color="indigo" elevation="1" class="rounded-xl pa-6 pa-md-10">
      <v-row align="center">
        <v-col cols="12" md="8">
          <div class="d-flex align-center ga-4">
            <v-avatar color="white" size="64" class="d-none d-sm-flex">
              <v-icon color="indigo" size="34"> mdi-headset </v-icon>
            </v-avatar>

            <div>
              <h2 class="text-h4 font-weight-bold text-white mb-2">Still need help?</h2>

              <p class="text-body-1 text-indigo-lighten-4 mb-0">
                Create a support ticket and our team will help you resolve your issue.
              </p>
            </div>
          </div>
        </v-col>

        <v-col cols="12" md="4" class="d-flex justify-md-end">
          <v-btn
            color="white"
            size="large"
            class="text-none text-indigo px-7"
            append-icon="mdi-arrow-right"
            :to="{ name: 'tickets' }"
          >
            Create a Ticket
          </v-btn>
        </v-col>
      </v-row>
    </v-card>
  </v-container>
</template>

<style scoped>
.knowledge-title {
  font-size: clamp(2.3rem, 4.5vw, 4.5rem);
  font-weight: 800;
  line-height: 1.08;
  letter-spacing: -2px;
}
</style>
