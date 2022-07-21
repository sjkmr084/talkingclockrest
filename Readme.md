# Talking clock
REST implementation of Talking clock.

# Tech used
- Java
- SpringBoot 
- Spring Rest
- Gradle

# How to Build the application

Import the project in any Java IDE (preferably IntelliJ). And Build the application.
Build can be done through the UI of the IDE
Or you can build the application using below command
```shell
cd talkingclockrest # go to project directory
./gradlew build # build the application
```

# Running the application
```shell
cd talkingclockrest # go to the project dir
cd build # go to the build folder under the project directory
cd libs # go to libs folder under build
java -jar <jar name> <press enter> # This will run the application and tomcat will start listening to port 8080. 
<At this point  try to hit the endpoint http://localhost:8080/talkingclock/{time} using browser or postman. Based on the input {time} serivce will respond appropriatly>
```
