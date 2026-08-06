import { defineStore } from 'pinia'
import { ref } from 'vue'
import { registerCompanyRequest } from '@/services/authService'

export const useAuthStore = defineStore('auth', () => {
  const loading = ref(false)
  const successMessage = ref('')
  const errorMessage = ref('')

  const registerCompany = async (registerData) => {
    loading.value = true
    successMessage.value = ''
    errorMessage.value = ''

    try {
      const response = await registerCompanyRequest(registerData)
      const data = await response.json()
    } catch (error) {
      console.error(error)
    } finally {
      loading.value = false
    }
  }

  return {
    loading,
    successMessage,
    errorMessage,
    registerCompany,
  }
})
