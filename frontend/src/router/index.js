import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/signup",
    name: "SignUp",
    component: () => import("../views/Accounts/SignUp.vue"),
  },
  {
    path: "/signup/next",
    name: "SignUpSecond",
    component: () => import("../views/Accounts/SignUpSecond.vue"),
  },
  {
    path: "/place",
    name: "Place",
    component: () => import("../views/Place/Place.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
