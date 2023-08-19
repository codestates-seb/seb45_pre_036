import TextEditor from "./TextEditor";

const AnswerForm = () => {
  return (
    <>
      <h2 className="answer__create-title">Your Answer</h2>
      <div className="answer__create-editor">
        <TextEditor />
        <button className="answer__create-button">Post your answer</button>
      </div>
    </>
  );
};
export default AnswerForm;
