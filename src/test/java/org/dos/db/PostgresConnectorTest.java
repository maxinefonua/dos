package org.dos.db;

import org.dos.config.PostgresConnectorConfig;
import org.dos.db.connectors.PostgresConnector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PostgresConnectorTest {
    PostgresConnector postgresConnector;

    @Before
    public void setup() {
        postgresConnector = new PostgresConnector(new PostgresConnectorConfig());
    }

    @Test
    public void testConstructor() {
        Assert.assertNotNull(postgresConnector);
    }

}
