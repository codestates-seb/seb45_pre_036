// import React, { useState } from 'react';
import { AuthProvider } from "./auth/AuthContext";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import "./styles/App.css";
// import UsernameBox from './components/UsernameBox';
// import ProfileContents from './components/ProfileContents';
// import ProfilePage from './components/Edit';
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Header from "./components/Header";
import Logout from "./pages/Logout";
import Test from "./pages/test";
import PostListTest from "./pages/PostListTest";

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
      <Header />
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/logout" element={<Logout />} />
          <Route path="/test-success" element={<Test />} />
          <Route path="/questions" element={<PostListTest />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
