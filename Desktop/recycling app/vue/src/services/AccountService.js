import axios from 'axios';

//should  we have fixed http??? Ask!

export default {

    //this method is to get all account details for user/driver based on username
    // getUserAccountInformation(username) {
    //     return http.get(`/users/${username}/details`)
    // },

    //this method is to get all account details for user/driver based on username
    getUserInfoById(userId) {
        return axios.get(`/users/${userId}`)
    },

    // //add new user detail.
    addUserDetails(userDetail) {

        return axios.post('/users/details', userDetail)
    },


    //get userdetail by username
    getUserDetails(){
        return axios.get('/users/myDetails')
    },

    //get total glass recycled with Vitrum
    getTotalGlassRecycled(){
        return axios.get('/stats/total')
    },

    //update userdetails
    updateUserDetails(userDetail, accountId){
        return axios.put(`users/details/${accountId}`, userDetail)
    }


}
