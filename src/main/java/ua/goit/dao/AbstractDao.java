package ua.goit.dao;

import ua.goit.model.Developer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class AbstractDao<T extends Identity> implements Dao<T> {

    abstract String getTableName();
    abstract T mapToEntity(ResultSet resultSet) throws SQLException;

    @Override
    public void delete(T entity) {
        String sql = String.format("delete from %s where id = ?", getTableName());
        DbHelper.executeWithPreparedStatement(sql, ps -> {
            ps.setLong(1, entity.getId());
        });
        System.out.printf("Record %s was deleted from %s \n", entity.getId(), getTableName());
    }

    @Override
    public Optional<T> get(Long id) {
        String query = String.format("select * from %s where id = ?", getTableName());
        Optional<T> empty = Optional.empty();
        try {
            ResultSet resultSet = DbHelper.getWithPreparedStatement(
                    query, ps -> {
                        ps.setLong(1, id);
                    });
            if (resultSet.next()) {
                return Optional.of(mapToEntity(resultSet));
            }
            else return empty;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empty;

    }

    @Override
    public List<T> getAll() {
        List<T> resultList = new ArrayList<>();
        String query = String.format("select * from %s", getTableName());
        try {
            ResultSet resultSet = DbHelper.getWithPreparedStatement(
                    query, ps -> {
                    });
            while (resultSet.next()) {
                resultList.add(mapToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }


}
