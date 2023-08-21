import React, { useState } from 'react';
import '../styles/components/Modal.css';
import Axiosinstance from "../auth/AxiosConfig";

// 탈퇴 처리하는 모달

const Modal = (  ) => {

    // 모달 창 관리
    const [ isOpen, setIsOpen ] = useState(false);

    const handleModal = () => {
        setIsOpen(!isOpen);
    }


    // 탈퇴 요청
    const handleSubmit = async (e) => {
        e.preventDefault();
    
        try {
            await Axiosinstance.delete("/members/{member-id}"); 
            console.log('회원 탈퇴');
            }
        catch (err) {
            console.log('탈퇴 중 오류 발생', err);
        }
    } 
    

    return (
        <div className="modal__container">
            <button className="modal__delete-button" onClick={handleModal}>Delete Button</button>
            { isOpen && (
                <div className="modal__box">
                    <button className="modal__content">
                        <span className="modal__exit-button" onClick={handleModal}>&times;</span>
                        <div>
                            <p>Are you sure?</p>
                            <p>Are you sure you want to delete your account? This cannot be undone.</p>
                        </div>
                        <div className="modal__buttons">
                            <a className="modal__delete-button" onClick={handleSubmit} href="로그아웃 페이지">Delete Profile</a>
                            <button className="modal__cancel-button" onClick={handleModal}>Cancle</button>
                        </div>
                    </button>
                </div>
            )}
        </div>
    )

}

export default Modal;