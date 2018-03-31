<template>
  <div class="search">
    <div class="search-wraper">
      <a href="" class="search-title">商品购</a>
      <div class="search-box">
        <input type="text" placeholder="好宝贝，等你搜">
        <button>搜索</button>
      </div>
      <div class="search-icon">
        <i></i>
        <i></i>
        <i></i>
      </div>
    </div>    
  </div>
</template>

<script>
 
  export default {
    name: 'Search',
    data () {
      return {
        
      }
    },
    methods:{
     
    },
    mounted:function(){
        this.$nextTick(function(){
          console.log('Search is loaded')
        })
      
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.search{
  height:80px;
  margin-top:20px;
}
.search-wraper{
  width:1200px;
  margin:0 auto;
  display:flex;
  .search-title{
    font-size:36px;
    color:$textPrimaryColor-2;
    text-decoration:none;
    width:400px;
  }
  .search-box{
    font-size:0;//去除html中的换行导致的inline元素的间距

    input{
      font-size:14px;//去除html中的换行导致的inline元素的间距
      height:36px;
      width:300px;
      border:2px solid $textPrimaryColor-2;
      border-top-left-radius:20px;
      border-bottom-left-radius:20px;
      padding:0 20px;
      outline:none;//去除焦点框


      vertical-align:bottom;//让vertical-align失效
    }

    button{
      font-size:20px;//去除html中的换行导致的inline元素的间距
      color:#fff;
      background-color:$textPrimaryColor-2;
      height:40px;
      padding:7px 23px;
      letter-spacing:2px;
      border:0;//去除button边框
      border-top-right-radius: 20px;
      border-bottom-right-radius: 20px;
      overflow:hidden;
    }
  }
}

</style>



