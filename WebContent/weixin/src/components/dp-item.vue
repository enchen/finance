<template>

  <a class="weui_grid"   href="#/{{roleid}} ">
    <div class="weui_grid_icon dp-width">
      <i class="icon icon_button"></i><i class="redCircle" v-show="condition">{{todo}}</i>
    </div>
    <p class="weui_grid_label">
      {{rolename}}
    </p>
  </a>

</template>
<style scoped>
  .icon_button {
    background: rgba(0, 0, 0, 0) url("../assets/icon_nav_article.png") no-repeat scroll 0 0 / 28px 28px;
  }
  .icon {
    display: inline-block;
    height: 28px;
    vertical-align: middle;
    width: 28px;
  }
  .dp-width
  {
    position: relative;
    width: 28px;
  }
  .redCircle
  {
    color: white;
    display:block;
    background:#f00;
    border-radius:50%;
    width:18px;
    height:18px;
    top:-7px;
    right:-10px;
    position:absolute;
  }
</style>
<script>
    export default{
      data(){
        return{
          todo:'',
          condition:false
        }
      },
      props:['rolename','roleid'],
      methods:{
        getTodo: function() {
          this.$http.get('user/gettodo.php',{params:{role:this.roleid}})
            .then((response) => {
alert(response.data);
              if(response.data>0)
              {
                this.condition=true
                this.todo=response.data
              }
            })
            .catch(function(response) {
              console.log(response)
            })
        }
      },
      ready:function() {
        this.getTodo()
      }
    }
</script>
