import Axiosinstance from "../auth/AxiosConfig";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import TextEditor from "./TextEditor";
import "../styles/components/EditForm.css";

export const EditForm = ({ data }) => {
  const navigate = useNavigate();
  const [newNickName, setNewNickName] = useState("");
  const [newTitle, setNewTitle] = useState("");
  const [about, setAbout] = useState("");

  const patchHandler = async () => {
    const accessToken = localStorage.getItem("accessToken");
    const memberId = data.memberId;
    const headers = {
      Authorization: `Bearer${accessToken}`,
    };
    const requestBody = {
      nickName: newNickName,
      title: newTitle,
      aboutMe: about,
    };
    try {
      const response = await Axiosinstance.patch(
        `/members/${memberId}`,
        requestBody,
        {
          headers,
        }
      );
      console.log("Updated successfully");
      navigate(`/members/${memberId}`);
      // 새로고침 안되면
      // window.location.href =`/members/${memberId}`;
    } catch (error) {
      console.error("Error updating profile:", error);
    }
  };

  return (
    <div className="edit-form__container">
      <div className="edit-form__title">
        <h1>Edit Your Profile</h1>
      </div>
      <div className="edit-form__content">
        <div className="edit-form__form-container">
          <div className="edit-form__form">
            <label className="edit-form__form-title" htmlFor="edit-name">
              Display Name
            </label>
            <input
              id="edit-name"
              placeholder={data.nickName}
              type="text"
              value={newNickName}
              onChange={(e) => setNewNickName(e.target.value)}
            />
          </div>
          <div className="edit-form__form">
            <label className="edit-form__form-title" htmlFor="edit-title">
              Title
            </label>
            <input
              id="edit-title"
              type="text"
              value={newTitle}
              onChange={(e) => setNewTitle(e.target.value)}
            />
          </div>
        </div>
        <TextEditor setBody={setAbout} />
        <div className="edit-form__buttons">
          <button
            className="edit-form__button save"
            type="button"
            onClick={patchHandler}
          >
            Save profile
          </button>
          <button className="edit-form__button cancel" type="button">
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
};
