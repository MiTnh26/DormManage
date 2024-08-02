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

@WebServlet("/admin/news/delete")
public class NewsDelete extends HttpServlet {

    private static final String VIEW_PATH =  "/admin/news";
    private static final NewsService newsService = new NewsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        News newsDelete = News.builder().newsId(id).build();
        System.out.println("NEW DLEETE " + newsDelete);
        newsService.delete(newsDelete);
        resp.sendRedirect(req.getContextPath() + VIEW_PATH);
    }
}
