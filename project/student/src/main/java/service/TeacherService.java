package service;

import bean.Statisticable;
import bean.Teacher;
import bean.TeacherStatistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TeacherService implements Statisticable {

    private Logger log = LoggerFactory.getLogger("Teacher Service");

     public double teacherAverage(List<Teacher> list) {
        double result = list.stream().mapToInt(Teacher::getSalary).summaryStatistics().getAverage();
        log.info("teacher average grade {}", result);
        return result;
    }

        public int teacherMax(List<Teacher> list) {
        int result = list.stream().mapToInt(Teacher::getSalary).summaryStatistics().getMax();
        log.info("teacher average grade {}", result);
        return result;
    }

      public int teacherMin(List<Teacher> list) {
        int result = list.stream().mapToInt(Teacher::getSalary).summaryStatistics().getMin();
        log.info("teacher average grade {}", result);
        return result;
    }
}
