<template>
  <div class="container">
    <div id="driver-dash">
      <div id="navbar">
        Welcome to your dashboard
        <strong>{{ $store.state.user.username }}</strong
        >!
      </div>
    </div>
    <set-route></set-route>
  </div>
</template>

<script>
import PickupService from "../services/PickupService.js";
import SetRoute from "./SetRoute.vue";
//import AccountService from "../services/AccountService.js";
export default {
  name: "driver-dashboard",
  components: {
    SetRoute,
  },
  data() {
    return {
      users: {
        pickup_id: "",
        route_id: "",
        requesting_username: "",
        pickup_date: "",
        num_of_bins: "",
        is_picked_up: false,
        full_address: "",
      },
      user: "",
    };
  },
  // mounted() {
  //   this.SetRoute.directionsService();
  //   this.SetRoute.assignRouteOne();
  // },

  //this created can be seen only Admin, this getPickups( ) return all pickups information
  // created() {
  //   PickupService.getPickups().then((response) => {
  //     this.users = response.data;
  // this.SetRoute.assignRouteOne();

  //      created() {

  //   AccountService.getUserDetails().then((response) => {
  //     this.user = response.data;
  //   });
  // },
  methods: {
    getDriverPickups() {
      PickupService.getDriverPickUpRoutes(this.user).then((response) => {
        this.users = response.data;
      });
    },
  },
};
</script>

<style scoped>
/* #driver-dash {
  display: flex;
  align-items: center;
} */

#navbar {
  font-size: 25px;
  text-align: center;
  margin-top: 1em;
  padding: 1.5em 0;
  background-color: rgb(233, 233, 233);
}
</style>