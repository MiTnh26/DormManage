package Controller.Student;

import Controller.General.Common;
import Entity.Request;
import Entity.Student;
import Enum.RequestType;
import Enum.RequestStatus;
import Service.Impl.ResidentRequestServiceImpl;
import Service.ResidentRequestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;

@WebServlet("/student/book-room-request")
public class BookRoom extends HttpServlet {

    private final Common common = new Common();
    private final ResidentRequestService requestService = new ResidentRequestServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String note = req.getParameter("Note");
        String floor = req.getParameter("floor");
        String dom = req.getParameter("domId");
        String roomType = req.getParameter("roomType");

        Student student = common.getStudentSession(req, resp);

        boolean isCheck = requestService.checkRequestByRollNameAndTermAndYearAndType(student.getRollId(), common.getSemester(), LocalDate.now().getYear(), RequestType.CHECKIN.name());

        if(isCheck){
            resp.sendRedirect(req.getContextPath() + "/student/choose-room?message=Request existed already!");
            return;
        }

        Request request = Request.builder()
                .domName("Dom " + dom)
                .requestType(RequestType.CHECKIN.name())
                .floor(Integer.valueOf(floor))
                .createDate(new Timestamp(System.currentTimeMillis()))
                .rollId(student.getRollId())
                .requestStatus(RequestStatus.WAITING.name())
                .roomType(roomType)
                .term(common.getSemester())
                .requestDetail(note)
                .build();

        requestService.createRequestBookRoom(request);
        resp.sendRedirect(req.getContextPath() + "/student/choose-room?message=Book bed success!");
    }
}
