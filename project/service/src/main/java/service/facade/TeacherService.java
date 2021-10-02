package service.facade;

import static repository.TeacherRepository.teacherList;
import static service.facade.Service.log;

public class TeacherService {
    public TeacherService() {
    }

       public  void getTeacherSalary(String name) {
        teacherList.stream().filter(teacher -> teacher.getName().equals(name))
                .forEach(teacher -> log.info("Teacher {}, salary {} ", name, teacher.getSalary()));
    }

    public  void getTeacherInfo(String name) {
        teacherList.stream().filter(teacher -> teacher.getName().equals(name))
                .forEach(teacher -> log.info("{} ", teacher.getSalary()));
    }

    public  void setTeacherSalary(String name, long salary) {
        teacherList.stream().filter(teacher -> teacher.getName().equals(name))
                .forEach(teacher -> teacher.setSalary(salary));
    }
}
