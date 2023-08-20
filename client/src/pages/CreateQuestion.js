import { useState } from "react";
import TextEditor from "../components/TextEditor";
import "../styles/pages/CreateQuestion.css";
import { useNavigate } from "react-router-dom";
import Axiosinstance from "../auth/AxiosConfig";
import jwtDecode from "jwt-decode";

const CreateQuestion = () => {
  const navigate = useNavigate();
  const [ title, setTitle] = useState("");
  const [ body, setBody]=useState('');

  const titleChangeHandler = (e) => {
    setTitle(e.target.value);
  };

  const createPostHandler = async () => {
    const accessToken = localStorage.getItem('accessToken');
    const memberId = jwtDecode(accessToken).memberId;
    // jwt payload에 있는데...

    const headers = {
      Authorization: `Bearer${accessToken}`,
    };
    const requestBody = {
      memberId: memberId,
      title: title,
      content: body,
    };

    try {
      const response = await Axiosinstance.post('/questions/ask', requestBody, { headers });
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
        <button className="ask-question__button next" type="button">
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
