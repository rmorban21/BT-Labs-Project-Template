// src/App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import LoginPage from './components/LoginPage';
import HomePageLoggedIn from './components/HomePageLoggedIn';
import LearnerDashboard from './components/LearnerDashboard';

const App = () => {
    return (
        <AuthProvider>
            <Router>
                <Routes>
                    <Route path="/" element={<LoginPage />} />
                    <Route path="/home" element={<HomePageLoggedIn />} />
                    <Route path="/dashboard" element={<LearnerDashboard />} />
                </Routes>
            </Router>
        </AuthProvider>
    );
};

export default App;
