const DEPARTMENT_API_URL = `${import.meta.env.VITE_API_URL}/departments`

export const getDepartmentsByCompanyRequest = async (companyId) => {
  return fetch(`${DEPARTMENT_API_URL}/company/${companyId}`, {
    method: 'GET',
  })
}
