package Controller.Student;

import Controller.General.Common;
import Entity.DomResident;
import Entity.Request;
import Entity.Room;
import Entity.Student;
import Enum.RequestStatus;
import Enum.StudentStatus;
import Service.DomResidentService;
import Service.Impl.DomResidentServiceImpl;
import Service.Impl.ResidentRequestServiceImpl;
import Service.Impl.RoomServiceImpl;
import Service.ResidentRequestService;
import Service.RoomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;

@WebServlet("/student/create-request")
public class RequestView extends HttpServlet {
    private final Common common = new Common();
    private final DomResidentService domResidentService = new DomResidentServiceImpl();
    private final RoomService roomService = new RoomServiceImpl();
    private final ResidentRequestService residentRequestService = new ResidentRequestServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = common.getStudentSession(req, resp);

        if (!StudentStatus.RESIDENT.equals(student.getStudentStatus())) {
            resp.sendRedirect(req.getContextPath() + "/student/request?message=Can't create request");
            return;
        }
        req.getRequestDispatcher("/views/student/create-request.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestType = req.getParameter("requestType");
        String checkOutDate = req.getParameter("checkOutDate");
        String requestDetail = req.getParameter("requestDetail");

        Student student = common.getStudentSession(req, resp);

        DomResident domResident = domResidentService.getByRollIdAndSemesterAndYear(student.getRollId(), common.getSemester(), LocalDate.now().getYear());
        Room room = roomService.getByRoomName(domResident.getRoomName());
        System.out.println(room.getRoomType());
        Request.RequestBuilder request = Request.builder();
        request.requestDetail(requestDetail)
                .requestType(requestType)
                .createDate(new Timestamp(System.currentTimeMillis()))
                .bed(domResident.getBedId())
                .term(String.valueOf(domResident.getTermId()))
                .floor(domResident.getFloor())
                .rollId(domResident.getRollId())
                .roomName(domResident.getRoomName())
                .requestStatus(RequestStatus.WAITING.name())
                .domName("DOM " + domResident.getRoomName().substring(0, 1))
                .roomType(room.getRoomType());
        if ("CHECKOUT".equals(requestType)) {
            LocalDate localDate = LocalDate.parse(checkOutDate);
            long timestamp = localDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
            request.checkOutDate(new Timestamp(timestamp));
        }
        residentRequestService.createRequestOther(request.build());
        req.setAttribute("message", "Create request success!");
        common.setTitle(req, "request");
        req.getRequestDispatcher("/views/student/create-request.jsp").forward(req, resp);
    }
}
