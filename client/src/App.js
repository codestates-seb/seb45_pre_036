import Header from './components/Header';
import Footer from './components/Footer';
import UsernameBox from './components/UsernameBox';
import ProfileContents from './components/ProfileContents';
import React, {useState} from 'react';
import ProfilePage from './components/Edit';
import './styles/App.css';


function App() {
  return (
    <>
    <UsernameBox />
    <ProfileContents />
    <ProfilePage />
    </>
  )
}

export default App;
