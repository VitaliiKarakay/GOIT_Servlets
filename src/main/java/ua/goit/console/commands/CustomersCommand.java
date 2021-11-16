package ua.goit.console.commands;

import ua.goit.console.Command;
import ua.goit.dao.CustomerDao;
import ua.goit.model.Customer;
import ua.goit.model.Developer;

import java.util.List;
import java.util.Optional;

public class CustomersCommand implements Command {

    private final CustomerDao customerDao = new CustomerDao();

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

    private void update(String params) { //customers update ID NAME TELEPHONE
        String[] paramsArray = params.split(" ");
        Optional<Customer> optionalCustomer = customerDao.get(Long.parseLong(paramsArray[0]));
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setName(paramsArray[1]);
            customer.setTelephone(Long.parseLong(paramsArray[2]));
            customerDao.update(customer);
        } else {
            System.out.println("Customer with id " + paramsArray[0] + " not found");
        }
    }

    private void getAll() {
        List<Customer> all = customerDao.getAll();
        System.out.println("Returned " + all.size() + " users");
        for (Customer customer : all) {
            System.out.println(customer);
        }

    }

    private void create(String params) { //customers create NAME TELEPHONE
        String[] paramsArray = params.split(" ");
        Customer customer = new Customer();
        customer.setName(paramsArray[0]);
        customer.setTelephone(Long.parseLong(paramsArray[1]));
        customerDao.create(customer);
    }

    private void get(String params) { // customers get ID
        String[] paramsArray = params.split(" ");
        Optional<Customer> customer = customerDao.get(Long.parseLong(paramsArray[0]));
        if (customer.isPresent()) {
            System.out.println(customer.get());
        } else {
            System.out.println("Customer with id " + paramsArray[0] + " not found");
        }
    }

    private void delete(String params) { // customers get ID
        String[] paramsArray = params.split(" ");
        Optional<Customer> customer = customerDao.get(Long.parseLong(paramsArray[0]));
        if (customer.isPresent()) {
            customerDao.delete(customer.get());
        } else {
            System.out.println("Customer with id " + paramsArray[0] + " not found");
        }
    }
}
