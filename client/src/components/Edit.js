import React, { useState, useEffect } from 'react';
import axios from 'axios';

// question: memberId는 어떻게 주어지는 거지?
// async/await 잘 써졌는지 확인
// (line 9) 가입 직후 nickName은 뭐가 되어 있는 거지? nickName 초기값에 이게 반영되어 있어야 함

const Profile = () => {
    const [ profileData, setProfileData ] = useState({});
    const [ newNickName, setNewNickName ] = useState("");
    const [ newTitle, setNewTitle ] = useState("");
    const [ newContent, setNewContent ] = useState(""); // 텍스트 편집기 구현해야 함

    // line 5 주석과 연관됨: ProfileData가 어떻게 되어 있는지 확인해야 함
    useEffect(() => {
        async function fetchProfileData() {
            try {
                const res = await axios.get('/members/{member-id}');
                setProfileData(res.data);
                console.log(res.data); // profile data 확인
            }
            catch (err) {
                console.log('Error getting profile data: ', err);
            }
        }

        fetchProfileData();
    }, []);

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

    return (
        <div>
        <form onSubmit={handleUpdate} action="/members/{member-id}">
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
            <div> {/* 텍스트 편집기 들어가야 함 */}
            <input
                type="text"
                value={profileData.content}
                onChange={e => setNewContent(e.target.value)}
            />
            </div>
            <a href>Submit</a>
            <a href="https://www.naver.com">Cancel</a>
        </form>
        </div>
    )
}

export default Profile;