import axios from "axios";

export const LoginFunc = async (email, password, setAuthState, setErrorMessage) => {
  try {
    const res = await axios.post("http://localhost:5050/auth/login", {
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