package Controller.Student;

import Controller.General.Common;
import Entity.News;
import Service.Impl.NewsServiceImpl;
import Service.NewsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/news-detail")
public class NewsDetail extends HttpServlet {
    private final NewsService newsService = new NewsServiceImpl();
    private final Common common = new Common();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        News news = newsService.getById(id);
        req.setAttribute("news", news);
        common.setTitle(req, "News");
        req.getRequestDispatcher("/views/student/news-detail.jsp").forward(req, resp);
    }
}
