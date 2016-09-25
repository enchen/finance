<template>
    <div class="">
      <div class="weui_cells weui_cells_form">
        <div class="weui_cells_title">费用</div>
        <div class="weui_cell">
          <div class="weui_cell_hd"><label class="weui_label">报销金额：</label></div>
          <div class="weui_cell_bd weui_cell_primary">
            <input type="number" step="0.01"  placeholder="元" class="weui_input" :value="fee">
          </div>
        </div>

        <div class="weui_cells_title">归属</div>
        <div class="weui_cell">
          <div class="weui_cell_hd"><label class="weui_label">常规支出：</label>

          </div>
          <div class="weui_cell_bd weui_cell_primary">
            <div class="weui_input">
              <select v-model="selectedProject">
                <option v-for="project in Projects | filterBy '0' in 'projectType'">{{project.projectName}}</option>
              </select>
            </div>
          </div>
        </div>

        <div class="weui_cell">
          <div class="weui_cell_hd"><label class="weui_label">生产项目：</label>

          </div>
          <div class="weui_cell_bd weui_cell_primary">
            <div class="weui_input">
              <select v-model="selectedProject">
              <option v-for="project in Projects | filterBy '1' in 'projectType'">{{project.projectName}}</option>
            </select>
            </div>
          </div>
        </div>
        <div class="weui_cells_title">明细</div>
        <div class="weui_cell">
          <div class="weui_cell_bd weui_cell_primary">
            <textarea rows="3" placeholder="请输入详情" maxlength="255" class="weui_textarea" :content="reason"></textarea>
          </div>
        </div>

        <div class="weui_cell">
          <div class="weui_cell_hd"><label class="weui_label">指定上级：</label>

          </div>
          <div class="weui_cell_bd weui_cell_primary">
            <div class="weui_input">
              <select v-model="selectedUser">
                <option v-for="user in Users" :value="user.userid">{{user.name}}</option>
                <!--<option v-for="user in Users" v-bind:value="user.userid">{{user.name}}</option>-->
              </select>
            </div>
          </div>
        </div>
</div>

    </div>
  <div class="weui_btn_area">
    <a id="showTooltips" href="javascript:" @click="gocheck" class="weui_btn weui_btn_primary">确定</a>
  </div>
</template>
<style>
  textarea
  {
    text-align: right;
  }

  select
  {
    height: 90%;
    width: 30%;
  }

 .dp-suo{
   width: 105px;
   text-align: right;
 }
  .weui_cell,input
  {
    text-align: right;
  }
</style>
<script>

    export default{
      data () {
        return {
          fee:0,
          reason:'',
          selectedProject:null,
          selectedUser:null,
          Projects: [],
          Users:[]
        }
      },

      ready: function() {

        this.getProjects()
        this.getUsers()
      },
      methods: {

        getProjects: function() {
          this.$http.get('user/getavailableprojects.php')
            .then((response) => {
              this.$set('Projects', response.data)
            })
            .catch(function(response) {
              console.log(response)
            })
        },
        getUsers: function() {
          this.$http.get('user/getusers.php',{params:{role:'user'}})
            .then((response) => {
              this.$set('Users', response.data)
            })
            .catch(function(response) {
              console.log(response)
            })
        },
        gocheck:function () {
         alert(this.selectedUser);

        }
      }
    }
</script>
