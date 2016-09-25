<template>
  <div v-bind:class="[show? '': hide]" id="dialog1" class="weui_dialog_confirm">
    <div class="weui_mask"></div>
    <div class="weui_dialog">
      <div class="weui_dialog_hd"><strong class="weui_dialog_title">{{tittle}}</strong></div>
      <div class="weui_dialog_bd">{{context}}</div>
      <div class="weui_dialog_ft">
        <a class="weui_btn_dialog default" href="javascript:;" @click="dispathEventAndClose('dialog-cancel')">取消</a>
        <a class="weui_btn_dialog primary" href="javascript:;" @click="dispathEventAndClose('dialog-confirm')">确定</a>
      </div>
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
        data(){
            return{
              hide:'dp-hide'
            }
        },
      props:['tittle','context','show'],
      methods:{
        dispathEventAndClose(event) {
          this.$dispatch(event);
        }
      }
    }
</script>
