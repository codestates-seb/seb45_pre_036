import "../styles/pages/Signup.css";
import { Link } from "react-router-dom";
import { LoginFunc } from "../auth/LoginFunc";
import { useContext, useState } from "react";
import { AuthContext } from "../auth/AuthContext";
import { useNavigate } from "react-router-dom";
import axios from "axios";

// sign up 하고 그 state받아서 로그인으로 내린 담에 로그인 요청도 갈 수 있게 해야 함.

const Signup = () => {
  const [newEmail, setNewEmail] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [newName, setNewName] = useState("");
  const navigate = useNavigate();

  const newNameChangeHandler = (e) => {
    setNewName(e.target.value);
  };

  const newEmailChangeHandler = (e) => {
    setNewEmail(e.target.value);
  };

  const newPasswordChangeHandler = (e) => {
    setNewPassword(e.target.value);
  };

  const signupHandler = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post(
        "http://ec2-13-125-169-3.ap-northeast-2.compute.amazonaws.com:8080/members/signup",
        {
          nickName: newName,
          email: newEmail,
          password: newPassword,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      console.info("status", res.status);
    } catch (error) {
      console.log(error);
    }

    LoginFunc(newEmail, newPassword);
    navigate("/");
  };

  return (
    <div className="signup-page">
      <div className="signup">
        <container className="signup-message">
          <h1 className="signup-message__title">Welcome to stackoverflow</h1>
        </container>
        <container className="signup-container">
          <div className="login-form__logo">
            <img
              src={require("../static/logo-short.png")}
              alt="stackoverflow logo img only"
            />
          </div>
          <form className="signup-form__form" onSubmit={signupHandler}>
            <div className="signup-form__item">
              <label htmlFor="nickname">Display name</label>
              <input
                id="nickname"
                type="text"
                value={newName}
                onChange={newNameChangeHandler}
              ></input>
            </div>
            <div className="signup-form__item">
              <label htmlFor="email">Email</label>
              <input
                id="email"
                type="email"
                value={newEmail}
                onChange={newEmailChangeHandler}
              ></input>
            </div>
            <div className="signup-form__item">
              <label htmlFor="password">Password</label>
              <input
                type="password"
                id="password"
                value={newPassword}
                onChange={newPasswordChangeHandler}
              ></input>
              <p>
                Passwords must contain at least eight characters, including at
                least 1 letter and 1 number.
              </p>
            </div>
            <div className="signup-form__item">
              <button className="signup-form__button signup">Sign up</button>
            </div>
            <div className="signup-form__item">
              <p>
                By By clicking “Sign up”, you agree to our{" "}
                <span>terms of service</span> and acknowledge that you have read
                and understand our <span>privacy policy</span> and{" "}
                <span>code of conduct</span>.
              </p>
            </div>
            <div className="signup-form__item">
              <button
                type="button"
                className="signup-form__button oauth__github-button"
              >
                <img className="signup-form__github-logo" src="" alt="" />
                Sign up with Github
              </button>
            </div>
          </form>
          <p className="signup-form__login-text">
            Already have an account?{" "}
            <Link to={"/"} className="signup-form__login-link">
              Log in
            </Link>
          </p>
        </container>
      </div>
    </div>
  );
};

export default Signup;
