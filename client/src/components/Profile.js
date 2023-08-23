import "../styles/components/Profile.css";

const Profile = ({ data }) => {
  const userdata = data.patchInfoResponse;
  const questionData = data.questions;
  const answerData = data.answers;

  return (
    <div className="profile-contents">
      <div className="profile-contents__section about">
        <div className="profile-contents__title">About</div>
        <div className="profile-contents__box">
          <div className="profile-contents__about-content">
            {/* {userdata.aboutMe ? userdata.aboutMe : "Edit your profile"} */}
          </div>
        </div>
      </div>

      <div className="profile-contents__section">
        <div className="profile-contents__title">
          {data.questionCount && data.questionCount} Questions
        </div>
        <div className="profile-contents__box">
          {/* 질문 제목 매핑 다시 */}
          <div>{questionData && questionData}</div>
        </div>
      </div>
      <div className="profile-contents__section">
        <div className="profile-contents__title">
          {data.answerCount && data.answerCount} Answers
        </div>
        <div className="profile-contents__box">
          {/* 답변 제목 매핑 다시 */}
          <div>{answerData && answerData}</div>
        </div>
      </div>
    </div>
  );
};

export default Profile;
