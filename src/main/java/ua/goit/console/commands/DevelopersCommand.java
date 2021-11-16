package ua.goit.console.commands;

import ua.goit.console.Command;
import ua.goit.dao.DeveloperDao;
import ua.goit.model.Developer;

import java.util.List;
import java.util.Optional;

public class DevelopersCommand implements Command {

    private final DeveloperDao developerDao = new DeveloperDao();

    @Override
    public void handle(String params) {
        String[] paramsArray = params.split(" ");
        String subParams = String.join(" ", params.replace(paramsArray[0] + " ", ""));
        switch (paramsArray[0]) {
            case "create": create(subParams);break;
            case "get": get(subParams);break;
            case "getAll": getAll();break;
            case "delete": delete(subParams);break;
            case "update": update(subParams);break;
            default:
                System.out.println("Unknown command");
            }
       }

    private void update(String params) { //developers update ID NAME SALARY AGE COMPANY_ID SEX
        String[] paramsArray = params.split(" ");
        Optional<Developer> optionalDeveloper = developerDao.get(Long.parseLong(paramsArray[0]));
        if (optionalDeveloper.isPresent()) {
            Developer developer = optionalDeveloper.get();
            developer.setName(paramsArray[1]);
            developer.setSalary(Long.parseLong(paramsArray[2]));
            developer.setAge(Long.parseLong(paramsArray[3]));
            developer.setCompanyId(Long.parseLong(paramsArray[4]));
            developer.setSex(paramsArray[5]);
            developerDao.update(developer);
        } else {
            System.out.println("Developer with id " + paramsArray[0] + " not found");
        }
    }

    private void getAll() {
        List<Developer> all = developerDao.getAll();
        System.out.println("Returned " + all.size() + " users");
        for (Developer developer : all) {
            System.out.println(developer);
        }
    }

    private void create(String params) { //developers create NAME SALARY AGE COMPANY_ID SEX
        String[] paramsArray = params.split(" ");
        Developer developer = new Developer();
        developer.setName(paramsArray[0]);
        developer.setSalary(Long.parseLong(paramsArray[1]));
        developer.setAge(Long.parseLong(paramsArray[2]));
        developer.setCompanyId(Long.parseLong(paramsArray[3]));
        developer.setSex(paramsArray[4]);
        developerDao.create(developer);
    }

    private void get(String params) { // developers get ID
        String[] paramsArray = params.split(" ");
        Optional<Developer> developer = developerDao.get(Long.parseLong(paramsArray[0]));
        if (developer.isPresent()) {
            System.out.println(developer.get());
        } else {
            System.out.println("Developer with id " + paramsArray[0] + " not found");
        }
    }

    private void delete(String params) { // developers get ID
        String[] paramsArray = params.split(" ");
        Optional<Developer> developer = developerDao.get(Long.parseLong(paramsArray[0]));
        if (developer.isPresent()) {
            developerDao.delete(developer.get());
        } else {
            System.out.println("Developer with id " + paramsArray[0] + " not found");
        }
    }
}
