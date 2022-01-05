<template>
  <div>
    <h1>Artikel schreiben</h1>
    <p>Bitte füllen Sie alle folgenden Felder aus!</p>
    <form>
      <p>Title: <input type="text" v-model="title"/></p>

      <p>Author:
        <select v-model="author">
          <option>Norbert Niemand</option>
          <option>Aida Omic</option>
          <option>Arber Muca</option>
          <option>Sabrine Nirgendwer</option>
        </select>
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
/*let authorid;
let attractionid;
let sex;
let date;*/


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
    sendRequest(){
      //Logik fürs zusammenfügen des Artikels
     /* switch (aut){
        case 'Norbert Niemand':
          authorid = 1;
          sex = "MALE";
          break;
        case 'Aida Omic':
          authorid = 2;
          sex = "FEMALE";
          break;
        case 'Arber Muca':
          authorid = 3;
          sex = "MALE";
          break;
        case 'Sabrine Nirgendwer':
          authorid = 4;
          sex = "MALE"
          break;
      }

      switch (att){
        case 'Stephansdom':
          attractionid = 1;
          break;
        case 'Schloss Schoenbrunn':
          attractionid = 2;
          break;
        case 'Wiener Prater':
          attractionid = 3;
          break;
        case 'Schloss Belvedere':
          attractionid = 4;
          break;
      }

      date = new Date().getFullYear()+'-'+(new Date().getMonth()+1)+'-'+new Date().getDate();
*/

      //Test mit angegebenen Werten um den Aufruf zu vereinfachen
      //Dieser Aufruf funktioniert nicht
      axios.post("http://localhost:5555/api/article/resources/articles",{
      id: 4,
          title: "Mein erster Titel",
          text: "Lorem Ipsum Lorem Ipsum Lorem Ipsum",
          publicationDate: "2022-01-04",
          attraction: {
            id: 4,
            name: "Schloss Belvedere"
          },
          author: [ {
            id: 2,
            sex: "FEMALE",
            firstName: "Martina",
            lastName: "Musterfrau",
            payment: 0
          }]
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