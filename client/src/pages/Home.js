import React from 'react';
import Header from '../component/Header'
import Menu from '../components/Menu'
import './Home.css';
/* import Nav from '../components/Nav' -> 헤더 밑으로 */

const Home = () => {
  return (
    <div>
      <div className='question__container'>
          <Header/>
          <Menu />
        <div className='question__wrapper'>
          <div className='top__container'>
            <p id='home__title'>Top Questions</p>
            <button id="askQuestion__Button">Ask Question</button>
          </div>
          <div className='question_wrapper'>
            <div className='post__summary'>
              <div className='answer__summary'>
                <div className='vote__count'>0 votes
                  <div className='vote__number'></div>
                  <div className='vote__name'></div>
                </div>
                <div className='answer__count'>1 answer
                  <div className='answer__number'></div>
                  <div className='answer__name'></div>
                </div>
                <div className='view__count'>23 views
                  <div className='view__number'></div>
                  <div className='view__name'></div>
                </div>
              </div>
              <div className='question__title'>Question title</div>
              <div className='answerer__container'>
                <div className='answerer__colorbox'></div>
                <div className='answerer__name'></div>
              </div>
            </div>
          </div>
        </div>
      </div>



    </div>
  );
}

export default Home;