import '../styles/pages/Logout.css';

const Logout = () => {
  return (
    <div className="logout-confirm">
      <h1 className="logout-confirm__title">Are you sure<br/>you want to log out?</h1>
      <div className="logout-confirm__container">
        <div className="logout-confirm__logo-container">
          <img
            src={require("../static/logo-short.png")}
            alt="stackoverflow logo img only"
          />
          <p className="logout-confirm__site-name">stackoverflow.com</p>
        </div>
        <div className="logout-confirm__checkbox">
          <input id="logout-checkbox" type="checkbox"></input>
          <label htmlFor="logout-checkbox">I agree to log out of this website.</label>
        </div>
        <div className="logout-confirm__buttons">
          <button className="logout-confirm__buttons logout" type="button">Log out</button>
          <button className="logout-confirm__buttons cancel" type="button">Cancel</button>
        </div>
      </div>
    </div>
  );
};

export default Logout;