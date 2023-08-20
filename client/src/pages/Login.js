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

  };

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
