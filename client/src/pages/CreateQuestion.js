import { useState } from "react";
import TextEditor from "../components/TextEditor";
import "../styles/pages/CreateQuestion.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const CreateQuestion = () => {
  const navigate = useNavigate();
  const [title, setTitle] = useState("");
  const [ body, setBody]=useState('');

  const titleChangeHandler = (e) => {
    setTitle(e.target.value);
  };

  const createPostHandler = async () => {
    const url = "http://localhost:8080/questions/ask";
    const headers = {
      // Authorization: `Bearer ${accessToken}`, // 
    };
    const requestBody = {
      // memberId, // accessToken payload에 있음. 
      title,
      content: body,
    };

    try {
      const response = await axios.post(url, requestBody, { headers });
      console.log("Post created successfully:", response.data);
      navigate('/');
    } catch (error) {
      console.error("Error creating post:", error);
    }
  };

  return (
    <div className="ask-question">
      <h1 className="ask-question__heading">Ask a public question</h1>
      <div className="ask-question__container title">
        <h2 className="ask-question__title">Title</h2>
        <p className="ask-question__desc">
          Be specific and imagine you're asking a question to another person.
        </p>
        <input
          className="ask-question__title-input"
          placeholder="Enter a title of your question."
          value={title}
          onChange={titleChangeHandler}
        ></input>
        <button class="ask-question__button next" type="button">
          Next
        </button>
      </div>
      <div className="ask-question__container content">
        <h2 className="ask-question__title">
          What are details of your problem?
        </h2>
        <p className="ask-question__desc">
          Introduce the problem and expand on what you put in the title.
        </p>
        <div className="ask-question__text-editor">
          <TextEditor setBody={setBody} />
        </div>
        <button className="ask-question__button create" onClick={createPostHandler}>Create</button>
      </div>
    </div>
  );
};

export default CreateQuestion;
