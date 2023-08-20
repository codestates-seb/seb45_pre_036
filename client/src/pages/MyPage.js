import React, { useState, useEffect } from "react";
import axios from "axios";
import "../styles/components/MyPage.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCalendarDays,
  faPencil,
  faExclamation,
  faCakeCandles,
  faClock,
} from "@fortawesome/free-solid-svg-icons";
import MyPageEdit from "./MyPageEdit";

const MyPage = () => {
  const memberId = "정해진 ID";
  const [profileData, setProfileData] = useState({});
  const [nickName, setNickName] = useState("");
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [isEditing, setIsEditing] = useState(false);

  useEffect(() => {
    async function fetchProfileData() {
      try {
        const res = await axios.patch("/members/{member-id}"); // get 아닌가?
        console.log(res.data); // 어떤 데이터 오는지 확인 필요
        setProfileData(res.data); // res.data.nikcName
        // 여기서 Q&A도 받아옴
      } catch (err) {
        console.log("error getting profile data :", err);
      }
    }

    fetchProfileData();
  }, []);

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const updatedData = {
      nickName: nickName,
      title: title,
      content: content,
    };

    try {
      const res = await axios.patch("members/{member-id}", updatedData);
      setProfileData(res.data); // 이게 맞나?
      setIsEditing(false);
    } catch (err) {
      console.log("Error updating profile data: ", err);
    }
  };

  return (
    <div>
      <div className="top__line"></div>
      <img src={require("../static/profile-picture.png")} alt="profile" />
      <div className="user__box">
        <div className="username">Charlie{profileData.nickName}</div>
        <div>{profileData.title}</div>
        <ul>
          <li className="icon1">
            <FontAwesomeIcon icon={faCakeCandles} /> Member for n days
          </li>
          <li className="icon1">
            <FontAwesomeIcon icon={faClock} /> Last seen this week
          </li>
          <li className="icon1">
            <FontAwesomeIcon icon={faCalendarDays} /> Visited 2 days, 2
            consecutive
          </li>
        </ul>
      </div>
      <div className="button__box">
        <div className="edit button border" onClick={handleEdit}>
          <a href="/members/edit/{member-id}">
            <FontAwesomeIcon icon={faPencil} /> Edit Profile
          </a>
        </div>
        <a className="delete button border" href>
          <FontAwesomeIcon icon={faExclamation} />
          <span className> Delete Profile</span>
        </a>
      </div>
      <div>
        <div className="profile__contents">
          <div className="contents__title">About</div>
          <div className="contents__box border">{profileData.content}</div>
          <div className="contents__title">the number of question</div>
          <div className="contents__box border">
            <div>{profileData.question}</div>
            <div className="qna__title">question title</div>
            <div>asked when</div>
          </div>
          <div className="contents__title">the number of annswer</div>
          <div className="contents__box border">
            <div>{profileData.answer}</div>
            <div className="qna__title">question title</div>
            <div>asked when</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default MyPage;
