package Controller.Admin;

import Controller.General.Common;
import Entity.Users;
import Service.Impl.UserServiceImpl;
import Service.UserService;
import com.google.api.client.util.Strings;
import com.google.api.services.drive.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/user/new")
public class AdminCreateNewUser extends HttpServlet {
    private static final String VIEW_PATH = "/views/admin/create-user.jsp";
    private static final String HOME_PATH = "/admin/user";

    private static final String END_POINT = "@fpt.edu.vn";
    private final Common common = new Common();

    private static UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String popup = Strings.isNullOrEmpty(req.getParameter("popup")) ? null : req.getParameter("popup");
        req.setAttribute("popup", popup);

        req.getRequestDispatcher(VIEW_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String gmail = req.getParameter("gmail");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");


        if(!gmail.endsWith(END_POINT)){
            //check o fontend nwa ne
            req.setAttribute("message", "Gmail k hop le");
            req.getRequestDispatcher(VIEW_PATH).forward(req, resp);
            return;
        }

        Users newUser = Users.builder()
                .fullName(fullName)
                .roleId(1)
                .gender(gender)
                .password(password)
                .gmail(gmail).build();

        userService.addUser(newUser);
        common.setTitle(req, "user");
        resp.sendRedirect(req.getContextPath() + "/admin/user/new" + "?popup=Add user success!");
    }
}
