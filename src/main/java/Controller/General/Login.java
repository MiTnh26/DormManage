package Controller.General;

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
import java.util.Objects;

@WebServlet("/account-login")
public class Login extends HttpServlet {
    private final StudentService studentService = new StudentServiceImpl();

    private final UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String gmail = req.getParameter("UserName");
        String password = req.getParameter("Password");
        Users users = userService.getByGmail(gmail);

        if(Objects.isNull(users) || !users.getPassword().equals(password)){
            req.setAttribute("message", "Invalid credentials");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        req.getSession().setAttribute("admin", users);
        req.getSession().setAttribute("title", "Home");
        resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
    }

}
