<template>
  <div class="main-title"><h1 class="dp-golden">DP</h1><h1>费用系统</h1></div>
  <div id="mainApp" class="weui_grids">
    <dp-item v-for="item in gridData | filterBy '1' in  'roleType'"  :roleid="item.roleId"  :rolename="item.roleName" ></dp-item>
  </div>
</template>

<script>
import Item from './components/dp-item.vue'
export default {
  data () {
    return {
      gridData: []
    }
  },
  components:{
    'dp-item':Item
  },

    ready: function() {
      this.getCustomers()
    },
    methods: {

            getCustomers: function() {
              this.$http.get('test.php')
                .then((response) => {
                  console.log(response)
                  this.$set('gridData', response.data)
                })
                .catch(function(response) {
                  console.log(response)
                })
            }
      }

  }


</script>

<style>
 .dp-golden{
    color: darkgoldenrod;
  }
  h1{
    float: left;;
  }
  .main-title{
    width: 100%;
    height: 40px;
    margin: 30px 15%;
  }
</style>
