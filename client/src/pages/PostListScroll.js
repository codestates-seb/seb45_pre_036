import { useState, useRef, useEffect } from "react";
import Post from "../components/PostItem";
import Menu from "../components/Menu";
import { Link } from "react-router-dom";
import '../styles/pages/PostList.css';
import Axiosinstance from "../auth/AxiosConfig";

const PostList = () => {
  const [posts, setPost] = useState([]);
  const [page, setPage] = useState(1);
  // for pagination logging
  const [loading, setLoading] = useState(false);
  const [hasmore, setHasmore] = useState(true);
  // more posts to load

  const observer = useRef();
  // last post observer

  const getPosts = async () => {
    setLoading(true);
    try {
      const res = await Axiosinstance.get(
        '/questions'
      );
      setPost((prev) => [...prev, ...res.data.data]);

      setHasmore(res.data.length === 10);
      // 10개 미만으로 주면 더이상 줄게 없다는 거잖아. 그럼 false.
    } catch (err) {
      console.log(err);
    } finally {
      setLoading(false);
    }
  };

  // 무한 스크롤하려면 필요함. 마지막 글에 대한 참조를 만들기 위한 함수
  // 유저가 스크롤해서 가장 마지막 포스트가 뷰포트에 보면 intersection observer가 콜백함수 트리거.
  // 이 콜백함수는 페이지 숫자 업데이트해. 그럼 page가 바뀔때 새 포스트 패치해오게 useEffect로 의존성 만들어주면 되겠지.
  const lastPostRef = (node) => {
    // node? 마지막 포스트의 DOM el node.
    if (loading) return;
    observer.current = new IntersectionObserver((entries) => {
      // new observer
      if (entries[0].isIntersecting && hasmore) {
        // 더 있나?
        setPage((prev) => prev + 1); // 페이지 + 1
      }
    }, {threshold: 0.5});
    if (node) observer.current.observe(node); // 마지막 post observer
  };

  useEffect(() => {
    getPosts();
  }, [page]);

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
            <Post
              key={post.questionId + Math.random()}
              post={post}
            />
          ))}
          {loading && <div className="post-list__loading">Loading...</div>}
          {!hasmore && <div className="post-list__no-more-posts" ref={lastPostRef} >No more posts</div>}
          {/* 여기에 observer 쓰면 안 되나. */}
        </div>
      </main>
    </div>
  );
};

export default PostList;
