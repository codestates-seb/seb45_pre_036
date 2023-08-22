import axios from "axios";

const Axiosinstance = axios.create({
    baseURL: `${process.env.BASE_URL}`
})

export default Axiosinstance;