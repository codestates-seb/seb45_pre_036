import { useState, useRef, useEffect } from "react";
// import axios from "axios";
import PostTest from "../components/PostTest";
import Menu from "../components/Menu";
import "../styles/pages/PostList.css";
import { Link } from "react-router-dom";

const dummy = {
  data: [],
  pageInfo: {
    page: 1,
    size: 10,
    totalElements: 2,
    totalPages: 1,
  },
};

const PostListTest = () => {
  const [posts, setPost] = useState([]);
  const [page, setPage] = useState(dummy.pageInfo.page);
  // for pagination logging
  const [loading, setLoading] = useState(false);
  const [hasmore, setHasmore] = useState(true);
  // more posts to load

  const observer = useRef();
  // last post observer

  const getPosts = async () => {
    setLoading(true);
    try {
      setTimeout(() => {
        setPost((prev) => [...prev, ...dummy.data]);
        setHasmore(page < dummy.pageInfo.totalPages);
      }, 500); // network latency
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
    // if (observer.current) observer.current.disconnect(); // previous observer disconnecting
    observer.current = new IntersectionObserver(
      (entries) => {
        // new observer
        if (entries[0].isIntersecting && hasmore) {
          // 더 있나?
          setPage((prev) => prev + 1); // 페이지 + 1
        }
      },
      { threshold: 0.5 }
    );
    if (node) observer.current.observe(node); // 마지막 post observer
  };

  useEffect(() => {
    dummy.data = new Array(10).fill(null).map((_, index) => {
      return {
        questionId: index + 1, // Unique integer starting from 1
        memberId: 1,
        email: "abc1@gmail.com",
        nickName: "닉네임1",
        title: `질문${index} 제목`,
        content: "질문2 내용",
        createdAt: "2023-08-16T22:25:22.434105",
        modifiedAt: "2023-08-16T22:25:22.434105",
      };
    });
  }, []);

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
          <Link to={"/ask"}>
            <button className="post-list__header-ask">Ask Question</button>
          </Link>
        </div>
        <div className="post-list__container">
          {posts.map((post, idx) => (
            <PostTest key={post.questionId + Math.random()} post={post} />
          ))}
          {/* 마지막 포스트면 ref 넣어주기. */}
          {loading && <div className="post-list__loading">Loading...</div>}
          {!hasmore && (
            <div className="post-list__no-more-posts">No more posts</div>
          )}
        </div>
        <div
          style={{
            backgroundColor: "red",
            height: "100px",
          }}
          ref={lastPostRef}
        >
          Bottom
        </div>
      </main>
    </div>
  );
};

export default PostListTest;
