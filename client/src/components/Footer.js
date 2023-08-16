import "../styles/components/Footer.css";

const Footer = () => {
  return (
    <>
      <main className="test"></main>
      <footer className="footer__container">
        <div className="footer__logo-container">
          <img
            src={require("../static/logo-short.png")}
            alt="stackoverflow logo img only"
          />
        </div>
        <div className="footer__content">
          <h5 className="footer__content-title">STACK OVERFLOW</h5>
          <ul className="footer__content-list">
            <li className="footer__content-item">
              <a href>Questions</a>
            </li>
            <li className="footer__content-item">
              <a href>Help</a>
            </li>
          </ul>
        </div>
      </footer>
    </>
  );
};

export default Footer;
