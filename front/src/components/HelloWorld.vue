<template>
  <div style="margin-top: 40px">
    <side-menu id="side-menu"></side-menu>
    <div class="home">
      <div id="header-div">
        <div style="height: 150px"></div>
        <Carousel></Carousel>
      </div>
    </div>
  </div>
</template>

<script>
    import {api} from '@/api/index.js'
    import Carousel from './home/Carousel'
    import SideMenu from "./library/SideMenu";
    export default {
        name: 'HelloWorld',
        components:{Carousel,SideMenu},
        data () {
          return {
            msg: 'Welcome to Your Vue.js App'
          }
        },
          methods:{
              test(){
                  let param = {};
                  api.loginTest(param)
                      .then(data => {
                          console.log(data.re.hello)
                    })
              }
          },
          mounted() {
            this.test();
          }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}

#side-menu {
  position: fixed;
  margin-left: 50%;
  left: -680px;
  top: 100px;
}

.home {
  width: 990px;
  margin-left: auto;
  margin-right: auto;
  margin-top: -20px;
}
</style>
