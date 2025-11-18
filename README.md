# Sports Hub Backend
This backend application waits for calls from the React web application for soccer fixtures to fetch data from the api-sports football site. This data is then transformed to be displayed in the React web app.

## 🚀 Tech Stack
- Java 25
- Springboot 3.5.7
- Maven
- Springboot Webflux: Reactive, non-blocking API
- Caffeine Cache: High-performance in-memory caching
- API-Football: Third party sports data API

## 📋 Features
- ⚽️ Fetch live football fixtures
- 🗓️ Get today's scheduled fixtures
- 📊 Fixture statistics
- 📡 Server-Sent Events (SSE) for real-time score streaming
- 💾 Caching to minimize API calls
- 💥 Reactive, non-blocking architecture

## 🏗️ Project Structure
```
sportshub-backend/
├── src/
│   ├── main/
│   │   ├── java/org/example/sportshub/
│   │   │   ├── config/
│   │   │   │   ├── CacheConfig.java      # Caffeine cache configuration
│   │   │   │   └── WebConfig.java        # CORS configuration
│   │   │   ├── controller/
│   │   │   │   └── FixturesController.java  # REST endpoints
│   │   │   ├── model/
│   │   │   │   ├── Match.java            # Match data model
│   │   │   │   └── Fixture.java          # Fixture data model
│   │   │   ├── service/
│   │   │   │   └── FootballApiService.java  # API integration logic
│   │   │   └── SportsHubApplication.java    # Main application
│   │   └── resources/
│   │       └── application.yml           # Configuration (use secrets file)
│   └── test/
├── pom.xml
└── README.md
```

## 🛠️ Pre-requisites
- Java 17+
- Maven 3.6+
- API-Football API Key

## ⚙️ Setup
### 1. Clone the Repository
   ``` bash
   git clone https://github.com/yourusername/sportshub-backend.git
   cd sportshub-backend
   ```
### 2. Configure API Key
   #### Create src/main/resources/application-secrets.yml:
   ``` yaml
   api-football:
     api-key: YOUR_API_KEY_HERE
   ```
   #### Get your API key:
   - Sign up at API-Football
   - Copy your API key from the dashboard
   - Free tier: 100 requests/day
### 3. Add to .gitignore
   Ensure your secrets file is never committed:
   ``` bash
   echo "application-secrets.yml" >> .gitignore
   ```
### 4. Install Dependencies
   ```bash
   mvn clean install
   ```
### 5. Run the Application
   ```bash
   mvn spring-boot:run
   ```
The server will start on http://localhost:8080

## 💾 Caching Strategy
### The application uses Caffeine Cache to minimize API calls:
  - Live Matches Cache: 5 minutes TTL
  - Today's Matches Cache: 5 minutes TTL
  - Maximum entries: 100 per cache
  - Async mode: Enabled for reactive types

### Cache configuration in CacheConfig.java:
``` java
maximumSize=100,expireAfterWrite=5m,recordStats
```
This keeps you well under API rate limits while maintaining fresh data.

## 🙏 Acknowledgments
- [API-Football](https://www.api-football.com) for providing sports data
- [Spring Boot](https://spring.io/projects/spring-boot) for the reactive framework
- [Project Reactor](https://projectreactor.io/) for reactive streams
