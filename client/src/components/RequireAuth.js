import useAuth from "../auth/useAuth";
import Setting from "../components/Setting";
import Login from "../pages/Login";

export default function RequireAuthRoute() {
  const authstate = useAuth();

  return authstate.isLoggedIn ? <Setting /> : <Login />;
}
