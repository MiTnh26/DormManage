package Controller.Admin;

import Controller.General.Common;
import Dto.DomResidentDto;
import Entity.DomResident;
import Service.DomResidentService;
import Service.Impl.DomResidentServiceImpl;
import com.google.api.client.util.Strings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/dom-resident")
public class DomResidentView extends HttpServlet {
    private final Common common = new Common();
    private final DomResidentService domResidentService = new DomResidentServiceImpl();
    private final int LIMIT = 2;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keySearch = Strings.isNullOrEmpty(req.getParameter("keySearch")) ? "" : req.getParameter("keySearch");
        int page = Strings.isNullOrEmpty(req.getParameter("page")) ? 1 : Integer.valueOf(req.getParameter("page"));
        List<DomResident> domResidentList = domResidentService.getAll();
        List<DomResident> domResidents = domResidentService.getAll(keySearch, page - 1, LIMIT);
        List<DomResidentDto> domResidentDtoList = domResidents.stream().map(x -> {
                    int year = Integer.valueOf(String.valueOf(x.getCheckInDate()).substring(0, 4));
                    return DomResidentDto.builder()
                            .studentId(x.getRollId())
                            .semester(x.getTermId())
                            .checkInDate(String.valueOf(x.getCheckInDate()))
                            .checkOutDate(String.valueOf(x.getCheckOutDate()))
                            .price(x.getBalance())
                            .bedInformation(x.getRoomName() + " - " + x.getBedId())
                            .year(year)
                            .build();
                }
        ).toList();
        req.setAttribute("domResidentList", domResidentDtoList);
        common.setTitle(req, "domResident");
        req.setAttribute("page", page - 1);
        req.setAttribute("keySearch", keySearch);
        req.setAttribute("totalPage", (int) Math.ceil(domResidentList.size() / LIMIT));
        req.getRequestDispatcher("/views/admin/dom-resident.jsp").forward(req, resp);
    }
}
