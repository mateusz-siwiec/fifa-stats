## FIFA stats
Application for storing results of FIFA computer game matches and calculating statistics. Implemented with Java FX.

#### Run instruction
1. `git clone https://github.com/mateusz-siwiec/fifa-stats.git`
2. Set up MySQL database
    1. Create new database and run `src/schema.sql` script to set up schema
    2. Update DB connection information in `fifa.stats.repositories.DatabaseConnector`
3. `mvn clean package`
4. `java -jar target/fifa-stats-beta.jar`
