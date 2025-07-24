
## Define Java 21
export JAVA_HOME="/usr/local/opt/openjdk@21"
export PATH="$JAVA_HOME/bin:$PATH"

## Run project
mvn spring-boot:run

## Run tests 
mvn test