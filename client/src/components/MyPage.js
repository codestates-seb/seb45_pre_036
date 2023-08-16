import '../styles/components/MyPage.css';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCalendarDays, faPencil, faExclamation, faCakeCandles, faClock } from "@fortawesome/free-solid-svg-icons";

const MyPage = () => {
    return (
        <main className="container">
            <div className="top__line">
                <img src={require("../static/profile-picture.png")} alt="profile" />
                <div className="user__box">
                    <div className="username">Charlie</div>
                    <ul>
                        <li className="icon1"><FontAwesomeIcon icon={faCakeCandles} /> Member for n days</li>
                        <li className="icon1"><FontAwesomeIcon icon={faClock} /> Last seen this week</li>
                        <li className="icon1"><FontAwesomeIcon icon={faCalendarDays} /> Visited 2 days, 2 consecutive</li>
                    </ul>
                </div>
                <div className="button__box">
                    <a className="edit button border" href>
                        <FontAwesomeIcon icon={faPencil} />
                        <span className> Edit Profile</span>
                    </a>
                    <a className="delete button border" href>
                        <FontAwesomeIcon icon={faExclamation} />
                        <span className> Delete Profile</span>
                    </a>
                </div>
            </div>
            <div className="profile__contents">
                <div className="contents__title">About</div>
                <div className="contents__box border">
                </div>
                <div className="contents__title">Questions</div>
                <div className="contents__box border">
                    {/* a href
                    {number} Questions or 1 Question */}
                </div>
                <div className="contents__title">Answers</div>
                <div className="contents__box border">
                    {/* a href
                    {number} Answers or 1 Answer */}
                </div>
            </div>
        </main>
    )
}

export default MyPage;