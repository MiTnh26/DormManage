package Controller.Admin;

import Service.Impl.UserServiceImpl;
import Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/user/delete")
public class UserDelete extends HttpServlet {
    private static final String VIEW_PATH = "/views/admin/user.jsp";
    private static final UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("id"));
        userService.deleteById(userId);
    }
}
