package Controller.Admin;

import Entity.Request;
import Service.Impl.RequestServiceImpl;
import Service.RequestService;
import Enum.RequestStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/request/reject")
public class RejectRequest extends HttpServlet {
    private final RequestService requestService = new RequestServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        Request request = requestService.getById(id);
        request.setRequestStatus(RequestStatus.DENIED.name());
        requestService.updateStatus(request);
        resp.sendRedirect(req.getContextPath()+ "/admin/request");
    }
}
