import useAuth from "../auth/useAuth";
import Setting from "../components/Setting";
import Login from "../pages/Login";

export default function RequireAuthRoute() {
  const { authState } = useAuth();

  return authState.isLoggedIn ? <Setting /> : <Login />;
}
