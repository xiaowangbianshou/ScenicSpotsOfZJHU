<template>
 <div class="leaflet">
    <div class="container">
      <div id="map" class="map"></div>
    </div>
 </div>
</template>
<script>
import L from "leaflet";
import 'leaflet/dist/leaflet.css';
import Bus from './EventBus.js'
export default {
  name:'AMap',
  props:['points'],
  data() {
    return {
      map:"",
    }
  },
  methods: {
    share(id){
        // console.log('okkkk')
        Bus.$emit('share',{value:id})
      },
    initMap(){
        this.map = L.map("map", {
        center: [30.873496009136253, 120.13106360224784], // 地图中心
        zoom: 16, //缩放比列
        zoomControl: true, //禁用 + - 按钮
        doubleClickZoom: true, // 禁用双击放大
        attributionControl: false // 移除右下角leaflet标识
      });
      let name = L.tileLayer(
         'http://wprd0{s}.is.autonavi.com/appmaptile?x={x}&y={y}&z={z}&lang=zh_cn&size=1&scl=1&style=7',
        {
            subdomains: '1234'
        }
      ).addTo(this.map);
      // let markers=[]
    },
    initpoint(){

      for (let i =0;i<=this.points.length-1;i++) {
          let point = this.points[i]
          //创建图形标记
          this.shipWreckIcon = L.icon({
            iconUrl: require('../../assets/images/map/location2.png'),//图片路径
            iconSize: [30, 30]//图片大小设置
          });
          //创建marker
          let marker = L.marker([point.coordinatex, point.coordinatey], { // 创建点
            draggable: true, //是否可以移动（拖动）默认为否
            icon: this.shipWreckIcon //将marker设置为上面引用的图形
          }).addTo(this.map).bindTooltip(point.name, { //添加提示文字
              permanent: true, //是永久打开还是悬停打开
            direction: 'right' //方向
          }).openTooltip(); //在图层打开
          marker.on('click',()=>{
            // console.log(i)
            this.share(parseInt(i))
          })
      };
    }
  },
  
  mounted(){
   this.initMap()
   setTimeout(() => {
     this.initpoint()
   }, 1000);
  }
}
</script>

<style scoped>
.leaflet{
  width: 100%;
  height: 666.6px;
}
.container {
  width: 100%;
  height: 100%;
  position: relative;
  left: 50.1%;
  top: 333px;
  transform: translate3d(-50%, -50%, 0);
  border: 1px solid #999;
}
.map {
  width: 100%;
  height: calc(100%);
  z-index: 1;
}
.my-div-icon{
            font-size:15px;
            /*background:red;*/
            /*width:5px;*/
            color:red;
        }
</style>

