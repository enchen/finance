<template>
  <div v-bind:class="[show? '': hide]" class="weui_loading_toast" id="loadingToast">
    <div class="weui_mask_transparent"></div>
    <div class="weui_toast">
      <div class="weui_loading">
        <div class="weui_loading_leaf weui_loading_leaf_0"></div>
        <div class="weui_loading_leaf weui_loading_leaf_1"></div>
        <div class="weui_loading_leaf weui_loading_leaf_2"></div>
        <div class="weui_loading_leaf weui_loading_leaf_3"></div>
        <div class="weui_loading_leaf weui_loading_leaf_4"></div>
        <div class="weui_loading_leaf weui_loading_leaf_5"></div>
        <div class="weui_loading_leaf weui_loading_leaf_6"></div>
        <div class="weui_loading_leaf weui_loading_leaf_7"></div>
        <div class="weui_loading_leaf weui_loading_leaf_8"></div>
        <div class="weui_loading_leaf weui_loading_leaf_9"></div>
        <div class="weui_loading_leaf weui_loading_leaf_10"></div>
        <div class="weui_loading_leaf weui_loading_leaf_11"></div>
      </div>
      <p class="weui_toast_content">{{msg}}</p>
    </div>
  </div>
</template>
<style>
.dp-hide{
  display: none;
}
</style>
<script>

    export default{
      data () {
        return {
          hide:'dp-hide'
        }
      },
        props:['msg','show']
    }
</script>
