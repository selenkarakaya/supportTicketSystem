<script setup>


</script>

<template>
  <v-app-bar color="white" elevation="0" border="b" height="72" class="px-6">
    <!-- Logo -->
    <router-link to="/dashboard" class="d-flex align-center text-decoration-none mr-8">
      <v-icon icon="mdi-ticket-confirmation" color="indigo" size="34" />

      <span class="text-h6 font-weight-bold text-grey-darken-4 ml-2"> TicketSystem </span>
    </router-link>

    <!-- Navigation -->
    <v-btn
      to="/dashboard"
      variant="text"
      color="indigo"
      prepend-icon="mdi-view-grid-outline"
      class="text-none font-weight-medium mx-1"
    >
      Dashboard
    </v-btn>

    <v-btn
      to="/dashboard/tickets"
      variant="text"
      color="grey-darken-4"
      prepend-icon="mdi-bell-outline"
      class="text-none font-weight-medium mx-1"
    >
      My Tickets
    </v-btn>

    <v-btn
      to="/dashboard/knowledge-base"
      variant="text"
      color="grey-darken-4"
      prepend-icon="mdi-book-open-outline"
      class="text-none font-weight-medium mx-1"
    >
      Knowledge Base
    </v-btn>

    <v-btn
      to="/dashboard/announcements"
      variant="text"
      color="grey-darken-4"
      prepend-icon="mdi-bullhorn-outline"
      class="text-none font-weight-medium mx-1"
    >
      Announcements
    </v-btn>

    <v-btn
      to="/dashboard/contact"
      variant="text"
      color="grey-darken-4"
      prepend-icon="mdi-message-outline"
      class="text-none font-weight-medium mx-1"
    >
      Contact Support
    </v-btn>

    <v-spacer />

    <!-- Search -->
    <v-text-field
      placeholder="Search tickets, articles..."
      prepend-inner-icon="mdi-magnify"
      variant="outlined"
      density="compact"
      hide-details
      rounded="lg"
      width="250"
      class="mr-3"
    />

    <!-- Notifications -->
    <v-btn icon variant="text" color="grey-darken-2" aria-label="Notifications">
      <v-badge content="3" color="indigo">
        <v-icon icon="mdi-bell-outline" />
      </v-badge>
    </v-btn>

    <v-divider vertical class="mx-3 my-4" />

    <!-- User -->
    <v-menu location="bottom end">
      <template #activator="{ props }">
        <v-btn v-bind="props" variant="text" class="text-none px-2">
          <v-avatar size="38" color="indigo-lighten-5">
            <v-icon icon="mdi-account" color="indigo" />
          </v-avatar>

          <div class="text-left ml-3">
            <div class="text-body-2 font-weight-bold text-grey-darken-4">Sarah Johnson</div>

            <div class="text-caption text-grey-darken-1">Employee</div>
          </div>

          <v-icon icon="mdi-chevron-down" size="20" class="ml-2" />
        </v-btn>
      </template>

      <v-card min-width="220" rounded="lg" elevation="4">
        <v-list class="pa-2">
          <v-list-item
            to="/dashboard/profile"
            prepend-icon="mdi-account-outline"
            title="My Profile"
            rounded="lg"
          />

          <v-list-item
            to="/dashboard/settings"
            prepend-icon="mdi-cog-outline"
            title="Settings"
            rounded="lg"
          />

          <v-divider class="my-2" />

          <v-list-item prepend-icon="mdi-logout" title="Log Out" base-color="error" rounded="lg" />
        </v-list>
      </v-card>
    </v-menu>
  </v-app-bar>
</template>
