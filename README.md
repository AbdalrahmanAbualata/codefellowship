# codefellows

## This is a full stack web app,it allows users to add post,follow users and see thier posts.

# Run the App:
$ ./gradlew bootRun

## Localhost
* localhost:8181

## application properties:
- after you clone this repo make sure to edit application.properties.
    * spring.datasource.platform=postgres
    * spring.datasource.url=jdbc:postgresql://localhost:5432/databaseNmae
    * spring.datasource.username=username
    * spring.datasource.password=password
    * spring.jpa.hibernate.ddl-auto=create

# Routes:
- ("/"): to get home page
- ("/login"): to login
- ("/signup"): to signup
- ("/userProfile"): to see your profile and posts.
