const USER_API_URL = `${import.meta.env.VITE_API_URL}/users`

// Get all users belonging to logged-in admin's company
export const getCompanyUsersRequest = async () => {
  return fetch(USER_API_URL, {
    method: 'GET',
    credentials: 'include',
  })
}

// Get users belonging to a department
export const getUsersByDepartmentRequest = (departmentId) => {
  return fetch(`${USER_API_URL}/department/${departmentId}`, {
    method: 'GET',
    credentials: 'include',
  })
}
