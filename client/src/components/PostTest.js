import '../styles/components/Post.css';
import FormattedDate from './FormattedDate';

const PostTest = ({ post }) => {

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
                <h1 className="post__content-title">{post.title}</h1>
                {/* 제목 클릭하면...detail view 열리게 구현... */}
                <p className="post__content-text">{post.content}</p>
            </div>
            <div className="post__meta">
                <ul className="post__meta-list">
                    <li className="post__meta-item">{post.createdAt}</li>
                    <li className="post__meta-item"><FormattedDate dateString={post.modifiedAt} /></li>
                    <li className="post__meta-item">{post.nickName}</li>
                </ul>
            </div>
        </div>
    )
}

export default PostTest;