<template>
  <div class="kind-nav">
    <ul>
      <li><a href=""><i></i>今日上新</a></li>
      <li><a href="">领券优惠直播</a></li>
      <li><a href="">咚咚抢</a></li>
      <li><a href="">超级人气榜</a></li>
      <li><a href="">9块9包邮</a></li>
    </ul>
  </div>
</template>

<script>
 
  export default {
    name: 'KindNav',
    data () {
      return {
        
      }
    },
    methods:{
     
    },
    mounted:function(){
        this.$nextTick(function(){
          console.log('KindNav is loaded')
        })
      
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.kind-nav{
  background-color: $textPrimaryColor-2;
  ul{
    width: 1200px;
    margin:0 auto;
    display: flex;
    height:38px;
    li{
      padding:0 40px;
      list-style:none;
      line-height:38px;
      a{
        text-decoration:none;
        color:#fff;
      }
    }
    li:hover{
      background-color: #bd166f;
    }
  }
}

</style>



