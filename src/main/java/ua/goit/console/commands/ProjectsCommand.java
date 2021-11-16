package ua.goit.console.commands;

import ua.goit.console.Command;
import ua.goit.dao.ProjectDao;
import ua.goit.model.Project;

import java.util.List;
import java.util.Optional;

public class ProjectsCommand implements Command {

    private final ProjectDao projectDao = new ProjectDao();

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

    private void update(String params) { //projects update ID NAME COMPANY_ID DURATION CUSTOMER_ID COST
        String[] paramsArray = params.split(" ");
        Optional<Project> optionalProject = projectDao.get(Long.parseLong(paramsArray[0]));
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.setName(paramsArray[1]);
            project.setCompanyId(Long.parseLong(paramsArray[2]));
            project.setDuration(Long.parseLong(paramsArray[3]));
            project.setCustomerId(Long.parseLong(paramsArray[4]));
            project.setCost(Long.parseLong(paramsArray[5]));
            projectDao.update(project);
        } else {
            System.out.println("Project with id " + paramsArray[0] + " not found");
        }
    }

    private void getAll() {
        List<Project> all = projectDao.getAll();
        System.out.println("Returned " + all.size() + " users");
        for (Project project : all) {
            System.out.println(project);
        }

    }

    private void create(String params) { //projects create NAME COMPANY_ID DURATION CUSTOMER_ID COST
        String[] paramsArray = params.split(" ");
        Project project = new Project();
        project.setName(paramsArray[0]);
        project.setCompanyId(Long.parseLong(paramsArray[1]));
        project.setDuration(Long.parseLong(paramsArray[2]));
        project.setCustomerId(Long.parseLong(paramsArray[3]));
        project.setCost(Long.parseLong(paramsArray[4]));
        projectDao.create(project);
    }

    private void get(String params) { // projects get ID
        String[] paramsArray = params.split(" ");
        Optional<Project> project = projectDao.get(Long.parseLong(paramsArray[0]));
        if (project.isPresent()) {
            System.out.println(project.get());
        } else {
            System.out.println("Project with id " + paramsArray[0] + " not found");
        }
    }

    private void delete(String params) { // projects get ID
        String[] paramsArray = params.split(" ");
        Optional<Project> project = projectDao.get(Long.parseLong(paramsArray[0]));
        if (project.isPresent()) {
            projectDao.delete(project.get());
        } else {
            System.out.println("Project with id " + paramsArray[0] + " not found");
        }
    }
}
