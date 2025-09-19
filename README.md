# Cricket Live Score Project

A full-stack web application for tracking cricket matches with real-time scores using Spring Boot, React, and MongoDB.

## Features

- Live match scores updated in real-time
- Upcoming matches schedule
- Completed matches with results
- Automatic data fetching from CricAPI
- Responsive design for all devices

## Technologies Used

### Backend
- Spring Boot
- Spring Data MongoDB
- Spring Web
- CricAPI integration

### Frontend
- React
- React Router
- Axios for API calls
- CSS3 with Flexbox and Grid

### Database
- MongoDB

## Project Structure

\\\
cricket-live-score/
+-- backend/                 # Spring Boot application
¦   +-- src/
¦   ¦   +-- main/
¦   ¦       +-- java/
¦   ¦       ¦   +-- com/
¦   ¦       ¦       +-- cricket/
¦   ¦       ¦           +-- config/
¦   ¦       ¦           +-- controller/
¦   ¦       ¦           +-- model/
¦   ¦       ¦           +-- repository/
¦   ¦       ¦           +-- scheduler/
¦   ¦       ¦           +-- service/
¦   ¦       +-- resources/
¦   ¦           +-- application.properties
¦   +-- pom.xml
+-- frontend/                # React application
¦   +-- public/
¦   +-- src/
¦   ¦   +-- components/
¦   ¦   +-- services/
¦   ¦   +-- styles/
¦   +-- package.json
¦   +-- package-lock.json
+-- README.md
\\\

## Setup Instructions

### Prerequisites
- Java 11 or higher
- Node.js 14 or higher
- MongoDB
- CricAPI account and API key

### Backend Setup

1. Navigate to the backend directory:
   \\\ash
   cd backend
   \\\

2. Update the \pplication.properties\ file with your MongoDB connection string and CricAPI key.

3. Build and run the Spring Boot application:
   \\\ash
   mvn spring-boot:run
   \\\

### Frontend Setup

1. Navigate to the frontend directory:
   \\\ash
   cd frontend
   \\\

2. Install dependencies:
   \\\ash
   npm install
   \\\

3. Start the development server:
   \\\ash
   npm start
   \\\

## API Endpoints

- \GET /api/matches\ - Get all matches
- \GET /api/matches/status/{status}\ - Get matches by status (upcoming, live, completed)
- \POST /api/matches/refresh\ - Manually refresh matches from CricAPI

## License

This project is licensed under the MIT License.
