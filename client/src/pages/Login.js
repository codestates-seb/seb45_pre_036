import "../styles/pages/Login.css";
import { useState } from "react";
import { Link } from "react-router-dom";
import { LoginFunc } from "../auth/LoginFunc";

const Login = () => {
  const [enteredEmail, setEmail] = useState("");
  const [enteredPassword, setPassword] = useState("");
  // const [errorMessage, setErrorMessage] = useState(null);

  const emailChangeHandler = (e) => {
    setEmail(e.target.value);
  };

  const passwordChangeHandler = (e) => {
    setPassword(e.target.value);
  };

  const loginHandler = async (e) => {
    e.preventDefault();

    LoginFunc(enteredEmail, enteredPassword);

    // navigate to main page -> App()리렌더링인지 확인하기 -> X; 어찌됐든 state의 변화가 있어야...
    // 안 되면 refresh해서 /questions로.
    // window.location.href = '/';
    // 이렇게 하면...state 사라짐.

    
    // 이하 로직을 Loginfunc에 담음: 회원가입 후 로그인 다시하는 과정 없애기 위해서
    // try {
    //   const res = await axios.post("http://localhost:8080/auth/login", {
    //     email: enteredEmail,
    //     password: enteredPassword,
    //   });

    //   const { memberId } = res.data;

    //   // 사실 어떤 게 맞는지 서버 테스트 해봐야 함.
    //   const accessToken = res.headers["Authorization"];
    //   // const accessToken = res.data.headers["Authorization"];
    //   // const accessToken = res.data.accessToken;
    //   // const accessToken = res.data.Authorization;

    //   // 액세스 토큰에 맞춰서 변경 필요
    //   const refreshToken = res.headers["Refresh"];

    //   // 일단 로컬에 저장.
    //   localStorage.setItem("accessToken", accessToken);
    //   localStorage.setItem("refreshToken", refreshToken);

    //   setAuthState({ isLoggedIn: true, memberId });

    // } catch (error) {
    //   setErrorMessage("Invalid email or password");
    // }
  };

  // 토큰 유효성 체크 및 토큰 요청은 AuthContext.js로 빼냄. 

  return (
    <>
      <div className="login-container">
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
          <Link to={'/signup'} className="login-form__signup-link">
            Sign up
          </Link>
        </p>
      </div>
    </>
  );
};

export default Login;
