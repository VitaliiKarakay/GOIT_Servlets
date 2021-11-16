package ua.goit.console;

import ua.goit.console.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    Map<String, Command> commandMap = new HashMap<>();

    public CommandHandler() {
        commandMap.put("developers", new DevelopersCommand());
        commandMap.put("companies", new CompaniesCommand());
        commandMap.put("projects", new ProjectsCommand());
        commandMap.put("customers", new CustomersCommand());
        commandMap.put("skills", new SkillsCommand());
    }

    public void handleCommand(String params) {
        int firstSpace = params.indexOf(" ");
        if (firstSpace > -1) {
            Command command = commandMap
                    .get(params.substring(0, firstSpace));
            if (command != null) {
                command.handle(params.substring(firstSpace + 1));
            } else {
                System.out.println("Unknown command");
            }
        } else {
            System.out.println("Unknown command");
        }
    }
}
