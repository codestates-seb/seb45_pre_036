import Header from "../components/Header";
import "../styles/pages/Signup.css";
import { Link } from "react-router-dom";

const Signup = () => {
  return (
    <>
      <Header />
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
          <form className="signup-form__form" onSubmit>
            <div className="signup-form__item top">
              <label htmlFor="nickname">Display name</label>
              <input id="nickname" type="text"></input>
            </div>
            <div className="signup-form__item">
              <label htmlFor="email">Email</label>
              <input id="email" type="email"></input>
            </div>
            <div className="signup-form__item">
              <label htmlFor="password">Password</label>
              <input type="password" id="password"></input>
              <p>
                Passwords must contain at least eight characters, including at
                least 1 letter and 1 number.
              </p>
            </div>
            <div className="signup-form__item signup">
              <button className="signup-form__button">Sign up</button>
            </div>
            <div className="signup-form__item">
              <p>
                By By clicking “Sign up”, you agree to our{" "}
                <span>terms of service</span> and acknowledge that you have read
                and understand our <span>privacy policy</span> and{" "}
                <span>code of conduct</span>.
              </p>
            </div>
            <div className="signup-form__item divider">
              <hr />
            </div>
            <div className="signup-form__item bottom">
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
            <Link to={'/'} className="signup-form__login-link">
              Log in
            </Link>
          </p>
        </container>
      </div>
    </>
  );
};

export default Signup;
