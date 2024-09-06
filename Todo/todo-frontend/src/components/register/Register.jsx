import React, {useState} from "react";
import './register.css';
import {useNavigate} from "react-router-dom";

const Register = () => {
    const [name, setName]= useState("");
    const [phone, setPhone]= useState("");
    const [email, setEmail]= useState("");
    const [password, setPassword]= useState("");
    const [message, setMessage]= useState("");
    const navigate = useNavigate();

    let handleRegister = async (e)=> {
        e.preventDefault();
        try{
            let res= await fetch("/api/user/register",{
                method: "POST",
                body: JSON.stringify({
                    name: name,
                    email: email,
                    phoneNo: phone,
                    password: password,
                }),
                headers: {'Content-Type': 'application/json'},
            });
            if(res.status===201){
                setName("");
                setPhone(null);
                setEmail("");
                setPassword("");
                alert("User Registered");
                navigate("/auth/login");
            }
            else{
                setMessage("Invalid Input");
            }
        }
        catch(err){
            console.log(err);
        }
    };

    return (
        <div className="register">
            <form onSubmit={handleRegister}>
                <div className='register-form-legend'><b>Register Yourself!</b></div>
                <div className='register-form-label'>
                    <label>Name :</label><br></br>
                    <input type="text" placeholder="Enter Name" value={name} onChange={(e)=> setName(e.target.value)} required></input><br></br>
                </div>
                <div className='register-form-label'>
                    <label>Phone No :</label><br></br>
                    <input type="number" placeholder="Enter Phone No" value={phone} onChange={(e)=> setPhone(e.target.value)} required></input><br></br>
                </div>
                <div className='register-form-label'>
                    <label>Email Id :</label><br></br>
                    <input type="email" placeholder="Enter Email" value={email} onChange={(e)=> setEmail(e.target.value)} required></input><br></br>
                </div>
                <div className='register-form-label'>
                    <label>Password :</label><br></br>
                    <input type="password" placeholder="Password" value={password} onChange={(e)=> setPassword(e.target.value)} required></input>
                </div>
                <div className='register-form-buttons'>
                    <input className='register-form-buttons-reset' type="reset" value="RESET"></input>
                    <input className='register-form-buttons-login' type="submit" value="REGISTER"></input>
                </div>
                <div className='register-form-message'>
                    {message ? <p id='message'>{message}</p> : null}
                </div>
                <div className='regsiter-form-register'>
                    <p>Existing User? <a href='/auth/login'>SignIn</a></p>
                </div>
            </form>
        </div>
    )
}
export default Register