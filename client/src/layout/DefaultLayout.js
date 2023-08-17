import { Outlet } from "react-router-dom";
import Header from "../components/Header";
import Menu from "../components/Menu";
import '../styles/layout/DefaultLayout.css';

export const DefaultLayout = () => {
  return (
    <>
      <Header />
      <div className="container">
        <div className="menu-container">
          <Menu />
        </div>
        <div className="main-container">
          <Outlet />
        </div>
      </div>
    </>
  );
};
