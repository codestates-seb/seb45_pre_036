import '../styles/components/Post.css';

const Post = ({ post }) => {

    return (
        <div className="post">
            <div className="post__info">
                <ul className="post__info-list">
                    <li className="post__info-item">0 votes</li>
                    <li className="post__info-item">{post.answers.length}answers</li>
                    <li className="post__info-item">{post.count}views</li>
                </ul>
            </div>
            <div className="post__content">
                <h1 className="post__content-title">{post.title}</h1>
                {/* 제목 클릭하면...detail view 열리게 구현... */}
                <p className="post__content-text">{post.content}</p>
            </div>
            <div className="post__meta">
                <ul className="post__meta-list">
                    <li className="post__meta-item">{post.createdAt}</li>
                    <li className="post__meta-item">{post.modifiedAt}</li>
                    <li className="post__meta-item">{post.nickname}</li>
                </ul>
            </div>
        </div>
    )
}

export default Post;