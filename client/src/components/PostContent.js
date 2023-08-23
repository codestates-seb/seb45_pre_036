import "../styles/components/PostContent.css";
import { useNavigate } from "react-router-dom";
import FormattedDate from "../components/FormattedDate";
import Axiosinstance from "../auth/AxiosConfig";

const PostContent = ({ answerId, questionId, content, nickname, modifiedAt, memberId }) => {
  const navigate = useNavigate();

  const deleteHandler =  async () => {
    const userConfirm = window.confirm("정말 삭제하시겠습니까?");
    if(userConfirm){
      try {
        const accessToken = localStorage.getItem('accessToken');
        const headers = {
          Authorization: `Bearer${accessToken}`,
        };

        const endpoint = answerId? `/answers/${answerId}` : `/questions/${questionId}`;
        const navigatePath = answerId? `/questions/${questionId}` : '/';
        const res = await Axiosinstance.delete(endpoint, { headers });
        window.location.href=navigatePath;
      } catch (err){
        console.log(err)
      }  
    }
  }

  return (
    <div className="post-content__container">
      <div className="post-content__content">{content}</div>
      <div className="post-content__meta">
        <ul className="post-content__actions">
          <li>Edit</li>
          <li onClick={deleteHandler}>Delete</li>
          <li>Add a comment</li>
        </ul>
        <div className="post-content__details-container">
          <ul className="post-content__details">
            <li className="post-content__timestamp">
              ModifiedAt
              <FormattedDate dateString={modifiedAt} />
            </li>
            <li className="post-content__author">
              Author
              <p
                className="post-content__author name"
                onClick={() => navigate(`/members/${memberId}`)}
              >
                {nickname}
              </p>
              {/* mypage 프론트 라우팅 */}
            </li>
          </ul>
        </div>
      </div>
    </div>
  );
};

export default PostContent;
