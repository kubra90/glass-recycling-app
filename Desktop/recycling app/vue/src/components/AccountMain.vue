<!--
This will be used for user's account page after logged in!
Three columns( history, calendar, account)

-->

<template>
  <div class="account">
    <div class="my-account">
      <router-link :to="{ name: 'account-detail' }">My Account</router-link>
      <h4>{{ $store.state.user.username }}</h4>
    </div>
    <button class="pickup-button" v-on:click="showForm = !showForm">
      Schedule Pick Up
    </button>
    <div v-show="showForm">
      <pick-up></pick-up>
    </div>

    <!-- <div class="history">
      <button>My Recycling History</button>
    </div> -->

    <div>
      <h2>My Recycling History</h2>
      <history></history>
    </div>

    <div>
      <h2>Available Prizes</h2>
      <prize-cards></prize-cards>
    </div>
  </div>
</template>

<script>
import AccountService from "../services/AccountService.js";
import PickUp from "../components/PickUp.vue";
import History from "../components/History.vue";
import PickupService from "../services/PickupService";
import PrizeCards from "../components/PrizeCards.vue";

export default {
  name: "account-main",
  components: {
    PickUp,
    History,
    PrizeCards,
  },
  data() {
    return {
      user: "",
      // expanded: false,
      showForm: false,
    };
  },

  //this created method update the user after we calling the method. then we can retrive the information that w want to here
  created() {
    // after saving account details, the form should be the same.
    // get address
    AccountService.getUserDetails().then((response) => {
      this.user = response.data;
    });
  },
  methods: {
    getPickups() {
      PickupService.getMyPickups(this.requesting_username).then((response) => {
        this.myPickups = response.data;
      });
    },
  },
};
</script>

<style scoped>
.account {
  display: flex;
  height: 800px;
  flex-flow: column wrap;
  align-items: center;
  font-size: 18px;
  /* width: 200px; */
}

.my-account h4 {
  font-size: 0.75em;
}

.my-account,
.pick-up {
  /* text-transform: uppercase; */
  font-size: 1.5em;
  padding: 1em;
  background-color: whitesmoke;
  /* height: 40px; */
  width: 100%;
  word-spacing: 1px;
  line-height: 0.5em;
  height: 2em; /* height is 2x line-height, so two lines will display */
  overflow: hidden;
  margin-bottom: 6px;
  text-align: center;
}

.pickup-button {
  font-size: 1em;
  font-weight: bold;
  letter-spacing: 0.07em;
  margin: 1.2em 0 1em;
  width: 12.5em;
  border: none;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.25);
}

h2 {
  text-align: center;
}

/* .history {
  font-size: 1em;
  font-weight: bold;
  letter-spacing: 0.07em;
  margin: 1.2em 0 1em;
  width: 12.5em;
  border: none;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.25);
} */
</style>