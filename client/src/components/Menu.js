import "../styles/components/Menu.css";
import { Link } from "react-router-dom";

const Menu = () => {
  return (
    <nav className="menu__container">
      <ul className="menu">
        <li className="menu__item">
          <Link to={"/"}>Home</Link>
        </li>
        <ul className="submenu">
          <li className="submenu__item">
            <Link to={"/"}>Questions</Link>
          </li>
          <li className="submenu__item">User profile</li>
          {/* Link to myPage */}
        </ul>
      </ul>
    </nav>
  );
};

export default Menu;
