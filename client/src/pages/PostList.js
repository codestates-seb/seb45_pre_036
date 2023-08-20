// Without infinite scroll

import { useState, useEffect } from "react";
import PostItem from "../components/PostItem";
import Menu from "../components/Menu";
import { Link } from "react-router-dom";
import '../styles/pages/PostList.css';
import Axiosinstance from "../auth/AxiosConfig";
// import jwtDecode from "jwt-decode";

const PostList = () => {
  const [posts, setPost] = useState([]);
  // const [page, setPage] = useState(1);
  const [loading, setLoading] = useState(false);

  const getPosts = async () => {
    setLoading(true);
    try {
      const res = await Axiosinstance.get(
        '/questions'
      );
      setPost((prev) => [...prev, ...res.data.data]);
    } catch (err) {
      console.log(err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    getPosts();
  }, []);

  return (
    <div className="main-container">
      <div className="side-menu">
        <Menu />
      </div>
      <main className="post-list__main">
        <div className="post-list__header">
          <h1 className="post-list__header-title">All Questions</h1>
          <Link to={'/ask'}><button className="post-list__header-ask">Add New Question</button></Link>
        </div>
        <div className="post-list__container">
          {posts.map((post) => (
            <PostItem
              key={post.questionId + Math.random()}
              post={post}
            />
          ))}
          {loading && <div className="post-list__loading">Loading...</div>}
        </div>
      </main>
    </div>
  );
};

export default PostList;
