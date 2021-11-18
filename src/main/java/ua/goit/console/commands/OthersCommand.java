package ua.goit.console.commands;

import ua.goit.console.Command;
import ua.goit.dao.OthersDao;

import java.sql.SQLException;

public class OthersCommand implements Command {

    private final OthersDao othersDao = new OthersDao();

    @Override
    public void handle(String params) throws SQLException {
        String[] paramsArray = params.split(" ");
        String subParams = String.join(" ", params.replace(paramsArray[0] + " ", ""));
        switch (paramsArray[0]) {
            case("getSalary"): getSalaryOnProject(subParams);break;
            case("getDevs"): getDevelopersOnProject(subParams);break;
            case("getBranch"): getDevsByBranch(subParams);break;
            case("getLevel"): getDevsByLevel(subParams);break;
            case("getProjects"): getProjectList();break;
        }
    }

    public void getSalaryOnProject(String params) throws SQLException { //getSalary ID
        othersDao.getSumSalaryOnProject(params);
    }

    public void getDevelopersOnProject(String projectName) throws SQLException {
        othersDao.getDevelopersOnProject(projectName);
    }

    public void getDevsByBranch(String branch) throws SQLException {
        othersDao.getDevsByBranch(branch);
    }

    public void getDevsByLevel(String level) throws SQLException {
        othersDao.getDevsByLevel(level);
    }

    public void getProjectList() throws SQLException {
        othersDao.getProjectList();
    }
}
