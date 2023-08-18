import React, { createContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import jwt from "jsonwebtoken";
import axios from "axios";

export const AuthContext = createContext();

function isValidToken(token) {
  // 유효한 토큰 여부만 반환
  const decodedToken = jwt.decode(token);
  const currentTime = Math.floor(Date.now() / 1000);

  if (!decodedToken) {
    return false;
  }

  if (currentTime > decodedToken.exp) {
    return false;
  }

  return true;
}

function getRefresh() {
  // 리프레쉬 토큰 가지고 오기
  const refresh = localStorage.getItem("refreshToken");

  if (!refresh) {
    return null;
  }

  return refresh;
}

export const AuthProvider = (props) => {
  const navigate = useNavigate();
  const [authState, setAuthState] = useState({
    isLoggedIn: false,
    accessToken: "",
  });

  const accessToken = localStorage.getItem("accessToken");

  if (!isValidToken(accessToken)) {
    localStorage.removeItem("accessToken");

    const refresh = getRefresh();

    if (!isValidToken(refresh)) {
      navigate("/login");
    }

    try {
      const res = axios.post(
        "/getRefreshToken",
        {},
        {
          headers: {
            Refresh: refresh,
            // 'Refresh': `Bearer ${refresh}`,
            // 어떤 것을 사용해야 하는지는 로그인 테스트하면서 알아보기
          },
        }
      );
      const newToken = res.data.accessToken;
      localStorage.setItem("accessToken", newToken);
    } catch (error) {
      console.log(error);
    }

  } else { // accessToken이 유효하면
    setAuthState({isLoggedIn:true, accessToken: "newToken"});
    // 스트링으로 주는거 맞..?
  }

  return (
    <AuthContext.Provider value={{ authState, setAuthState }}>
      {props.children}
    </AuthContext.Provider>
  );
};
