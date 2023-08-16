import '../styles/components/MyPage.css';

const ProfileContents = () => {
    return (
        <div className="profile__contents">
            <div className="contents__title">About</div>
            <div className="contents__box border"></div>
            <div className="contents__title">the number of question</div>
            <div className="contents__box border">
                <div className="title">question title</div>
                <div>asked when</div>
            </div>
            <div className="contents__title">the number of annswer</div>
            <div className="contents__box border">
                <div className="title">question title</div>
                <div>asked when</div>
            </div>
        </div>
    )
}

export default ProfileContents;