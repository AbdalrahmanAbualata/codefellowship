# codefellows

## This is a full stack web app,it allows users to add post,follow users and see thier posts.

# Run the App:
$ ./gradlew bootRun

## Localhost
* localhost:8080

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
- ("/Profile"): to see your profile and posts.
- ("/addPost"): to add a post
-("/allUsers"): to see all users and follow them.
- ("/feed"): to see posts of your followers.
- ("/error"): this page appears if there is problem in other pages.
- ("/index"): to see home page.
- ("/users"): to see other users  pages.
-("/userFollowingMe"): to see who is following you.
-("/usersIFollowed"): to see all users you are following.