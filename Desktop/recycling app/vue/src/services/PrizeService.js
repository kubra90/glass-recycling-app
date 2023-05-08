import axios from 'axios';

export default {

      //Get Prize List
      getAllPrizes() {
            return axios.get('/prizes');
      },

      //Get Prize by PrizeId
      getPrizeByPrizeId(prizeId) {
            return axios.get(`/prizes/${prizeId}`);
      },

      //add new Prize
      addPrize(prize) {
            return axios.post('/prizes', prize);
      },

      //update a Prize
      updatePrize(prize, prizeId) {
            return axios.put(`/prizes/${prizeId}`, prize);
      },

      //delete a Prize
      deletePrize(prizeId) {
            return axios.delete(`/prizes/${prizeId}`);
      }
      
}