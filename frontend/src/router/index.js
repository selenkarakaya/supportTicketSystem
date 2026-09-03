import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '@/stores/authStore'

import HomeView from '../views/HomeView.vue'
import PricingView from '../views/PricingView.vue'
import ContactView from '../views/ContactView.vue'
import RegisterView from '../views/RegisterView.vue'
import LoginView from '../views/LoginView.vue'
import ProfileView from '../views/ProfileView.vue'
import DashboardView from '../views/DashboardView.vue'
import EmployeeManagementView from '../views/EmployeeManagementView.vue'
import CreateTicketView from '../views/tickets/CreateTicketView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/dashboard',
      name: 'dashboard',
      component: DashboardView,
    },
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/pricing',
      name: 'pricing',
      component: PricingView,
    },

    {
      path: '/contact',
      name: 'contact',
      component: ContactView,
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterView,
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView,
    },
    {
      path: '/dashboard/profile',
      name: 'profile',
      component: ProfileView,
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: '/register-employee',
      name: 'register-employee',
      component: () => import('@/views/InvitedUserRegisterView.vue'),
    },

    {
      path: '/register-support-agent/',
      name: 'register-support-agent',
      component: () => import('@/views/InvitedUserRegisterView.vue'),
    },

    {
      path: '/employees',
      name: 'employees',
      component: EmployeeManagementView,
    },

    {
      path: '/create-ticket',
      name: 'create-ticket',
      component: CreateTicketView,
    },
    {
      path: '/my-tickets',
      name: 'my-tickets',
      component: () => import('@/views/tickets/MyTicketsView.vue'),
      meta: {
        requiresAuth: true,
      },
    },
  ],
})

router.beforeEach(async (to) => {
  const authStore = useAuthStore()

  if (to.meta.requiresAuth) {
    const authenticated = await authStore.checkAuth()

    if (!authenticated) {
      return {
        name: 'login',
      }
    }
  }
})
export default router
