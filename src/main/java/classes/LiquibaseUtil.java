package classes;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LiquibaseUtil {

    public static Liquibase lqBase;
    public static void createLQBConnection(){


        try {
            Connection connection= DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/eventlaba3",
                    "postgres",
                    "postgres"
            );

            Database db = DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(connection));

            lqBase= new Liquibase("src/main/resources/master.xml",
                    new FileSystemResourceAccessor(),
                    db);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (LiquibaseException e) {
            e.printStackTrace();
        }


    }

    public static void updateTo(String tag){
        try {
            lqBase.update(tag,"");
        } catch (LiquibaseException e) {
            e.printStackTrace();
        }
    }

}