import React from 'react';
import Header from '../component/Header'
import Footer from '../components/Footer'
import Menu from '../components/Menu'
import './Home.css';
/* import Nav from '../components/Nav' -> 헤더 밑으로 */

const Home = () => {
  return (
    <div>
      <div className='question__container'>
          <Header/>
          
        <div className='question__wrapper'>
          <div className='top__container'>
            <p>Top Questions</p>
            <button id="askQuestion__Button">Ask Question</button>
          </div>
          <div className='question_wrapper'>
            <div className='post__summary'>
              <div className='answer__summary'>
                <div className='vote__count'></div>
                <div className='answer__count'></div>
                <div className='view__count'></div>
              </div>
              <div className='question__title'>Question title</div>
              <div className='answerer__name'></div>
            </div>
          </div>
        </div>
      </div>



    </div>
  );
}

export default Home;