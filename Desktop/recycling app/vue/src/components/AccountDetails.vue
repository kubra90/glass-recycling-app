<template>
  <div class="form-container">
    <form v-on:submit.prevent="saveAccountDetails" class="account-form">
      <div class="form-header">
        <h1>My Account</h1>
      </div>
      <!-- Username input -->
      <div class="form-item">
        <p>Username:</p>
        <input type="text" name="name" v-model="userDetails.username" />
      </div>
      <!-- <h3>{{$store.state.user.username}}</h3> -->
      <!-- Name input -->
      <div class="form-item">
        <p>Full Name:</p>
        <input type="text" name="fullname" v-model="userDetails.full_name" />
      </div>
      <!-- Phone input -->
      <div class="form-item">
        <p>Phone Number:</p>
        <input type="text" name="name" v-model="userDetails.phone_number" />
      </div>
      <!-- Email input -->
      <div class="form-item">
        <p>Email:</p>
        <input type="text" name="name" v-model="userDetails.email_address" />
      </div>
      <!-- Address input -->
      <div class="form-item">
        <p>Address:</p>
        <input
          type="text"
          name="name"
          placeholder="Street address"
          v-model="userDetails.street_address"
        />
        <div class="form-item">
          <input
            type="text"
            name="name"
            placeholder="City"
            v-model="userDetails.city"
          />
          <input
            type="text"
            name="name"
            placeholder="State"
            v-model="userDetails.state_abbreviation"
          />
          <input
            type="text"
            name="name"
            placeholder="Postal / Zip code"
            v-model="userDetails.zipcode"
          />
        </div>
      </div>
      <!-- Submit button -->
      <div class="btn-block">
        <button type="submit">Save</button>
        <button type="button" v-on:click="cancel()">Cancel</button>
      </div>
    </form>
  </div>
</template>

<script>
import AccountService from "../services/AccountService.js";
export default {
  name: "account-details",
  data() {
    return {
      userDetails: {
        username: "",
        full_name: "",
        street_address: "",
        city: "",
        state_abbreviation: "",
        zipcode: "",
        phone_number: "",
        email_address: "",
        // total_pounds_recycled:0,
        // credits_balance:0,
        // credits_redeemed:0,
      },
    };
  },

  created() {
    AccountService.getUserDetails().then((response) => {
      this.userDetails = Object.assign({}, response.data);
      console.log(response.data);
    });
  },
 
  methods: {
    saveAccountDetails() {
      AccountService.addUserDetails(this.userDetails).then((response) => {
        if (response.status === 201) {
          this.$router.push("/account");
        }
      });
    },
    cancel() {
      this.$router.push("/account");
    },
  },
};
</script>

<style scoped>
.form-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 100%;
  background-color: whitesmoke;
  border: 1px solid red;
}
/* need to fix gap under this */

h2 {
  font-weight: 400;
}

.form-header {
  text-align: center;
  margin-bottom: 2em;
}
.account-form {
  width: 450px;
  padding: 3em;
  margin-top: 2em;
  border-radius: 5px;
  background: white;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.15);
}

.form-input {
  background: #fafafa;
  border: 1px solid #eeeeee;
  padding: 12px;
  width: 100%;
}

.form-item {
  margin-bottom: 1em;
}

.form-container button {
  padding: 10px;
  color: #ffffff;
  font-weight: bold;
  letter-spacing: 0.12em;
  background: #4bc970;
  width: 100%;
  border: none;
  outline: none;
  border-radius: 5px;
  cursor: pointer;
  width: 85%;
  margin: 10px;
  padding: 10px 5px 10px 5px;
}

.form-container button::before {
  transform: translate(-2%, -40%);
}

input,
select {
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
}
input {
  width: calc(100% - 10px);
  padding: 5px;
}

select {
  width: 100%;
  padding: 7px 0;
  background: transparent;
}

.form-item input:hover,
.form-item select:hover {
  border: 1px solid #4bc970;
  box-shadow: 0 0 6px 0 #369151;
  font-family: "Raleway", sans-serif;
}

.btn-block {
  margin-top: 10px;
  text-align: center;
}
button {
  padding: 10px;
  color: #ffffff;
  font-weight: bold;
  letter-spacing: 0.12em;
  background: #4bc970;
  width: 100%;
  border: none;
  outline: none;
  border-radius: 5px;
  cursor: pointer;
  width: 95%;
  margin: 10px;
  padding: 10px 5px 10px 5px;
}

@media (min-width: 568px) {
  .name-item,
  .city-item {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
  }
  .name-item input,
  .city-item input {
    width: calc(50% - 20px);
  }
  .city-item select {
    width: calc(50% - 8px);
  }
}
</style>