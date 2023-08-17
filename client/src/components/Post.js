const Post = (props) => {

    return (
        <div className="post">
            <div className="post__info">
                <ul className="post__info-list">
                    <li className="post__info-item">votes</li>
                    <li className="post__info-item">answers</li>
                    <li className="post__info-item">views</li>
                </ul>
            </div>
            <div className="post__content">
                <h1 className="post__content-title">post title</h1>
                <p className="post__content-text">post content</p>
            </div>
            <div className="post__meta">
                <ul className="post__meta-list">
                    <li className="post__meta-item">createdAt</li>
                    <li className="post__meta-item">updatedAt</li>
                    <li className="post__meta-item">author</li>
                </ul>
            </div>
        </div>
    )
}

export default Post;