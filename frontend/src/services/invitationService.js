const INVITATION_API_URL = `${import.meta.env.VITE_API_URL}/invitations`

export const createEmployeeInvitationRequest = async () => {
  return fetch(`${INVITATION_API_URL}/employee`, {
    method: 'POST',
    credentials: 'include',
  })
}

export const createSupportAgentInvitationRequest = async () => {
  return fetch(`${INVITATION_API_URL}/support-agent`, {
    method: 'POST',
    credentials: 'include',
  })
}

export const validateInvitationRequest = async (token) => {
  return fetch(`${INVITATION_API_URL}/validate?token=${encodeURIComponent(token)}`, {
    method: 'GET',
  })
}

export const registerInvitedUserRequest = async (registerData) => {
  return fetch(`${INVITATION_API_URL}/register`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(registerData),
  })
}
