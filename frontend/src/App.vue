<script setup>
import { useAuthStore } from '@/stores/authStore'
import { onMounted } from 'vue'

import PublicNavbar from '@/components/layout/PublicNavbar.vue'
import PublicFooter from '@/components/layout/PublicFooter.vue'
import DashboardNavbar from '@/components/layout/DashboardNavbar.vue'
import DashboardFooter from '@/components/layout/DashboardFooter.vue'

const authStore = useAuthStore()
onMounted(() => {
  authStore.checkAuth()
})
</script>

<template>
  <v-app>
    <v-main>
      <PublicNavbar v-if="!authStore.isAuthenticated" />
      <DashboardNavbar v-else />

      <RouterView />

      <PublicFooter />
    </v-main>
  </v-app>
</template>
