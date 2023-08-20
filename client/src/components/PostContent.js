import "../styles/components/PostContent.css";

const PostContent = ({ content, nickName, modifiedAt }) => {
  return (
    <div className="post-content__container">
      <div className="post-content__content">{content}</div>
      <div className="post-content__meta">
        <div className="post-content__actions">
          <button>Edit</button>
          <button>Delete</button>
          <button>Add a comment</button>
        </div>
        <div className="post-content__details">
          <p className="post-content__timestamp">{modifiedAt}</p>
          <h1 className="post-content__author">{nickName}</h1>
        </div>
      </div>
    </div>
  );
};

export default PostContent;
