package bean;

import java.util.List;

public abstract class TeacherStatistic implements Statisticable {
    double teacherAverage(List<Teacher> list) {
        return 0;
    }

    int teacherMax(List<Teacher> list) {
        return 0;
    }

    int teacherMin(List<Teacher> list) {
        return 0;
    }
}
