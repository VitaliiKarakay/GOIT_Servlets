package ua.goit.console;

import java.sql.SQLException;

public interface Command {

    void handle(String params) throws SQLException;

}
