# Support Ticket System – Frontend

The frontend application for the Support Ticket System, built with Vue 3 and Vite.

## Technologies

- Vue 3
- Vite
- Vue Router
- Pinia
- ESLint
- Prettier

## Project Setup

1- Open the `frontend` directory:

```sh
cd frontend
```

2- Install the dependencies

```sh
npm install
```

3-Run Prettier to format the code:

```sh
npm run format
```

4-Start the development server:

```sh
npm run dev
```

### Production Build

Compile and minify the application for production:

```sh
npm run build
```

### Linting

Run ESLint to check the code:

```sh
npm run lint
```

## Project Structure

frontend/
├── public/
├── src/
│ ├── assets/
│ ├── components/
│ ├── router/
│ ├── stores/
│ ├── views/
│ ├── App.vue
│ └── main.js
├── package.json
└── vite.config.js
Backend

#### This frontend application will be connected to a Spring Boot backend.

src/
├── components/
│ ├── common/
│ ├── dashboard/
│ ├── knowledge-base/
│ ├── layout/
│ └── tickets/
│
├── views/
│ ├── public/
│ │ ├── HomeView.vue
│ │ ├── PricingView.vue
│ │ ├── ContactView.vue
│ │ ├── LoginView.vue
│ │ └── RegisterView.vue
│ │
│ └── dashboard/
│ ├── DashboardView.vue
│ ├── MyTicketsView.vue
│ ├── CreateTicketView.vue
│ ├── TicketDetailView.vue
│ ├── KnowledgeBaseView.vue
│ ├── AnnouncementsView.vue
│ ├── ContactSupportView.vue
│ └── ProfileView.vue
│
└── router/
└── index.js
