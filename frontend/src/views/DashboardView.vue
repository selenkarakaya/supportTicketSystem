<script setup>
import { useAuthStore } from '@/stores/authStore'
import { onMounted } from 'vue'

const authStore = useAuthStore()
onMounted(() => {
  const firstName = authStore.user?.fullName?.split(' ')[0]
  const formattedName = firstName
    ? firstName.charAt(0).toUpperCase() + firstName.slice(1).toLowerCase()
    : ''

  return authStore.user
})
const ticketStats = [
  {
    title: 'Open Tickets',
    value: 7,
    change: '2 from last week',
    icon: 'mdi-file-document-outline',
    color: 'blue',
    background: 'blue-lighten-5',
  },
  {
    title: 'In Progress',
    value: 3,
    change: '1 from last week',
    icon: 'mdi-progress-clock',
    color: 'orange',
    background: 'orange-lighten-5',
  },
  {
    title: 'Resolved',
    value: 24,
    change: '6 from last week',
    icon: 'mdi-check-circle-outline',
    color: 'green',
    background: 'green-lighten-5',
  },
  {
    title: 'Pending Response',
    value: 5,
    change: '1 from last week',
    icon: 'mdi-message-outline',
    color: 'purple',
    background: 'purple-lighten-5',
  },
]

const recentTickets = [
  {
    id: 'TS-10452',
    subject: 'Unable to access VPN',
    status: 'Open',
    priority: 'High',
    assignedTo: 'IT Support',
    updatedAt: 'May 15, 2026 10:24 AM',
  },
  {
    id: 'TS-10431',
    subject: 'Error in payroll portal',
    status: 'In Progress',
    priority: 'Medium',
    assignedTo: 'Payroll Team',
    updatedAt: 'May 14, 2026 02:15 PM',
  },
  {
    id: 'TS-10418',
    subject: 'Request for software access',
    status: 'Pending Response',
    priority: 'Low',
    assignedTo: 'IT Support',
    updatedAt: 'May 13, 2026 11:03 AM',
  },
  {
    id: 'TS-10392',
    subject: 'Printer not working',
    status: 'Resolved',
    priority: 'Medium',
    assignedTo: 'Facilities Team',
    updatedAt: 'May 10, 2026 04:45 PM',
  },
  {
    id: 'TS-10371',
    subject: 'Email not syncing on mobile',
    status: 'Resolved',
    priority: 'Low',
    assignedTo: 'IT Support',
    updatedAt: 'May 9, 2026 09:30 AM',
  },
]

const recentActivities = [
  {
    title: 'New comment on TS-10452',
    description: 'IT Support added a comment',
    time: '10 minutes ago',
    icon: 'mdi-message-outline',
    color: 'blue',
    background: 'blue-lighten-5',
  },
  {
    title: 'Status updated on TS-10431',
    description: 'Payroll Team changed status to In Progress',
    time: '1 hour ago',
    icon: 'mdi-progress-clock',
    color: 'orange',
    background: 'orange-lighten-5',
  },
  {
    title: 'Ticket resolved: TS-10392',
    description: 'Facilities Team marked the ticket as Resolved',
    time: '2 days ago',
    icon: 'mdi-check-circle-outline',
    color: 'green',
    background: 'green-lighten-5',
  },
  {
    title: 'Attachment added to TS-10418',
    description: 'IT Support uploaded an attachment',
    time: '2 days ago',
    icon: 'mdi-paperclip',
    color: 'purple',
    background: 'purple-lighten-5',
  },
]

const quickActions = [
  {
    title: 'Create a Ticket',
    description: 'Submit a new request or report an issue.',
    icon: 'mdi-plus',
  },
  {
    title: 'View My Tickets',
    description: 'View and track all your submitted tickets.',
    icon: 'mdi-file-document-outline',
  },
  {
    title: 'Upload Attachment',
    description: 'Upload files to add to an existing ticket.',
    icon: 'mdi-paperclip',
  },
  {
    title: 'Contact Support',
    description: 'Get help from our support team.',
    icon: 'mdi-headset',
  },
]

const articles = [
  {
    title: 'How to Reset Your Password',
    description: 'Step-by-step guide to reset your account password.',
    category: 'Account & Access',
    icon: 'mdi-lock-outline',
  },
  {
    title: 'Connecting to the VPN',
    description: 'Learn how to connect to the company VPN on different devices.',
    category: 'IT & Connectivity',
    icon: 'mdi-laptop',
  },
  {
    title: 'Email Troubleshooting Tips',
    description: 'Common solutions for email sync and login issues.',
    category: 'Email & Communication',
    icon: 'mdi-email-outline',
  },
]

const getStatusColor = (status) => {
  const colors = {
    Open: 'blue',
    'In Progress': 'orange',
    Resolved: 'green',
    'Pending Response': 'purple',
  }

  return colors[status] || 'grey'
}

const getPriorityColor = (priority) => {
  const colors = {
    High: 'red',
    Medium: 'orange',
    Low: 'green',
  }

  return colors[priority] || 'grey'
}
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
            Welcome back, Sarah 👋
          </h1>

          <p class="text-body-1 text-blue-grey-darken-1 mb-0">
            Track your requests and get support quickly.
          </p>
        </v-col>

        <v-col cols="12" md="5" class="d-flex flex-column flex-sm-row justify-md-end ga-3">
          <v-btn color="indigo" size="large" prepend-icon="mdi-plus" class="text-none px-6">
            Create Ticket
          </v-btn>

          <v-btn
            color="indigo"
            variant="outlined"
            size="large"
            prepend-icon="mdi-book-open-page-variant-outline"
            class="text-none px-6"
          >
            Browse Knowledge Base
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
              <v-icon :color="stat.color" size="30">
                {{ stat.icon }}
              </v-icon>
            </v-avatar>

            <div>
              <p class="text-body-2 font-weight-medium mb-1">
                {{ stat.title }}
              </p>

              <p class="text-h4 font-weight-bold mb-1">
                {{ stat.value }}
              </p>

              <div class="d-flex align-center ga-1">
                <v-icon :color="stat.color" size="small"> mdi-trending-up </v-icon>

                <span class="text-caption" :class="`text-${stat.color}`">
                  {{ stat.change }}
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
      <v-col cols="12" lg="8">
        <v-card color="white" elevation="1" class="rounded-xl h-100 overflow-hidden">
          <div class="d-flex align-center justify-space-between pa-5">
            <h2 class="text-h6 font-weight-bold">Recent Tickets</h2>

            <v-btn color="indigo" variant="text" append-icon="mdi-arrow-right" class="text-none">
              View all tickets
            </v-btn>
          </div>

          <v-divider />

          <v-table class="d-none d-md-table">
            <thead>
              <tr>
                <th>Ticket ID</th>
                <th>Subject</th>
                <th>Status</th>
                <th>Priority</th>
                <th>Assigned To</th>
                <th>Last Updated</th>
                <th />
              </tr>
            </thead>

            <tbody>
              <tr v-for="ticket in recentTickets" :key="ticket.id">
                <td class="font-weight-bold text-indigo">
                  {{ ticket.id }}
                </td>

                <td>{{ ticket.subject }}</td>

                <td>
                  <v-chip :color="getStatusColor(ticket.status)" variant="tonal" size="small">
                    {{ ticket.status }}
                  </v-chip>
                </td>

                <td>
                  <div class="d-flex align-center ga-2">
                    <v-icon :color="getPriorityColor(ticket.priority)" size="10">
                      mdi-circle
                    </v-icon>

                    <span>{{ ticket.priority }}</span>
                  </div>
                </td>

                <td>{{ ticket.assignedTo }}</td>

                <td class="text-caption">
                  {{ ticket.updatedAt }}
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
                  {{ ticket.id }} · {{ ticket.assignedTo }}
                </v-list-item-subtitle>

                <div class="d-flex flex-wrap align-center ga-2">
                  <v-chip :color="getStatusColor(ticket.status)" variant="tonal" size="small">
                    {{ ticket.status }}
                  </v-chip>

                  <v-chip :color="getPriorityColor(ticket.priority)" variant="tonal" size="small">
                    {{ ticket.priority }}
                  </v-chip>
                </div>

                <template #append>
                  <v-icon color="indigo"> mdi-chevron-right </v-icon>
                </template>
              </v-list-item>

              <v-divider v-if="index < recentTickets.length - 1" />
            </template>
          </v-list>
        </v-card>
      </v-col>

      <!-- Recent activity -->
      <v-col cols="12" lg="4">
        <v-card color="white" elevation="1" class="rounded-xl h-100">
          <div class="d-flex align-center justify-space-between pa-5">
            <h2 class="text-h6 font-weight-bold">Recent Activity</h2>

            <v-btn color="indigo" variant="text" append-icon="mdi-arrow-right" class="text-none">
              View all
            </v-btn>
          </div>

          <v-divider />

          <v-list bg-color="transparent" class="py-2">
            <v-list-item
              v-for="activity in recentActivities"
              :key="activity.title"
              class="px-5 py-3"
            >
              <template #prepend>
                <v-avatar :color="activity.background" size="42" class="me-3">
                  <v-icon :color="activity.color" size="22">
                    {{ activity.icon }}
                  </v-icon>
                </v-avatar>
              </template>

              <v-list-item-title class="text-body-2 font-weight-bold mb-1">
                {{ activity.title }}
              </v-list-item-title>

              <v-list-item-subtitle class="text-caption mb-1">
                {{ activity.description }}
              </v-list-item-subtitle>

              <p class="text-caption text-blue-grey-lighten-1 mb-0">
                {{ activity.time }}
              </p>
            </v-list-item>
          </v-list>
        </v-card>
      </v-col>
    </v-row>

    <!-- Quick actions and announcement -->
    <v-row class="mb-1">
      <v-col cols="12" lg="8">
        <v-card color="white" elevation="1" class="pa-5 rounded-xl h-100">
          <h2 class="text-h6 font-weight-bold mb-4">Quick Actions</h2>

          <v-row>
            <v-col v-for="action in quickActions" :key="action.title" cols="12" sm="6" xl="3">
              <v-card variant="outlined" class="pa-4 rounded-lg h-100">
                <div class="d-flex justify-space-between align-center h-100 ga-3">
                  <div class="d-flex align-start ga-3">
                    <v-avatar color="indigo" rounded="lg" size="46">
                      <v-icon color="white">
                        {{ action.icon }}
                      </v-icon>
                    </v-avatar>

                    <div>
                      <p class="text-body-2 font-weight-bold mb-1">
                        {{ action.title }}
                      </p>

                      <p class="text-caption text-blue-grey-darken-1 mb-0">
                        {{ action.description }}
                      </p>
                    </div>
                  </div>

                  <v-icon size="small"> mdi-arrow-right </v-icon>
                </div>
              </v-card>
            </v-col>
          </v-row>
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

    <!-- Recommended articles -->
    <v-card color="white" elevation="1" class="pa-5 rounded-xl">
      <div class="d-flex align-center justify-space-between mb-4">
        <h2 class="text-h6 font-weight-bold">Recommended Knowledge Base Articles</h2>

        <v-btn
          color="indigo"
          variant="text"
          append-icon="mdi-arrow-right"
          class="text-none d-none d-sm-flex"
        >
          Browse all articles
        </v-btn>
      </div>

      <v-row>
        <v-col v-for="article in articles" :key="article.title" cols="12" md="4">
          <v-card variant="outlined" class="pa-4 rounded-lg h-100">
            <div class="d-flex align-start ga-4">
              <v-avatar color="blue-lighten-5" size="58" rounded="lg">
                <v-icon color="indigo" size="30">
                  {{ article.icon }}
                </v-icon>
              </v-avatar>

              <div class="flex-grow-1">
                <p class="font-weight-bold mb-1">
                  {{ article.title }}
                </p>

                <p class="text-body-2 text-blue-grey-darken-1 mb-3">
                  {{ article.description }}
                </p>

                <div class="d-flex align-center justify-space-between">
                  <v-chip color="indigo" variant="tonal" size="x-small">
                    {{ article.category }}
                  </v-chip>

                  <v-icon size="small"> mdi-arrow-right </v-icon>
                </div>
              </div>
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-card>
  </v-container>
</template>
