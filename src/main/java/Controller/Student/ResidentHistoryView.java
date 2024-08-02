package Controller.Student;

import Controller.General.Common;
import Dto.DomResidentDto;
import Entity.DomResident;
import Entity.Student;
import Service.DomResidentService;
import Service.Impl.DomResidentServiceImpl;
import org.checkerframework.checker.units.qual.C;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/student/resident-history")
public class ResidentHistoryView extends HttpServlet {
    private final DomResidentService domResidentService = new DomResidentServiceImpl();
    private final Common common = new Common();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = common.getStudentSession(req, resp);
        List<DomResident> domResident = domResidentService.getByRollId(student.getRollId());

        List<DomResidentDto> dtos = domResident.stream().map(x ->
                DomResidentDto.builder()
                        .studentId(x.getRollId())
                        .checkInDate(common.convertTimeToStringYYYYMMDD(x.getCheckInDate()))
                        .checkOutDate(common.convertTimeToStringYYYYMMDD(x.getCheckOutDate()))
                        .price(x.getBalance())
                        .semester(x.getTermId())
                        .year(x.getCheckInDate().getYear())
                        .bedInformation(x.getRoomName() + "-" + x.getBedId())
                        .build()
        ).toList();
        req.setAttribute("domResident", dtos);
        common.setTitle(req, "resident");
        req.getRequestDispatcher("/views/student/resident-history.jsp").forward(req, resp);
    }
}
