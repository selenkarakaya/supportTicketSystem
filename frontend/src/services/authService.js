const AUTH_API_URL = `${import.meta.env.VITE_API_URL}/auth`

export const registerCompanyRequest = async (registerData) => {
  const response = await fetch(`${AUTH_API_URL}/register-company`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(registerData),
  })

  return response
}

export const loginUserRequest = async (loginData) => {
  const response = await fetch(`${AUTH_API_URL}/login-user`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    credentials: 'include',
    body: JSON.stringify(loginData),
  })

  return response
}
export const logoutUserRequest = async () => {
  return fetch(`${AUTH_API_URL}/logout`, {
    method: 'POST',
    credentials: 'include',
  })
}
export const getCurrentUserRequest = async () => {
  return fetch(`${AUTH_API_URL}/me`, {
    method: 'GET',
    credentials: 'include',
  })
}
