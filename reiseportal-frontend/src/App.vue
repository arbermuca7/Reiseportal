<template>
  <MyNavigation/>
</template>

<script>
import MyNavigation from "./components/Navigation"

export default {
  name: 'Home',
  components: {
    MyNavigation
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
