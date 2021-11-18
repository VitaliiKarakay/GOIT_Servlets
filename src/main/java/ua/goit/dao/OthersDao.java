package ua.goit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class OthersDao {

    public void getSumSalaryOnProject(String params) throws SQLException {
        String sql = String.format("select sum(salary) from developers d " +
                "join developer_project dp on dp.developer_id = d.id " +
                "join projects p on p.id = dp.project_id where p.id = " +
                "%s group by p.name order by sum(salary)", params);
        ResultSet resultSet = DbHelper.getWithPreparedStatement(
                sql, ps -> {
                });
        if (resultSet.next()) {
            System.out.println("Sum of salary is " + resultSet.getString("sum"));
        }
    }

    public void getDevelopersOnProject(String params) throws SQLException {
        List<String> devList = new ArrayList<>();
        String sql = String.format("select d.name from developers d " +
                "join developer_project dp on dp.developer_id = d.id " +
                "join projects p on dp.project_id = p.id " +
                "where p.id = '%s'", params);
        ResultSet resultSet = DbHelper.getWithPreparedStatement(sql, ps -> {});
        while (resultSet.next()) {
            devList.add(resultSet.getString("name"));
        }
        resultSet.close();
        for (String s : devList) {
            System.out.println(s);
        }
    }

    public void getDevsByBranch(String params) throws SQLException {
        List<String> devList = new ArrayList<>();
        String sql = String.format("select d.name from developers d " +
                "join developer_skill ds on ds.developer_id =  d.id " +
                "join skills s on s.id = ds.skill_id where s.branch = '%s'", params);
        ResultSet resultSet = DbHelper.getWithPreparedStatement(sql, ps -> {});
        while (resultSet.next()) {
            devList.add(resultSet.getString("name"));
        }
        for (String s : devList) {
            System.out.println(s);
        }
    }

    public void getDevsByLevel(String params) throws SQLException {
        List<String> devList = new ArrayList<>();
        String sql = String.format(" select d.name from developers d " +
                "join developer_skill ds on ds.developer_id =  d.id " +
                "join skills s on s.id = ds.skill_id where level = '%s'", params);
        ResultSet resultSet = DbHelper.getWithPreparedStatement(sql, ps -> {});
        while (resultSet.next()) {
            devList.add(resultSet.getString("name"));
        }
        for (String s : devList) {
            System.out.println(s);
        }
    }

    public void getProjectList() throws SQLException {
        List<Map<String, Map<Long, Long>>> results = new ArrayList<>();
        String sql = "select p.duration, p.name, count(*) " +
                "from developers d " +
                "join developer_project dp on dp.developer_id = d.id " +
                "join projects p on p.id = dp.project_id " +
                "group by p.id order by count(*) desc";
        ResultSet resultSet = DbHelper.getWithPreparedStatement(sql, ps -> {});
        while (resultSet.next()) {
            Map<Long, Long> res1 = new HashMap<>();
            res1.put(resultSet.getLong("duration"), resultSet.getLong("count"));
            Map<String, Map<Long, Long>> res2 = new HashMap<>();
            res2.put(resultSet.getString("name"), res1);
            results.add(res2);
        }
        System.out.println("DURATION=DEVELOPERS PROJECT_NAME");
        for (Map<String, Map<Long, Long>> result : results) {
            System.out.println(result.values() + " " + result.keySet());
        }
    }
}
