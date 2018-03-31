<template>
 <header id="header"> 
     <p id="header-left">每天万款优惠券秒杀，一折限时疯抢！欢迎您</p>
     <ul id="header-right">
       <li>设为首页</li>
       <li>加入收藏</li>
       <li id="header-service"><a href="http://www.baidu.com" target="_blank">遇到购物问题？联系我></a></li>
     </ul>
 </header>
</template>

<script>
 
  export default {
    name: 'Header',
    data:function(){
      return {}
    },
    methods:{
     
    },
    mounted:function(){
      this.$nextTick(function(){
        console.log('header is loaded')
      })
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">

#header{
  color: $textPrimaryColor-1;
  display: flex;
  justify-content: space-between;
  #header-right{
    
    cursor: pointer;
    li{
      display:inline-block;
      padding: 0 10px 0 10px;
    }
  }
}
#header-service a{
  color:#8da7cb;
}
#header-service a:visited{
  color:#8da7cb;
}
</style>



