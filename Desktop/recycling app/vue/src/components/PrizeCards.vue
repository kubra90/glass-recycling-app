<template>
  <!-- <div class="card-container">
    <div class="card">
      <div class="content" v-for="(prize, key) in prizeList" v-bind:key="key">
        <div class="front">
          {{ prize.prize_name }}
        </div>
        <div class="back">
          {{ prize.point_value }}
        </div>
      </div>
    </div>
  </div> -->
  <div class="container">
    <!-- flippy card one -->
    <div class="flip-card">
      <div class="flippy-inner">
        <div class="flippy-front">
          <img alt="Vitrum white tshirt with logo" src="../img/tshirt.png" />
        </div>
        <div class="flippy-back">
          <h3>75 points</h3>
          <p>
            Let everyone know you're an eco-friendly warrior who helps keep
            glass out of landfills. As an honorary glass wrangler, feel free to
            stop by and volunteer!
          </p>
        </div>
      </div>
    </div>
    <!-- flippy card two -->
    <div class="flip-card">
      <div class="flippy-inner">
        <div class="flippy-front">
          <img
            alt="Image of man with umbrella in flood disaster area"
            src="../img/redcross.jpg"
          />
        </div>
        <div class="flippy-back">
          <h3>100 Points</h3>
          <p>
            This donation helps the Red Cross get sandbags to places where
            they're needed most.
          </p>
        </div>
      </div>
    </div>
    <!-- flippy card three -->
    <div class="flip-card">
      <div class="flippy-inner">
        <div class="flippy-front">
          <img alt="Black Vitrum snapback hat" src="../img/hat.png" />
        </div>
        <div class="flippy-back">
          <h3>150 Points</h3>
          <p>
            Keep the sun out of your eyes while you're out and about. Or maybe
            working in your garden, or taking your dog for a walk.
          </p>
        </div>
      </div>

      <!-- flippy card four -->
      <div class="flip-card">
        <div class="flippy-inner">
          <div class="flippy-front">
            <img alt="Amazon giftcard" src="../img/amazoncard.png" />
          </div>
          <div class="flippy-back">
            <h3>250 Points</h3>
            <p>
              Buy yourself that OEKO-TEX certified organic cotton blanket you've
              always wanted.
            </p>
          </div>
        </div>
      </div>
      <!-- flippy card five -->
      <div class="flip-card">
        <div class="flippy-inner">
          <div class="flippy-front">
            <img
              alt="Overhead image of glasses with beer"
              src="../img/brewery.jpg"
            />
          </div>
          <div class="flippy-back">
            <h3>400 Points</h3>
            <p>
              Mm, smell the hops! Get a behind the scenes look at how your
              favorite beer is made and drink some brew with your friends!
            </p>
          </div>
        </div>
      </div>
      <!-- flippy card six -->
      <div class="flip-card">
        <div class="flippy-inner">
          <div class="flippy-front">
            <img alt="REI giftcard" src="../img/reicard.jpg" />
          </div>
          <div class="flippy-back">
            <h3>600 Points</h3>
            <p>
              Go buy some hiking gear and a reusable waterbottle to prepare for
              your weekend getaway to 7 Springs Resort!
            </p>
          </div>
        </div>
      </div>
      <!-- flippy card seven -->
      <div class="flip-card">
        <div class="flippy-inner">
          <div class="flippy-front">
            <img
              alt="Espresso machine with white mug"
              src="../img/espresso.jpg"
            />
          </div>
          <div class="flippy-back">
            <h3>1500 Points</h3>
            <p>
              With this espresso machine, you are no longer beholden to the $6
              coffees at your local shop. Buy some beans and get caffinated!
            </p>
          </div>
        </div>
      </div>
      <!-- flippy card eight -->
      <div class="flip-card">
        <div class="flippy-inner">
          <div class="flippy-front">
            <img alt="Playstation 5 controller" src="../img/psfive.jpg" />
          </div>
          <div class="flippy-back">
            <h3>2500 Points</h3>
            <p>
              Relax and go play some games! You deserve it! When is the next
              Assassin's created coming out?
            </p>
          </div>
        </div>
      </div>
      <div class="flip-card">
        <div class="flippy-inner">
          <div class="flippy-front">
            <img
              alt="Image of man hiking in forest with arms raised in jubilation"
              src="../img/vacation.jpg"
            />
          </div>
          <div class="flippy-back">
            <h3>3750 Points</h3>
            <p>
              Welcome to paradise. Don't forget to bring your gear, your dog,
              and your family. Or you can leave them at home. Probably bring
              your dog though.
            </p>
          </div>
        </div>
      </div>
      <!-- flippy card ten -->
      <div class="flip-card">
        <div class="flippy-inner">
          <div class="flippy-front">
            <img alt="Recycled glass countertop" src="../img/countertop.jpg" />
          </div>
          <div class="flippy-back">
            <h3>5000 Points</h3>
            <p>
              It looks like broken glass but it's actually really expensive.
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PrizeService from "../services/PrizeService.js";
export default {
  name: "prize-cards",
  data() {},
  created() {
    PrizeService.getAllPrizes().then((response) => {
      this.prizeList = response.data;
    });
  },
};
</script>

<style scoped>
.flip-card img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.container {
  height: 100%;
  width: 100%;
  margin-top: 2em;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.flip-card {
  background-color: transparent;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  flex-shrink: 0;
  flex-direction: column;
  perspective: 1000px;
  margin: 2em;
  width: 300px;
  height: 300px;
  box-shadow: 0.5em 0.5em 3em rgba(0, 0, 0, 0.2);
}

.flippy-inner {
  position: relative;
  width: 100%;
  height: 100%;
  transition: transform 0.5s;
  transform-style: preserve-3d;
}

.flip-card:hover .flippy-inner {
  transform: rotateY(180deg);
}

.flippy-front,
.flippy-back {
  position: absolute;
  height: 100%;
  width: 100%;
  backface-visibility: hidden;
}

.flippy-back {
  transform: rotateY(180deg);
}

.flip-card h3 {
  text-align: center;
}
p {
  padding-top: 5em;
  text-align: center;
}

/* @media screen and (min-width: 700px) {
  .container .flip-card {
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
  }

  .last-container .card {
    margin: 2em;
  } */
</style>