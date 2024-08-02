package Controller.Admin;

import Entity.News;
import Service.Impl.NewsServiceImpl;
import Service.NewsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/news/detail")
public class NewsDetail extends HttpServlet {
    private static final String VIEW_PATH =  "/views/admin/news-detail.jsp";

    private static final NewsService newsService = new NewsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        News news = newsService.getById(id);

        System.out.println("NEWs: " + news);

        req.setAttribute("news", news);
        req.getRequestDispatcher(VIEW_PATH).forward(req, resp);
    }
}
