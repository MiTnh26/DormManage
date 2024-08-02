package Controller.Admin;

import Controller.General.Common;
import Dto.RoomAdminDto;
import Dto.RoomBillAdminDto;
import Dto.StudentBedDto;
import Entity.Money;
import Entity.Room;
import Entity.RoomBill;
import Service.Impl.MoneyServiceImpl;
import Service.Impl.RoomBillServiceImpl;
import Service.Impl.RoomServiceImpl;
import Service.Impl.StudentServiceImpl;
import Service.MoneyService;
import Service.RoomBillService;
import Service.RoomService;
import Service.StudentService;
import Utils.SendMail;
import Enum.Semester;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/admin/room-detail")
public class RoomDetailView extends HttpServlet {
    private final RoomService roomService = new RoomServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();
    private final MoneyService moneyService = new MoneyServiceImpl();
    private final RoomBillService roomBillService = new RoomBillServiceImpl();
    private final Common common = new Common();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomName = req.getParameter("roomName");
        Room room = roomService.getByRoomName(roomName);
        String term = common.getSemester();
        int year = LocalDate.now().getYear();
        Set<StudentBedDto> studentList = new HashSet<>(studentService.getByRoomNameAndSemesterAndYear(roomName, term, year));
        List<Integer> usedBed = studentList.stream().map(StudentBedDto::getBed).toList();
        List<Integer> freeBed = new ArrayList<>();
        Money money = moneyService.getByMoneyTypeAndRoomType("ROOM", room.getRoomType());
        for (int i = 1; i <= money.getBedTotal(); i++) {
            if (!usedBed.contains(i)) {
                freeBed.add(i);
            }
        }
        String usedBedString = getStringBed(usedBed);
        String freeBedString = getStringBed(freeBed);
        RoomAdminDto roomAdminDto = RoomAdminDto.builder()
                .room(room.getRoomName())
                .dom("Dom" + room.getRoomName())
                .floor(room.getFloor())
                .amount(common.convertAmount(money.getAmount()))
                .totalBed(money.getBedTotal())
                .status(room.getRoomStatus().name())
                .studentBedDtoList(studentList)
                .freeBed(freeBedString)
                .usedBed(usedBedString)
                .build();

        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        if (month < 5) {
            term = Semester.DONG.name();
            year--;
        } else if (month < 9) {
            term = Semester.XUAN.name();
        } else {
            term = Semester.HA.name();
        }

        List<RoomBill> roomBills = roomBillService.getByRoomNameAndTermAndYear(roomName, term, year);
        List<RoomBillAdminDto> roomBillAdminDtos = roomBills.stream().map(x -> RoomBillAdminDto.builder()
                .billId(x.getBillId())
                .billStatus(x.getBillStatus())
                .rollName(x.getRollName())
                .roomName(x.getRoomName())
                .dayCreate(x.getDayCreate())
                .electricNumber(x.getElectricNumber())
                .electricMoney(common.convertAmount(x.getElectricMoney()))
                .waterNumber(x.getWaterNumber())
                .waterMoney(common.convertAmount(x.getWaterMoney()))
                .totalAmount(common.convertAmount(x.getTotalAmount()))
                .description(x.getDescription())
                .term(x.getTerm())
                .year(x.getYear())
                .build()).toList();
        req.setAttribute("roomBills", roomBillAdminDtos);
        req.setAttribute("roomAdmin", roomAdminDto);
        common.setTitle(req, "bed");
        req.getRequestDispatcher("/views/admin/room-detail.jsp").forward(req, resp);
    }

    private String getStringBed(List<Integer> bed) {
        if (bed.size() == 0) return "0";
        StringBuilder bedString = new StringBuilder(bed.size() + " (Bed no: ");
        for (int i = 0; i < bed.size(); i++) {
            if (i > 0) {
                bedString.append(", ");
            }
            bedString.append(bed.get(i));
        }
        return bedString.append(")").toString();
    }
}
