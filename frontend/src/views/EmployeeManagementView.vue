<script setup>
import { computed, onMounted, ref } from 'vue'

import { useUserStore } from '@/stores/userStore'

const userStore = useUserStore()

// Search input
const search = ref('')

// Table headers
const headers = [
  {
    title: 'Employee',
    key: 'fullName',
  },
  {
    title: 'Email',
    key: 'email',
  },
  {
    title: 'Department',
    key: 'departmentName',
  },
  {
    title: 'Job Title',
    key: 'jobTitle',
  },
  {
    title: 'Role',
    key: 'role',
  },
  {
    title: 'Status',
    key: 'active',
  },
  {
    title: 'Actions',
    key: 'actions',
    sortable: false,
  },
]

// Do not show COMPANY_ADMIN in employee management table
const employees = computed(() => {
  return userStore.users.filter((user) => user.role !== 'COMPANY_ADMIN')
})

// Format role
// SUPPORT_AGENT -> Support Agent
const formatRole = (role) => {
  if (!role) {
    return ''
  }

  return role
    .toLowerCase()
    .replaceAll('_', ' ')
    .split(' ')
    .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
    .join(' ')
}

// Role chip color
const getRoleColor = (role) => {
  if (role === 'SUPPORT_AGENT') {
    return 'purple'
  }

  return 'blue'
}

// Load company users when page opens
onMounted(async () => {
  await userStore.getCompanyUsers()
})
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
    <!-- Page Header -->
    <div class="d-flex flex-column flex-md-row align-md-center justify-space-between ga-4 mb-6">
      <div>
        <v-chip
          color="indigo"
          variant="tonal"
          prepend-icon="mdi-account-group-outline"
          class="mb-3"
        >
          Team Management
        </v-chip>

        <h1 class="text-h4 font-weight-bold text-indigo-darken-4 mb-2">Employees</h1>

        <p class="text-body-2 text-blue-grey-darken-1 mb-0">
          Manage employees and support agents in your company workspace.
        </p>
      </div>

      <div class="d-flex ga-2">
        <v-btn
          color="indigo"
          variant="tonal"
          prepend-icon="mdi-account-plus-outline"
          class="text-none"
        >
          Invite Employee
        </v-btn>

        <v-btn color="indigo" prepend-icon="mdi-headset" class="text-none">
          Invite Support Agent
        </v-btn>
      </div>
    </div>

    <!-- Error -->
    <v-alert v-if="userStore.errorMessage" type="error" variant="tonal" class="mb-5">
      {{ userStore.errorMessage }}
    </v-alert>

    <!-- Employee Table -->
    <v-card color="white" elevation="1" class="rounded-xl">
      <!-- Table toolbar -->
      <div class="d-flex flex-column flex-sm-row align-sm-center justify-space-between ga-4 pa-5">
        <div>
          <h2 class="text-h6 font-weight-bold text-indigo-darken-4">Company Users</h2>

          <p class="text-caption text-blue-grey-darken-1 mb-0">{{ employees.length }} users</p>
        </div>

        <v-text-field
          v-model="search"
          prepend-inner-icon="mdi-magnify"
          label="Search employees"
          variant="outlined"
          density="compact"
          hide-details
          clearable
          style="max-width: 320px"
        />
      </div>

      <v-divider />

      <v-data-table
        :headers="headers"
        :items="employees"
        :search="search"
        :loading="userStore.loading"
      >
        <!-- Employee name -->
        <template #item.fullName="{ item }">
          <div class="d-flex align-center ga-3 py-2">
            <v-avatar color="indigo-lighten-5" size="40">
              <v-icon color="indigo"> mdi-account-outline </v-icon>
            </v-avatar>

            <span class="font-weight-medium">
              {{ item.fullName }}
            </span>
          </div>
        </template>

        <!-- Role -->
        <template #item.role="{ item }">
          <v-chip :color="getRoleColor(item.role)" variant="tonal" size="small">
            {{ formatRole(item.role) }}
          </v-chip>
        </template>

        <!-- Status -->
        <template #item.active="{ item }">
          <v-chip :color="item.active ? 'green' : 'red'" variant="tonal" size="small">
            {{ item.active ? 'Active' : 'Inactive' }}
          </v-chip>
        </template>

        <!-- Actions -->
        <template #item.actions="{ item }">
          <v-btn icon="mdi-dots-vertical" variant="text" size="small" />
        </template>

        <!-- Empty table -->
        <template #no-data>
          <div class="text-center pa-10">
            <v-icon size="50" color="blue-grey-lighten-2" class="mb-3">
              mdi-account-search-outline
            </v-icon>

            <p class="font-weight-bold mb-1">No employees found</p>

            <p class="text-body-2 text-blue-grey-darken-1">
              Invite employees to start building your team.
            </p>
          </div>
        </template>
      </v-data-table>
    </v-card>
  </v-container>
</template>
