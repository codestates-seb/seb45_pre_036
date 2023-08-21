import "../styles/components/Header.css";
import { Link } from "react-router-dom";
import useAuth from "../auth/useAuth";
import jwtDecode from 'jwt-decode';

const LoginHeader = () => {
  return (
    <header className="login-header__container">
      <Link to={'/'} className="login-header__logo" >
        <img src={require("../static/logo.png")} alt="Stack Overflow logo" />
      </Link>
      <div className="login-header__nav-container">
        <Link to={'/login'}><button className="login-header__nav-item login-btn">Login</button></Link>
        <Link to={'/signup'}><button className="login-header__nav-item signup-btn">Signup</button></Link>
      </div>
    </header>
  );
};

export const UserHeader = ({authState}) => {
  const userName = jwtDecode(authState.accessToken).username;

  return (
    <header className="header__container">
      <Link to={'/'} className="header__logo">
        <img src={require("../static/logo.png")} alt="stackoverflow logo" />
      </Link>
      <div className="header__nav-container">
        <div className="header__nav-username">{userName}</div>
        <nav className="header__nav">
          <ul className="header__nav-list">
            <li className="header__nav-item">
              {/* <div> */}
                <img src={require("../static/inbox.png")} alt="alarm-inbox" />
              {/* </div> */}
            </li>
            <li className="header__nav-item">
              {/* <div> */}
                <img src={require("../static/trophy.png")} alt="achievement" />
              {/* </div> */}
            </li>
            <li className="header__nav-item">
              {/* <div> */}
                <img src={require("../static/help.png")} alt="help" />
              {/* </div> */}
            </li>
            <li className="header__nav-item logout">
              {/* <div> */}
                <Link to={'/logout'}><img
                  src={require("../static/stack.png")}
                  alt="stack-exchange"
                /></Link>
              {/* </div> */}
            </li>
          </ul>
        </nav>
      </div>
    </header>
  );
};

const Header = () => {
  const {authState} = useAuth();

  return (
    <>
    { authState.isLoggedIn ? <UserHeader authState={authState} /> : <LoginHeader />}
    </>
  )
}

export default Header;
