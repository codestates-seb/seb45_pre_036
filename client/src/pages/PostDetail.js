import PostContent from "../components/PostContent";
import Comment from "../components/Comment";

const PostDetail = () => {
  return (
    <>
      <PostContent />
      <Comment />
      <div>
        text editor
        <button>Post your answer</button>
      </div>
    </>
  );
};

export default PostDetail;
