<template>
  <div class="module-container">
    <div class="module-body">
      <Map :points="this.points"></Map>
    </div>
    <div class="module-right">
      <Meri></Meri>
    </div>
</div>
</template>

<script>
import Map from '../../components/front/Map.vue'
import Meri from '../../components/front/Meritorious.vue'
export default {
  components:{
    Map,
    Meri
  },
  data(){
    return{
      points:'',
      getpointids:'',
      getpoints:'',
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      GetModelInfo:{
          username:'',
          modelid:null,
      },
    }
  },
  created(){
    this.getinfo(this.user.username)
    setTimeout(() => {
     this.init()
     this.getmodel()
   }, 1000);
  },
  methods:{
    init(){
      this.$http.post("/point/getpoint",this.getpointids).then(res=>{
        // console.log(res);
        this.points=res.data
      })
    },
     getinfo(username){
        this.$http.get("/pointsubmit/"+username,{
          params:{
            username:username,
          }
        }).then(res=>{
          if(res.code === "200"){
            this.getpointids=res.data.map(item=>item.pointid)
      }
      })
    },
    getmodel(){
      this.GetModelInfo.username=this.user.username
      if(this.getpointids.length==1){
        this.GetModelInfo.modelid=1
      }
      if(this.getpointids.length==2){
        this.GetModelInfo.modelid=2
      }
      if(this.getpointids.length==3){
        this.GetModelInfo.modelid=3
      }
      if(this.getpointids.length==5){
        this.GetModelInfo.modelid=4
      }
      if(this.getpointids.length==10){
        this.GetModelInfo.modelid=5
      }
      if(this.GetModelInfo.modelid){
        this.$http.post("/getmodel",this.GetModelInfo)
      }
    }
  }

}
</script>

<style lang='scss' scope>
@import '../../scss/front/welcome.component.scss';
</style>