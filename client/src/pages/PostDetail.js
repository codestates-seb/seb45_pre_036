import PostContent from "../components/PostContent";
import Comment from "../components/Comment";

// question ID로 title받아야

const PostDetail = () => {
  return (
    <>
      <div className="post__header">
        <h1 className="post__header-title">Post title</h1>
        <div className="post__header-meta">
          <ul>
            <li>createdAt</li>
            <li>modifiedAt</li>
          </ul>
        </div>
      </div>
      <div className="post__content">
        <PostContent />
        <Comment />
      </div>
      <div className="post__create-answer">
        <div>
          text editor
          <button>Post your answer</button>
        </div>
      </div>
    </>
  );
};

export default PostDetail;
