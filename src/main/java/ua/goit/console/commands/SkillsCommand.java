package ua.goit.console.commands;

import ua.goit.console.Command;
import ua.goit.dao.SkillsDao;
import ua.goit.model.Skill;

import java.util.List;
import java.util.Optional;

public class SkillsCommand implements Command {

    private final SkillsDao skillsDao = new SkillsDao();

    @Override
    public void handle(String params) {
        String[] paramsArray = params.split(" ");
        String subParams = String.join(" ",params.replace(paramsArray[0] + " ", ""));
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

    private void delete(String params) {
        String[] paramsArray = params.split(" ");
        Optional<Skill> skill = skillsDao.get(Long.parseLong(paramsArray[0]));
        if (skill.isPresent()) {
            skillsDao.delete(skill.get());
        } else {
            System.out.println("Skill with id " + paramsArray[0] + " not found");
        }
    }

    private void create(String params) { // skills create BRANCH, LEVEL
        String[] paramsArray = params.split(" ");
        Skill skill = new Skill();
        skill.setBranch(paramsArray[0]);
        skill.setLevel(paramsArray[1]);
        skillsDao.create(skill);
    }

    private void get(String params) { //skills get ID
        String[] paramsArray = params.split(" ");
        Optional<Skill> skill = skillsDao.get(Long.parseLong(paramsArray[0]));
        if (skill.isPresent()) {
            System.out.println(skill.get());
        } else {
            System.out.println("Developer with id " + paramsArray[0] + " not found");
        }
    }

    private void getAll() { // skills getAll
        List<Skill> all = skillsDao.getAll();
        System.out.println("Returned " + all.size() + " users");
        for (Skill skill : all) {
            System.out.println(skill);
        }
    }

    private void update(String params) { //skills update ID, BRANCH, LEVEL
        String[] paramsArray = params.split(" ");
        Optional<Skill> optionalSkill = skillsDao.get(Long.parseLong(paramsArray[0]));
        if (optionalSkill.isPresent()) {
            Skill skill = optionalSkill.get();
            skill.setBranch(paramsArray[1]);
            skill.setLevel(paramsArray[2]);
            skillsDao.update(skill);
        } else {
            System.out.println("Skill with id " + paramsArray[0] + " not found");
        }
    }
}
