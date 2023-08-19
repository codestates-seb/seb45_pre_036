import PostContent from "../components/PostContent";
import Comment from "../components/Comment";
import axios from "axios";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import FormattedDate from '../components/FormattedDate';

const PostDetail = () => {
  const { questionId } = useParams();
  const [post, setPost] = useState(null);

  const getPost = async () => {
    try {
      const res = await axios.get(
        `http://localhost:8080/questions/${questionId}`
      );
      setPost(res.data.data);
    } catch (err) {
      console.log(err);
    }
  };
  useEffect(() => {
    getPost();
  }, []);

  return (
    <>
      <div className="post__header">
        <h1 className="post__header-title">{post.title}</h1>
        <div className="post__header-meta">
          <ul>
            <li><FormattedDate dateString={post.createdAt} /></li>
            <li><FormattedDate dateString={post.modifiedAt} /></li>
          </ul>
        </div>
      </div>
      <div className="post__content">
        <PostContent content={post.content} />
        <Comment comments={post.answers} />
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
