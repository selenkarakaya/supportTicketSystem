const API_URL = `${import.meta.env.VITE_API_URL}/tickets`

export const createTicketRequest = (ticketData) => {
  return fetch(`${API_URL}/create-ticket`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    credentials: 'include',
    body: JSON.stringify(ticketData),
  })
}
export const getTicketsRequest = () => {
  return fetch(API_URL, {
    method: 'GET',
    credentials: 'include',
  })
}
