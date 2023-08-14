import '../styles/pages/Login.css';

const Login = () => {
  return (
    <container className="login-container">
      <div className="login-form__logo">Logo</div>
      <div className="login-form__container">
        <form className="login-form__form" onSubmit>
          <div className="login-form__item">
            <label className="login-form__label" htmlFor="email">
              Email
            </label>
            <input className="login-form__input" id="email" type="text"></input>
          </div>
          <div className="login-form__item">
            <label className="login-form__label" htmlFor="password">
              Password
            </label>
            <input
              className="login-form__input"
              type="password"
              id="password"
            ></input>
          </div>
          <div className="login-form__item">
            <button className="login-form__button">Log in</button>
          </div>
          <hr className="login-form__divider" />
          <div className="login-form__item">
            <button className="login-form__github-button" type="submit">
              <img className="login-form__github-logo" src="" alt="" />
              Log in with Github
            </button>
          </div>
        </form>
      </div>
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
