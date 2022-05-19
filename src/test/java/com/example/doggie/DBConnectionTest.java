package com.example.doggie;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DBConnectionTest {

    @BeforeEach
    public void testInfoBefore() throws SQLException {
        System.out.println("Test started");
    }

    @AfterEach
    public void testInfoAfter() {
        System.out.println("Test completed");
    }

    @Test
    void Connect(){

        boolean flag = true;
        String url = "jdbc:postgresql://localhost/dgdb";

        try {
            DriverManager.registerDriver(new Driver());
            Connection conn = DriverManager.getConnection(url,"postgres", "Lushlife2018");
            System.out.println("Test success");

        } catch (SQLException se) {
            flag = false;
            System.out.println("Test Failed");
        }
        assertTrue(flag);
    }
}
