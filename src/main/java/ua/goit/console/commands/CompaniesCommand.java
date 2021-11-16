package ua.goit.console.commands;

import ua.goit.console.Command;
import ua.goit.dao.CompanyDao;
import ua.goit.model.Company;
import ua.goit.model.Developer;

import java.util.List;
import java.util.Optional;

public class CompaniesCommand implements Command {

    private final CompanyDao companyDao = new CompanyDao();

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

    private void update(String params) { //companies update ID, NAME COUNTRY
        String[] paramsArray = params.split(" ");
        Optional<Company> optionalCompany = companyDao.get(Long.parseLong(paramsArray[0]));
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.setName(paramsArray[1]);
            company.setCountry(paramsArray[2]);
            companyDao.update(company);
        } else {
            System.out.println("Company with id " + paramsArray[0] + " not found");
        }
    }

    private void delete(String params) {
        String[] paramsArray = params.split(" ");
        Optional<Company> company = companyDao.get(Long.parseLong(paramsArray[0]));
        if (company.isPresent()) {
            companyDao.delete(company.get());
        } else {
            System.out.println("Company with id " + paramsArray[0] + " not found");
        }
    }

    private void getAll() {
        List<Company> all = companyDao.getAll();
        System.out.println("Returned " + all.size() + " companies");
        for (Company company : all) {
            System.out.println(company);
        }
    }

    private void get(String params) { // companies get ID
        String[] paramsArray = params.split(" ");
        Optional<Company> company = companyDao.get(Long.parseLong(paramsArray[0]));
        if (company.isPresent()) {
            System.out.println(company.get());
        } else {
            System.out.println("Company with id " + paramsArray[0] + " not found");
        }
    }

    private void create(String params) {// companies create NAME COUNTRY
        String[] paramsArray = params.split(" ");
        Company company = new Company();
        company.setName(paramsArray[0]);
        company.setCountry(paramsArray[1]);
        companyDao.create(company);
    }
}
