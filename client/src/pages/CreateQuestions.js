import React from 'react';
import Header from '../component/Header'
// import Footer from '../components/Footer'
// import Menu from '../components/Menu'
import './CreateQuestions.css';

const CreateQuestions = () => {
  return (
    <div>
      <div className='createquestions__container'>
        <Header/>
        <p id='createquestions__title'>Ask a question</p>
        <div className='createqusetions__wrapper'>
          <div className='question__title__container'>
            <h1>Title</h1>
            <h2>Be a specific and imagine you're asking a question to another person.</h2>
            <textarea id="question__title__textarea">e.g. Is there an R function for finding the index of an element in a vector?</textarea>
            <button className='Next'>Next</button>
          </div>
        </div>

      </div>
    </div>
  );
}

export default CreateQuestions;