package ua.goit.dao;

import ua.goit.model.Skill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SkillsDao extends AbstractDao<Skill>{
    @Override
    String getTableName() {
        return "skills";
    }

    @Override
    Skill mapToEntity(ResultSet resultSet) throws SQLException {
        Skill skill = new Skill();
        skill.setId(resultSet.getLong("id"));
        skill.setBranch(resultSet.getString("branch"));
        skill.setLevel(resultSet.getString("level"));
        return skill;
    }

    @Override
    public Optional<Skill> create(Skill skill) {
        String sql = "insert into skills (branch, level) values (?, ?)";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setString(1, skill.getBranch());
            ps.setString(2, skill.getLevel());
        });
        System.out.println("Record was created");
        return Optional.empty();
    }

    @Override
    public void update(Skill skill) {
        String sql = "update skills set level = ? where id = ?";
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setString(1, skill.getLevel());
            ps.setLong(2, skill.getId());
        });
        System.out.println("Record with id" + skill.getId() + " was created");
    }
}
