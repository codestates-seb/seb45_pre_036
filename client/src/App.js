import Login from "./pages/Login";
import "./styles/App.css";
import { AuthProvider } from "./auth/AuthContext";
// import Signup from "./pages/Signup";

function App() {
  return (
    <AuthProvider>
      <Login /> 
      {/* <Signup /> */}
    </AuthProvider>
  );
}

export default App;
