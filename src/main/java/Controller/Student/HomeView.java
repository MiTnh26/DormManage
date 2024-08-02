package Controller.Student;

import Controller.General.Common;
import Dto.NewsDto;
import Entity.News;
import Entity.Users;
import Service.Impl.NewsServiceImpl;
import Service.Impl.UserServiceImpl;
import Service.NewsService;
import Service.UserService;
import com.google.api.client.util.Strings;
import org.checkerframework.checker.units.qual.C;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@WebServlet("/student/home")
public class HomeView extends HttpServlet {
    private final Common common = new Common();
    private final NewsService newsService = new NewsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Strings.isNullOrEmpty(req.getParameter("page")) ? 1 : Integer.valueOf(req.getParameter("page"));
        List<NewsDto> newsDtoList = common.getListNewsDto(page);
        long total = newsService.getAll().stream().count();
        common.setTitle(req, "Home");
        req.setAttribute("news", newsDtoList);
        req.setAttribute("page", page - 1);
        req.setAttribute("totalPage", (int) Math.ceil(total / 10));
        req.getRequestDispatcher("/views/student/home.jsp").forward(req, resp);
    }
}
