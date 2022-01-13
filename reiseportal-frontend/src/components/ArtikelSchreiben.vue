<template>
  <div>
    <h1>Artikel schreiben</h1>
    <h4 style="color: darkred">{{message}}</h4>
    <p>Bitte füllen Sie alle folgenden Felder aus!</p>
    <form>
      <p>Title: <input type="text" v-model="title"/></p>

      <p>Authors:</p>
      <p v-for="(auth) in authorsList" v-bind:key="auth.id" style="margin-left: 30px">
        <input type="checkbox" :value="auth.id" v-model="author">
        <label style="margin-left: 5px" >{{auth.firstName}} {{auth.lastName}}</label>
      </p>
      <p>Attraction:
       <select v-model="attraction">
          <option  v-for="(attr) in attractionsList" v-bind:key="attr.id">{{attr.name}}</option>
        </select>
      </p>

      <p>Artikel-Text: <input type="text" v-model="text"></p>
      <button @click="sendRequest()" type="submit">Artikel speichern</button>
    </form>

  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "ArtikelSchreiben",
  data() {
    return {
      title: '',
      attraction: '',
      authorsList: [],
      attractionsList: [],
      chosenAuthors: [],
      author: [],
      text: '',
      message:''
    }
  },
  mounted() {
    axios.get("http://localhost:5555/api/article/resources/authors")
        .then((response) => {
          this.authorsList = response.data;
        })
        .catch(error => console.log(error));

    axios.get("http://localhost:5555/api/article/resources/attractions")
        .then((response) => {
          this.attractionsList = response.data;
        })

        .catch(error => console.log(error));
  },
  methods:{
    sendRequest() {
      this.chosenAuthors.length=0;

      if(this.author.length===0){
        console.log(this.author)
        this.message = 'Sie haben keine Autoren gewählt!';
        this.$root.$emit( 'comp', 'schreiben');
        return;
      }
      if(this.attraction === ''){
        this.message = 'Sie haben keine Sehenswürdigkeit gewählt!';
        this.$root.$emit( 'comp', 'schreiben');
        return;
      }
      if(this.text === ''){
        this.message = 'Geben Sie einen Text ein!';
        this.$root.$emit( 'comp', 'schreiben');
        return;
      }
      if(this.title === ''){
        this.message = 'Geben Sie einen Titel ein';
        this.$root.$emit( 'comp', 'schreiben');
        return;
      }


      for(let i = 0; i < this.author.length; i++){
        this.chosenAuthors.push(this.authorsList[this.author[i]-1])
      }

      for (let a = 0; a < this.attractionsList; a++){
        console.log(this.attractionsList[a].name)
        console.log(this.attraction)
        if (this.attractionsList[a].name === this.attraction)
          this.attractionid = this.attractionsList[a].id;
      }

      this.date = new Date();
      var dd = String(this.date.getDate()).padStart(2, '0');
      var mm = String(this.date.getMonth() + 1).padStart(2, '0');
      var yyyy = this.date.getFullYear();
      this.date = yyyy + '-' + mm + '-' + dd;

      axios.post("http://localhost:5555/api/article/resources/articles",{
          id: "0", //sowieso auto increment
          title: this.title,
          text: this.text,
          publicationDate: this.date,
          attraction: {
            id: this.attractionid,
            name: this.attraction
          },
          authors: this.chosenAuthors
      }).catch(error => console.log(error));

      this.$root.$emit( 'comp', 'alleArtikel');
    }
  }
}
</script>

<style scoped>
</style>