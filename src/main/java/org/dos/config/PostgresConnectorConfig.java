package org.dos.config;

import lombok.Getter;
import lombok.Setter;

public class PostgresConnectorConfig {
    @Getter @Setter
    String host;
    @Getter @Setter
    String port;
    @Getter @Setter
    String database;
    @Getter @Setter
    String user;
    @Getter @Setter
    String password;
    @Getter @Setter
    String connectionUrl;
}
