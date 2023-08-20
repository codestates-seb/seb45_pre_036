import "../styles/components/PostContent.css";
import { useNavigate, Link } from "react-router-dom";

const PostContent = ({ content, nickName, modifiedAt, memberId }) => {
    const navigate = useNavigate();
  return (
    <div className="post-content__container">
      <div className="post-content__content">{content}</div>
      <div className="post-content__meta">
        <div className="post-content__actions">
          <Link to={'#'}>Edit</Link>
          <p>Delete</p>
          <p>Add a comment</p>
        </div>
        <div className="post-content__details">
          <p className="post-content__timestamp">{modifiedAt}</p>
          <h1 onClick={()=>navigate(`/members/${memberId}`)} className="post-content__author">{nickName}</h1>
          {/* mypage 프론트 라우팅 */}
        </div>
      </div>
    </div>
  );
};

export default PostContent;
