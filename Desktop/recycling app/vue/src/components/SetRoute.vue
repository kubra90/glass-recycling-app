<template>
  <div id="route-map">
    <h1>Driver Route Map</h1>
    <div id="map"></div>
    <div id="panel"></div>
  </div>
</template>
<script>
import PickupService from "../services/PickupService";
export default {
  name: "RouteMap",
  data() {
    return {
      allPickups: [],
      filter: {
        pickup_id: "",
        route_id: "",
        requesting_username: "",
        pickup_date: "",
        num_of_bins: "",
        is_picked_up: false,
        full_address: "",
      },
      routeOne: [],
      routeTwo: [],
    };
  },
  mounted() {},
  created() {
    PickupService.getPickups().then((response) => {
      this.allPickups = response.data;
      this.directionsService();
      this.assignRouteOne();
      this.assignRouteTwo();
    });
  },

  methods: {
    directionsService() {
      const map = new window.google.maps.Map(document.getElementById("map"), {
        zoom: 4,
        center: { lat: 40.46083373916581, lng: -79.97458794232827 },
      });
      const directionsService = new window.google.maps.DirectionsService();
      const directionsRenderer = new window.google.maps.DirectionsRenderer({
        draggable: true,
        map,
        panel: document.getElementById("panel"),
      });
      this.displayRouteOne(
        "3001 Railroad St, Pittsburgh, PA 15201",
        "3001 Railroad St, Pittsburgh, PA 15201",
        directionsService,
        directionsRenderer
      );
    },
    displayRouteOne(origin, destination, service, display) {
      service
        .route({
          origin: origin,
          destination: destination,
          waypoints: this.routeOne,
          travelMode: window.google.maps.TravelMode.DRIVING,
          avoidTolls: true,
          optimizeWaypoints: true,
        })
        .then((result) => {
          display.setDirections(result);
        })
        .catch((e) => {
          alert("Could not display directions due to: " + e);
        });
    },
    assignRouteOne() {
      for (var i = 0; i < this.allPickups.length; i++) {
        if (parseInt(this.allPickups[i].route_id) == 1) {
          this.routeOne.push({
            location: this.allPickups[i].full_address,
            stopover: true,
          });
        }
      }
    },
    assignRouteTwo() {
      for (var i = 0; i < this.allPickups.length; i++) {
        if (parseInt(this.allPickups[i].route_id) == 2) {
          this.routeTwo.push({
            location: this.allPickups[i].full_address,
            stopover: true,
          });
        }
      }
      //   },
      //   filterVersionOne() {
      //             return this.allPickups.filter((p) =>
      //             p.route_id == 1)
      //         },
    },
  },
};
</script>
<style scoped>
#route-map h1 {
  text-align: center;
  margin: 1em;
}
#map {
  width: 70vw;
  height: 500px;
  margin: auto;
  margin-bottom: 40px;
  padding-bottom: 40px;
}
</style>