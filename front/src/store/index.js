import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store(
  {
    state:{
      user:{
        userName: window.localStorage.getItem('user') == null ? '' : JSON.parse(window.localStorage.getItem('user')).userName
      }
    },
    mutations:{
      login (state,user) {
        state.user = user;
        window.localStorage.setItem('user',JSON.stringify(user))
      },
      del(state){
        state.user = null;
        window.localStorage.removeItem('user');SecretConstant
        window.localStorage.removeItem('userName')
      },
      logout(state){
        state.user = [];
        window.localStorage.removeItem('user');
      }

    }
  }
)
