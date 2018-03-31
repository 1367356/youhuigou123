<template>
  <div id="app">
    <Header />
    <Search />
    <Kind />
    <router-view/>
  </div>
</template>

<script>
import Header from '@/components/Header'
import Search from '@/components/Search'
import KindNav from '@/components/KindNav'

export default {
  name: 'App',
  components:{
  Header,
  Search,
  KindNav
  }
}
</script>

<style lang="scss">

*{
  padding:0px;
  margin:0px;
}
</style>
