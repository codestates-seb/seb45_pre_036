import React, { createContext, useState } from 'react';

export const AuthContext = createContext();

export const AuthProvider = (props) => {
  const [authState, setAuthState] = useState({ isLoggedIn: false });
  //authState를 그냥 isLoggedin으로 할까

  return (
    <AuthContext.Provider value={{ authState, setAuthState }}>
      {props.children}
    </AuthContext.Provider>
  );
};
