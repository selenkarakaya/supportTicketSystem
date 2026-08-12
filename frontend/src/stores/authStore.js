import { defineStore } from 'pinia'
import { ref } from 'vue'
import {
  registerCompanyRequest,
  loginUserRequest,
  getCurrentUserRequest,
} from '@/services/authService'

export const useAuthStore = defineStore('auth', () => {
  const loading = ref(false)
  const successMessage = ref('')
  const errorMessage = ref('')
  const isAuthenticated = ref(false)

  const registerCompany = async (registerData) => {
    loading.value = true
    successMessage.value = ''
    errorMessage.value = ''

    try {
      const response = await registerCompanyRequest(registerData)
      const data = await response.json()

      if (!response.ok) {
        errorMessage.value = data.message || 'Registration failed.'
        return false
      }

      successMessage.value = data.message
      return true
    } catch (error) {
      errorMessage.value = 'Something went wrong.'
      return false
    } finally {
      loading.value = false
    }
  }

  const loginUser = async (loginData) => {
    loading.value = true
    successMessage.value = ''
    errorMessage.value = ''

    try {
      const response = await loginUserRequest(loginData)
      const data = await response.json()

      if (!response.ok) {
        errorMessage.value = data.message || 'Log in failed.'
        return false
      }

      successMessage.value = data.message
      isAuthenticated.value = true

      return true
    } catch (error) {
      errorMessage.value = 'Something went wrong.'
      return false
    } finally {
      loading.value = false
    }
  }

  const checkAuth = async () => {
    try {
      const response = await getCurrentUserRequest()

      if (!response.ok) {
        isAuthenticated.value = false
        return false
      }

      isAuthenticated.value = true
      return true
    } catch (error) {
      isAuthenticated.value = false
      return false
    }
  }

  return {
    loading,
    successMessage,
    errorMessage,
    registerCompany,
    loginUser,
    checkAuth,
    isAuthenticated,
  }
})
