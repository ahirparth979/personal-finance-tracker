// src/components/Dashboard.js
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../Header/Header';
import './Dashboard.css'; // Assuming you have a CSS file for styling

const Dashboard = () => {
  const [user, setUser] = useState(null);
  const [error, setError] = useState(null);
  const navigate = useNavigate(); // Hook to handle navigation

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/users/me', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('jwtToken'),
          },
        });

        if (response.ok) {
          const data = await response.json();
          setUser(data);
        } else {
          // If response is not ok, handle redirection
          const errMessage = await response.text();
          setError(`Failed to fetch user data: ${errMessage}`);
          // Redirect to login page
          navigate('/login');
        }
      } catch (err) {
        setError(`An error occurred: ${err.message}`);
        // Redirect to login page
        navigate('/login');
      }
    };

    fetchUserData();
  }, [navigate]); // Add navigate to dependency array

  return (
    <div>
      <Header /> {/* Render the Header component */}
    <div className="dashboard-container">
      {user ? (
        <div className="user-info-card">
          <h1>Welcome, {user.username}!</h1>
          <p>Email: {user.email}</p>
          <p>Account Balance: $1000.00</p> {/* Example balance, adjust based on real data */}
          <div className="actions">
            <button className="btn">Add Transaction</button>
            <button className="btn">View Reports</button>
          </div>
        </div>
      ) : (
        <p>Loading user data...</p>
      )}
      {error && <p className="error-message">{error}</p>}
    </div>
    </div>
  );
};

export default Dashboard;
