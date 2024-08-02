package Controller.Admin;

import Controller.General.Common;
import Dto.DomTotalDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/admin/dorm")
public class DomView extends HttpServlet {
    private static final String VIEW_PATH = "/views/admin/dom.jsp";
    private static final String HOME_PATH = "views/admin/home";
    private final Common common = new Common();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DomTotalDto> domTotalDtoList = common.getListDomDto();
        req.setAttribute("domTotalDto", domTotalDtoList);
        req.setAttribute("semester", common.getSemester() + " - " + LocalDate.now().getYear());
        common.setTitle(req, "bed");
        req.getRequestDispatcher("/views/admin/dom.jsp").forward(req,resp);
    }
}
