const AUTH_API_URL = `${import.meta.env.VITE_API_URL}/auth`

export const registerCompanyRequest = async (registerData) => {
  const response = await fetch(`${AUTH_API_URL}/register-company`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(registerData),
  })

  return response
}
