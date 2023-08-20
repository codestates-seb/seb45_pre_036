import { AuthProvider } from "./auth/AuthContext";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import "./styles/App.css";
// import UsernameBox from './components/UsernameBox';
// import ProfileContents from './components/ProfileContents';
// import ProfilePage from './components/Edit';
import Signup from "./pages/Signup";
import Header from "./components/Header";
import Logout from "./pages/Logout";
import Login from "./pages/Login";
import Test from "./pages/Test";
import RequireAuthRoute from "./auth/RequireAuth";
import PostDetail from "./pages/PostDetail";
import CreateQuestion from "./pages/CreateQuestion";
// import PostList from "./pages/PostList";
import PostListScroll from "./pages/PostListScroll";

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/setting" element={<RequireAuthRoute />} />
          <Route path="/" element={<PostListScroll />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/logout" element={<Logout />} />
          <Route path="/ask" element={<CreateQuestion />} />
          <Route path="/test-success" element={<Test />} />
          <Route path="/questions/:questionId" element={<PostDetail />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
