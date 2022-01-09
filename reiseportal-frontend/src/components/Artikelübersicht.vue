<template>
  <div>
    <h1>Artikelübersicht</h1>
    <ul v-for="(value, index) in articles" v-bind:key="index">
      <li>Artikel {{index+1}}: {{value}} <button @click="showArticle(index)">Artikel Lesen</button></li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'
let articles = [];

function sendRequest() {
  axios.get("http://localhost:5555/api/article/resources/articles")
      .then((response) => {
        articles.length = 0
        for (var i = 0; i < response.data.length; i++){
          articles.push(response.data[i])
        }
      })
      .catch(error => console.log(error));
}

export default {
  name: "alleArtikel",
  data(){
    return{
      articles
    }
  },
  methods: {
    showArticle(id) {
      this.$root.$emit( 'comp', 'artikel');
      this.$root.$emit( 'artikelId', id+1);
    }
  },
  mounted() {
    sendRequest()
  }
}
</script>

<style scoped>

</style>