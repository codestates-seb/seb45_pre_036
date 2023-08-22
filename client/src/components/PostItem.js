import '../styles/components/PostItem.css';
import FormattedDate from './FormattedDate';
import { Link } from 'react-router-dom';

const Post = ({ post }) => {
    // const navigate = useNavigate();
    console.log(post.title);
    
    return (
        <div className="post">
            <div className="post__info">
                <div className="post__info-list">
                    <div className="post__info-item">0 votes</div>
                    <div className="post__info-item">{post.answerCount} answers</div>
                    <div className="post__info-item">{post.view} views</div>
                </div>
            </div>
            <div className="post__content">
                <Link to={`/questions/${post.questionId}`}><h1 className="post__content-title">{post.title}</h1></Link>
                <p className="post__content-text">{post.content}</p>
            </div>
            <div className="post__meta">
                <ul className="post__meta-list">
                    <li className="post__meta-item"><FormattedDate dateString={post.createdAt} /></li>
                    <li className="post__meta-item"><FormattedDate dateString={post.modifiedAt} /></li>
                    <li className="post__meta-item">Author {post.nickName}</li>
                </ul>
            </div>
        </div>
    )
}

export default Post;