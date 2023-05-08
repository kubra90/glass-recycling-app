<template>
  <div id="card-driver">
    <h3>Drivers</h3>
    <table id="tbl-drivers">
      <thead id="tbl-head-drivers">
        <tr>
          <th>&nbsp;</th>
          <th>Driver Id</th>
          <th>Username</th>
          <th>Home Office Address</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>
            <input
              type="checkbox"
              id="selectAll"
              v-on:change="selectAllDrivers($event)"
            />
          </td>
          <td>
            <input
              type="text"
              id="firstNameFilter"
              v-model="filter.driver_id"
            />
          </td>
          <td>
            <input type="text" id="lastNameFilter" v-model="filter.username" />
          </td>
          <td>
            <input
              type="text"
              id="address"
              v-model="filter.home_office_address"
            />
          </td>
        </tr>
        <tr v-for="(driver, key) in filteredList" v-bind:key="key">
          <td>
            <input
              type="checkbox"
              v-model="selectedUserIDs"
              v-bind:checked="
                selectedUserIDs.includes(Number.parseInt(driver.driver_id))
              "
              v-bind:value="parseInt(driver.driver_id)"
            />
          </td>
          <td>{{ driver.driver_id }}</td>
          <td>{{ driver.username }}</td>
          <td>{{ driver.home_office_address }}</td>
        </tr>
      </tbody>
    </table>
    <div id="all-actions">
      <button
        v-on:click.prevent="deleteDriver()"
      >
        Delete Driver
      </button>

      <button v-on:click="showForm = !showForm">Add New Driver!</button>
      <form id="frmAddNewDriver" v-show="showForm">
        <div class="field">
          <label for="firstName">Username:</label>
          <input type="text" name="firstName" v-model="newDriver.username" />
        </div>
        <div class="field">
          <label for="firstName">Password:</label>
          <input type="text" name="firstName" v-model="newDriver.password" />
        </div>
        <div class="field">
          <label for="firstName">confirmPassword:</label>
          <input
            type="text"
            name="firstName"
            v-model="newDriver.confirmPassword"
          />
        </div>
        <div class="field">
          <label for="lastName">Status:</label>
          <input type="text" name="lastName" v-model="newDriver.is_driver" />
        </div>
        <button type="submit" class="btn save" v-on:click.prevent="addDriver">
          Save Driver
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import DriverService from "../services/DriverService.js";
export default {
  name: "driver-table",
  data() {
    return {
      drivers: [],
      selectedUserIDs: [],
      showForm: false,
      errorMsg: '',
      filter: {
        driver_id: "",
        username: "",
        home_office_address: "",
      },

      newDriver: {
        username: "",
        password: "",
        confirmPassword: "",
        is_driver: true,
        role: "user",
        home_office_address: "3001 Railroad St, Pittsburgh, PA 15201",
      },
    };
  },

  //created method to get all updated driver list
  created() {
    DriverService.getAllDrivers().then((response) => {
      this.drivers = response.data;
    });
  },

  methods: {
    //delete driver
    deleteDriver() {
      if (
        confirm(
          "Are you sure you want to delete this driver? This action cannot be undone."
        )
      ) {
        for (let i = 0; i < this.selectedUserIDs.length; i++) {
          DriverService.deleteDriver(this.selectedUserIDs[i])
            .then((response) => {
              if (response.status === 204) {
                //alert("driver successfully deleted")
                DriverService.getAllDrivers().then((response) => {
                  this.drivers = response.data;
                });
              }
            })
            .catch((error) => {
              if (error.response) {
                const statusCode = error.response.statusCode;
                this.errorMsg = `ERROR: delete driver returned ${statusCode}`;
              } else if(error.request){
                this.errorMsg = 'ERROR: server not found';
              } else {
                this.errorMsg = 'ERROR: unable to send request';
              }
            });
        }
        alert("driver successfully deleted");
      }
    },

    //add new driver to the table
    addDriver() {
      DriverService.addDriver(this.newDriver).then((response) => {
        if (response.status === 201) {
          // this.$router.push("/admin");
          this.showForm = false;
          DriverService.getAllDrivers().then((response) => {
            this.drivers = response.data;
          });
        }
      });
    },
    selectAllDrivers(event) {
      if (event.target.checked) {
        this.selectedUserIDs = [];
        for (let i = 0; i < this.drivers.length; i++) {
          this.selectedUserIDs.push(this.drivers[i].driver_id);
        }
      } else {
        this.selectedUserIDs = [];
      }
    },
  },
  computed: {
  
    filteredList() {
      let filteredUsers = this.drivers;
      if (this.filter.driver_id != "") {
        filteredUsers = filteredUsers.filter(
          (driver) => driver.driver_id == Number.parseInt(this.filter.driver_id)
        );
      }
      if (this.filter.username != "") {
        filteredUsers = filteredUsers.filter((driver) =>
          driver.username
            .toLowerCase()
            .includes(this.filter.username.toLowerCase())
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

#card-driver {
  display: flex;
  align-items: center;
  align-content: center;
  flex-direction: column;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  box-sizing: border-box;
  padding: 20px;
  border-radius: 3em;
}

#card-driver button {
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
  background-color: rgb(233, 233, 233);
  font-weight: 600;
  width: 97.5%;
}

/* #tbl-head-drivers {
  padding: 10px;
} */

th {
  text-transform: uppercase;
  letter-spacing: 0.35em;
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

/* tr.disabled {
  color: red;
} */

input,
select {
  font-family: "Raleway", sans-serif;
  font-size: 14px;
  padding: 5px;
}

#frmAddNewDriver {
  display: flex;
  align-items: center;
  justify-content: space-around;
}

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

.btn save {
  margin: 10px;
  float: right;
}
</style>
