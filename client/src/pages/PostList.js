import { useState, useRef, useEffect } from "react";
import axios from "axios";
import Post from "../components/Post";
import Menu from "../components/Menu";

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
      const res = await axios.get(
        `http://localhost:8080/questions?page=${page}&size=10`
      );
      setPost((prev) => [...prev, res.data]);
      // setPost((prev) => [...prev, ...res.data]);
      // 스프레드로 줘야하는지, 아닌지 체크가 필요함. 

      setHasmore(res.data.length === 10);
      // 10개 미만으로 주면 더이상 줄게 없다는 거잖아. 그럼 false가 되는거지.
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
    if (observer.current) observer.current.disconnect(); // previous observer disconnecting
    observer.current = new IntersectionObserver((entries) => {
      // new observer
      if (entries[0].isIntersecting && hasmore) {
        // 더 있나?
        setPage((prev) => prev + 1); // 페이지 + 1
      }
    });
    if (node) observer.current.observe(node); // 마지막 post observer
  };

  useEffect(() => {
    getPosts();
  }, [page]);

  return (
    <div>
      <Menu />
      <main>
        <div>
          <h1>All Questions</h1>
          <button>Add New Question</button>
        </div>
        <div>
          {posts.map((post, idx) => (
            <Post
              key={post.id}
              post={post}
              ref={idx === posts.length - 1 ? lastPostRef : null}
            />
          ))}
          {/* 마지막 포스트면 ref 넣어주기.  */}
          {loading && <div>Loading...</div>}
          {!hasmore && <div>No more posts</div>}
        </div>
      </main>
    </div>
  );
};

export default PostList;
