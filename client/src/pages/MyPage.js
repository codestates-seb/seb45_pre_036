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
import { useParams } from 'react-router-dom';

const MyPage = () => {
  const memberId = useParams();
  const [userData, setUserData] = useState({});


  useEffect(() => {
    async function fetchUserData() {
      try {
        const res = await Axiosinstance.get("/members/myPage/" + memberId); 
        console.log(res.data); // 어떤 데이터 오는지 확인 필요
        setUserData(res.data.data);
      } catch (err) {
        console.log("error getting profile data :", err);
      }
    }

    fetchUserData();
  }, [memberId]
  );


  return (
    <div>
      <div className="top__line"></div>
      <img src={require("../static/profile-picture.png")} alt="profile" />
      <div className="user__box">
        <div className="username">{userData.nickName}</div>
        <div>{userData.title}</div>
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
        <div className="edit button border">
          <a href="/members/edit/{member-id}">
            <FontAwesomeIcon icon={faPencil} /> Edit Profile
          </a>
        </div>
        <a className="delete button border" href="members/delete/{member-id}">
          <FontAwesomeIcon icon={faExclamation} /> Delete Profile
        </a>
      </div>
      <div>
        <div className="profile__contents">
          <div className="contents__title">About</div>
          <div className="contents__box border">{userData.content}</div>
          <div className="contents__title">{userData.questionCount} Questions</div>
          <div className="contents__box border">
            <div>{userData.questions}</div>
            {/* <div className="qna__title">question title</div>
            <div>asked when</div> */}
          </div>
          <div className="contents__title">{userData.answerCount} Answers</div>
          <div className="contents__box border">
            <div>{userData.answers}</div>
            {/* 제목, answered when */}
          </div>
        </div>
      </div>
    </div>
  );
};

export default MyPage;