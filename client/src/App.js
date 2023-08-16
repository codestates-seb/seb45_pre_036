// import Header from './components/Header';
// import Footer from './components/Footer';
// import UsernameBox from './components/UsernameBox';
// import ProfileContents from './components/ProfileContents';
// import React, { useState } from 'react';
// import ProfilePage from './components/Edit';
import Login from "./pages/Login";
import { AuthProvider } from "./auth/AuthContext";
// import Signup from "./pages/Signup";
import './styles/App.css';
import Menu from "./components/Menu";


function App() {
  return (
    <AuthProvider>
      <Login /> 
      <Menu />
      {/* <Signup /> */}
    </AuthProvider>
  );
}

export default App;
