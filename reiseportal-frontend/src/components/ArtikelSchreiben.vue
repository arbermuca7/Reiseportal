<template>
  <div>
    <h1>Artikel schreiben</h1>
    <p>Bitte füllen Sie alle folgenden Felder aus!</p>
    <form>
      <p>Title: <input type="text" v-model="title"/></p>

      <p>Author:
        <select v-model="author">
          <option>Author 1</option>
          <option>Author 2</option>
          <option>Author 3</option>
          <option>Author 4</option>
        </select>
      </p>

      <p>Attraction:
       <select v-model="attraction">
          <option>Attraction 1</option>
          <option>Attraction 2</option>
          <option>Attraction 3</option>
          <option>Attraction 4</option>
        </select>
      </p>

      <p>Artikel-Text: <input type="text" v-model="text"></p>
      <button @click="sendRequest(title, author, attraction, text)" type="submit">Artikel speichern</button>
    </form>

  </div>
</template>

<script>
import axios from 'axios'
let authorid;
let attractionid;
let date;


export default {
  name: "ArtikelSchreiben",
  data() {
    return {
      title: '',
      attraction: '',
      author: '',
      text: ''
    }
  },
  methods:{
    sendRequest(tit, aut, att, tex){
      switch (aut){
        case 'Author 1':
          authorid = 1;
          break;
        case 'Author 2':
          authorid = 2;
          break;
        case 'Author 3':
          authorid = 3;
          break;
        case 'Author 4':
          authorid = 4;
          break;
      }

      switch (att){
        case 'Attraction 1':
          attractionid = 1;
          break;
        case 'Attraction 2':
          attractionid = 2;
          break;
        case 'Attraction 3':
          attractionid = 3;
          break;
        case 'Attraction 4':
          attractionid = 4;
          break;
      }

      date = new Date().getFullYear()+'-'+(new Date().getMonth()+1)+'-'+new Date().getDate();

      console.log(aut.id)
      axios.post("http://localhost:5555/api/article/resources/articles",{
        articles: {
          id: null,
          title: tit,
          text: tex,
          publicationDate: date,
          attraction: {
            id: attractionid,
            name: att
          },
          author: {
            id: authorid,
            sex: null,
            firstName: aut,
            lastName: aut,
            payment: 0
          }
        }
      })
          .then((response) => {
            console.log(response.data)
            //this.articles = response.data
          })
          .catch(error => console.log(error));
      this.$root.$emit( 'comp', 'alleArtikel');
    }
  }
}
</script>

<style scoped>

</style>