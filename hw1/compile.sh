./clean.sh mysql
./run.sh mysql mysql
sleep 7
mvn -f ./fs_webApp/pom.xml spring-boot:run
