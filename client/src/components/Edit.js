import React, { useState } from 'react';

const ProfilePage = () => {
    const [ name, setName ] = useState("");
    const [ title, setTitle ] = useState("");

    const handleChange = (e) => {
        setName(e.target.value);
    }

    const handleSubmit = (e) => {
        e.preventDefault();
    }

    return (
        <>
        <div>
            <form onSubmit={handleSubmit} action="/members/{member-id}">
                <div>Display Name</div>
                <label>
                    Name
                    <input type="text" value={name} onChange={handleChange} />
                </label>
                <br />
                <div>
                    Title
                    <input type="text" value={title} onChange={handleChange} />
                </div>
                <button type="submit">Save profile</button>
            </form>
        </div>
        </>
    )
}

export default ProfilePage;
