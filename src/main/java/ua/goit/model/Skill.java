package ua.goit.model;

import ua.goit.dao.Identity;

public class Skill implements Identity {

    private Long id;
    private String branch;
    private String level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", branch='" + branch + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
