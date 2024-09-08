import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Header.css'; // Assuming you have a CSS file for styling
import appLogo from '../../assets/app-logo.png';

const Header = () => {
    const username = localStorage.getItem('username');
    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem('jwtToken');
        localStorage.removeItem('username');
        localStorage.removeItem('roles');
        navigate('/login');
    };

    return (
        <header className="header">
            <div className="logo">
                <Link to="/">
                    <img src={appLogo} alt="Finance Tracker Logo" className="logo-img" /> {/* Placeholder logo path */}
                    <span className="app-name">Finance Tracker</span>
                </Link>
            </div>
            <div className="user-info">
                {username && (
                    <>
                        <span className="welcome-msg">Welcome, {username}!</span>
                        <button className="logout-btn" onClick={handleLogout}>Logout</button>
                    </>
                )}
            </div>
        </header>
    );
};

export default Header;
