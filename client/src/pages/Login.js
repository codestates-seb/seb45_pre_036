import "../styles/pages/Login.css";
import axios from "axios";
// import jwt from "jsonwebtoken";
import { useContext, useState } from "react";
import { AuthContext } from "../auth/AuthContext";
import Header from "../components/Header";
import Footer from "../components/Footer";

const Login = () => {
  const { setAuthState } = useContext(AuthContext);
  const [enteredEmail, setEmail] = useState("");
  const [enteredPassword, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState(null);

  const emailChangeHandler = (e) => {
    setEmail(e.target.value);
  };

  const passwordChangeHandler = (e) => {
    setPassword(e.target.value);
  };

  const loginHandler = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post("http://localhost:5050/auth/login", {
        email: enteredEmail,
        password: enteredPassword,
      });

      const { memberId } = res.data;

      // 사실 어떤 게 맞는지 서버 테스트 해봐야 함.
      const accessToken = res.headers["Authorization"];
      // const accessToken = res.data.headers["Authorization"];
      // const accessToken = res.data.accessToken;
      // const accessToken = res.data.Authorization;

      // 액세스 토큰에 맞춰서 변경 필요
      const refreshToken = res.headers["Refresh"];

      // 일단 로컬에 저장.
      localStorage.setItem("accessToken", accessToken);
      localStorage.setItem("refreshToken", refreshToken);

      setAuthState({ isLoggedIn: true, memberId });

    } catch (error) {
      setErrorMessage("Invalid email or password");
    }
  };

  // 토큰 유효성 체크 및 토큰 요청

  return (
    <>
      {/* <Header /> */}
      <container className="login-container">
        <div className="login-form__logo"><img src={require("../static/logo-short.png")} alt="stackoverflow logo img only" /></div>
        <form className="login-form__form" onSubmit={loginHandler}>
          <div className="login-form__item top">
            <label htmlFor="email">Email</label>
            <input
              id="email"
              type="email"
              value={enteredEmail}
              onChange={emailChangeHandler}
            ></input>
          </div>
          <div className="login-form__item">
            <label htmlFor="password">Password</label>
            <input
              type="password"
              id="password"
              value={enteredPassword}
              onChange={passwordChangeHandler}
            ></input>
          </div>
          <div className="login-form__item login">
            <button className="login-form__button">Log in</button>
          </div>
          <div className="login-form__item divider">
            <hr />
          </div>
          <div className="login-form__item bottom">
            <button
              type="button"
              className="login-form__button oauth__github-button"
            >
              <img className="login-form__github-logo" src="" alt="" />
              Log in with Github
            </button>
          </div>
        </form>
        <p className="login-form__signup-text">
          Don't have an account?{" "}
          <a href className="login-form__signup-link">
            Sign up
          </a>
        </p>
      </container>
    </>
  );
};

export default Login;
