import React, { createContext, useState } from "react";
import jwtDecode from "jwt-decode";
import axios from "axios";

export const AuthContext = createContext();

function isValidToken(token) {
  // 유효한 토큰 여부만 반환
  const decodedToken = jwtDecode(token);
  if (!decodedToken) {
    return false;
  }
 
  const currentTime = Math.floor(Date.now() / 1000);
  return currentTime < decodedToken.exp
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
  const [authState, setAuthState] = useState({
    isLoggedIn: false,
    accessToken: "",
  });
  
  const accessToken = localStorage.getItem("accessToken");

  if (accessToken && isValidToken(accessToken)) {
    // accessToken이 유효하면
    if (!authState.isLoggedIn){
      setAuthState({ isLoggedIn: true, accessToken: accessToken });
    }
  } else {
    localStorage.removeItem("accessToken");

    const refresh = getRefresh();
    if (refresh && isValidToken(refresh)) {
      try {
        const res = axios.post(
          "http://localhost:8080/members/renewAccessToken",
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
    } 
  }

  return (
    <AuthContext.Provider value={{ authState }}>
      {props.children}
    </AuthContext.Provider>
  );
};
