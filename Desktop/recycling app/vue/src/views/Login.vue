<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <div class="login-form__logo-container">
        <img
          class="login-form__logo"
          src="../img/bottles.png"
          alt="Bottles in circle"
        />
      </div>
      <div class="login-form__content">
        <div class="login-form__header">Thanks for recycling with us!</div>
      </div>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <!-- <label class="container">
        <input type="radio" name="radio" class="radio" />
        <span class="checkmark">Login as Business Admin</span>
      </label> -->
      <br />
      <!--
      <label for="username" class="sr-only"><b>Username: </b></label> -->
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <br />
      <!--
      <label for="password" class="sr-only"><b>Password: </b></label> -->
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <br />

      <button class="login-form__button" type="submit">Login</button>
      <!-- <router-link :to="{ name: 'account'}">
      </router-link> -->
      <div class="login-form_links">
        <router-link :to="{ name: 'register' }" class="login-form_link"
          >Need an account?</router-link
        >
      </div>
      <!--
      <button type="submit">Sign in</button>
      -->
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  checked: false,
  data() {
    return {
      user: {
        username: "",
        password: "",
        // is_driver:'',
      },
      invalidCredentials: false,
    };
  },

  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            if (response.data.user.username === "admin") {
              this.$router.push("/admin");
            } 
            else if(response.data.user.is_driver === true){
              this.$router.push('/driver')
            }
            else {
              this.$router.push("/account");
            }
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>


<style scoped>
.text-center {
  margin: 0px;
  padding: 150px;
}

.form-signin,
.form-signin * {
  box-sizing: border-box;
  font-family: "Raleway", sans-serif;
  font-weight: 600;
}

.form-signin {
  background-color: white;
  max-width: 400px;
  height: 570px;
  margin: 0 auto;
  border-radius: 5px;
  overflow: hidden;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.15);
}

.login-form__logo-container {
  padding: 30px;
}

.login-form__logo {
  display: block;
  max-width: 125px;
  margin: 0 auto;
}

.login-form__content {
  padding: 30px;
  background: #eeeeee;
}

.login-form__header {
  margin-bottom: 15px;
  text-align: center;
  font-size: 20px;
  background: #eeeeee;
  margin-bottom: 1px;
}

.form-control {
  width: 95%;
  margin: 10px;
  padding: 10px 5px 10px 5px;
  border-radius: 5px;
  border: 2px solid #dddddd;
  background: #ffffff;
  outline: none;
  transition: border-color 0.5s;
}

.form-control:focus {
  border-color: #5dc06a;
}

.form-control::placeholder {
  color: #aaaaaa;
  font-size: 14px;
}

.login-form__button {
  padding: 10px;
  color: #ffffff;
  font-weight: bold;
  letter-spacing: .12em;
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

.login-form__button::before {
  transform: translate(-2%, -40%);
}


.login-form_links {
  margin-top: 15px;
  text-align: center;
}

.login-form_link {
  font-size: 0.9em;
  text-decoration: none;
}

.checkmark {
  font-size: 15px;
  margin: 10px;
}

.radio {
  margin-left: 15px;
  margin-top: 10px;
}
</style>
