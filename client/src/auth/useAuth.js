import { useContext } from 'react';
import { AuthContext } from './AuthContext';

// context를 사용하는 컴포넌트 입장에서 사용하는 거. 
const useAuth = () => {
  return useContext(AuthContext);
};

export default useAuth;