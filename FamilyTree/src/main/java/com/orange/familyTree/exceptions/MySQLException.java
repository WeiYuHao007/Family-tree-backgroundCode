package com.orange.familyTree.exceptions;

import java.sql.SQLException;

public class MySQLException extends SQLException {
    public MySQLException() {}
    public MySQLException(String message) {
        super(message);
        this.message = message;
    }

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
