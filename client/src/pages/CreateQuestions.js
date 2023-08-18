import React from 'react';
import Header from '../component/Header'
// import Footer from '../components/Footer'
// import Menu from '../components/Menu'
import './CreateQuestions.css';
import axios from 'axios';
import useAuth from '../auth/useAuth';

const CreateQuestions = () => {
  const auth = useAuth()

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
        <button onClick={() => {
          axios.post('', {
            'Authortization': auth.accesstoken
          })
        }}>Submit</button>
      </div>
    </div>
  );
}

export default CreateQuestions;