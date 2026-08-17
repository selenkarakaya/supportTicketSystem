<template>
  <v-footer color="grey-lighten-5" class="border-t">
    <v-container fluid class="pa-6">
      <v-row>
        <v-col cols="12" sm="6" md="3">
          <div class="d-flex align-center ga-2 mb-3">
            <RouterLink :to="{ name: 'home' }" class="text-decoration-none">
              <div class="d-flex align-center ga-2">
                <v-avatar color="indigo" size="32" rounded="lg">
                  <v-icon color="white" size="20"> mdi-shield-check </v-icon>
                </v-avatar>

                <span class="text-h6 font-weight-bold text-indigo-darken-4"> TicketSystem </span>
              </div>
            </RouterLink>
          </div>

          <p class="text-body-2 text-blue-grey-darken-1 mb-4">
            A modern, easy-to-use support ticket system for teams that care about their customers.
          </p>

          <div class="d-flex ga-2">
            <v-btn icon="mdi-linkedin" variant="outlined" size="small" color="indigo" />

            <v-btn icon="mdi-twitter" variant="outlined" size="small" color="indigo" />

            <v-btn icon="mdi-facebook" variant="outlined" size="small" color="indigo" />

            <v-btn icon="mdi-web" variant="outlined" size="small" color="indigo" />
          </div>
        </v-col>

        <v-col cols="6" sm="3" md="2">
          <h3 class="text-subtitle-2 font-weight-bold mb-3">Product</h3>

          <v-list density="compact" bg-color="transparent" class="pa-0">
            <v-list-item title="Features" class="px-0" :to="{ name: 'home', hash: '#features' }" />
            <v-list-item title="Pricing" class="px-0" to="/pricing" />
            <v-list-item title="Integrations" class="px-0" />
            <v-list-item title="Status Page" class="px-0" />
          </v-list>
        </v-col>

        <v-col cols="6" sm="3" md="2">
          <h3 class="text-subtitle-2 font-weight-bold mb-3">Resources</h3>

          <v-list density="compact" bg-color="transparent" class="pa-0">
            <v-list-item title="Help Center" to="/contact" class="px-0" />
            <v-list-item title="Guides" class="px-0" />
            <v-list-item title="API Documentation" class="px-0" />
          </v-list>
        </v-col>

        <v-col cols="6" sm="3" md="2">
          <h3 class="text-subtitle-2 font-weight-bold mb-3">Company</h3>

          <v-list density="compact" bg-color="transparent" class="pa-0">
            <v-list-item title="About Us" class="px-0" />
            <v-list-item title="Blog" class="px-0" />
            <v-list-item title="Careers" class="px-0" />
            <v-list-item title="Privacy Policy" class="px-0" />
            <v-list-item title="Terms of Service" class="px-0" />
          </v-list>
        </v-col>

        <v-col cols="12" sm="6" md="3">
          <h3 class="text-subtitle-2 font-weight-bold mb-3">Contact Us</h3>

          <v-list density="compact" bg-color="transparent" class="pa-0">
            <v-list-item
              prepend-icon="mdi-email-outline"
              title="support@ticketsystem.com"
              href="mailto:support@ticketsystem.com"
              class="px-0"
            />

            <v-list-item
              prepend-icon="mdi-phone-outline"
              title="+44 1234 567890"
              href="tel:+441234567890"
              class="px-0"
            />

            <v-list-item
              prepend-icon="mdi-map-marker-outline"
              title="Southampton, UK"
              class="px-0"
            />
          </v-list>
        </v-col>
      </v-row>

      <v-divider class="my-6" />

      <p class="text-center text-caption text-blue-grey-darken-1 mb-0">
        © 2026 TicketSystem. All rights reserved.
      </p>
    </v-container>
  </v-footer>
</template>
