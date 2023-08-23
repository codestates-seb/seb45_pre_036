import "../styles/pages/Logout.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Logout = () => {
  const navigate = useNavigate();
  const [isChecked, setIsChecked] = useState(false);
  const [alert, setAlert] = useState(false);

  const logoutHandler = () => {
    if (isChecked) {
      localStorage.removeItem("accessToken");
      localStorage.removeItem("refreshToken");
      window.location.href='/';
      // 라우터 문제 시에 여기부터 체크
    } else {
      setAlert(true);
    }
  };

  return (
    <div className="logout-confirm">
      <h1 className="logout-confirm__title">
        Are you sure
        <br />
        you want to log out?
      </h1>
      <div className="logout-confirm__container">
        <div className="logout-confirm__logo-container">
          <img
            src={require("../static/logo-short.png")}
            alt="stackoverflow logo img only"
          />
          <p className="logout-confirm__site-name">stackoverflow.com</p>
        </div>
        <div className="logout-confirm__checkbox">
          <input
            className="hidden-checkbox"
            id="logout-checkbox"
            type="checkbox"
            onChange={(e) => {
              setIsChecked(e.target.checked);
              setAlert(false);
            }}
          ></input>
          <label
            className={`custom-checkbox ${alert ? "alert" : ""}`}
            htmlFor="logout-checkbox"
          >
            <span>I agree to log out of this website.</span>
          </label>
        </div>
        <div className="logout-confirm__buttons">
          <button
            className="logout-confirm__buttons logout"
            type="button"
            disabled={!isChecked}
            onClick={logoutHandler}
          >
            Log out
          </button>
          <button className="logout-confirm__buttons cancel" type="button" onClick={()=>navigate('/')}>
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
};

export default Logout;
