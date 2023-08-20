import PostContent from "../components/PostContent";
import Comment from "../components/Comment";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import FormattedDate from "../components/FormattedDate";
import Menu from "../components/Menu";
import "../styles/pages/PostDetail.css";
import AnswerForm from '../components/AnswerForm';
import Axiosinstance from "../auth/AxiosConfig";

const PostDetail = () => {
  const { questionId } = useParams();
  const [post, setPost] = useState(null);

  const getPost = async () => {
    try {
      const res = await Axiosinstance.get(
        `/questions/${questionId}`
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
    <div className="detail-container">
      <div className="side-menu">
        <Menu />
      </div>
      <main className="post-detail__main">
        <div className="post__header">
          <h1 className="post__header-title">{post.title}</h1>
          <ul className="post__header-meta">
            <li>
              <FormattedDate dateString={post.createdAt} />
            </li>
            <li>
              <FormattedDate dateString={post.modifiedAt} />
            </li>
          </ul>
        </div>
        <div className="post__content">
          <PostContent content={post.content} nickname={post.nickName} modifiedAt={post.modifiedAt} />
          {/* 글, 답변이 렌더링 되어야 함 */}
          <Comment comments={post.answers} />
        </div>
        <div className="post__create-answer">
          <AnswerForm />
          {/* lifting content up 필요 */}
        </div>
      </main>
    </div>
  );
};

export default PostDetail;
