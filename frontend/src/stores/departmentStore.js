import { defineStore } from 'pinia'
import { ref } from 'vue'
import { getDepartmentsByCompanyRequest } from '@/services/departmentService'

export const useDepartmentStore = defineStore('department', () => {
  const departments = ref([])
  const loading = ref(false)
  const errorMessage = ref('')

  const getDepartmentsByCompany = async (companyId) => {
    loading.value = true
    errorMessage.value = ''

    try {
      const response = await getDepartmentsByCompanyRequest(companyId)

      if (!response.ok) {
        errorMessage.value = 'Departments could not be loaded.'
        departments.value = []
        return false
      }

      const data = await response.json()

      departments.value = data

      return true
    } catch (error) {
      errorMessage.value = 'Something went wrong.'
      departments.value = []
      return false
    } finally {
      loading.value = false
    }
  }

  return {
    departments,
    loading,
    errorMessage,
    getDepartmentsByCompany,
  }
})
