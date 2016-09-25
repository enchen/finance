<template>
  <div class="">
    <div class="weui_cells weui_cells_form">
      <div class="weui_cells_title">费用</div>
      <div class="weui_cell">
        <div class="weui_cell_hd"><label class="weui_label">报销金额：</label></div>
        <div class="weui_cell_bd weui_cell_primary">
          <input type="number" step="0.01"  placeholder="元" class="weui_input" v-model="fee">
        </div>
      </div>

      <div class="weui_cells_title">归属</div>
      <div class="weui_cell">
        <div class="weui_cell_hd"><label class="weui_label">所属项目：</label>

        </div>
        <div class="weui_cell_bd weui_cell_primary">
          <div class="weui_input">
            <a href="javascript:" class="prjSelect" @click="doSelectProject">{{selectedProjectText}}</a>

          </div>
        </div>
      </div>

      <!--<div class="weui_cell">-->
        <!--<div class="weui_cell_hd"><label class="weui_label">生产项目：</label>-->

        <!--</div>-->
        <!--<div class="weui_cell_bd weui_cell_primary">-->
          <!--<div class="weui_input">-->
            <!--<select v-model="selectedProject">-->
              <!--<option v-for="project in Projects | filterBy '1' in 'projectType'" :value="project.projectId">{{project.projectName}}</option>-->
            <!--</select>-->
          <!--</div>-->
        <!--</div>-->
      <!--</div>-->

      <div class="weui_cells_title">明细</div>
      <div class="weui_cell">
        <div class="weui_cell_hd"><label class="weui_label">报销摘要：</label></div>
        <div class="weui_cell_bd weui_cell_primary">
          <input type="text"   placeholder="40字以内" maxlength="40" class="weui_input" v-model="summary">
        </div>
      </div>
      <div class="weui_cell">
        <div class="weui_cell_bd weui_cell_primary">
          <textarea rows="3" placeholder="请输入详情" maxlength="255" class="weui_textarea" v-model="reason" ></textarea>

        </div>
      </div>
      <div class="weui_cells_title">审批</div>
      <div class="weui_cell">
        <div class="weui_cell_hd"><label class="weui_label">指定上级：</label>

        </div>
        <div class="weui_cell_bd weui_cell_primary">
          <div class="weui_input">
            <a href="javascript:" class="prjSelect" @click="doSelectUser">{{selectedUserText}}</a>
            <!--<select v-model="selectedUser">-->
              <!--<option v-for="user in Users" :value="user.userid">{{user.name}}</option>-->
              <!--&lt;!&ndash;<option v-for="user in Users" v-bind:value="user.userid">{{user.name}}</option>&ndash;&gt;-->
            <!--</select>-->
          </div>
        </div>
      </div>

    </div>
    <div class="weui_btn_area">
      <a id="showTooltips" href="javascript:" @click="gocheck" class="weui_btn weui_btn_primary">确定</a>
    </div>
  </div>
<dp-loading msg="正在提交" :show="isloading"></dp-loading>
  <dp-dialog title="提交报销" context="您的报销申请将会提交到指定上级审批" :show="isdiolog"
             @dialog-confirm="handleDialogAction('确定')"  @dialog-cancel="handleDialogAction('取消')"></dp-dialog>
  <dp-select id="projectSelect" :show.sync="projectSelectShow" :menus="Menus"
             @menu-click="handleProjectMenuClick" @cancle-click="handleProjectCancelClick"></dp-select>
</template>
<style scoped>
  .prjSelect
  {
    height: 90%;
    color: black;
    width: 30%;
  }
  .weui_btn_area
  {
    margin-top: 40px;
  }
  textarea
  {
    text-align: right;
  }
  select
  {
    width: 30%;
  }
  .weui_cell,input
  {
    text-align: right;
  }

</style>
<script>
import Loading from './dp-loading.vue'
import Dialog from './dp-dialog.vue'
import  SelectSheet from './dp-select-sheet.vue'
  export default{
    data () {
      return {
        projectSelectShow:false,
        isdiolog:false,
        isloading:false,
        fee:'',
        summary:'',
        reason:'',
        selectedProjectText:'请选择...',
        selectedUserText:'请选择...',
        selectedProject:'',
        selectedUser:'',
        Menus:{},
        Projects:{},
        Users:{}
      }
    },
    components:{
      'dp-loading':Loading,
      'dp-dialog':Dialog,
      'dp-select':SelectSheet
    }
    ,

    ready: function() {

      this.getProjects()
      this.getUsers()
    },
    methods: {

      getProjects: function() {
        this.$http.get('user/getavailableprojects.php')
          .then((response) => {
            var projects=response.data
            for(var i=0;i<projects.length;i++)
            {
              var prjkey=projects[i].projectId
              var value=projects[i].projectName
              this.Projects[prjkey]=value
            }

//            this.$set('Projects', response.data)
          })
          .catch(function(response) {
            console.log(response)
          })
      },
      handleDialogAction(action) {
        if (action === '确定') {
          this[`isdiolog`] = false;
          this.createSpend();
        } else {
          this[`isdiolog`] = false;
        }
      },
      getUsers: function() {
        this.$http.get('user/getusers.php',{params:{role:'user'}})
          .then((response) => {
            var users=response.data
            for(var i=0;i<users.length;i++)
            {
              var ukey=users[i].userid
              var value=users[i].name
              this.Users[ukey]=value
            }
          })
          .catch(function(response) {
            console.log(response)
          })
      },
      doSelectProject:function()
      {
        this.projectSelectShow=true
        this.Menus=this.Projects
      },
      doSelectUser:function()
      {
        this.projectSelectShow=true
        this.Menus=this.Users
      },
      handleProjectMenuClick:function (key) {
        this.projectSelectShow=false
        if( this.Menus==this.Projects)
        {
        this.selectedProject=key
        this.selectedProjectText=this.Projects[key]
        }
        else if(this.Menus=this.Users)
        {
          this.selectedUser=key
          this.selectedUserText=this.Users[key]
        }
      },
      handleProjectCancelClick:function (key) {

      },

      gocheck:function () {

        if(this.fee==0)
        {
          alert("请填写金额")
          return
        }
        if(this.sumary=='')
        {
          alert("请填写摘要")
          return
        }
        if(this.selectedProject=='')
        {
          alert("请选择所属科目")
          return
        }
        if(this.selectedUser=='')
        {
          alert("请选择审批上级")
          return
        }
        this[`isdiolog`]=true;


      },
      createSpend:function(){

        this.isloading=true;
        var spend = {
          fee:this.fee,
          summary:this.summary,
          reason:this.reason,
          projectId:this.selectedProject,
          checkId:this.selectedUser
        }

        this.$http.post('user/newspend.php',{
          fee:this.fee,
          summary:this.summary,
          reason:this.reason,
          projectId:this.selectedProject,
          checkId:this.selectedUser
        })
          .then((response) => {
            if(response.data==1)
            {
              this.$router.go('success')
            }
            else
            {
              this.isloading=false;
              console.log(response)
            }
          })
          .catch(function(response) {
            this.isloading=false;
            console.log(response)
          })
      }
    }

  }
</script>
