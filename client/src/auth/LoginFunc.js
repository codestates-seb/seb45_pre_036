// import axios from "axios";
import Axiosinstance from "./AxiosConfig";

export const LoginFunc = async (email, password) => {
  try {
    const res = await Axiosinstance.post("/auth/login", {
      email: email,
      password: password,
    });

    // const { memberId } = res.data;
    // payload에 있음.

    const auth = res.headers.getAuthorization();

    const accessToken = auth.split("Bearer")[1];
    const refreshToken = res.headers.get("Refresh");

    localStorage.setItem("accessToken", accessToken);
    localStorage.setItem("refreshToken", refreshToken);


    return true
  } catch (error) {
    console.log(error);
    return false
  }
};
