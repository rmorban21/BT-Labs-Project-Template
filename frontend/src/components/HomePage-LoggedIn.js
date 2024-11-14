// src/components/HomePageLoggedIn.js
import React from 'react';
import { useNavigate } from 'react-router-dom';
import './HomePageLoggedIn.css';

const HomePageLoggedIn = () => {
    const navigate = useNavigate();

    const goToDashboard = () => {
        navigate('/dashboard');
    };

    return (
        <div className="homepage-container">
            <h1>Welcome to the Assignment Review App</h1>
            <div className="boxes-container">
                <div className="box">Box 1 Content</div>
                <div className="box">Box 2 Content</div>
                <div className="box">Box 3 Content</div>
            </div>
            <button className="dashboard-button" onClick={goToDashboard}>
                Go to Dashboard
            </button>
        </div>
    );
};

export default HomePageLoggedIn;
