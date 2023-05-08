<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <form v-on:submit.prevent="addPickUp" >
          <h3>Schedule Pick Up</h3>

          <fieldset>
            <label for="name">Name:</label>
            <input
              type="text"
              id="name"
              name="user_name"
              v-model="pickup.requesting_username"
            />

            <label for="email">Date:</label>
            <input
              type="date"
              id="email"
              name="date"
              v-model="pickup.pickup_date"
            />
            <label for="job">Number of Bins:</label>
            <select id="job" name="user_job" v-model="pickup.num_of_bins">
              <optgroup label="">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
              </optgroup>
            </select>
          </fieldset>
          <button type="submit" >Submit</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import PickupService from "../services/PickupService.js";

export default {
  name: "pick-up",
  data() {
    return {
      pickup: {
        requesting_username: "",
        pickup_date: "",
        num_of_bins: "",
        is_picked_up: false,
      },
      showForm: false,
      msg:'',
    };
  },
  methods: {
    addPickUp() {
      PickupService.addPickup(this.pickup).then((response) => {
        if (response.status === 201) {
          this.$router.push("/account");
        }
      }
      ).catch((error)=> {
        if(error.response){
          this.msg=' Error in scheduling pick up, please try another date'
          alert(this.msg)
        }
      })
  }
  }
}
</script>

<style scoped>
*,
*:before,
*:after {
  box-sizing: border-box;
}

form {
  width: 400px;
  margin: 10px auto;
  padding: 10px 20px;
  background: whitesmoke;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.15);
}

h1 {
  margin: 0 0 30px 0;
  text-align: center;
}

input[type="text"],
input[type="date"],
input[type="search"],
input[type="url"],
textarea,
select {
  /* background: rgba(255, 255, 255, 0.041); */
  border: none;
  font-size: 16px;
  font-family: "Raleway", sans-serif;
  height: auto;
  margin: 0;
  outline: 0;
  padding: 15px;
  width: 100%;
  background-color: #e6e6e6;
  color: #9e9e9e;
  box-shadow: 0 1px 0 rgba(0, 0, 0, 0.03) inset;
  margin-bottom: 30px;
}

select {
  padding: 6px;
  height: 32px;
  border-radius: 2px;
}

button {
  padding: 19px 39px 18px 39px;
  color: #fff;
  background-color: #4bc970;
  font-size: 18px;
  text-align: center;
  font-weight: bold;
  letter-spacing: 0.12em;
  border-radius: 5px;
  width: 80%;
  margin: 0 2em 3em 2em;
}

button::before {
  transform: translate(-25%, -40%);
}

fieldset {
  margin-bottom: 30px;
  border: none;
}

legend {
  font-size: 1.4em;
  margin-bottom: 10px;
}

label {
  display: block;
  margin-bottom: 8px;
}

label.light {
  font-weight: 300;
  display: inline;
}

/* .number {
  background-color: #5fcf80;
  color: #fff;
  height: 30px;
  width: 30px;
  display: inline-block;
  font-size: 0.8em;
  margin-right: 4px;
  line-height: 30px;
  text-align: center;
  text-shadow: 0 1px 0 rgba(255, 255, 255, 0.2);
  border-radius: 100%;
} */

@media screen and (min-width: 480px) {
  form {
    max-width: 480px;
  }
}
</style>
