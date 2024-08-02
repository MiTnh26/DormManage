package Controller.Student;

import Controller.General.Common;
import Entity.Student;
import Service.Impl.StudentServiceImpl;
import Service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/student/add-balance")
public class AddBalance extends HttpServlet {

    private static  final StudentService studentService = new StudentServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Common common = new Common();
        Student student = common.getStudentSession(req, resp);
        String rollId = student.getRollId();
        Long balance = Long.valueOf(req.getParameter("balance")) + student.getBalance();

        if(balance == null){
            //check o fontend nwa ne
            req.setAttribute("message", "tien k hop le");
            req.getRequestDispatcher(req.getContextPath() + "/student/add-balance").forward(req, resp);
            return;
        }

        studentService.updateBalance(rollId, balance);
        student.setBalance(balance);
        req.getSession().setAttribute("student", student);
        resp.sendRedirect(req.getContextPath() + "/student/home" + "?popup=Add balance success!");
    }
}
