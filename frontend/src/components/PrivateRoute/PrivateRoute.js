// src/components/PrivateRoute.js
import React from 'react';
import { Navigate } from 'react-router-dom';

const PrivateRoute = ({ children }) => {
  const isAuthenticated = !!localStorage.getItem('jwtToken'); // Example of checking auth status

  return isAuthenticated ? children : <Navigate to="/login" />;
};

export default PrivateRoute;
