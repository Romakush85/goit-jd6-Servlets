package ua.goit.jdbc.command;

import java.sql.SQLException;

public interface Command {
    boolean canExecute(String input);

    void execute() throws SQLException;
}
