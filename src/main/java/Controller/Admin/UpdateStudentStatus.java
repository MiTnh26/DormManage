package Controller.Admin;

import Entity.Student;
import Entity.Users;
import Service.Impl.StudentServiceImpl;
import Service.Impl.UserServiceImpl;
import Service.StudentService;
import Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/student/status")
public class UpdateStudentStatus extends HttpServlet {
    private static final StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rollId = req.getParameter("rollId");
        System.out.println("RollId  + " + rollId);
        Student student = studentService.getByRollId(rollId);
        if(student.getStudentStatus().toString().equals("ACTIVE")){
            studentService.updateStatus(rollId, "BANNED");
        }
        if(student.getStudentStatus().toString().equals("BANNED")){
            studentService.updateStatus(rollId, "ACTIVE");
        }
        resp.sendRedirect(req.getContextPath() + "/admin/student");
    }
}
