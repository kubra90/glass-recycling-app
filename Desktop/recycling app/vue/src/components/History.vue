<template>
    <div>
        <div class="recycle-history" v-for="pickup in myPickups"
        v-bind:key="pickup.pickup_id">
        
        <ul>
            <li>
          <h3>On {{ pickup.pickup_date }}, you saved 
              {{ pickup.pickup_weight }} lbs of glass
              from landfills!</h3>
          </li>
        
        </ul>
        </div>
    </div>
</template>

<script>

import PickupService from '../services/PickupService';
export default {
    name: "history",
    data(){
        return {
            user: '',
        myPickups : {
        pickup_date:'',
        pickup_weight:'',
        is_picked_up: true,
        num_of_bins: '',
        pickup_id: '',
        route_id: null,
        requesting_username :''
    }
}
    },


created(){
         PickupService.getMyPickupsHistory(this.user).then((response)=>{
             this.myPickups = response.data;
         })
        }
    }
</script>

<style scoped>
.recycle-history {
    text-align:left;
}
</style>
