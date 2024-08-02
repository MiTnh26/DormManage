package Controller.Admin;

import Controller.General.Common;
import Entity.Student;
import Service.Impl.StudentServiceImpl;
import Service.StudentService;
import com.google.api.client.util.Strings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/student")
public class StudentView extends HttpServlet {
    private static final String VIEW_PATH = "/views/admin/student.jsp";
    private static StudentService studentService = new StudentServiceImpl();
    private final Common common = new Common();
    private final int LIMIT = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keySearch = Strings.isNullOrEmpty(req.getParameter("keySearch")) ? "" : req.getParameter("keySearch");
        int page = Strings.isNullOrEmpty(req.getParameter("page")) ? 1 : Integer.valueOf(req.getParameter("page"));
        List<Student> studentList = studentService.getAll(keySearch, page - 1, LIMIT);
        List<Student> students = studentService.getAll();
        req.setAttribute("studentList", studentList);
        req.setAttribute("page", page - 1);
        req.setAttribute("keySearch", keySearch);
        req.setAttribute("totalPage", (int) Math.ceil(students.size() / LIMIT));
        common.setTitle(req, "student");
        req.getRequestDispatcher(VIEW_PATH).forward(req, resp);
    }
}
