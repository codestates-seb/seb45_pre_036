const Profile = ({ data }) => {
  const userdata = data.patchInfoResponse;
  
  return (
    <div className="profile-contents__section">
      <div className="profile-contents__about">
        <div className="profile-contents__title">About</div>
        <div className="profile-contents__title border">{userdata.aboutMe}</div>
      </div>

      <div className="profile-contents__questions">
        <div className="profile-contents__title">
          {data.questionCount} Questions
        </div>
        <div className="profile-contents__box border">
          {/* 질문 제목 매핑 다시 */}
          <div>{data.questions}</div>
        </div>
      </div>
      <div className="profile-contents__answers">
        <div className="profile-contents__title">
          {data.answerCount} Answers
        </div>
        <div className="profile-contents__box border">
          {/* 답변 제목 매핑 다시 */}
          <div>{data.answers}</div>
        </div>
      </div>
    </div>
  );
};

export default Profile;
