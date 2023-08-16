import Login from "./pages/Login";
import "./styles/App.css";
import { AuthProvider } from "./auth/AuthContext";
// import Header from "./components/Header";

function App() {
  return (
    <AuthProvider>
      {/* <Header /> */}
      <Login />
    </AuthProvider>
  );
}

export default App;
