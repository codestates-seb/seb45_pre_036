const Login = () => {

    return (
        <div className='login-form'>
            <div className='login-form__logo'>Logo</div>
            <form className='login-form__form' onSubmit>
                <label className='login-form__label' htmlFor='email'>Email</label>
                <input className='login-form__input' id='email' type='text' ></input>
                <label className='login-form__label' htmlFor='password'>Password</label>
                <input className='login-form__input' type='password' id='password'></input>
                <button className='login-form__button' >Log in</button>
                <hr className='login-form__divider' />
                <button className='login-form__github-button' type='submit'><img className='login-form__github-logo' src alt/>Log in with Github</button>
            </form>
            <p className='login-form__signup-text'>Don't have an account? <a href className="login-form__signup-link">Sign up</a></p>
        </div>
    )
}

export default Login;