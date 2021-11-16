package ua.goit.dao;

import ua.goit.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerDao extends AbstractDao<Customer>{
    @Override
    String getTableName() {
        return "customers";
    }

    @Override
    Customer mapToEntity(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getLong("id"));
        customer.setName(resultSet.getString("name"));
        customer.setTelephone(resultSet.getLong("telephone"));
        return customer;
    }

    @Override
    public Optional<Customer> create(Customer customer) {
        String sql = "insert into customers(name, telephone) values (?, ?)";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setString(1, customer.getName());
            ps.setLong(2, customer.getTelephone());
        });
        System.out.println("Record was created");
        return Optional.empty();
    }

    @Override
    public void update(Customer customer) {
        String sql = "update customers set telephone = ? where id = ?";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setLong(1, customer.getTelephone());
            ps.setLong(2, customer.getId());
        });
        System.out.println("Record with id " + customer.getId() + " was updated");
    }
}
