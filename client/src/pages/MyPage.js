import React, { useState, useEffect } from "react";
import "../styles/pages/MyPage.css";
import Axiosinstance from "../auth/AxiosConfig";
import { useParams } from "react-router-dom";
import Menu from "../components/Menu";
import Profile from "../components/Profile";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCalendarDays,
  faPencil,
  faExclamation,
  faCakeCandles,
  faClock,
} from "@fortawesome/free-solid-svg-icons";
import { EditForm } from "../components/EditForm";
import DeleteProfile from "../components/DeleteProfile";

const MyPage = () => {
  const memberId = useParams();
  const [userData, setUserData] = useState({});
  const [view, setView] = useState("profile");

  useEffect(() => {
    async function fetchUserData() {
      try {
        const res = await Axiosinstance.get("/members/Mypage/" + memberId);
        // console.log(res.data.data); // 어떤 데이터 오는지 확인 필요
        setUserData(res.data.data);
      } catch (err) {
        console.log("error getting profile data :", err);
      }
    }

    fetchUserData();
  }, []);

  return (
    <div className="main-container">
      <div className="side-menu">
        <Menu />
      </div>
      <div className="profile-main">
        <div className="profile-top__image-container">
          <img
            className="profile-top__image"
            src={require("../static/profile-picture.png")}
            alt="profile"
          />
        </div>
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
          <div className="button-box__edit button-box__button border">
            <button type="button" onClick={()=>setView("edit")}>
              <FontAwesomeIcon icon={faPencil} /> Edit Profile
            </button>
          </div>
          <button type="button" onClick={()=>setView("delete")}>
            <FontAwesomeIcon icon={faExclamation} /> Delete Profile
          </button>
        </div>
        { view === "profile" && <Profile data={userData} />}
        { view === "edit" && <EditForm data={userData} />}
        { view === "delete" && <DeleteProfile memberId={memberId} />}
      </div>
    </div>
  );
};

export default MyPage;
