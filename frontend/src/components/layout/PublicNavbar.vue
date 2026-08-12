<script setup></script>

<template>
  <v-app-bar color="white" elevation="0" class="border-b px-6" height="72">
    <div class="d-flex align-center ga-2 mb-3">
      <RouterLink :to="{ name: 'home' }" class="text-decoration-none">
        <div class="d-flex align-center ga-2">
          <v-avatar color="indigo" size="32" rounded="lg">
            <v-icon color="white" size="20"> mdi-shield-check </v-icon>
          </v-avatar>

          <span class="text-h6 font-weight-bold text-indigo-darken-4"> TicketSystem </span>
        </div>
      </RouterLink>
    </div>

    <v-spacer />

    <div class="d-none d-md-flex align-center ga-2">
      <v-btn variant="text" color="indigo" class="text-none font-weight-medium" to="/">
        Home
      </v-btn>

      <v-btn variant="text" class="text-none font-weight-medium" to="/knowledge-base">
        Knowledge Base
      </v-btn>

      <v-btn variant="text" class="text-none font-weight-medium" to="/pricing"> Pricing </v-btn>
      <v-btn variant="text" class="text-none font-weight-medium" to="/contact"> Contact </v-btn>
    </div>

    <v-spacer />

    <div class="d-none d-md-flex align-center ga-3">
      <v-btn variant="outlined" color="indigo" class="text-none px-6" to="/login"> Login </v-btn>

      <v-btn color="indigo" class="text-none px-6" to="/register"> Register </v-btn>
    </div>

    <v-btn icon="mdi-menu" variant="text" class="d-md-none" />
  </v-app-bar>
</template>
