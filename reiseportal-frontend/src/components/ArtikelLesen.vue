<template>
<div style="text-align: center">
  <br>
  <h1 style="display: inline; padding-right: 20px; font-size: 40px; font-weight: bold">{{article.title}}</h1>
  <p style="font-size: 10px; display: inline">Erstellt am {{article.publicationDate}}</p>
  <br><br>

  <p style="display: inline; font-weight: bold">Autoren: </p><p style="display: inline; font-style: italic" v-for="(author, index) in authorsList" v-bind:key="index">{{author}}; </p>
  <br><br>
  <h5 style="font-style: italic; font-weight: bolder">Sehenswürdigkeit: {{attraction.name}}</h5>
  <p>{{article.text}}</p>
  <br>
</div>
</template>

<script>
import axios from 'axios'

export default {
  name: "ArtikelLesen",
  props: ['id'],
  data(){
    return{
      article: Object(),
      authorsList: [],
      attraction: Object()
    }
  },
  mounted() {
    axios.get("http://localhost:5555/api/article/resources/articles/"+ this.id)
        .then((response) => {
          this.article = response.data
          for (var i = 0; i < response.data.authors.length; i++){
            this.authorsList.push(response.data.authors[i].firstName + " " + response.data.authors[i].lastName)
          }
          this.attraction = response.data.attraction
        })
        .catch(error => console.log(error));
  }
}
</script>

<style scoped>

</style>