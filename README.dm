# Retrospective Test Project

The Retrospective project is a simple Spring Boot application designed to manage retrospective data for SCRUM ceremonies.
It provides RESTful endpoints for creating, retrieving, and managing retrospectives, including feedback items.

## How to run

1. Download or clone the application from  https://github.com/andrewAdjei/retrospective.git
2. Import the project into your preferred IDE, for example Intellij or Eclipse
3. Build and run the maven application = mvn spring-boot:run
4. The application runs on port 9092 but this could be changed to your desired port (8080) in the application.properties file
5. Access the endpoints through your preferred API testing tool (e.g., Postman).

## Dependencies

- **Spring Boot:** Framework for building Java-based enterprise applications.
- **Spring Data JPA:** Part of the larger Spring Data project and provides easy integration with JPA repositories.
- **H2 Database:** Embedded in-memory database for development and testing purposes.

## End Points
- Create a retrospective:
  POST /createRetro
  http://localhost:9092/api/v1/retrospective
      {
     "name": "sprint2",
     "summary": "Reflecting on Sprint 1",
     "date": "2024-02-10",
     "participants": ["John", "Jane"],
     "feedbackItems": [
       {
         "feedbackProvider": "John",
         "body": "Positive feedback",
         "feedbackType": "positive"
       },
       {
         "feedbackProvider": "Jane",
         "body": "Negative feedback",
         "feedbackType": "POSITIVE"
       }
     ]
       }

  List all retrospectives:
  GET /getAllRetros
  http://localhost:9092/api/v1/retrospective

  Get a retrospective by name:
  GET /getRetroByName
  http://localhost:9092/api/sprint2

  Get All Retrospectives By Pagination
  GET/getAllRetrosByPage
  http://localhost:9092/api/v1/retrospective/page/0/6


  Add feedback item to a retrospective:
  POST /createFeedback
  http://localhost:9092/api/sprint1/feedback

  {
  "feedbackProvider": "Alice",
    "body": "New positive feedback",
    "feedbackType": "positive"
  }

   Update feedback item:
   PUT /updateFeedback
   http://localhost:9092/api/v1/sprint1/feedback/1

   {
     "body": "Updated positive feedback",
     "feedbackType": "positive"
   }

    Get All Retrospectives By sort
    GET/ getAllRetrosBySort
    http://localhost:9092/api/v1/retrospective/sort

The Controller provides more information on the endpoints.

