const Header = () => {
  return (
    <header>
      <a href>
        <img src={require("../static/logo.png")} alt="stackoverflow logo" />
      </a>
      <div>
        <div>profile</div>
        <nav>
          <ul>
            <li><img src={require("../static/inbox.png")} alt="alarm-inbox" /></li>
            <li><img src={require("../static/trophy.png")} alt="achievement" /></li>
            <li><img src={require("../static/help.png")} alt="help" /></li>
            <li><img src={require("../static/stack.png")} alt="stack-exchange" /></li>
          </ul>
        </nav>
      </div>
    </header>
  );
};

export default Header;
