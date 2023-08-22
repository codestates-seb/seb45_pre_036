import { useState } from "react";
import TextEditor from "./TextEditor";
import Axiosinstance from "../auth/AxiosConfig";
import { useNavigate } from "react-router-dom";
import "../styles/components/AnswerForm.css";
import jwtDecode from "jwt-decode";

const AnswerForm = ({ questionId }) => {
  const navigate = useNavigate();
  const [body, setBody] = useState("");

  const createAnswerHandler = async () => {
    const accessToken = localStorage.getItem("accessToken");
    const memberId = jwtDecode(accessToken).memberId;
    const headers = {
      Authorization: `Bearer${accessToken}`,
    };
    const requestBody = {
      memberId: memberId,
      questionId: questionId,
      content: body,
    };

    try {
      const response = await Axiosinstance.post("/answers", requestBody, {
        headers,
      });
      console.log("Post created successfully");
      window.location.href = `/questions/${questionId}`;
    } catch (error) {
      console.error("Error creating post:", error);
      navigate("/login");
    }
  };

  return (
    <>
      <h2 className="answer__create-title">Your Answer</h2>
      <div className="answer__create-editor">
        <TextEditor setBody={setBody} />
        <button className="answer__create-button" onClick={createAnswerHandler}>
          Post your answer
        </button>
      </div>
    </>
  );
};

export default AnswerForm;
