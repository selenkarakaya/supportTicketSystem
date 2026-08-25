const API_URL = import.meta.env.VITE_API_URL

export const getTicketCategoriesRequest = () => {
  return fetch(`${API_URL}/ticket-categories`, {
    method: 'GET',
    credentials: 'include',
  })
}
