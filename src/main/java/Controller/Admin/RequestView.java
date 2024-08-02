package Controller.Admin;

import Controller.General.Common;
import Entity.Request;
import Service.Impl.RequestServiceImpl;
import Service.RequestService;
import com.google.api.client.util.Strings;
import org.checkerframework.checker.units.qual.C;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/request")
public class RequestView extends HttpServlet {

    private static final String VIEW_PATH = "/views/admin/request.jsp";
    private static RequestService requestService = new RequestServiceImpl();
    private final Common common = new Common();

    private final int LIMIT = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String keySearch = Strings.isNullOrEmpty(req.getParameter("keySearch")) ? "" : req.getParameter("keySearch");
        int page = Strings.isNullOrEmpty(req.getParameter("page")) ? 1 : Integer.valueOf(req.getParameter("page"));
        List<Request> requestList = requestService.getAll();
        List<Request> requests = requestService.getAll(keySearch, page - 1, LIMIT);
        req.setAttribute("requestList", requestList);
        req.setAttribute("page", page - 1);
        req.setAttribute("keySearch", keySearch);
        req.setAttribute("totalPage", (int) Math.ceil(requestList.size() / LIMIT));
        common.setTitle(req, "request");
        req.getRequestDispatcher(VIEW_PATH).forward(req, resp);
    }
}
