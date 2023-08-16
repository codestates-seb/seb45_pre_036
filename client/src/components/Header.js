import { useContext } from "react";
import "../styles/components/Header.css";
import {logo} from "../static/logo.png";
import { AuthContext } from "../auth/AuthContext";

const LoginHeader = () => {
  return (
    <header className="login-header__container">
      <a className="login-header__logo" href="/">
        <img src={logo} alt="Stack Overflow logo" />
      </a>
      <div className="login-header__nav-container">
        <button className="login-header__nav-item login-btn">Login</button>
        <button className="login-header__nav-item signup-btn">Signup</button>
      </div>
    </header>
  );
};

const UserHeader = () => {
  return (
    <header className="header__container">
      <a className="header__logo" href>
        <img src={logo} alt="stackoverflow logo" />
      </a>
      <div className="header__nav-container">
        <div className="header__nav-profile">profile</div>
        <nav className="header__nav">
          <ul className="header__nav-list">
            <li className="header__nav-item">
              <div>
                <img src={require("../static/inbox.png")} alt="alarm-inbox" />
              </div>
            </li>
            <li className="header__nav-item">
              <div>
                <img src={require("../static/trophy.png")} alt="achievement" />
              </div>
            </li>
            <li className="header__nav-item">
              <div>
                <img src={require("../static/help.png")} alt="help" />
              </div>
            </li>
            <li className="header__nav-item">
              <div>
                <img
                  src={require("../static/stack.png")}
                  alt="stack-exchange"
                />
              </div>
            </li>
          </ul>
        </nav>
      </div>
    </header>
  );
};

const Header = () => {
  const {authState} = useContext(AuthContext);
  return (
    <>
    { authState.isLoggedIn ? <UserHeader/> : <LoginHeader />}
    </>
  )
}

export default Header;
