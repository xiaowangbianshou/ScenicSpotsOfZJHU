<template>
<div class="module-container">
  <div class="module-body">
    <!-- <map-control (mapInit)="mapInit($event)"></map-control> -->
    <Map :points="this.points"></Map>
  </div>
  <div class="module-right">
      <Pointdetail :Points="this.points"></Pointdetail>
  </div>
</div>
</template>

<script>
import Map from '../../components/front/PointMap.vue'
import Pointdetail from '../../components/front/Pointdetail.vue'
export default {
  name:'Point',
  components:{
    Map,
    Pointdetail
  },
  data(){
    return{
      points:''
    }
  },
  created(){
    this.init()
  },
  methods:{
    init(){
      this.$http.get("/point/page",{
        params:{
          pageNum:1,
          pageSize:50,
        }
      }
      ).then(res=>{
        // console.log(res);
        this.points=res.data.records.filter(item=>item.enable).reverse()
      })
    }
  }
}
</script>

<style lang='scss' scope>
// @import "./point.component.scss"
</style>