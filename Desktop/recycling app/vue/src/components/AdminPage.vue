<template>
  <div class="container">
    <h2>Welcome to your Dashboard!</h2>
    <div class="map">
      <locations></locations>
    </div>

    <div>
      <driver-table class="driver-main">driver</driver-table>
    </div>

    <div id="card-pickup">
      <!-- does this need to be a button? -->
      <h3>Pickups</h3>
      <table id="tbl-pickups">
        <thead id="tbl-head-pickups">
          <tr>
            <th>&nbsp;</th>
            <th>Pickup ID</th>
            <th>Route ID</th>
            <th>Requester</th>
            <th>Date</th>

            <th>Number of Bins</th>
            <th>Status</th>
            <th>Address</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>
              <!-- this method is working! -->
              <input
                type="checkbox"
                id="selectAll"
                v-on:change="selectAllUsers($event)"
              />
            </td>
            <td>
              <input
                type="text"
                id="firstNameFilter"
                v-model="filter.pickup_id"
              />
            </td>
            <td>
              <input
                type="text"
                id="lastNameFilter"
                v-model="filter.route_id"
              />
            </td>
            <td>
              <input
                type="text"
                id="usernameFilter"
                v-model="filter.requesting_username"
              />
            </td>
            <td>
              <input
                type="text"
                id="emailFilter"
                v-model="filter.pickup_date"
              />
            </td>
            <td>
              <select id="statusFilter" v-model="filter.num_of_bins">
                <option value>All Bins</option>
                <option value="Active">1</option>
                <option value="Disabled">2</option>
                <option value="">3</option>
              </select>
            </td>
            <td>&nbsp;</td>
            <td>
              <input type="text" id="AddressFilter" v-model="filter.Address" />
            </td>
          </tr>
          <tr
            v-for="user in users"
            v-bind:key="user.pickup_id"
            v-bind:class="{ disabled: user.is_picked_up === 'Not Picked Up' }"
          >
            <td>
              <input
                type="checkbox"
                name="selectedUsers"
                v-model="selectedUserIDs"
                v-bind:checked="selectedUserIDs.includes(user)"
                v-bind:value="user"
              />
            </td>
            <td>{{ user.pickup_id }}</td>
            <td>{{ user.route_id }}</td>
            <td>{{ user.requesting_username }}</td>
            <td>{{ user.pickup_date }}</td>
            <td>{{ user.num_of_bins }}</td>
            <td>
              <button class="btnEnableDisable" @click="updatePickup">
                {{
                  user.is_picked_up === "Not Picked Up"
                    ? "Picked Up"
                    : "Not Picked Up"
                }}
              </button>
            </td>
            <td>{{ user.full_address }}</td>
          </tr>
        </tbody>
      </table>
      <div class="all-actions">
        <button
          v-bind:disabled="actionButtonDisabled"
          v-on:click.prevent="deletePickups()"
        >
          Delete Pickup
        </button>

        <!--one button for assigning route id  
        route_id is type of number-->
        <button v-on:click="showForm = !showForm">Assign Route</button>
        <form id="frmAddNewDriver" v-show="showForm">
          <div class="field">
            <label for="routeID">RouteId:</label>
            <input type="number" name="routeID" v-model="newRouteId" />
          </div>
          <!-- <button type="submit" class="btn save" v-on:click.prevent="addDriver">
          Save Route
        </button> -->

          <button type="submit" class="btn.save"
            v-on:click.prevent="updatePickup"
          >
            Save Route
          </button>
        </form>
      </div>
      <set-route></set-route>
    </div>
    <div>
      <prize-details>prizes</prize-details>
    </div>
  </div>
</template>

<script>
import Locations from "./Locations.vue";
import PickupService from "../services/PickupService.js";
import SetRoute from "./SetRoute.vue";
import DriverTable from "../components/DriverTable.vue";
import PrizeDetails from "../components/PrizeDetails.vue";

export default {
  name: "admin-page",
  components: {
    Locations,
    SetRoute,
    DriverTable,
    PrizeDetails,
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
        pickup_weight: 0,
      },
      newRouteId: 0,

      showForm: false,
      selectedUserIDs: [],
      filter: {
        pickup_id: "",
        route_id: "",
        requesting_username: "",
        pickup_date: "",
        num_of_bins: "",
        is_picked_up: false,
        full_address: "",
      },
      // nextUserId: 7,
      newUser: {
        id: null,
        firstName: "",
        lastName: "",
        username: "",
        emailAddress: "",
        status: "Active",
      },
    };
  },

  created() {
    //get all users unassigned pickup data here
    PickupService.getPickups().then((response) => {
      this.users = response.data;
    });
  },

  methods: {
    //admin needs to delete pickups
    deletePickups() {
      if (
        confirm(
          "Are you sure you want to delete this card? This action cannot be undone."
        )
      ) {
        for (let i = 0; i < this.selectedUserIDs.length; i++) {
          PickupService.deletePickup(this.selectedUserIDs[i].pickup_id)
            .then((response) => {
              if (response.status === 204) {
                alert("pickup successfully deleted");
                // this.$router.push('/admin')
                PickupService.getPickups().then((response) => {
                  this.users = response.data;
                });
              }
            })
            .catch((error) => {
              if (error.response) {
                this.errorMsg =
                  "Error deleting pickup. Response received was '" +
                  error.response.statusText +
                  "'.";
              } else if (error.request) {
                this.errorMsg =
                  "Error deleting pickup. Server could not be reached.";
              } else {
                this.errorMsg =
                  "Error deleting pickup. Request could not be created.";
              }
            });
        }
      }
    },

    updatePickup() {
      for (let i = 0; i < this.selectedUserIDs.length; i++) {
        this.selectedUserIDs[i].route_id = parseInt(this.newRouteId);
        PickupService.updatePickup(this.selectedUserIDs[i]).then((response) => {
          if (response.status === 200) {
            alert("successful");
            this.showForm = false;
            //response, how can detect the error and fix inside the code.
          }else{
            alert("unsuccessful");
          }
        });
      }
      this.showForm = false;
      this.selectedUserIDs = [];
    },

    flipStatus(pickup_id) {
      this.users.forEach((user) => {
        if (user.pickup_id === pickup_id) {
          if (user.is_picked_up === "Not Picked Up") {
            user.is_picked_up === " Picked Up";
          } else {
            user.is_picked_up = "Not Picked Up";
          }
        }
      });
    },
    enabledSelectedUsers() {
      this.changeStatus("Active");
    },
    disableSelectedUsers() {
      this.changeStatus("Disabled");
    },
    deleteSelectedUsers() {
      this.changeStatus("Delete");
    },
    changeStatus(statusToChange) {
      for (let i = 0; i < this.selectedUserIDs.length; i++) {
        for (let j = 0; j < this.users.length; j++) {
          if (this.users[j].pickup_id === this.selectedUserIDs[i]) {
            if (
              statusToChange === "Not Picked Up" ||
              statusToChange === "Picked Up"
            ) {
              this.users[j].is_picked_up = statusToChange;
            } else if (statusToChange === "Delete") {
              this.users.splice(i, 1);
            }
          }
        }
      }
    },
    selectAllUsers(event) {
      if (event.target.checked) {
        this.selectedUserIDs = [];
        for (let i = 0; i < this.users.length; i++) {
          this.selectedUserIDs.push(this.users[i].pickup_id);
        }
      } else {
        this.selectedUserIDs = [];
      }
    },
  },
  computed: {
    actionButtonDisabled() {
      if (this.selectedUserIDs.length === 0) {
        return true;
      } else {
        return false;
      }
    },
    filteredList() {
      let filteredUsers = this.users;
      if (this.filter.firstName != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.firstName
            .toLowerCase()
            .includes(this.filter.firstName.toLowerCase())
        );
      }
      if (this.filter.lastName != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.lastName
            .toLowerCase()
            .includes(this.filter.lastName.toLowerCase())
        );
      }
      if (this.filter.username != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.username
            .toLowerCase()
            .includes(this.filter.username.toLowerCase())
        );
      }
      if (this.filter.emailAddress != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.emailAddress
            .toLowerCase()
            .includes(this.filter.emailAddress.toLowerCase())
        );
      }
      if (this.filter.status != "") {
        filteredUsers = filteredUsers.filter(
          (user) => user.status === this.filter.status
        );
      }
      return filteredUsers;
    },
  },
};
</script>

<style scoped>
.container {
  padding: 0.75em;
  margin: 4em;
  text-align: center;
}

h2 {
  font-size: 3em;
  text-transform: uppercase;
  text-align: center;
}

#card-pickup {
  display: flex;
  align-items: center;
  align-content: center;
  flex-direction: column;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  box-sizing: border-box;
  padding: 20px;
  margin-top: 5em;
  border-radius: 3em;
}

#card-pickup button {
  font-size: 1em;
  font-weight: bold;
  letter-spacing: 0.07em;
  border-radius: 25px;
  border: none;
  padding: 10px 25px;
  margin: 1.2em 1.5em 2em;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.25);
}

table {
  border-collapse: collapse;
  border: 1px solid rgb(233, 233, 233);
  background-color: rgb(233, 233, 233);
  font-weight: 600;
}

#tbl-pickups button {
  padding: 5px 10px;
  margin: 0.5em;
  box-shadow: 0 3px 10px rgba(0, 0, 0, 0.25);
  border: none;
}

th {
  text-transform: uppercase;
  letter-spacing: 0.35em;
  /* bold not working? */
  font-weight: bold;
  background-color: rgb(93, 192, 106);
  padding: 1em;
}

td input {
  border: 1px solid white;
  width: 8em;
  margin: 0.4em;
  border-radius: 1em;
}

td {
  padding: 7px;
}

tr {
  margin: 1px;
}

tr:nth-child(even) {
  background-color: white;
}

/* when picked up */
tr.disabled {
  color: rgb(47, 211, 25);
}

input,
select {
  font-family: "Raleway", sans-serif;
  font-size: 14px;
  padding: 5px;
}

/* #frmAddNewPickup {
  display: flex;
  align-items: center;
  justify-content: space-around;
} */

.field {
  padding: 10px;
}

label {
  padding-right: 5px;
  display: inline-block;
}

button {
  border: 1px solid white;
  background-color: white;
  padding: 6px 20px;
  text-align: center;
  font-size: 0.8em;
  text-decoration: none;
  display: inline-block;
  margin: 6px 4px;
  border-radius: 8px;
}

.all-actions {
  margin-bottom: 20px;
}

.btn.save {
  margin: 10px;
  float: right;
}

/* .map {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  box-sizing: border-box;
  grid-area: map;
  padding: 20px 0;
  background-color: white;
} */
</style>