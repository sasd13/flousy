package flousy.db;

import java.sql.ResultSet;

/**
 * Created by simo on 23/03/2015.
 */
public abstract  class DbConnect {



            public abstract  java.sql.Connection openConnection(DatabaseInformation databaseInformation) throws Throwable;


    public abstract  ResultSet execute(String req) throws Throwable;
        public abstract  int executeUpdate(String req) throws Throwable;

}
