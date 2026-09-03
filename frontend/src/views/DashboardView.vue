<script setup>
import { useAuthStore } from '@/stores/authStore'
import { useTicketStore } from '@/stores/ticketStore'
import { computed, onMounted } from 'vue'

const authStore = useAuthStore()
const ticketStore = useTicketStore()

const firstName = computed(() => {
  const name = authStore.user?.fullName?.split(' ')[0]

  return name ? name.charAt(0).toUpperCase() + name.slice(1).toLowerCase() : ''
})

const recentTickets = computed(() => {
  return ticketStore.tickets.slice(0, 5)
})

onMounted(async () => {
  if (!authStore.user) {
    const authenticated = await authStore.checkAuth()

    if (!authenticated) {
      return
    }
  }

  await ticketStore.getTickets()
})

const getStatusColor = (status) => {
  const colors = {
    open: 'blue',
    'in progress': 'orange',
    pending: 'purple',
    resolved: 'green',
    closed: 'grey',
  }

  return colors[status?.toLowerCase().replaceAll('_', ' ')] || 'grey'
}

const getPriorityColor = (priority) => {
  const colors = {
    urgent: 'red-darken-2',
    high: 'red',
    medium: 'orange',
    low: 'green',
  }

  return colors[priority?.toLowerCase()] || 'grey'
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

const normalizeStatus = (status) => {
  return status?.trim().toLowerCase().replaceAll('_', ' ')
}

const countTicketsByStatus = (...statuses) => {
  const normalizedStatuses = statuses.map(normalizeStatus)
  const tickets = ticketStore.tickets ?? []

  return tickets.filter((ticket) => {
    const ticketStatus = normalizeStatus(ticket.statusLabel)

    return normalizedStatuses.includes(ticketStatus)
  }).length
}

const ticketStats = computed(() => [
  {
    title: 'Open Tickets',
    value: countTicketsByStatus('open'),
    description: 'Currently open',
    icon: 'mdi-file-document-outline',
    color: 'blue',
    background: 'blue-lighten-5',
  },
  {
    title: 'In Progress',
    value: countTicketsByStatus('in progress'),
    description: 'Currently being handled',
    icon: 'mdi-progress-clock',
    color: 'orange',
    background: 'orange-lighten-5',
  },
  {
    title: 'Resolved',
    value: countTicketsByStatus('resolved'),
    description: 'Successfully resolved',
    icon: 'mdi-check-circle-outline',
    color: 'green',
    background: 'green-lighten-5',
  },
  {
    title: 'Pending',
    value: countTicketsByStatus('pending', 'pending response'),
    description: 'Waiting for response',
    icon: 'mdi-message-outline',
    color: 'purple',
    background: 'purple-lighten-5',
  },
])
</script>

<template>
  <v-container
    fluid
    class="pa-4 pa-md-6"
    style="
      min-height: 100vh;
      background: linear-gradient(135deg, #fcfcfd 0%, #f4f8fd 50%, #fcfcfd 100%);
    "
  >
    <!-- Welcome -->
    <v-card color="white" elevation="1" class="pa-5 pa-md-7 rounded-xl mb-5">
      <v-row align="center">
        <v-col cols="12" md="7">
          <h1 class="text-h4 text-md-h3 font-weight-bold text-indigo-darken-4 mb-2">
            Welcome back, {{ firstName }} 👋
          </h1>

          <p class="text-body-1 text-blue-grey-darken-1 mb-0">
            Track your requests and get support quickly.
          </p>
        </v-col>

        <v-col cols="12" md="5" class="d-flex flex-column flex-sm-row justify-md-end ga-3">
          <v-btn
            color="indigo"
            size="large"
            prepend-icon="mdi-plus"
            class="text-none px-6"
            :to="{ name: 'create-ticket' }"
          >
            Create Ticket
          </v-btn>
        </v-col>
      </v-row>
    </v-card>

    <!-- Ticket statistics -->
    <v-row class="mb-1">
      <v-col v-for="stat in ticketStats" :key="stat.title" cols="12" sm="6" lg="3">
        <v-card color="white" elevation="1" class="pa-5 rounded-xl h-100">
          <div class="d-flex align-center ga-4">
            <v-avatar :color="stat.background" size="60" rounded="lg">
              <v-icon :color="stat.color" :icon="stat.icon" size="30" />
            </v-avatar>

            <div>
              <p class="text-body-2 font-weight-medium mb-1">
                {{ stat.title }}
              </p>

              <p class="text-h4 font-weight-bold mb-1">
                {{ ticketStore.loading ? '—' : stat.value }}
              </p>

              <div class="d-flex align-center ga-1">
                <v-icon :color="stat.color" icon="mdi-database-outline" size="small" />

                <span class="text-caption" :class="`text-${stat.color}`">
                  {{ stat.description }}
                </span>
              </div>
            </div>
          </div>
        </v-card>
      </v-col>
    </v-row>

    <!-- Tickets and activity -->
    <v-row class="mb-1">
      <!-- Recent tickets -->
      <v-col cols="12" lg="12">
        <v-card color="white" elevation="1" class="rounded-xl h-100 overflow-hidden">
          <div class="d-flex align-center justify-space-between pa-5">
            <h2 class="text-h6 font-weight-bold">Recent Tickets</h2>

            <v-btn color="indigo" variant="text" append-icon="mdi-arrow-right" class="text-none">
              View all tickets
            </v-btn>
          </div>

          <v-divider />

          <v-progress-linear v-if="ticketStore.loading" color="indigo" indeterminate />

          <v-alert v-else-if="ticketStore.errorMessage" class="ma-5" color="error" variant="tonal">
            {{ ticketStore.errorMessage }}
          </v-alert>

          <div
            v-else-if="recentTickets.length === 0"
            class="pa-8 text-center text-blue-grey-darken-1"
          >
            No tickets found.
          </div>

          <template v-else>
            <v-table class="d-none d-md-table">
              <thead>
                <tr>
                  <th>Ticket ID</th>
                  <th>Subject</th>
                  <th>Status</th>
                  <th>Priority</th>
                  <th>Department</th>
                  <th>Last Updated</th>
                  <th />
                </tr>
              </thead>

              <tbody>
                <tr v-for="ticket in recentTickets" :key="ticket.id">
                  <td class="font-weight-bold text-indigo">
                    {{ ticket.ticketNumber }}
                  </td>

                  <td>{{ ticket.subject }}</td>

                  <td>
                    <v-chip
                      :color="getStatusColor(ticket.statusLabel)"
                      variant="tonal"
                      size="small"
                    >
                      {{ ticket.statusLabel }}
                    </v-chip>
                  </td>

                  <td>
                    <div class="d-flex align-center ga-2">
                      <v-icon :color="getPriorityColor(ticket.priorityLabel)" size="10">
                        mdi-circle
                      </v-icon>

                      <span>{{ ticket.priorityLabel }}</span>
                    </div>
                  </td>

                  <td>{{ ticket.departmentName || 'Everyone' }}</td>

                  <td class="text-caption">
                    {{ formatDate(ticket.updatedAt) }}
                  </td>

                  <td>
                    <v-btn icon="mdi-arrow-right" variant="text" color="indigo" size="small" />
                  </td>
                </tr>
              </tbody>
            </v-table>

            <!-- Mobile ticket list -->
            <v-list class="d-md-none py-0">
              <template v-for="(ticket, index) in recentTickets" :key="ticket.id">
                <v-list-item class="pa-4">
                  <v-list-item-title class="font-weight-bold mb-1">
                    {{ ticket.subject }}
                  </v-list-item-title>

                  <v-list-item-subtitle class="mb-3">
                    {{ ticket.ticketNumber }} · {{ ticket.departmentName || 'Everyone' }}
                  </v-list-item-subtitle>

                  <div class="d-flex flex-wrap align-center ga-2">
                    <v-chip
                      :color="getStatusColor(ticket.statusLabel)"
                      variant="tonal"
                      size="small"
                    >
                      {{ ticket.statusLabel }}
                    </v-chip>

                    <v-chip
                      :color="getPriorityColor(ticket.priorityLabel)"
                      variant="tonal"
                      size="small"
                    >
                      {{ ticket.priorityLabel }}
                    </v-chip>
                  </div>

                  <template #append>
                    <v-icon color="indigo"> mdi-chevron-right </v-icon>
                  </template>
                </v-list-item>

                <v-divider v-if="index < recentTickets.length - 1" />
              </template>
            </v-list>
          </template>
        </v-card>
      </v-col>
    </v-row>

    <!-- Quick actions and announcement -->

    <v-row class="mb-1">
      <v-col cols="12" lg="8">
        <v-card color="white" elevation="1" class="pa-5 rounded-xl h-100">
          <v-col cols="12" lg="8">
            <v-card class="pa-5 rounded-xl h-100" color="white" elevation="1">
              <h2 class="text-h6 font-weight-bold mb-4">Quick Actions</h2>

              <v-row>
                <!-- Create Ticket -->
                <v-col cols="12" sm="6" xl="3">
                  <v-card
                    :to="{ name: 'create-ticket' }"
                    class="pa-4 rounded-lg h-100"
                    color="indigo-lighten-5"
                    hover
                    link
                    variant="outlined"
                  >
                    <div class="d-flex align-center ga-3">
                      <v-avatar color="indigo" rounded="lg" size="46">
                        <v-icon color="white" icon="mdi-plus" />
                      </v-avatar>

                      <div>
                        <p class="text-body-2 font-weight-bold mb-1">Create a Ticket</p>

                        <p class="text-caption text-blue-grey-darken-1 mb-0">
                          Submit a new request or report an issue.
                        </p>
                      </div>
                    </div>
                  </v-card>
                </v-col>

                <!-- View Tickets -->
                <v-col cols="12" sm="6" xl="3">
                  <v-card
                    class="pa-4 rounded-lg h-100"
                    hover
                    link
                    variant="outlined"
                    :to="{ name: 'my-tickets' }"
                  >
                    <div class="d-flex align-center ga-3">
                      <v-avatar color="indigo" rounded="lg" size="46">
                        <v-icon color="white" icon="mdi-file-document-outline" />
                      </v-avatar>

                      <div>
                        <p class="text-body-2 font-weight-bold mb-1">View My Tickets</p>

                        <p class="text-caption text-blue-grey-darken-1 mb-0">
                          View and track all your submitted tickets.
                        </p>
                      </div>
                    </div>
                  </v-card>
                </v-col>

                <!-- Upload Attachment -->
                <v-col cols="12" sm="6" xl="3">
                  <v-card class="pa-4 rounded-lg h-100" hover link variant="outlined">
                    <div class="d-flex align-center ga-3">
                      <v-avatar color="indigo" rounded="lg" size="46">
                        <v-icon color="white" icon="mdi-paperclip" />
                      </v-avatar>

                      <div>
                        <p class="text-body-2 font-weight-bold mb-1">Upload Attachment</p>

                        <p class="text-caption text-blue-grey-darken-1 mb-0">
                          Upload files to an existing ticket.
                        </p>
                      </div>
                    </div>
                  </v-card>
                </v-col>

                <!-- Contact Support -->
                <v-col cols="12" sm="6" xl="3">
                  <v-card class="pa-4 rounded-lg h-100" hover link variant="outlined">
                    <div class="d-flex align-center ga-3">
                      <v-avatar color="indigo" rounded="lg" size="46">
                        <v-icon color="white" icon="mdi-headset" />
                      </v-avatar>

                      <div>
                        <p class="text-body-2 font-weight-bold mb-1">Contact Support</p>

                        <p class="text-caption text-blue-grey-darken-1 mb-0">
                          Get help from our support team.
                        </p>
                      </div>
                    </div>
                  </v-card>
                </v-col>
              </v-row>
            </v-card>
          </v-col>
        </v-card>
      </v-col>

      <v-col cols="12" lg="4">
        <v-card color="white" elevation="1" class="pa-5 rounded-xl h-100">
          <div class="d-flex align-center justify-space-between mb-4">
            <h2 class="text-h6 font-weight-bold">Announcements</h2>

            <v-btn color="indigo" variant="text" append-icon="mdi-arrow-right" class="text-none">
              View all
            </v-btn>
          </div>

          <div class="d-flex align-start ga-4">
            <v-avatar color="purple-lighten-5" size="58" rounded="lg">
              <v-icon color="purple" size="30"> mdi-bullhorn-outline </v-icon>
            </v-avatar>

            <div>
              <v-chip color="purple" variant="tonal" size="x-small" class="mb-2">
                Maintenance
              </v-chip>

              <p class="font-weight-bold mb-1">Scheduled Maintenance – May 18</p>

              <p class="text-body-2 text-blue-grey-darken-1 mb-2">
                Our system will be under maintenance on May 18 from 10:00 PM to 2:00 AM. Some
                services may be temporarily unavailable.
              </p>

              <p class="text-caption text-blue-grey-lighten-1 mb-0">May 15, 2026</p>
            </div>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
