package Controller.Admin;

import Controller.General.Common;
import Dto.UsersDto;
import Service.Impl.UserServiceImpl;
import Service.UserService;
import com.google.api.client.util.Strings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/admin/user")
public class UserView extends HttpServlet {
    private static final String VIEW_PATH =  "/views/admin/user.jsp";
    private static final String HOME_PATH = "/views/admin/home.jsp";

    private static UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Common common = new Common();
        List<UsersDto> usersDtoList = common.getListUsersDto();
        common.setTitle(req, "user");
        req.setAttribute("usersDtoList", usersDtoList);
        req.getRequestDispatcher(VIEW_PATH).forward(req, resp);
    }
}
