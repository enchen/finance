<template>
  <div class="actionsheet_wrap">
    <div class="weui_mask_transition" :class="{'weui_fade_toggle': show}" :style="{display: show ? 'block' : 'none'}" @click="hideActionSheet"></div>
    <div class="weui_actionsheet" :class="{'weui_actionsheet_toggle': show}">
      <div class="weui_actionsheet_menu">
        <div class="weui_actionsheet_cell" v-for="(key, text) in menus" @click="dispatchEvent('menu-click', key)">
          {{{text}}}
        </div>
      </div>
      <div class="weui_actionsheet_action">
        <div class="weui_actionsheet_cell" @click="dispatchEvent('cancel-click','0')">
          取消
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
    .actionsheet_wrap{
      margin: 0;
      padding: 0;
    }
    .weui_actionsheet_menu,.weui_actionsheet
    {
      overflow:auto;
      height:100%;
    }
</style>
<script>

    export default{
      props: {
        /**
         * 双向绑定的数据，用于控制是否显示Actionsheet
         */
        show: {
          type: Boolean,
          required: true,
          twoWay: true
        },

        /**
         * 菜单键值对
         */
        menus: {
          type: Object,
          required: false,
          default: {}
        }
        },
      methods: {
        dispatchEvent(event, message) {
          this.$dispatch(event, message);
          if (event === 'cancel-click') {
            this.hideActionSheet();
          }
        },

        hideActionSheet() {
          this.show = false;
        }
      }

    }
</script>
