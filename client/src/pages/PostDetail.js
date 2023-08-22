import PostContent from "../components/PostContent";
import Comment from "../components/Comment";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import FormattedDate from "../components/FormattedDate";
import Menu from "../components/Menu";
import "../styles/pages/PostDetail.css";
import AnswerForm from "../components/AnswerForm";
import Axiosinstance from "../auth/AxiosConfig";
import useAuth from "../auth/useAuth";

const PostDetail = () => {
  const { questionId } = useParams();
  const [post, setPost] = useState({});
  const { authState } = useAuth();

  // answers / answerId, content, crete,modifi, email, nckN,
  // answers -> comments / commentId, nickN, cre, modi
  // 가지고 온 post의 answers필드 길이가 1이상이면 PostContent에 내려줘서 렌더링
  
  useEffect(() => {
    const getPost = async () => {
      try {
        const res = await Axiosinstance.get("/questions/" + questionId);
        // console.log(res.data.data);
        setPost(res.data.data);
      } catch (err) {
        console.log(err);
      }
    };
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
              <p>Asked</p>
              <FormattedDate dateString={post.createdAt} />
            </li>
            <li>
              <p>Modified</p>
              <FormattedDate dateString={post.modifiedAt} />
            </li>
          </ul>
        </div>
        <div className="post__content">
          <PostContent
            questionId={questionId}
            content={post.content}
            nickname={post.nickName}
            modifiedAt={post.modifiedAt}
            memberId={post.memberId}
          />
          {/* 글, 답변이 렌더링 되어야 함 */}
          <div className="post__answer">
            {post.answers && post.answers.length >=1 && post.answers.map((answer)=>(
              <PostContent key={answer.answerId} content={answer.content} nickname={answer.nickName} modifiedAt={answer.modifiedAt} createdAt={answer.createdAt} answerId={answer.answerId} questionId={questionId} />
            ))}
          </div>
          <Comment comments={post.answers} />
        </div>
        <div className="post__create-answer">
          {authState.isLoggedIn && <AnswerForm questionId={questionId} />}
        </div>
      </main>
    </div>
  );
};

export default PostDetail;
