const PostContent = ({ content, nickName, modifiedAt }) => {
    return (
        <div>
            <div>{content}</div>
            <div>
                <button>Edit</button>
                <button>Delete</button>
                <button>Add a comment</button>
                <div>
                    <p>{modifiedAt}</p>
                    <h1>{nickName}</h1>
                </div>
            </div>
        </div>
    )
}

export default PostContent;