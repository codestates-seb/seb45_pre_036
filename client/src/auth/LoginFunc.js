import axios from "axios";

export const LoginFunc = async (email, password, setAuthState, setErrorMessage) => {
  try {
    const res = await axios.post("http://localhost:8080/auth/login", {
      email: email,
      password: password,
    });

    const { memberId } = res.data;

    const accessToken = res.headers["Authorization"];
    const refreshToken = res.headers["Refresh"];

    localStorage.setItem("accessToken", accessToken);
    localStorage.setItem("refreshToken", refreshToken);

    setAuthState({ isLoggedIn: true, memberId });
  } catch (error) {
    setErrorMessage("Invalid email or password");
  }
};

  // 응답에서 유효하지 않은 토큰이라고 하면 리프레쉬 토큰 헤더에 넣어서 보내야 함. 
  