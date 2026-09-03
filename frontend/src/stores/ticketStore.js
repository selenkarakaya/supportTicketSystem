import { defineStore } from 'pinia'
import { ref } from 'vue'

import { createTicketRequest, getTicketsRequest } from '@/services/ticketService'

export const useTicketStore = defineStore('ticket', () => {
  const createdTicket = ref(null)
  const loading = ref(false)
  const errorMessage = ref('')
  const successMessage = ref('')
  const tickets = ref([])

  const createTicket = async (ticketData) => {
    loading.value = true
    errorMessage.value = ''
    successMessage.value = ''
    createdTicket.value = null

    try {
      const response = await createTicketRequest(ticketData)
      const data = await response.json()

      if (!response.ok) {
        errorMessage.value = data.message || 'Ticket could not be created.'

        return false
      }

      createdTicket.value = data
      successMessage.value = data.message || 'Ticket created successfully.'

      return true
    } catch (error) {
      errorMessage.value = 'Something went wrong.'
      return false
    } finally {
      loading.value = false
    }
  }

  const getTickets = async () => {
    loading.value = true
    errorMessage.value = ''

    try {
      const response = await getTicketsRequest()
      const data = await response.json()

      if (!response.ok) {
        tickets.value = []
        errorMessage.value = data.message || 'Tickets could not be loaded.'
        return false
      }

      tickets.value = data
      return true
    } catch (error) {
      tickets.value = []
      errorMessage.value = 'Something went wrong.'
      return false
    } finally {
      loading.value = false
    }
  }

  return {
    createdTicket,
    loading,
    errorMessage,
    successMessage,
    createTicket,
    getTickets,
    tickets,
  }
})
