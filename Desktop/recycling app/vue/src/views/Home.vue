<template>
  <div class="body-container">
    <div class="hero-image">
      <div class="hero-text">
        <h1>We're all in this together! Recycle for the planet!</h1>
        <!-- <button>
          <router-link id="register" v-bind:to="{ name: 'register' }">
          </router-link
          >Sign up today!
        </button> -->
        <button>
          <router-link id="register" :to="{ name: 'register' }"
            >Sign up today!</router-link
          >
        </button>
      </div>
    </div>
    <section class="about-us">
      <div id="info">
        <h2>Get to know Vitrum</h2>
        <p>
          We're a grassroots program offering curbside glass recycling in the
          Pittsburgh metro region. Despite its status as an infinitely
          recyclable material, 75% of glass containers used by consumers will be
          disposed of in landfills where it will never decompose. Enroll with
          Vitrum today and our fleet of glass wranglers will deliver custom bins
          to your home or business.
        </p>
        <div class="illustration"><img src="../img/happyrecycle.jpg" /></div>
      </div>
    </section>
    <section class="mid-container">
      <div id="services">
        <p>
          When those bins are full, simply schedule a pickup date on our handy
          app and drop those wine bottles and jam jars at the curb; they will be
          crushed to a fine sand which we sell or donate to our commercial
          partners for a wide variety of sustainable applications: erosion
          remediation, creating road services, and even to make swanky and
          unique kitchen countertops. Keep track of your recycling stats on your
          dashboard and earn points with every pickup to redeem for cool prizes.
        </p>
      </div>
    </section>
    <section class="counter-container">
      <div id="counter">
        We've saved <span>{{ totalGlass }}</span> lbs of glass from landfills!
      </div>
    </section>
    <section class="last-container">
      <div class="meet-team">
        <h2 class="mt-header">Meet Our Team</h2>
        <div class="card-wrapper">
          <div class="card">
            <img
              src="../img/matt.jpg"
              alt="Profile image"
              class="profile-img"
            />
            <h3>Matt Hosner</h3>
            <p class="job-title">Founder &amp; CEO</p>
            <p class="about-matt">
              Matt left a career in real estate finance to pursue his dream of
              bringing sustainable glass recycling to the Pittsburgh region. He
              enjoys birding, photography, and attending live music events.
            </p>
          </div>

          <div class="card">
            <img
              src="../img/kubra.jpg"
              alt="Profile image"
              class="profile-img"
            />
            <h3>Kubra Tapan</h3>
            <p class="job-title">Fullstack Developer</p>
            <p class="about-kubra">
              Kubra moved recently to Boston from her native Turkey and is now
              pursuing her dream of a career in software development. Her
              fantastic all around coding skills and willingness to jump in and
              help anywhere she is needed make her an essential component of the
              Vitrum team.
            </p>
          </div>

          <div class="card">
            <img
              src="../img/greg.jpg"
              alt="Profile image"
              class="profile-img"
            />
            <h3>Greg Sabella</h3>
            <p class="job-title">Backend Developer</p>
            <p class="about-greg">
              As an avid rock climber and outdoor enthusiast, Greg attacks
              server development issues with the same determination and
              analytical intensity he brings to a Class 5 backcountry route. He
              currently resides in Lynchburg, VA, where he enjoys taking his dog
              out 17 times a night.
            </p>
          </div>

          <div class="card">
            <img
              src="../img/alexis.jpg"
              alt="Profile image"
              class="profile-img"
            />
            <h3>Alexis Horst</h3>
            <p class="job-title">Frontend Developer</p>
            <p class="about-alexis">
              With strong technical skill, extensive training in creative
              fields, and a keen eye for design, Alexis was the natural choice
              to lead Vitrum's front end development. She keeps the site looking
              great and makes sure the experience is fun and intuitive for our
              customers.
            </p>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import AccountService from "../services/AccountService";
export default {
  name: "home",
  //name: "account"
  data() {
    return {
      totalGlass: "",
    };
  },
  created() {
    AccountService.getTotalGlassRecycled().then((response) => {
      this.totalGlass = response.data;
    });
  },
};
</script>


<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  border: 0;
}

.body-container {
  width: 100%;
}

.hero-image {
  background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.4)),
    url("../img/glass-bottles-honeycomb.jpg");
  height: 600px;
  width: 100%;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}

.hero-text {
  text-align: center;
  position: absolute;
  letter-spacing: 0.3em;
  font-size: 1.7em;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
}

.hero-image button {
  display: inline-block;
  padding: 10px 25px;
  color: white;
  font-size: 0.75em;
  text-align: center;
  font-weight: bold;
  letter-spacing: 0.12em;
  cursor: pointer;
  margin-top: 2em;
  background-color: #5dc06a;
  padding: 19px 39px 18px 39px;
  transition: all 0.3s;
}

.hero-image button:hover {
  transform: translateY(-2px);
  box-shadow: 0.5em 0.5em 2em rgba(0, 0, 0, 0.2);
}

.hero-image button a {
  color: white;
  box-shadow: none;
}

.about-us h2 {
  text-transform: uppercase;
  font-size: 2.5em;
  letter-spacing: 3px;
  margin-right: -3px;
  margin-bottom: 1.5em;
  text-align: center;
  position: relative;
}

.about-us h2::after {
  content: "";
  width: 5em;
  height: 0.2em;
  background-color: #ffe46c;
  position: absolute;
  bottom: -0.5em;
  left: 50%;
  transform: translateX(-50%);
  border-radius: 2em;
}

.about-us {
  background-color: white;
  margin-top: 1em;
  padding: 30px;
  padding-left: 50px;
  padding-right: 50px;
  height: auto;
  font-size: 1.5em;
  text-align: justify;
}

p {
  padding-left: 50px;
  padding-right: 50px;
}

.illustration img {
  max-width: 100%;
  margin-top: 2em;
}

.mid-container {
  background-color: white;
  margin-top: 1em;
  padding: 20px;
  height: auto;
  font-size: 1.5em;
  text-align: justify;
  align-items: center;
  justify-content: center;
}

.counter-container {
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  /* background-color: #9bdb66; */
  margin-top: 5em;
  height: 400px;
  width: 100%;
  background-image: url("../img/bottles-in-crate.jpg");
}

#counter {
  font-size: 3em;
  border-radius: 15px;
  padding: 1em;
  width: 100%;
  text-align: center;
  backdrop-filter: blur(10px);
}

#counter span {
  font-size: 2em;
  font-weight: bold;
  border-bottom: 6px solid;
}

/* none of this animation works yet /sob */

/* #counter span {
  animation: 0.5s steps(10) forwards infinite;
}

#counter span:before {
  content: attr(0);

}

@keyframes animate {
  100% {
    transform: translate(0, -10em, 0);
  }
} */

.last-container {
  background-color: white;
  padding: 10em 0;
}

.mt-header {
  text-transform: uppercase;
  font-size: 3.5em;
  letter-spacing: 3px;
  margin-right: -3px;
  margin-bottom: 1.5em;
  text-align: center;
  position: relative;
}

.mt-header::after {
  content: "";
  width: 5em;
  height: 0.2em;
  background-color: #ffe46c;
  position: absolute;
  bottom: -0.5em;
  left: 50%;
  transform: translateX(-50%);
  border-radius: 2em;
}

.last-container .card-wrapper {
  display: flex;
  align-items: center;
  align-content: center;
  flex-direction: column;
}

.last-container .card {
  width: 32em;
  background-color: #e4e7e1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 2em 0;
  height: 800px;
  box-shadow: 0.5em 0.5em 3em rgba(0, 0, 0, 0.2);
}

.profile-img {
  width: 100%;
  height: 26em;
  object-fit: cover;
}

.last-container .card h3 {
  font-size: 2em;
  margin: 1.5em 0 0.5em;
}

.last-container .card-matt h3 {
  font-size: 2em;
  margin: 1.5em 0 0.5em;
}

.last-container .job-title {
  color: #4d885b;
  font-size: 1.5em;
  font-weight: bold;
}

.last-container .about {
  font-size: 1.3em;
  margin: 1.5em 0;
  text-align: center;
}

.last-container .about-matt {
  font-size: 1.3em;
  text-align: center;
  margin: 1.5em 0 4.5em;
}

.last-container .about-kubra,
.about-alexis {
  font-size: 1.3em;
  text-align: center;
  margin: 1.5em 0 2.2em;
}

.last-container .about-greg {
  font-size: 1.3em;
  text-align: center;
  margin: 1.5em 0 1em;
}

@media screen and (min-width: 700px) {
  .last-container .card-wrapper {
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
  }

  .last-container .card {
    margin: 2em;
  }
}

/* @media screen and (max-width: 650px) {
  .column {
    width: 100%;
    display: block;
  }
}

.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
}

.container {
  padding: 0 16px;
}

.container::after, .row::after {
  content: "";
  clear: both;
  display: table;
}

.title {
  color: grey;
}

.button {
  border: none;
  outline: 0;
  display: inline-block;
  padding: 8px;
  color: white;
  background-color: #000;
  text-align: center;
  cursor: pointer;
  width: 100%;
}

.button:hover {
  background-color: #555;
} */
</style>