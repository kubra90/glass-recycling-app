<template>
  <div id="app">
    <!-- Nav container -->
    <div class="toggle">
      <i class="fa-solid fa-bars" @click="showMenu()"></i>
    </div>
    <nav :class="this.showMobileMenu ? 'open-menu' : 'closed-menu'">
      <img id="logo" src="./img/logo-clear.png" alt="Vitrum Recycling Logo" />
      <ul>
        <li>
          <router-link v-bind:to="{ name: 'home' }"
            ><i class="fa-solid fa-house"></i
          ></router-link>
        </li>
        <li>About Us</li>
        <li>Services</li>
        <li>Schedule Pickup</li>
        <li>Contact Us</li>
      </ul>
      <div class="search-container">
        <input type="checkbox" id="check" />
        <div class="search-box">
          <input type="text" placeholder="Search..." />
          <label for="check" class="icon">
            <i class="fa-solid fa-magnifying-glass"></i>
          </label>
        </div>
      </div>
      <!-- <div class="spacer">&nbsp;</div> -->
      <div class="dropdown">
        <!--I need to change name to "account-detail" because of I can't see the account form"Kubra-->
        <router-link v-bind:to="{ name: 'account' }"
          >Account<i class="fa-solid fa-user"></i
        ></router-link>

    
        <div class="dropdown-content">
          <router-link id="login" v-bind:to="{ name: 'login' }">
            Login</router-link
          >
          <router-link
            id="logout"
            v-bind:to="{ name: 'logout' }"
            v-if="$store.state.token != ''"
            >Logout</router-link
          >
          <!-- for some reason, with the v-if 'logout' doesn't show on dropdown -->
        </div>
      </div>
    </nav>
    <div class="main-view">
      <router-view />
    </div>
    <footer>
      <!-- Footer main -->
      <section class="footer-main">
        <div class="footer-main-item">
          <h3 class="footer-title">About</h3>
          <ul>
            <li>Services</li>
            <li>Pricing</li>
            <li>Customers</li>
            <li>Careers</li>
          </ul>
        </div>
        <div class="footer-main-item">
          <h3 class="footer-title">Resources</h3>
          <ul>
            <li>Be a better recycler</li>
            <li>How to recycle</li>
            <li>What to recycle</li>
            <li>Recycle an item</li>
          </ul>
        </div>
        <div class="footer-main-item">
          <h3 class="footer-title">Contact</h3>
          <ul>
            <li>Help</li>
            <li>News</li>
            <li>FAQs</li>
            <li>Volunteer</li>
          </ul>
        </div>
        <div class="footer-main-item">
          <h3 class="footer-title">Stay Updated</h3>
          <p>Subscribe to our newsletter to get the latest recycling news!</p>
          <form>
            <input
              type="email"
              name="email"
              placeholder="Enter email address"
            />
            <input type="submit" value="Subscribe" />
          </form>
        </div>
      </section>
      <!-- Footer socials -->
      <section class="footer-social">
        <ul class="footer-social-list">
          <li><i class="fa-brands fa-facebook"></i></li>
          <li><i class="fa-brands fa-twitter"></i></li>
          <li><i class="fa-brands fa-instagram"></i></li>
        </ul>
      </section>
      <!-- Footer legal -->
      <section class="footer-legal">
        <ul class="footer-legal-list">
          <li>Terms &amp; Conditions</li>
          <li>Privacy Policy</li>
          <li>&copy; 2023 Copyright Vitrum Inc.</li>
        </ul>
      </section>
    </footer>
  </div>
</template>
<script>
export default {
  data() {
    return {
      showMobileMenu: false,
    };
  },

  methods: {
    showMenu() {
      this.showMobileMenu = !this.showMobileMenu;
    },
  },
};
</script>

<style scope>
@import url("https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap");

html {
  background: white;
  font-family: "Raleway", sans-serif;
  margin: 0;
  padding: 0;
}

body {
  margin: 0;
}
template {
  display: flex;
  min-height: 100vh;
  flex-direction: column;
}

#app {
  flex: 1;
}

#logo {
  float: left;
  padding-left: 10px;
  height: auto;
  width: 15%;
}

.main-view {
  margin-top: 5%;
  margin-bottom: 5%;
}

nav {
  background-color: white;
  position: fixed;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  font-size: 20px;
  padding: 10px 30px;
  width: 100vw;
  left: 0;
  top: 0;
  z-index: 2;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}

nav ul {
  display: flex;
  margin: 0;
  padding: 0;
  list-style: none;
}

nav li {
  font-size: 20px;
  font-weight: 600;
  padding: 0 20px 0 10px;
  margin-left: 10px;
  color: #0b7a1e;
}

.search-container {
  max-width: 400px;
  width: 100%;
}

.search-container .search-box {
  position: relative;
  height: 40px;
  max-width: 40px;
  margin: auto;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.25);
  border-radius: 25px;
  transition: all 0.3s ease;
}

#check:checked ~ .search-box {
  max-width: 300px;
}

.search-box input {
  font-family: "Raleway", sans-serif;
  position: absolute;
  height: 94%;
  width: 50%;
  border-radius: 25px;
  background: white;
  outline: none;
  border: none;
  padding-left: 20px;
  font-size: 14px;
}

.search-box .icon {
  position: absolute;
  right: -2px;
  top: 0;
  width: 40px;
  height: 100%;
  text-align: center;
  background: white;
  line-height: 45px;
  color: #9bdb66;
  font-size: 20px;
  border-radius: 25px;
}

#check:checked ~ .search-box .icon {
  background: #9bdb66;
  color: white;
  width: 60px;
  border-radius: 0 25px 25px 0;
}

#check {
  display: none;
}

button {
  --color: #5dc06a;
  padding: 0.5em 0.5em 0.5em 1em;
  background-color: transparent;
  border-radius: 7em;
  position: relative;
  overflow: hidden;
  cursor: pointer;
  transition: 0.5s;
  font-weight: 500;
  font-size: 17px;
  margin-left: 8em;
  margin-right: 3em;
  border: 1px solid;
  font-family: inherit;
  text-transform: uppercase;
  color: var(--color);
  z-index: 1;
}

button::before,
.button::after {
  content: "";
  display: block;
  width: 50px;
  height: 20px;
  transform: translate(-40%, -40%);
  position: absolute;
  border-radius: 50%;
  z-index: -1;
  background-color: var(--color);
  transition: 1s ease;
}

button::before {
  top: -1em;
  left: -1em;
}

button::after {
  left: calc(100% + 1em);
  top: calc(100% + 1em);
}

button:hover::before,
.button:hover::after {
  height: 410px;
  width: 410px;
}

button:hover {
  color: rgb(255, 255, 255);
}

button:active {
  filter: brightness(0.8);
}

.dropdown {
}

.toggle {
  display: none;
}

i {
  padding-right: 5px;
  padding-left: 7px;
}
/* .spacer {
  flex-grow: 1;
} */

a {
  box-shadow: inset 0 0 0 0 #0b7a1e;
  color: #0b7a1e;
  margin: 0 -0.25rem;
  padding: 0 0.25rem;
  transition: color 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
  text-decoration: none;
  text-align: center;
  margin-right: 10px;
  font-weight: 600;
}

a:hover {
  box-shadow: inset 200px 0 0 0 #9bdb66;
  color: white;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: white;
  min-width: 140px;
  right: 0;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5);
  z-index: 1;
}

.dropdown-content a {
  text-decoration: none;
  display: block;
  box-shadow: inset 0 0 0 0 #0b7a1e;
  color: #0b7a1e;
  margin: 0 -0.25rem;
  padding: 0.5rem;
  transition: color 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
}

.dropdown-content a:hover {
  background-color: #9bdb66;
  box-shadow: inset 200px 0 0 0 white;
  color: black;
}

.dropdown:hover .dropdown-content {
  display: block;
}

footer {
  background-color: #9bdb66;
  line-height: 1.5;
  color: #0b7a1e;
}

.footer-title {
  font-size: 1.475em;
}

.footer-main {
  padding: 0.2em 1.875em;
  display: flex;
  flex-wrap: wrap;
}

.footer-main-item {
  padding: 1.25em;
  min-width: 12.5em;
}

footer ul {
  list-style: none;
  padding-left: 0;
  font-size: 1.175em;
}

footer form {
  display: flex;
  flex-wrap: wrap;
}

footer input[type="email"] {
  border: 0;
  padding: 0.625em;
}

footer input[type="submit"] {
  font-family: "Raleway", sans-serif;
  background-color: #5dc06a;
  color: white;
  border: 1px solid #5dc06a;
  cursor: pointer;
  font-size: 0.95em;
  text-transform: uppercase;
  font-size: 1em;
  font-weight: bold;
  letter-spacing: 0.1em;
  border-radius: 2em;
  padding: 0.57em 2.15em;
  margin-left: 0.5em;
  transition: all 0.3s;
}

footer input[type="submit"]:hover {
  transform: translateY(-2px);
  box-shadow: 0.5em 0.5em 2em rgba(0, 0, 0, 0.2);
}

footer input[type="submit"]:active {
  transform: translateY(0);
  box-shadow: none;
}

.footer-social {
  padding: 0 1.875em 0;
  color: #0b7a1e;
}

.footer-social-list {
  display: flex;
  justify-content: center;
}

.footer-social-list li {
  margin: 0.5em;
  font-size: 1.5em;
}

.footer-legal {
  border-top: 1px solid #9bdb66;
  padding: 0 0.875em;
}

.footer-legal-list {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
}

.footer-legal-list li {
  margin: 0.125em 0.625em;
  white-space: nowrap;
  font-size: 0.75em;
}

.footer-legal-list li:nth-last-child(2) {
  flex: 1;
}

@media only screen and (min-width: 477px) {
  .footer-main {
    justify-content: space-around;
  }
}

@media only screen and (max-width: 768px) {
  nav {
    flex-direction: column;
    z-index: 100;
    position: relative;
    transition: all 0.2s ease-out;
    padding-top: 8px;
    width: 20%;
    float: right;
  }

  nav ul {
    flex-direction: column;
    text-align: center;
  }

  #logo {
    display: none;
  }

  .dropdown i {
    display: none;
  }

  .open-menu {
    opacity: 1;
    height: auto;
  }

  .closed-menu {
    opacity: 0;
    height: 0;
    padding: 0;
  }

  .toggle {
    display: block;
    text-align: right;
    padding: 0 10px 10px 0;
  }

  #search-box {
    display: none;
  }
}

@media only screen and (min-width: 1240px) {
  .footer-main {
    justify-content: space-evenly;
  }
}
</style>



