import { defineStore } from 'pinia'
import { ref } from 'vue'

import {
  createEmployeeInvitationRequest,
  createSupportAgentInvitationRequest,
  validateInvitationRequest,
  registerInvitedUserRequest,
} from '@/services/invitationService'

export const useInvitationStore = defineStore('invitation', () => {
  const loading = ref(false)

  const invitationLink = ref('')
  const invitation = ref(null)

  const successMessage = ref('')
  const errorMessage = ref('')

  const createEmployeeInvitation = async () => {
    loading.value = true
    invitationLink.value = ''
    successMessage.value = ''
    errorMessage.value = ''

    try {
      const response = await createEmployeeInvitationRequest()

      if (!response.ok) {
        errorMessage.value = 'Employee invitation could not be created.'

        return false
      }

      const data = await response.json()

      invitationLink.value = data.invitationLink
      successMessage.value = data.message

      return true
    } catch (error) {
      errorMessage.value = 'Something went wrong.'

      return false
    } finally {
      loading.value = false
    }
  }

  const createSupportAgentInvitation = async () => {
    loading.value = true
    invitationLink.value = ''
    successMessage.value = ''
    errorMessage.value = ''

    try {
      const response = await createSupportAgentInvitationRequest()

      if (!response.ok) {
        errorMessage.value = 'Support agent invitation could not be created.'

        return false
      }

      const data = await response.json()

      invitationLink.value = data.invitationLink
      successMessage.value = data.message

      return true
    } catch (error) {
      errorMessage.value = 'Something went wrong.'

      return false
    } finally {
      loading.value = false
    }
  }

  const validateInvitation = async (token) => {
    loading.value = true
    invitation.value = null
    errorMessage.value = ''

    try {
      const response = await validateInvitationRequest(token)

      if (!response.ok) {
        errorMessage.value = 'Invitation is invalid or expired.'

        return false
      }

      const data = await response.json()

      invitation.value = data

      return true
    } catch (error) {
      errorMessage.value = 'Something went wrong.'

      return false
    } finally {
      loading.value = false
    }
  }

  const registerInvitedUser = async (registerData) => {
    loading.value = true
    successMessage.value = ''
    errorMessage.value = ''

    try {
      const response = await registerInvitedUserRequest(registerData)

      const data = await response.json()

      if (!response.ok) {
        errorMessage.value = data.message || 'Account could not be created.'

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

  return {
    loading,
    invitationLink,
    invitation,
    successMessage,
    errorMessage,
    createEmployeeInvitation,
    createSupportAgentInvitation,
    validateInvitation,
    registerInvitedUser,
  }
})
