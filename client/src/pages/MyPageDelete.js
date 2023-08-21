import UsernameBox from "../components/UsernameBox";
import React, { useState } from "react";
import Modal from "../components/Modal";
import "../styles/pages/MyPageDelete.css";
import Menu from "../components/Menu";

const MyPageDelete = () => {
  const [isChecked, setIsChecked] = useState(false);

  const handleCheckboxChange = () => {
    setIsChecked(!isChecked);
  };

  return (
    <div className="main-container">
      <div className="side-menu">
        <Menu />
      </div>
      <div className="delete-main">
        <UsernameBox />
        <div className="delete-profile__container">
          <div className="delete-profile__title">Delete Profile</div>
          <div className="delete-profile__desc">
            Before confirming that you would like your profile deleted, we'd
            like to take a moment to explain the implications of deletion:
          </div>
          <ul className="delete-profile__content">
            <li className="delete-profile__item">
              Deletion is irreversible, and you will have no way to regain any
              of your original content, should this deletion be carried out and
              you change your mind later on.
            </li>
            <li className="delete-profile__item">
              Your questions and answers will remain on the site, but will be
              disassociated and anonymized (the author will be listed as
              "user22361598") and will not indicate your authorship even if you
              later return to the site.
            </li>
          </ul>
          <div className="delete-profile__notice">
            Confirming deletion will only delete your profile on Stack Overflow
            - it will not affect any of your other profiles on the Stack
            Exchange network. If you want to delete multiple profiles, you'll
            need to visit each site separately and request deletion of those
            individual profiles.
          </div>
          <label className="delete-profile__confirmation">
            <input
              type="checkbox"
              checked={isChecked}
              onClick={handleCheckboxChange}
              className="delete-profile__checkbox"
            />{" "}
            <span className="delete-profile__confirmation-text">
              I have read the information stated above and understand the
              implications of having my profile deleted. I wish to proceed with
              the deletion of my profile.
            </span>
          </label>
        </div>
        <Modal />
      </div>
    </div>
  );
};

export default MyPageDelete;
