package ua.goit;


import ua.goit.console.CommandHandler;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException {
        System.out.println("Start application");
        CommandHandler commandHandler = new CommandHandler();
        Scanner scanner = new Scanner(System.in);
        System.out.println("others help - for help");
        while (scanner.hasNext()){
            commandHandler.handleCommand(scanner.nextLine());
        }
        scanner.close();

        System.out.println("END application");

    }

}
