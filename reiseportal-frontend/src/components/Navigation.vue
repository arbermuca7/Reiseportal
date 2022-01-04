<template>
  <div class="Start">
    <h1>Reiseportal</h1>
    <nav style="margin-bottom: 20px; margin-top: 20px">
      <button style="background-color: #ff9d93" @click="currentComponent='alleArtikel'">Artikelübersicht</button>
      <button style="background-color: #ffffff" @click="currentComponent='schreiben'">Artikel verfassen</button>
      <button style="background-color: #ff9d93" @click="currentComponent='stats'">Statistik</button>
    </nav>
    <component :id=artikelId v-bind:is="currentComponent"></component>
  </div>

</template>

<script>
import alleArtikel from "./Artikelübersicht"
import schreiben from "./ArtikelSchreiben"
import stats from "./Statistik"
import artikel from "./ArtikelLesen"

let currentComponent = alleArtikel

export default {
  name: "Homepage",
  data: function (){
    return{
      currentComponent,
      artikelId: 0
    }
  },
  components: {
    alleArtikel,
    schreiben,
    stats,
    artikel
  },
  mounted: function() {
    this.$root.$on('comp', (text)=> {
      this.currentComponent = text;
    })

    this.$root.$on('artikelId', id => {
      this.currentComponent = 'artikel';
      this.artikelId = id;
    })
  }
}
</script>

<style scoped>
.Start nav{
  width: 100vw;
}
.Start button{
  width: 100vw;
}
</style>