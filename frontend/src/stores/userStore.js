import { defineStore } from 'pinia'
import { ref } from 'vue'

import { getCompanyUsersRequest } from '@/services/userService'

export const useUserStore = defineStore('user', () => {
  // Company employees and support agents
  const users = ref([])

  // Request state
  const loading = ref(false)
  const errorMessage = ref('')

  // Get company users
  const getCompanyUsers = async () => {
    loading.value = true
    errorMessage.value = ''

    try {
      const response = await getCompanyUsersRequest()
      const data = await response.json()

      if (!response.ok) {
        errorMessage.value = data.message || 'Users could not be loaded.'

        users.value = []

        return false
      }

      users.value = data

      return true
    } catch (error) {
      console.error(error)

      errorMessage.value = 'Something went wrong.'
      users.value = []

      return false
    } finally {
      loading.value = false
    }
  }

  return {
    users,
    loading,
    errorMessage,
    getCompanyUsers,
  }
})
