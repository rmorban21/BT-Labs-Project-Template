// src/components/LearnerDashboard.js
import React from 'react';
import './LearnerDashboard.css';

const LearnerDashboard = () => {
    return (
        <div className="dashboard-container">
            <h1>Learner Dashboard</h1>
            <section>
                <h2>Needs Work</h2>
                <p>Assignments needing attention...</p>
            </section>
            <section>
                <h2>In Review</h2>
                <p>Assignments under review...</p>
            </section>
            <section>
                <h2>Complete</h2>
                <p>Completed assignments...</p>
            </section>
            <button className="submit-assignment-btn">Submit New Assignment</button>
        </div>
    );
};

export default LearnerDashboard;
