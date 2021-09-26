package service;

import bean.Student;
import bean.StudentStatistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StudentService extends StudentStatistic {

        private Logger log = LoggerFactory.getLogger("Student Service");

    public double studentAverage(List<Student> list) {
        double result = list.stream().mapToInt(Student::getGrade).summaryStatistics().getAverage();
        log.info("student average grade {}", result);
        return result;
    }

    public int studentMax(List<Student> list) {
        int result = list.stream().mapToInt(Student::getGrade).summaryStatistics().getMax();
        log.info("student max grade {}", result);
        return result;
    }

    public int studentMin(List<Student> list) {
        int result = list.stream().mapToInt(Student::getGrade).summaryStatistics().getMin();
        log.info("student min grade {}", result);
        return result;
    }
}
