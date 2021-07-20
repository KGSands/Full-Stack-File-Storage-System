Spring Boot Backend and ReactJS frontend.
=====================================
INSTRUCTIONS
=====================================
1. Navigate to /target, run the following command:
`java -jar target/file-storage-0.0.1-SNAPSHOT.jar`

** Java is required **

2. When it has complete loading, you will be at the CLI main screen, enter 'help' for a list of commands.

3. Navigate to localhost:8080 to use the UI version

=====================================
ADDITIONAL INFORMATION
=====================================
1. You can navigate to src/main/java/com/challenge and open FileStorageApplication.java to run the server/CLI separately.

2. You can navigate to src/main/ui and use `npm start` to run the UI separately.
    - Note: `npm install` will be required

3. Max file upload size is 10MB.

4. To build the project I did the following:
    - built the UI (/src/main/ui) with npm run build
    - copy the contents from /build to my spring-boot static directory (/src/main/resources/static)
    - from the top directory ran `./mvnw clean install -DskipTests`

=====================================
FILE STRUCTURE
=====================================
1. Server is located here: /src/main/java

2. UI is located here: /src/main/ui
