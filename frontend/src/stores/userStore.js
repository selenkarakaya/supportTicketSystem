import { defineStore } from 'pinia'
import { ref } from 'vue'

import { getCompanyUsersRequest, getUsersByDepartmentRequest } from '@/services/userService'

export const useUserStore = defineStore('user', () => {
  const users = ref([])
  const departmentUsers = ref([])

  const loading = ref(false)
  const errorMessage = ref('')

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
      errorMessage.value = 'Something went wrong.'
      users.value = []
      return false
    } finally {
      loading.value = false
    }
  }

  const getUsersByDepartment = async (departmentId) => {
    loading.value = true
    errorMessage.value = ''

    try {
      const response = await getUsersByDepartmentRequest(departmentId)

      const data = await response.json()

      if (!response.ok) {
        errorMessage.value = data.message || 'Department users could not be loaded.'
        departmentUsers.value = []
        return false
      }

      departmentUsers.value = data
      return true
    } catch (error) {
      errorMessage.value = 'Something went wrong.'
      departmentUsers.value = []
      return false
    } finally {
      loading.value = false
    }
  }

  return {
    users,
    departmentUsers,
    loading,
    errorMessage,
    getCompanyUsers,
    getUsersByDepartment,
  }
})
