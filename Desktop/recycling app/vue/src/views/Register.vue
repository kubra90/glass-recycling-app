<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <div class="login-form__logo-container">
        <img class="login-form__logo" src="../img/bottles.png" alt="Logo" />
      </div>
      <div class="login-form__content">
        <div class="login-form__header">Create Account</div>
      </div>

      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />

      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />

      <button class="login-form__button" type="submit">Create Account</button>
      <div class="login-form_links">
        <router-link :to="{ name: 'login' }">Have an account?</router-link>
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
        is_driver: false,

        // admin action addidng driver then it turns to true       driver: false,
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg =
                "A user with that username already exists!";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
.text-center {
  margin: 0px;
  padding: 150px;
}

.form-register,
.form-register * {
  box-sizing: border-box;
  font-family: "Raleway", sans-serif;
  font-weight: 600;
}

.form-register {
  max-width: 400px;
  height: 600px;
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

.alert {
  padding-left: 10px;
  padding-top: 5px;
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
  border-color: #4bc970;
}

.form-control::placeholder {
  color: #aaaaaa;
  font-size: 14px;
}

.login-form__button {
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
</style>
