import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import UsernameBox from '../components/UsernameBox';
import { Link } from 'react-router-dom';

const MyPageEdit = () => {
    const memberId = '정해진 ID';
    const [ profileData, setProfileData ] = useState({});
    const [ newNickName, setNewNickName ] = useState("");
    const [ newTitle, setNewTitle ] = useState("");
    const [ newContent, setNewContent ] = useState(""); // 텍스트 편집기 구현해야 함
    const [ isEditing, setIsEditing ] = useState(false);
    // 서버에서 profileData 받아오기
    useEffect(() => {
        async function fetchProfileData() {
            try {
                const res = await axios.get('/members/{member-id}');
                setProfileData(res.data); // res.data.nickName 등으로 원하는 걸 받아올 수 있음
                console.log(res.data); // 뭐가 나오는지 받아봐야 알 듯
           }
            catch (err) {
                console.log('Error getting profile data: ', err);
            }
        }

        fetchProfileData();
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
            const res = await axios.patch('/members/{member-id}', updatedData);
            setProfileData(res.data); 
        }

        catch (err) {
                console.log('Error updating profile data: ', err);
        }
    }

    const handleCancel = () => {
        setIsEditing(false);
    }

    return (
        <>
        <UsernameBox /> 
        <div>
        <form>
            <div>Display Name</div>
            <input
                type="text"
                value={profileData.nickName}
                onChange={e => setNewNickName(e.target.value)}
            />
            <br />
            <div>Title</div>
            <input
                type="text"
                value={profileData.title}
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
            <a href="/members/{member-id}" onClick={handleCancel}>Cancel</a>
        </form>
        </div>
        </>
    )
}

export default MyPageEdit;