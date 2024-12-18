package org.dos.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class PostgresConnectorConfigTest {
    PostgresConnectorConfig postgresConnectorConfig;
    final String HOST = "HOST";
    final String DATABASE = "DATABASE";
    final String PORT = "8080";
    final String URL = "URL";
    final String USER = "USER";
    final String PASSWORD = "PASSWORD";


    @Before
    public void setup() {
        postgresConnectorConfig = new PostgresConnectorConfig();
    }

    @Test
    public void testSettersAndGetters() {
        postgresConnectorConfig.setHost(HOST);
        Assert.assertEquals(HOST,postgresConnectorConfig.getHost());

        postgresConnectorConfig.setConnectionUrl(URL);
        Assert.assertEquals(URL,postgresConnectorConfig.getConnectionUrl());

        postgresConnectorConfig.setDatabase(DATABASE);
        Assert.assertEquals(DATABASE,postgresConnectorConfig.getDatabase());

        postgresConnectorConfig.setPort(PORT);
        Assert.assertEquals(PORT,postgresConnectorConfig.getPort());

        postgresConnectorConfig.setUser(USER);
        Assert.assertEquals(USER,postgresConnectorConfig.getUser());

        postgresConnectorConfig.setPassword(PASSWORD);
        Assert.assertEquals(PASSWORD,postgresConnectorConfig.getPassword());
    }

}
