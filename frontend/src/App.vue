<script setup>
import { ref } from 'vue'

import PublicNavbar from '@/components/layout/PublicNavbar.vue'
import PublicFooter from '@/components/layout/PublicFooter.vue'
import DashboardNavbar from '@/components/layout/DashboardNavbar.vue'
import DashboardFooter from '@/components/layout/DashboardFooter.vue'

const isLoggedIn = ref(false)

const login = () => {
  isLoggedIn.value = true
}

const logout = () => {
  isLoggedIn.value = false
}
</script>

<template>
  <v-app>
    <v-main>
      <PublicNavbar v-if="!isLoggedIn" @login="login" />

      <DashboardNavbar v-else @logout="logout" />
      <RouterView />

      <PublicFooter />
    </v-main>
  </v-app>
</template>
