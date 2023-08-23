import axios from "axios";

const Axiosinstance = axios.create({
    baseURL: `${process.env.REACT_APP_BASE_URL}`
})

export default Axiosinstance;