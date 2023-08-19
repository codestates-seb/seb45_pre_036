import TextEditor from "../components/TextEditor";
import '../styles/pages/CreateQuestion.css';

const CreateQuestion = () => {
  // title이랑 content로 body 보내기
  // header로 accessToken 보내기

  return (
    <div class="ask-question">
      <h1 class="ask-question__heading">Ask a public question</h1>
      <div class="ask-question__container title">
        <h2 class="ask-question__title">Title</h2>
        <p class="ask-question__desc">
          Be specific and imagine you're asking a question to another person.
        </p>
        <input class="ask-question__title-input" placeholder="Enter a title of your question."></input>
        <button class="ask-question__button next" type="button">Next</button>
      </div>
      <div class="ask-question__container content">
        <h2 class="ask-question__title">What are details of your problem?</h2>
        <p class="ask-question__desc">
          Introduce the problem and expand on what you put in the title.
        </p>
        <div class="ask-question__text-editor">
          <TextEditor />
        </div>
        <button class="ask-question__button create">Create</button>
      </div>
    </div>
  );
};

export default CreateQuestion;
