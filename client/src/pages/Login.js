import "../styles/pages/Login.css";

const Login = () => {
  return (
    <container className="login-container">
      <div className="login-form__logo">Logo</div>
      <form className="login-form__form" onSubmit>
        <div className="login-form__item top">
          <label htmlFor="email">
            Email
          </label>
          <input id="email" type="email"></input>
        </div>
        <div className="login-form__item">
          <label htmlFor="password">Password</label>
          <input type="password" id="password"></input>
        </div>
        <div className="login-form__item login">
          <button className="login-form__button">Log in</button>
        </div>
        <div className="login-form__item divider">
          <hr />
        </div>
        <div className="login-form__item bottom">
          <button type='button' className="login-form__button oauth__github-button" >
            <img className="login-form__github-logo" src="" alt="" />Log in with Github</button>
        </div>
      </form>
      <p className="login-form__signup-text">
        Don't have an account?{" "}
        <a href className="login-form__signup-link">Sign up</a>
      </p>
    </container>
  );
};

export default Login;
