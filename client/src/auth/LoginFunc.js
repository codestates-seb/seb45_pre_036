import axios from "axios";

export const LoginFunc = async (email, password, setErrorMessage) => {
  try {
    const res = await axios.post("http://localhost:8080/auth/login", {
      email: email,
      password: password,
    });

    // const { memberId } = res.data;
    // payload에 있음. 

    const accessToken = res.headers["Authorization"];
    const refreshToken = res.headers["Refresh"];

    localStorage.setItem("accessToken", accessToken);
    localStorage.setItem("refreshToken", refreshToken);

    // setAuthState({ isLoggedIn: true, accessToken: accessToken, memberId });
    // 로그인 함수에서 할 필요 없음. 

  } catch (error) {
    setErrorMessage("Invalid email or password");
  }
};
  