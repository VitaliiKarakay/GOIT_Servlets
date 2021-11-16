package ua.goit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import ua.goit.model.Developer;

public class DeveloperDao extends AbstractDao<Developer>{

    String getTableName() {
        return "developers";
    }

    @Override
    Developer mapToEntity(ResultSet resultSet) throws SQLException {
        Developer developer = new Developer();
        developer.setId(resultSet.getLong("id"));
        developer.setName(resultSet.getString("name"));
        developer.setSex(resultSet.getString("sex"));
        developer.setAge(resultSet.getLong("age"));
        developer.setCompanyId(resultSet.getLong("company_id"));
        developer.setSalary(resultSet.getLong("salary"));
        return developer;
    }

    @Override
    public Optional<Developer> create(Developer developer) {
        String sql = "insert into developers(name, sex, age, company_id, salary) values (?, ?, ?, ?, ?)";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setString(1, developer.getName());
            ps.setString(2, developer.getSex());
            ps.setLong(3, developer.getAge());
            ps.setLong(4, developer.getCompanyId());
            ps.setLong(5, developer.getSalary());
        });
        System.out.println("Record was created");
        return Optional.empty();
    }

    @Override
    public void update(Developer developer) {
        String sql = "update developers set salary = ? where id = ?";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setLong(1, developer.getSalary());
            ps.setLong(2, developer.getId());
        });
        System.out.println("Record with id " + developer.getId() + " was updated");
    }
}
