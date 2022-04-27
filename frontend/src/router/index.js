import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/login",
    name: "Login",
    component: () => import("../views/Accounts/Login.vue"),
  },
  {
    path: "/place",
    name: "Place",
    component: () => import("../views/Place/Place.vue"),
  },
  {
    path: "/placesearch",
    name: "PlaceSearch",
    component: () => import("../views/PlaceSearch/PlaceSearch.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
