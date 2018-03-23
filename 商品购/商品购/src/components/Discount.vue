<template>
  <div class="discount">
    <ul>
      <a href="" v-for="item in dataList">
        <li class="discount-item" >
          <img :src="item.image" alt="">
          <div>
            <p>{{item.name}}</p>
            <div>
              <span class="theme-color-4">券{{filterDisPrice(item)}}</span>
              <span class="theme-color-4">店铺名称:</span>
            </div>
            <p>优惠券总数<span class="theme-color-1">{{item.disSum}}</span>张</p>
            <div class="price">
              <div class="price-actual">{{filterActualPrice(item)}}</div>
              <div class="price-origianl">
               {{item.price}}
                <!-- <p>券后价</p> -->
              </div>
              <div class="price-goshopping">去抢购</div>
            </div>
          </div>
        </li>
      </a>      
    </ul>
  </div>
</template>

<script>
  var url='http://xn--hlr65r.iok.la:22496/TestDeployed/getListByPage?page=50'
  var jsonp=require('jsonp')
  export default {
    name: 'Discount',
    data () {
      return {
        dataList:[]
      }
    },
    methods:{
      filterActualPrice:function(item){
        var y =item.disSize.match(/(\d+)\u5143(?!\u51cf)/)
        return (item.price-y[1]).toFixed(2)
      },
      //优惠券为多少
      //匹配，XX元无门槛，和，满XX元减XX元
      filterDisPrice:function(item){ 
        var y =item.disSize.match(/(\d+)\u5143(?!\u51cf)/)
        return y[1]+'元'
      }
    },
    mounted:function(){
      var self=this
      this.$nextTick(function(){
        jsonp(url,null,function(err,dataList){
          if (err) {
              console.error(err.message);
            } else {
              // console.log('app',dataList)

              self.dataList=dataList.data
             
          }
        })
        }      
      )
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
  width: 1200px;
}
li {
  display: inline-block;
  width: 582px;
  height: 272px;
  margin-bottom: 20px;
  margin-right: 10px;
  border:1px solid #eee;
  background-color: #fffcfd;
}
li img{
  max-width: 272px;
  float: left;
}
a {
  color: #787878;
}
.orignal-price{
  text-decoration: line-through;
  color: #fef181;
}
.theme-color-1{
  color: #fc7e91;
}
.theme-color-4{
  color: #ff435e;
}
.price{
  display: flex;
  justify-content: space-between;
  background-color: #FF435E;
}
.price .price-actual{
  color:#fff;
}
.price .price-origianl{
  text-decoration: line-through;
  color: #fef181;

}
.price .price-goshopping{
  background-color: #fef490;
  color: #f61d5a;
}
</style>



