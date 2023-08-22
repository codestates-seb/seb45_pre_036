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
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import UsernameBox from '../components/UsernameBox';
import axios from "axios";


export const EditForm = ( ) => {
  const [ userData, setUserData ] = useState({});
  const [ newNickName, setNewNickName ] = useState("");
  const [ newTitle, setNewTitle ] = useState("");
  const [ newContent, setNewContent ] = useState(""); // 텍스트 편집기 구현해야 함
  const { memberId } = useParams();

  useEffect(() => {
    async function fetchUserData() {
        try {
            const res = await Axiosinstance.patch('/members/' + memberId);
            setUserData(res.data.data);
            console.log(res.data.data); // 뭐가 나오는지 확인
       }
        catch (err) {
            console.log('Error getting profile data: ', err);
        }
    }

    fetchUserData();
}, []);


// 받아온 profileData 폼에 반영하기
const handleUpdate = async (e) => {
    e.preventDefault();

    const updatedData = {
        nickName: newNickName,
        title: newTitle,
        content: newContent
    };

    try {
        const res = await Axiosinstance.patch('/members/' + memberId, updatedData);
        setUserData(res.data.data); 
    }

    catch (err) {
            console.log('Error updating profile data: ', err);
    }
}


return (
    <>
    <div>
    <form>
        <div>Display Name</div>
        <input
            type="text"
            value={userData.nickName}
            onChange={e => setNewNickName(e.target.value)}
        />
        <br />
        <div>Title</div>
        <input
            type="text"
            value={userData.title}
            onChange={e => setNewTitle(e.target.value)}
        />
        <div> {/* 텍스트 편집기 */}
            <CKEditor
                editor={ ClassicEditor }
                data={newContent}
                onReady={ editor => {
                    console.log( 'Editor is ready to use!', editor );
                } }
                onChange={ ( event, editor ) => {
                    const data = editor.getData();
                    console.log( { event, editor, data } );
                    setNewContent(data);
                } }
                onBlur={ ( event, editor ) => {
                    console.log( 'Blur.', editor );
                } }
                onFocus={ ( event, editor ) => {
                    console.log( 'Focus.', editor );
                } }
            />
        </div>
        <a href="/members/{member-id}" onClick={handleUpdate}>Submit</a>
        <button>Cancel</button>
    </form>
    </div>
    </>
)
}


const MyPage = ( ) => {
  const memberId = useParams();
  const [userData, setUserData] = useState({});
  const [ isEditing, setIsEditing ] = useState(false);

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
    { !isEditing && (
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
          <a href="/members/edit/{memberId}" >
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
    )}
    </div>
  )
};

export default MyPage;