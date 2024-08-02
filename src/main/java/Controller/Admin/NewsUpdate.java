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

@WebServlet("/admin/news/update")
public class NewsUpdate extends HttpServlet {

    private static final String VIEW_PATH =  "/admin/news";
    private static final NewsService newsService = new NewsServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("newsTitle");
        String detail = req.getParameter("newsDetail");
        News newsUpdate = News.builder().newsTitle(title).newsDetail(detail).newsId(id).build();
        System.out.printf("NEWS UPDATE " + newsUpdate);
        newsService.update(newsUpdate);
        resp.sendRedirect(req.getContextPath() +  VIEW_PATH);
    }
}
