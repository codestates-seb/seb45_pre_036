import React from 'react';
import Header from '../component/Header'
import Footer from '../components/Footer'
import Menu from '../components/Menu'
import './Home.css';

const Home = () => {
  return (
    <div>
    <Header />
      <div className='home__container'>
        <div className='userInfo__container'>
          <div className='userInfo__wrapper'>
          {/* <img src="..styles/경로/이미지파일명.jpg" alt="프로필 사진"/> */}
            <div className="userInfo__input">
             <div className="userInfo__inputWrapper">
                </div>
              </div>
          </div>
        </div>
        <section className='about'>
          <p>About</p>
          <div className='about__container'></div>
        </section>
        <section className='questions'>Questions
          <p>Questions</p>
          <div className='questions__container'></div>
        </section>
        <section className='answers'>Answers
        <p>Answers</p>
          <div className='answers_container'></div>
        </section>
        <Footer />
      </div>
      </div>
  );
}

export default Home;