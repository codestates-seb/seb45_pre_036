import React, { useState } from "react";
import "../styles/components/DeleteProfile.css";
import Axiosinstance from "../auth/AxiosConfig";

const DeleteProfile = ({ memberId }) => {
  const [isChecked, setIsChecked] = useState(false);

  const handleCheckboxChange = () => {
    setIsChecked(!isChecked);
  };

  const deleteHandler = async () => {
    if (isChecked) {
      const isConfirmed = window.confirm("정말 탈퇴하시겠습니까?");
      if (isConfirmed) {
        try {
          const accessToken = localStorage.getItem("accessToken");
          const headers = {
            Authorization: `Bearer${accessToken}`,
          };
          const res = await Axiosinstance.delete(`/members/${memberId}`, {
            headers,
          });
          localStorage.removeItem("accessToken");
          localStorage.removeItem("refreshToken");
          window.location.href = `/signup`;
        } catch (err) {
          console.log(err);
        }
      }
    }
  };

  return (
    <div className="delete-main">
      <div className="delete-profile">
        <h1 className="delete-profile__title">Delete Profile</h1>
        <div className="delete-profile__content">
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
          <div className="delete-checkbx__container">
            <input
              id="delete-checkbox"
              type="checkbox"
              onClick={handleCheckboxChange}
              className="delete-profile__checkbox"
            />
            <label
              htmlFor="delete-checkbox"
              className="delete-profile__confirmation"
            >
              I have read the information stated above and understand the
              implications of having my profile deleted. I wish to proceed with
              the deletion of my profile.
            </label>
          </div>
          <div className="delete-button__container">
            <button
              className="delete-button"
              type="button"
              disabled={!isChecked}
              onClick={deleteHandler}
            >
              Delete Profile
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default DeleteProfile;
