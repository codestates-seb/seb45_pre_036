import React, { useState, useEffect } from "react";
import { CKEditor } from "@ckeditor/ckeditor5-react";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import UsernameBox from "../components/UsernameBox";
import Axiosinstance from "../auth/AxiosConfig";
import { useParams } from "react-router-dom";
import Menu from "../components/Menu";

const MyPageEdit = () => {
  const [userData, setUserData] = useState({});
  const [newNickName, setNewNickName] = useState("");
  const [newTitle, setNewTitle] = useState("");
  const [newContent, setNewContent] = useState(""); // 텍스트 편집기 구현해야 함
  const [isEditing, setIsEditing] = useState(false);
  const { memberId } = useParams();
  // 서버에서 profileData 받아오기

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
      content: newContent,
    };

        try {
            const res = await Axiosinstance.patch('/members/{member-id}', updatedData);
            setUserData(res.data.data); 
        }

        catch (err) {
                console.log('Error updating profile data: ', err);
        }
    }


  const handleCancel = () => {
    setIsEditing(false);
  };

  return (
    <div className="main-container">
      <div className="side-menu">
        <Menu />
      </div>
      <UsernameBox />
      <div className="edit-main">
        <form>
          <div>Display Name</div>
          <input
            type="text"
            value={userData.nickName}
            onChange={(e) => setNewNickName(e.target.value)}
          />
          <br />
          <div>Title</div>
          <input
            type="text"
            value={userData.title}
            onChange={(e) => setNewTitle(e.target.value)}
          />
          <div>
            {" "}
            {/* 텍스트 편집기 */}
            <CKEditor
              editor={ClassicEditor}
              data={newContent}
              onReady={(editor) => {
                console.log("Editor is ready to use!", editor);
              }}
              onChange={(event, editor) => {
                const data = editor.getData();
                console.log({ event, editor, data });
                setNewContent(data);
              }}
              onBlur={(event, editor) => {
                console.log("Blur.", editor);
              }}
              onFocus={(event, editor) => {
                console.log("Focus.", editor);
              }}
            />
          </div>
          <a href="/members/{member-id}" onClick={handleUpdate}>
            Submit
          </a>
          <a href="/members/{member-id}" onClick={handleCancel}>
            Cancel
          </a>
        </form>
      </div>
    </div>
  );
};

export default MyPageEdit;
