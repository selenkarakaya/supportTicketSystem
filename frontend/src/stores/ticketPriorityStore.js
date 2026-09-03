import { defineStore } from 'pinia'
import { ref } from 'vue'
import { getTicketPrioritiesRequest } from '@/services/ticketPriorityService'

export const useTicketPriorityStore = defineStore('ticketPriority', () => {
  const priorities = ref([])
  const loading = ref(false)
  const errorMessage = ref('')

  const getTicketPriorities = async () => {
    loading.value = true
    errorMessage.value = ''

    try {
      const response = await getTicketPrioritiesRequest()

      if (!response.ok) {
        priorities.value = []
        errorMessage.value = 'Priorities could not be loaded.'
        return false
      }

      priorities.value = await response.json()

      return true
    } catch (error) {
      priorities.value = []
      errorMessage.value = 'Something went wrong.'
      return false
    } finally {
      loading.value = false
    }
  }

  return { getTicketPriorities, priorities, loading, errorMessage }
})
