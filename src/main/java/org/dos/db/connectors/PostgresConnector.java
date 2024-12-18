package org.dos.db.connectors;

import io.vavr.control.Either;
import org.dos.config.PostgresConnectorConfig;
import org.dos.data.models.AdvisoryEntry;
import org.dos.db.DBConnectorImpl;
import org.dos.errors.DBError;
import org.dos.errors.PostgresDBError;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresConnector implements DBConnectorImpl<AdvisoryEntry> {

    PostgresConnectorConfig postgresConnectorConfig;
    private static Connection connection;

    public PostgresConnector(PostgresConnectorConfig postgresConnectorConfig) {
        this.postgresConnectorConfig = postgresConnectorConfig;
    }

    // TODO: add connection and test
//    private Connection getConnection() throws SQLException {
//        if (connection == null) connection = DriverManager.getConnection(postgresConnectorConfig.getConnectionUrl());
//        return connection;
//    }

    // TODO: add tests for each
//    @Override
//    public Either<DBError, AdvisoryEntry> create(AdvisoryEntry entity) {
//        return Either.left(new PostgresDBError());
//    }
//
//    @Override
//    public Either<DBError, AdvisoryEntry> read(String id) {
//        return Either.left(new PostgresDBError());
//    }
//
//    @Override
//    public Either<DBError, AdvisoryEntry> update(AdvisoryEntry entity) {
//        return Either.left(new PostgresDBError());
//    }
//
//    @Override
//    public Either<DBError, AdvisoryEntry> delete(String id) {
//        return Either.left(new PostgresDBError());
//    }
//
//    public Either<DBError,Integer> executeUpdate(String statement) {
//        return Either.left(new PostgresDBError()); // returns number of rows affected
//    }
//
//    public Either<DBError,ResultSet> executeQuery(String query){
//        return Either.left(new PostgresDBError());
//    }
}
