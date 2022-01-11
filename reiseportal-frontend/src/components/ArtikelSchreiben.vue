<template>
  <div>
    <h1>Artikel schreiben</h1>
    <p>Bitte füllen Sie alle folgenden Felder aus!</p>
    <form>
      <p>Title: <input type="text" v-model="title"/></p>

      <p>Authors:</p>
        <p style="margin-left: 30px">
          <input type="checkbox" id="a1" value="Norbert Niemand" v-model="author">
          <label style="margin-left: 5px" for="a1">Norbert Niemand</label>
        </p>
        <p style="margin-left: 30px">
          <input type="checkbox" id="a2" value="Aida Omic" v-model="author">
          <label style="margin-left: 5px"  for="a2">Aida Omic</label>
        </p>
        <p style="margin-left: 30px">
          <input type="checkbox" id="a3" value="Arber Muca" v-model="author">
          <label style="margin-left: 5px"  for="a3">Arber Muca</label>
        </p>
        <p style="margin-left: 30px">
          <input type="checkbox" id="a4" value="Sabrine Nirgendwer" v-model="author">
          <label style="margin-left: 5px"  for="a4">Sabrine Nirgendwer</label>
        </p>

      <p>Attraction:
       <select v-model="attraction">
          <option>Stephansdom</option>
          <option>Schloss Schoenbrunn</option>
          <option>Wiener Prater</option>
          <option>Schloss Belverdere</option>
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
      author: [],
      text: ''
    }
  },
  methods:{
    sendRequest() {
      let authorList = [];
      //authorList.length=0;
      if (this.author.includes('Norbert Niemand')) {
        authorList.push({
          id: 1,
          sex: "MALE",
          firstName: "Norebert",
          lastName: "Niemand",
          payment: 0
        });
      }if (this.author.includes('Aida Omic')) {
        authorList.push({
          id: 2,
          sex: "FEMALE",
          firstName: "Aida",
          lastName: "Omic",
          payment: 0
        });
      }if (this.author.includes('Arber Muca')) {
        authorList.push({
          id: 3,
          sex: "MALE",
          firstName: "Arber",
          lastName: "Muca",
          payment: 0
        });
      }if(this.author.includes('Sabrine Nirgendwer')){
        authorList.push({
        id: 4,
        sex: "FEMALE",
        firstName: "Sabrine",
        lastName: "Nirgendwer",
        payment: 0
        });
      }

      switch (this.attraction){
        case 'Stephansdom':
          this.attractionid = 1;
          break;
        case 'Schloss Schoenbrunn':
          this.attractionid = 2;
          break;
        case 'Wiener Prater':
          this.attractionid = 3;
          break;
        case 'Schloss Belvedere':
          this.attractionid = 4;
          break;
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
          authors: authorList
      })
          .catch(error => console.log(error));

      this.$root.$emit( 'comp', 'alleArtikel');
    }
  }
}
</script>

<style scoped>
</style>