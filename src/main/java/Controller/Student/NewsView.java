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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@WebServlet("/student/news")
public class NewsView extends HttpServlet {
    private final NewsService newsService = new NewsServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final Common common = new Common();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Strings.isNullOrEmpty(req.getParameter("page")) ? 1 : Integer.valueOf(req.getParameter("page"));
        List<NewsDto> newsDtoList = common.getListNewsDto(page);
        long total = newsService.getAll().stream().count();
        req.setAttribute("news", newsDtoList);
        req.setAttribute("page", page - 1);
        req.setAttribute("totalPage", (int) Math.ceil(total / 10));
        req.setAttribute("newsDtoList", newsDtoList);
        common.setTitle(req, "News");
        req.getRequestDispatcher("/views/student/news.jsp").forward(req, resp);
    }
}
