package org.dos.errors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServiceErrorTest {
    ServiceError serviceError;
    DBError dbError;
    PostgresDBError postgresDBError;

    @Before
    public void setup() {
        postgresDBError = new PostgresDBError();
        dbError = postgresDBError;
        serviceError = dbError;


    }

    @Test
    public void testInstanceOf() {
        Assert.assertEquals(postgresDBError,dbError);
        Assert.assertTrue(postgresDBError instanceof ServiceError);
        Assert.assertEquals(postgresDBError,serviceError);
    }
}
