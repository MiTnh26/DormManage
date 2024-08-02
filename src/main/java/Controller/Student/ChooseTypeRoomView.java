package Controller.Student;

import Controller.General.Common;
import Dto.RoomTypeDto;
import Entity.Money;
import Entity.Student;
import Enum.StudentStatus;
import Service.DomResidentService;
import Service.Impl.DomResidentServiceImpl;
import Service.Impl.MoneyServiceImpl;
import Service.MoneyService;
import com.google.api.client.util.Strings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/student/choose-room")
public class ChooseTypeRoomView extends HttpServlet {
    private final DomResidentService domResidentService = new DomResidentServiceImpl();
    private final MoneyService moneyService = new MoneyServiceImpl();
    private final Common common = new Common();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Student student = common.getStudentSession(req, resp);

        if (StudentStatus.RESIDENT.equals(student.getStudentStatus())) {
            req.setAttribute("roomTypes", null);
            req.getRequestDispatcher("/views/student/choose-type-room.jsp").forward(req, resp);
            return;
        }

        List<Money> monies = moneyService.getByMoneyType("ROOM");

        List<RoomTypeDto> roomTypeDtoList = monies.stream().map(x -> {
                    String amount = new Common().convertAmount(x.getAmount());
                    return RoomTypeDto.builder()
                            .bed(x.getBedTotal())
                            .key(x.getRoomType())
                            .amount(amount)
                            .build();
                }
        ).toList();

        String message = Strings.isNullOrEmpty(req.getParameter("message")) ? null : req.getParameter("message");
        req.setAttribute("roomTypes", roomTypeDtoList);
        req.setAttribute("message", message);
        common.setTitle(req, "BookRoom");
        req.getRequestDispatcher("/views/student/choose-type-room.jsp").forward(req, resp);
    }
}
