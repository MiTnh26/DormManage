package Controller.Admin;

import Controller.General.Common;
import Entity.News;
import Entity.Users;
import Service.Impl.NewsServiceImpl;
import Service.NewsService;
import com.google.api.client.util.DateTime;
import com.google.api.services.drive.model.User;
import org.checkerframework.checker.units.qual.C;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@WebServlet("/admin/news/create")
public class NewsCreate extends HttpServlet {
    private static final String VIEW_PATH = "/views/admin/create-news.jsp";
    private static final NewsService newsService = new NewsServiceImpl();
    private final Common common = new Common();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        common.setTitle(req, "new");
        req.getRequestDispatcher(VIEW_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Timestamp dateTime = Timestamp.valueOf(LocalDateTime.now());
        Users author = (Users) session.getAttribute("admin");
        String newsTitle = req.getParameter("newsTitle");
        String newsDetail = req.getParameter("newsDetail");

        if(newsTitle == null){
            //check o fontend nwa ne
            req.setAttribute("message", "Title không được để trống!");
            req.getRequestDispatcher(VIEW_PATH).forward(req, resp);
            return;
        }
        if(newsDetail == null){
            //check o fontend nwa ne
            req.setAttribute("message", "Detail không được để trống!");
            req.getRequestDispatcher(VIEW_PATH).forward(req, resp);
            return;
        }
        News  news = News.builder()
                .createdTime(dateTime)
                .newsTitle(newsTitle)
                .newsDetail(newsDetail)
                .author(author.getUserId()).build();

        newsService.insert(news);
        resp.sendRedirect(req.getContextPath() + "/admin/news/create" + "?popup=Add news success!");
    }
}
