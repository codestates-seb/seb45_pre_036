import axios from "axios";

const Axiosinstance = axios.create({
    baseURL: 'http://ec2-13-125-134-114.ap-northeast-2.compute.amazonaws.com:8080'
})

export default Axiosinstance;