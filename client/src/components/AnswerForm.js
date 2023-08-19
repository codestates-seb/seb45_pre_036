import { useState } from "react";
import TextEditor from "./TextEditor";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const AnswerForm = () => {
  const navigate = useNavigate();
  const [body, setBody] = useState("");

  const createAnswerHandler = async () => {
    const url = "http://localhost:8080/answers";
    const headers = {
      // Authorization: `Bearer ${accessToken}`, //
    };
    const requestBody = {
      // memberId, // accessToken payload에 있음.
      // questionId, // param에서 가지고 와야 할 듯.
      content: body,
    };

    try {
      const response = await axios.post(url, requestBody, { headers });
      console.log("Post created successfully:", response.data);
      navigate("/");
    } catch (error) {
      console.error("Error creating post:", error);
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
