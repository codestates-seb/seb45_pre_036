import '../styles/components/Header.css';

const Header = () => {
  return (
    <header className="header__container">
      <a className="header__logo" href>
        <img src={require("../static/logo.png")} alt="stackoverflow logo" />
      </a>
      <div className="header__nav-container">
        <div className="header__nav-profile">profile</div>
        <nav className="header__nav">
          <ul className="header__nav-list">
            <li className="header__nav-item"><div><img src={require("../static/inbox.png")} alt="alarm-inbox" /></div></li>
            <li className="header__nav-item"><div><img src={require("../static/trophy.png")} alt="achievement" /></div></li>
            <li className="header__nav-item"><div><img src={require("../static/help.png")} alt="help" /></div></li>
            <li className="header__nav-item"><div><img src={require("../static/stack.png")} alt="stack-exchange" /></div></li>
          </ul>
        </nav>
      </div>
    </header>
  );
};

export default Header;
