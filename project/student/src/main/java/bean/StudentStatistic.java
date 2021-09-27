package bean;

import java.util.List;

public abstract class StudentStatistic implements Statisticable {
    double studentAverage(List<Student> list) {
        return 0;
    }

    int studentMax(List<Student> list) {
        return 0;
    }

    int studentMin(List<Student> list) {
        return 0;
    }
}
