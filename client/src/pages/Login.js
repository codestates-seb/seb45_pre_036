import "../styles/pages/Login.css";
import axios from "axios";
// import jwt from "jsonwebtoken";
import { useContext, useState } from "react";
import { AuthContext } from "../auth/AuthContext";

const Login = () => {
  const { setAuthState } = useContext(AuthContext);
  const [ enteredEmail, setEmail ] = useState('');
  const [ enteredPassword, setPassword] = useState(''); 
  const [ errorMessage, setErrorMessage] = useState(null);


  const emailChangeHandler = (e) => {
    setEmail(e.target.value);
  }

  const passwordChangeHandler = (e) => {
    setPassword(e.target.value);
  }

  const loginHandler = async (e) => {
    e.preventDefault();
    try {
      
      const res = await axios.post("", {
        email: enteredEmail,
        password: enteredPassword,
      });
      
      const { memberId } = res.data;
      // 어떤 키값을 가지고 오는지 확인 필요
      setAuthState({ isLoggedIn: true, memberId });
  
      const accessToken = res.headers['access-token'];
      const refreshToken = res.headers['refrech-token'];
  
      localStorage.setItem('accessToken', accessToken);
      localStorage.setItem('refreshToken', refreshToken);
      
    } catch (error) {
      setErrorMessage('Invalid email or password');
    }
  };

  // 토큰 유효성 체크 및 토큰 요청

  return (
    <container className="login-container">
      <div className="login-form__logo">Logo</div>
      <form className="login-form__form" onSubmit={loginHandler}>
        <div className="login-form__item top">
          <label htmlFor="email">Email</label>
          <input id="email" type="email" value={enteredEmail} onChange={emailChangeHandler}></input>
        </div>
        <div className="login-form__item">
          <label htmlFor="password">Password</label>
          <input type="password" id="password" value={enteredPassword} onChange={passwordChangeHandler}></input>
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
  );
};

export default Login;
