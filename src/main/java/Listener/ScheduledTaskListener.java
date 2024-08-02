package Listener;

import Entity.Bed;
import Entity.Student;
import Enum.BedStatus;
import Service.BedService;
import Service.Impl.BedServiceImpl;
import Service.Impl.StudentServiceImpl;
import Service.StudentService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@WebListener
public class ScheduledTaskListener implements ServletContextListener {
    private final StudentService studentService = new StudentServiceImpl();
    private final BedService bedService = new BedServiceImpl();

    private Timer timer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        timer = new Timer(true);

        TimerTask dailyTask = new TimerTask() {
            @Override
            public void run() {
                List<Student> students = studentService.getByDate();
                students.forEach(x -> {
                    studentService.updateStatus(x.getRollId(), "ACTIVE");
                    Bed bed = bedService.getByStudent(x.getRollId());
                    bed.setBedStatus(BedStatus.NOTAVAILABLE);
                    bedService.updateStatus(bed);
                });
            }
        };
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 23);
        now.set(Calendar.MINUTE, 57);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);

        long initialDelay = now.getTimeInMillis() - System.currentTimeMillis();
        if (initialDelay < 0) {
            initialDelay += 24 * 60 * 60 * 1000;
        }

        timer.scheduleAtFixedRate(dailyTask, initialDelay, 24 * 60 * 60 * 1000);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Hủy bỏ Timer khi ứng dụng dừng
        if (timer != null) {
            timer.cancel();
        }
    }
}
