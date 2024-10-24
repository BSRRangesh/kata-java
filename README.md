There are two modules in the Repository:

1. **book-store** --> Java Spring boot project for backend processing
2. **ui-project-files**  --> UI component of BookStore


Steps to setup **book-store**:

  1. Clone the repository.
  2. Navigate to the project directory.
  3. Build the project using Maven:
        **mvn clean install**
  4. Run the application:
      **mvn spring-boot:run**

 H2 in memory database is used store records in the database.
 src/main/resources/data.sql - Has the initial setup to create few entries into BOOKS Table and same will be used to display in UI.


Steps to try application from Front end:
1. Locate and open register.html file from **UI-project-files** directory.
2. Initially, no users will exist in the applcation, proceed to register.
3. Provide username(min 3 characters), password(min 8 characters), email(valid format) to register user successfully(username and email are unique fields to register).
4. You will be redirected directed to Login page upon registration.
5. Login with registered username and password to start using the application.

 

