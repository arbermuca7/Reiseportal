<template>
  <div class="Start">
    <h1>Statistik</h1>
    <table class="table" style='border:solid 1px;text-align:left;'>
      <thead>
        <tr>
          <th scope="col">Sehenswürdigkeit</th>
          <th scope="col">Beliebtheit</th>
        </tr>
      </thead>
      <tbody v-for="(attraction) in statistik" :key="attraction">
        <tr>
          <td>{{attraction.name}}</td>
          <td>{{attraction.timesVisited}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "Statistik",
  data(){
    return{
      statistik: Object()
    }
  },
  mounted() {
    axios.get("http://localhost:5555/api/statistic/resources/attractions/statistics")
        .then((response) => {
          if(response != null){
            this.statistik = response.data
            /*this.statistik.length=0
            for (var i = 0; i < response.data.length; i++){
              this.statistik.push(response.data[i])
            }*/
            console.log(this.statistik)
          }
        })
        .catch(error => console.log(error));
  }
}
</script>

<style scoped>

</style>