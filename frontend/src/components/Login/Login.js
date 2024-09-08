import React, { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import Header from '../Header/Header';
import './Login.css';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);
    const location = useLocation();
    const navigate = useNavigate();

    const from = location.state?.from?.pathname || '/dashboard'; // Redirect to /dashboard or default

    const handleSubmit = async (e) => {
        e.preventDefault();

        const loginData = {
            username: username,
            password: password
        };

        try {
            const response = await fetch('http://localhost:8080/api/users/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(loginData),
            });

            if (response.ok) {
                const data = await response.json();
                localStorage.setItem('jwtToken', data.jwtToken);
                localStorage.setItem('username', data.username);
                localStorage.setItem('roles', JSON.stringify(data.roles));
                navigate(from, { replace: true });
            } else {
                const errMessage = await response.text();
                setError(`Login failed: ${errMessage}`);
            }
        } catch (err) {
            setError(`An error occurred: ${err.message}`);
        }
    };

    const handleRegister = async (e) => {
        e.preventDefault();

        navigate('/register');
    };

    return (
        <div>
        <Header /> {/* Render the Header component */}
        <div className="login-container">
            <div className="login-form">
                <h2>Welcome Back</h2>
                <p>Please login to access your Personal Finance Tracker</p>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="username">Username</label>
                        <input
                            type="text"
                            id="username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <input
                            type="password"
                            id="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    <button type="submit" className="login-btn">Login</button>
                </form>
                <button onClick={handleRegister} type="submit" className="register-btn">Register</button>
                {error && <p className="error-message">{error}</p>}
            </div>
        </div>
        </div>
    );
};

export default Login;
