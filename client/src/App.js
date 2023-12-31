import { AuthProvider } from "./auth/AuthContext";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import "./styles/App.css";
import Signup from "./pages/Signup";
import Header from "./components/Header";
import Logout from "./pages/Logout";
import Login from "./pages/Login";
import Test from "./pages/Test";
import RequireAuthRoute from "./auth/RequireAuth";
import PostDetail from "./pages/PostDetail";
import CreateQuestion from "./pages/CreateQuestion";
import PostListScroll from "./pages/PostListScroll";
import Setting from "./components/Setting";
import MyPage from "./pages/MyPage";


function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/setting" element={<RequireAuthRoute component={<Setting />} />} />
          <Route path="/ask" element={<RequireAuthRoute component={<CreateQuestion />} />} />
          <Route path="/members/:memberId" element={<RequireAuthRoute component={<MyPage />} />} />
          <Route path="/" element={<PostListScroll />} />
          <Route path="/questions/:questionId" element={<PostDetail />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/logout" element={<Logout />} />
          <Route path="/test-success" element={<Test />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
