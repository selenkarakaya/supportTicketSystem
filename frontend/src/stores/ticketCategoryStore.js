import { defineStore } from 'pinia'
import { ref } from 'vue'
import { getTicketCategoriesRequest } from '@/services/ticketCategoryService'

export const useTicketCategoryStore = defineStore('ticketCategory', () => {
  const categories = ref([])
  const loading = ref(false)
  const errorMessage = ref('')

  const getTicketCategories = async () => {
    loading.value = true
    errorMessage.value = ''

    try {
      const response = await getTicketCategoriesRequest()

      if (!response.ok) {
        categories.value = []
        errorMessage.value = 'Categories could not be loaded.'
        return false
      }

      categories.value = await response.json()
      return true
    } catch (error) {
      categories.value = []
      errorMessage.value = 'Something went wrong.'
      return false
    } finally {
      loading.value = false
    }
  }

  return {
    categories,
    loading,
    errorMessage,
    getTicketCategories,
  }
})
