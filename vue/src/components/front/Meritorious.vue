<template>
  <div class="wall">
      <div class="wall-title">
        <img class="wall-badge" src="../../assets/images/map/badge.png" /> 
        <div class="wall-desc">勋章墙</div>
      </div>
      <div class="wall-archive">
        <div v-for="rule in rules" :key="rule.id" class="wall-archive-item" :class="getClass(rule)">
          <img class="wall-archive-item-image" src="../../assets/images/map/badge2.png" /> 
          <div class="wall-archive-item-text">{{rule.name}}</div>
        </div>
      </div>
    </div>
</template>

<script>
export default {
  data(){
    return{
      rules:'',
      getmodels:'',
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created(){
    this.init()
    this.getmodel(this.user.username)
  },
  methods:{
    init(){
      this.$http.get("/medal/page",{
        params:{
          pageNum:1,
          pageSize:50,
        }
      }
      ).then(res=>{
        this.rules=res.data.records.reverse().filter(item=>item.enable)
      })
    },
    getClass:function(item){
      if(!this.getmodels.includes(item.id)){
        return 'wall-archive-item-gray'
      }
      if(item.type=='打卡次数'){
        return 'wall-archive-item-1'
      }else if(item.type=='打卡地点'){
        return 'wall-archive-item-2'
      }else if(item.type=='打卡'){
        return 'wall-archive-item-3'
      }
      else{
        return 'wall-archive-item-gray'
      }
    },
    getmodel(username){
      this.$http.get("/getmodel/"+username).then(res=>{
        this.getmodels=res.data.map(item=>parseInt(item.modelid))
        // console.log(this.getmodels);
      })
    }
  }
}
</script>

<style lang='scss' scope>
@import '../../scss/front/welcome.component.scss';
.wall-get{
  color: red;
}
</style>