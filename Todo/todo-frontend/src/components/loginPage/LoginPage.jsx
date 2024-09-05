import React, {useContext, useState} from 'react';
import './loginPage.css';
import {UserContext} from "../../context/UserContext.jsx";
import {useNavigate} from "react-router-dom";

const LoginPage = () => {
    const [email, setEmail]= useState("");
    const [password, setPassword]= useState("");
    const [message, setMessage]= useState("");
    const navigate = useNavigate();
    const user = useContext(UserContext);

    let handleSubmit = async (e)=> {
        e.preventDefault();
        try{
            let res = await fetch("/api/auth/login",{
                method: "POST",
                body: JSON.stringify({
                    username: email,
                    password: password,
                }),
                headers: {'Content-Type': 'application/json'},
            });
            let resJSON = await res.text();
            if(res.status===200){
                user.setCurrentUser(JSON.parse(resJSON));
                setEmail("");
                setPassword("");
                setMessage("Token Received Successfully");
                navigate("/dashboard");
            }
            else{
                setMessage("Error");
            }
        }
        catch(err){
            console.log(err);
        }
    };

    return (
        <div className="loginPage">
            <form onSubmit={handleSubmit}>
                <div className='login-form-legend'><b>User Login</b></div>
                <div className='login-form-label'>
                    <label>Email Id :</label><br></br>
                    <input type="email" placeholder="Enter email" value={email} onChange={(e)=> setEmail(e.target.value)} required></input><br></br>
                </div>
                <div className='login-form-label'>
                    <label>Password :</label><br></br>
                    <input type="password" placeholder="Password" value={password} onChange={(e)=> setPassword(e.target.value)} required></input>
                </div>
                <div className='login-form-buttons'>
                    <input className='login-form-buttons-reset' type="reset" value="RESET"></input>
                    <input className='login-form-buttons-login' type="submit" value="LOGIN"></input>
                </div>
                <div className='login-form-message'>
                    {message ? <p>{message}</p> : null}
                </div>
                <div className='login-form-register'>
                    <p>New User? <a href='#'>Register here</a></p>
                </div>
            </form>
        </div>
    )
}

export default LoginPage