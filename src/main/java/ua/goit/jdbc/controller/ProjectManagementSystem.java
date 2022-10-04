package ua.goit.jdbc.controller;

import ua.goit.jdbc.command.Command;
import ua.goit.jdbc.exceptions.ExitException;
import ua.goit.jdbc.view.View;

import java.sql.SQLException;
import java.util.List;

public class ProjectManagementSystem {
    private final View view;
    private final List<Command> commands;

    public ProjectManagementSystem(View view, List<Command> commands) {
        this.view = view;
        this.commands = commands;
    }

    public void run(){
        view.write("Hello, pls enter help to see all commands");
        try{
            execute();
        } catch(ExitException e) {} catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void execute() throws SQLException {
        while(true){
            String input = view.read();
            boolean isInputCorrect = false;
            for(Command command : commands) {
                if(command.canExecute(input)) {
                    command.execute();
                    isInputCorrect = true;
                }
            }
            if(!isInputCorrect){
                view.write("Command not found, pls enter help to see all commands");
            }
        }
    }
}
