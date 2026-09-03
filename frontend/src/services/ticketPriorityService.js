const API_URL = import.meta.env.VITE_API_URL

export const getTicketPrioritiesRequest = () => {
  return fetch(`${API_URL}/ticket-priorities`, {
    method: 'GET',
    credentials: 'include',
  })
}
