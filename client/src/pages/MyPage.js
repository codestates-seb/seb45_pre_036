import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCalendarDays, faPencil, faExclamation, faCakeCandles, faClock } from "@fortawesome/free-solid-svg-icons";

const MyPage = () => {
    return (
        <main className="container">
            <div className="profile__box">
                <img src={require("../static/profile-picture.png")} alt="profile" />
                <div className="username">Charlie</div>
                <ul className="userinfo">
                    <li><FontAwesomeIcon icon={faCakeCandles} />Member for</li>
                    <li><FontAwesomeIcon icon={faClock} />Last seen</li>
                    <li><FontAwesomeIcon icon={faCalendarDays} />Visited</li>
                </ul>
            </div>
            <div className="button__box">
                <a className="edit__button" href>
                    <FontAwesomeIcon icon={faPencil} />
                    <span className>Edit Profile</span>
                </a>
                <a className="delete__button" href>
                    <FontAwesomeIcon icon={faExclamation} />
                    <span className>Delete Profile</span>
                </a>
            </div>
            <div className="profile__contents">
                <ul>
                    <div className="about">
                        About
                    </div>
                    <div className="questions__by__user">
                        Questions {/* a href
                        {number} Questions or 1 Question */}
                    </div>
                    <div className="answers__by__user">
                        Questions {/* a href
                        {number} Answers or 1 Answer */}
                    </div>
                </ul>
            </div>
        </main>
    )
}

export default MyPage;