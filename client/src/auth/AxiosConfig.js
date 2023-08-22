import axios from "axios";

const Axiosinstance = axios.create({
    baseURL: 'http://ec2-3-34-99-175.ap-northeast-2.compute.amazonaws.com:8080'
})

export default Axiosinstance;