import Axiosinstance from "../auth/AxiosConfig";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import TextEditor from "./TextEditor";

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
    <div>
      <div className="edit-form__title">
        <h1>Edit Your Profile</h1>
      </div>
      <div className="edit-form__form">
        <label>Display Name</label>
        <input
          placeholder={data.nickName}
          type="text"
          value={newNickName}
          onChange={(e) => setNewNickName(e.target.value)}
        />
        <label>Title</label>
        <input
          type="text"
          value={newTitle}
          onChange={(e) => setNewTitle(e.target.value)}
        />
        <TextEditor setBody={setAbout} />
        <button onClick={patchHandler}>Save profile</button>
        <button>Cancel</button>
      </div>
    </div>
  );
};
