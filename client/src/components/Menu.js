const Menu = () => {
  return (
    <nav className="menu__container">
      <ul className="menu">
        <li className="menu__item">
          <a href>Home</a>
        </li>
        <ul className="submenu">
          <li className="submenu__item">
            <a href>Questions</a>
          </li>
          <li className="submenu__item">
            <a href>User profile</a>
          </li>
        </ul>
      </ul>
    </nav>
  );
};

export default Menu;
