<script setup>
import { computed, onMounted, ref } from 'vue'
import { useTicketStore } from '@/stores/ticketStore'

const ticketStore = useTicketStore()

const search = ref('')
const selectedStatus = ref(null)

const statusOptions = [
  { title: 'All statuses', value: null },
  { title: 'Open', value: 'open' },
  { title: 'In Progress', value: 'in progress' },
  { title: 'Pending', value: 'pending' },
  { title: 'Resolved', value: 'resolved' },
  { title: 'Closed', value: 'closed' },
]

const headers = [
  { title: 'Ticket ID', key: 'ticketNumber' },
  { title: 'Subject', key: 'subject' },
  { title: 'Status', key: 'statusLabel' },
  { title: 'Priority', key: 'priorityLabel' },
  { title: 'Department', key: 'departmentName' },
  { title: 'Created', key: 'createdAt' },
  { title: 'Last Updated', key: 'updatedAt' },
]

const normalizeText = (value) => {
  return value?.trim().toLowerCase().replaceAll('_', ' ')
}

const filteredTickets = computed(() => {
  const tickets = ticketStore.tickets ?? []

  if (!selectedStatus.value) {
    return tickets
  }

  return tickets.filter((ticket) => {
    return normalizeText(ticket.statusLabel) === selectedStatus.value
  })
})

const loadTickets = async () => {
  await ticketStore.getTickets()
}

const getStatusColor = (status) => {
  const colors = {
    open: 'blue',
    'in progress': 'orange',
    pending: 'purple',
    resolved: 'green',
    closed: 'grey',
  }

  return colors[normalizeText(status)] || 'grey'
}

const getPriorityColor = (priority) => {
  const colors = {
    urgent: 'red-darken-2',
    high: 'red',
    medium: 'orange',
    low: 'green',
  }

  return colors[normalizeText(priority)] || 'grey'
}

const formatDate = (date) => {
  if (!date) {
    return '-'
  }

  return new Intl.DateTimeFormat('en-GB', {
    day: '2-digit',
    month: 'short',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
  }).format(new Date(date))
}

onMounted(loadTickets)
</script>

<template>
  <v-container class="py-8" fluid>
    <v-row justify="center">
      <v-col cols="12" xl="11">
        <div class="d-flex flex-column flex-sm-row align-sm-center justify-space-between ga-4 mb-6">
          <div>
            <h1 class="text-h4 font-weight-bold text-indigo-darken-4 mb-1">My Tickets</h1>

            <p class="text-body-1 text-blue-grey-darken-1 mb-0">
              View and track the tickets available to your account.
            </p>
          </div>

          <div class="d-flex ga-2">
            <v-btn
              :loading="ticketStore.loading"
              color="indigo"
              prepend-icon="mdi-refresh"
              variant="tonal"
              @click="loadTickets"
            >
              Refresh
            </v-btn>

            <v-btn
              :to="{ name: 'create-ticket' }"
              color="indigo"
              prepend-icon="mdi-plus"
              variant="flat"
            >
              Create Ticket
            </v-btn>
          </div>
        </div>

        <v-alert
          v-if="ticketStore.errorMessage"
          class="mb-5"
          closable
          color="error"
          variant="tonal"
        >
          {{ ticketStore.errorMessage }}
        </v-alert>

        <v-card border elevation="0" rounded="xl">
          <v-card-text class="pa-5">
            <v-row>
              <v-col cols="12" md="8">
                <v-text-field
                  v-model="search"
                  clearable
                  color="indigo"
                  hide-details
                  label="Search tickets"
                  placeholder="Search by ticket number, subject or department"
                  prepend-inner-icon="mdi-magnify"
                  variant="outlined"
                />
              </v-col>

              <v-col cols="12" md="4">
                <v-select
                  v-model="selectedStatus"
                  :items="statusOptions"
                  color="indigo"
                  hide-details
                  item-title="title"
                  item-value="value"
                  label="Status"
                  prepend-inner-icon="mdi-filter-variant"
                  variant="outlined"
                />
              </v-col>
            </v-row>
          </v-card-text>

          <v-divider />

          <v-data-table
            :headers="headers"
            :items="filteredTickets"
            :loading="ticketStore.loading"
            :search="search"
            item-value="id"
            items-per-page="10"
            loading-text="Tickets are loading..."
            no-data-text="No tickets found."
          >
            <template #item.ticketNumber="{ value }">
              <span class="font-weight-bold text-indigo">
                {{ value }}
              </span>
            </template>

            <template #item.statusLabel="{ value }">
              <v-chip :color="getStatusColor(value)" size="small" variant="tonal">
                {{ value }}
              </v-chip>
            </template>

            <template #item.priorityLabel="{ value }">
              <div class="d-flex align-center ga-2">
                <v-icon :color="getPriorityColor(value)" size="10"> mdi-circle </v-icon>

                <span>{{ value }}</span>
              </div>
            </template>

            <template #item.departmentName="{ value }">
              {{ value || 'Everyone' }}
            </template>

            <template #item.createdAt="{ value }">
              <span class="text-caption">
                {{ formatDate(value) }}
              </span>
            </template>

            <template #item.updatedAt="{ value }">
              <span class="text-caption">
                {{ formatDate(value) }}
              </span>
            </template>
          </v-data-table>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
