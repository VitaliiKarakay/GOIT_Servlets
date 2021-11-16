package ua.goit.dao;

import ua.goit.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ProjectDao extends AbstractDao<Project>{
    @Override
    String getTableName() {
        return "projects";
    }

    @Override
    Project mapToEntity(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        project.setId(resultSet.getLong("id"));
        project.setName(resultSet.getString("name"));
        project.setCompanyId(resultSet.getLong("company_id"));
        project.setDuration(resultSet.getLong("duration"));
        project.setCustomerId(resultSet.getLong("customer_id"));
        project.setCost(resultSet.getLong("cost"));
        return project;
    }

    @Override
    public Optional<Project> create(Project project) {
        String sql = "insert into projects(name, company_id, duration, customer_id, cost) values (?, ?, ?, ?, ?)";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setString(1, project.getName());
            ps.setLong(2, project.getCompanyId());
            ps.setLong(3, project.getDuration());
            ps.setLong(4, project.getCustomerId());
            ps.setLong(5, project.getCost());
        });
        System.out.println("Record was created");
        return Optional.empty();
    }

    @Override
    public void update(Project project) {
        String sql = "update projects set cost = ? where id = ?";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setLong(1, project.getCost());
            ps.setLong(2, project.getId());
        });
        System.out.println("Record with id " + project.getId() + " was updated");
    }
}
