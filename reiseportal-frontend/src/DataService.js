import http from '../http-common'

class DataService {
    getAll() {
        return http.get("/api/article/resources/articles")
    }
}

export default new DataService()