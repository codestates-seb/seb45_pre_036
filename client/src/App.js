// import Login from "./pages/Login";
import "./styles/App.css";
import { AuthProvider } from "./auth/AuthContext";
import Signup from "./pages/Signup";
// import Header from "./components/Header";

function App() {
  return (
    <AuthProvider>
      {/* <Header /> */}
      {/* <Login />  */}
      <Signup />
    </AuthProvider>
  );
}

export default App;
