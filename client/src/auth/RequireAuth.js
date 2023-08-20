import useAuth from "./useAuth";
import Login from "../pages/Login";

export default function RequireAuthRoute({ component: Component }) {
  const { authState } = useAuth();

  return authState.isLoggedIn ? Component : <Login />;
}
