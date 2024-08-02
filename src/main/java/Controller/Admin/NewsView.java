package Controller.Admin;

import Controller.General.Common;
import Dto.NewsDto;
import Dto.UsersDto;
import Service.Impl.NewsServiceImpl;
import Service.NewsService;
import com.google.api.client.util.Strings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


//thieu dau gach ơ đây này
@WebServlet("/admin/news")
public class NewsView extends HttpServlet {
    private static final String VIEW_PATH = "/views/admin/news.jsp";
    private static NewsService newsService = new NewsServiceImpl();

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
        common.setTitle(req, "new");
        req.getRequestDispatcher(VIEW_PATH).forward(req, resp);
    }
}
