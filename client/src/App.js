import Login from "./pages/Login";
import "./styles/App.css";
import { AuthProvider } from "./auth/AuthContext";

function App() {
  return (
    <AuthProvider>
      <Login />
    </AuthProvider>
  );
}

export default App;
