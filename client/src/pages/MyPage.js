import React, { useState, useEffect } from "react";
import "../styles/components/MyPage.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCalendarDays,
  faPencil,
  faExclamation,
  faCakeCandles,
  faClock,
} from "@fortawesome/free-solid-svg-icons";
import Axiosinstance from "../auth/AxiosConfig";
import { useParams } from "react-router-dom";
import Menu from "../components/Menu";

const MyPage = () => {
  const memberId = useParams();
  const [userData, setUserData] = useState({});
  const [nickName, setNickName] = useState("");
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [isEditing, setIsEditing] = useState(false);

  useEffect(() => {
    async function fetchUserData() {
      try {
        const res = await Axiosinstance.get("/members/Mypage/" + memberId);
        console.log(res.data.data); // 어떤 데이터 오는지 확인 필요
        setUserData(res.data.data);
      } catch (err) {
        console.log("error getting profile data :", err);
      }
    }

    fetchUserData();
  }, []);

  const handleEdit = () => {
    setIsEditing(true);
  };

  // const handleSubmit = async (e) => {
  //   e.preventDefault();

  //   const updatedData = {
  //     nickName: nickName,
  //     title: title,
  //     content: content,
  //   };

  //   try {
  //     const res = await Axiosinstance.patch("members/{member-id}", updatedData);
  //     setUserData(res.data);
  //     setIsEditing(false);
  //   } catch (err) {
  //     console.log("Error updating profile data: ", err);
  //   }
  // };

  return (
    <div className="main-container">
      <div className="side-menu">
        <Menu />
      </div>
      <div className="profile-main">
        <div className="profile-top__line"></div>
        <img
          className="profile-top__image"
          src={require("../static/profile-picture.png")}
          alt="profile"
        />
        <div className="user-info">
          <div className="user-info__username">{userData.nickName}</div>
          <div className="user-info__title">{userData.title}</div>
          <ul className="user-info__icon-list">
            <li className="user-info__icon-item">
              <FontAwesomeIcon icon={faCakeCandles} /> Member for n days
            </li>
            <li className="user-info__icon-item">
              <FontAwesomeIcon icon={faClock} /> Last seen this week
            </li>
            <li className="user-info__icon-item">
              <FontAwesomeIcon icon={faCalendarDays} /> Visited 2 days, 2
              consecutive
            </li>
          </ul>
        </div>
        <div className="button-box">
          <div
            className="button-box__edit button-box__button border"
            onClick={handleEdit}
          >
            <a href="/members/edit/{member-id}">
              <FontAwesomeIcon icon={faPencil} /> Edit Profile
            </a>
          </div>
          <a
            className="button-box__delete button-box__button border"
            href="members/delete/{member-id}"
          >
            <FontAwesomeIcon icon={faExclamation} /> Delete Profile
          </a>
        </div>
        <div className="profile-contents">
          <div className="profile-contents__section">
            <div className="profile-contents__title">About</div>
            <div className="profile-contents__title border">
              {userData.content}
            </div>
            <div className="profile-contents__title">
              {userData.questionCount} Questions
            </div>
            <div className="profile-contents__box border">
              <div>{userData.questions}</div>
              {/* <div className="qna__title">question title</div>
            <div>asked when</div> */}
            </div>
            <div className="profile-contents__title">
              {userData.answerCount} Answers
            </div>
            <div className="profile-contents__box border">
              <div>{userData.answers}</div>
              {/* 제목, answered when */}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default MyPage;
