import axios from "axios";

export const LoginFunc = async (email, password) => {
  try {
    const res = await axios.post("http://ec2-13-125-169-3.ap-northeast-2.compute.amazonaws.com:8080/auth/login", {
      email: email,
      password: password,
    }, {
      headers: {
        'Content-Type': 'application/json'
      }
    });

    // const { memberId } = res.data;
    // payload에 있음. 

    
    const auth = res.headers.getAuthorization()

    const accessToken = auth.split('Bearer')[1]
    const refreshToken = res.headers.get('Refresh')

    console.info(accessToken)

    localStorage.setItem("accessToken", accessToken);
    localStorage.setItem("refreshToken", refreshToken);

    // setAuthState({ isLoggedIn: true, accessToken: accessToken, memberId });
    // 로그인 함수에서 할 필요 없음. 
  } catch (error) {
    console.log(error);
  }
};
  