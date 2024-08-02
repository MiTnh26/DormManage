package Controller.Student;

import Controller.General.Common;
import Dto.BookRoomDto;
import Entity.Bed;
import Entity.Student;
import Enum.BedStatus;
import Enum.RoomType;
import Service.BedService;
import Service.Impl.BedServiceImpl;
import com.google.api.client.util.Strings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/student/book-room")
public class BookRoomView extends HttpServlet {
    private final BedService bedService = new BedServiceImpl();

    private final Common common = new Common();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Strings.isNullOrEmpty(req.getParameter("roomTypeName")) || Strings.isNullOrEmpty(req.getParameter("roomAmount"))) {
            resp.sendRedirect(req.getContextPath() + "/views/error.jsp");
            return;
        }
        String roomTypeName = req.getParameter("roomTypeName");
        long roomAmount = Long.parseLong(req.getParameter("roomAmount").replaceAll("\\.", ""));

        Set<String> doms = new HashSet<>();
        Set<Integer> floors = new HashSet<>();

        Student student = common.getStudentSession(req, resp);
        List<Bed> beds = bedService.getByRoomTypeAndGender(roomTypeName, student.getGender());

        long freeBed = beds.stream()
                .peek(x -> {
                    String key = x.getRoomName().substring(0, 1);
                    doms.add(key);
                    if ("A".equals(key)) {
                        floors.add(x.getFloor());
                    }
                })
                .filter(x -> {
                    String key = x.getRoomName().substring(0, 1);
                    return BedStatus.NOTAVAILABLE.equals(x.getBedStatus()) && x.getFloor().equals(1) && "A".equals(key);
                })
                .count();


        long amountSe = roomAmount * 4;
        long studentBalance = student.getBalance();
        BookRoomDto bookRoomDto = BookRoomDto.builder()
                .doms(doms)
                .floors(floors)
                .key(roomTypeName)
                .minimumBalance(common.convertAmount(amountSe))
                .yourAccountBalance(common.convertAmount(studentBalance))
                .roomType(RoomType.valueOf(roomTypeName).getType() + "")
                .roomAmount(common.convertAmount(roomAmount))
                .numberSlotsRemaining(freeBed)
                .yourBalanceAfterBooking(common.convertAmount(studentBalance - amountSe))
                .build();

        req.setAttribute("bookRoomDto", bookRoomDto);
        common.setTitle(req, "BookRoom");
        req.getRequestDispatcher("/views/student/book-room.jsp").forward(req, resp);
    }
}
