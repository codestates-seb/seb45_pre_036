import '../styles/components/PostItem.css';
import FormattedDate from './FormattedDate';
import { useNavigate } from 'react-router-dom';

const Post = ({ post }) => {
    const navigate = useNavigate();
    
    return (
        <div className="post">
            <div className="post__info">
                <div className="post__info-list">
                    <div className="post__info-item">0 votes</div>
                    <div className="post__info-item">0 answers</div>
                    <div className="post__info-item">0 views</div>
                </div>
            </div>
            <div className="post__content">
                <h1 className="post__content-title" onClick={() => navigate(`/questions/${post.questionId}`)}>{post.title}</h1>
                <p className="post__content-text">{post.content}</p>
            </div>
            <div className="post__meta">
                <ul className="post__meta-list">
                    <li className="post__meta-item"><FormattedDate dateString={post.createdAt} /></li>
                    <li className="post__meta-item"><FormattedDate dateString={post.modifiedAt} /></li>
                    <li className="post__meta-item">{post.nickname}</li>
                </ul>
            </div>
        </div>
    )
}

export default Post;