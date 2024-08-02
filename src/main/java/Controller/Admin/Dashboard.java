package Controller.Admin;

import Controller.General.Common;
import Dto.DashboardDto;
import Dto.RoomBillDashBoard;
import Entity.Bed;
import Entity.Money;
import Entity.RoomBill;
import Entity.Student;
import Enum.BedStatus;
import Enum.Semester;
import Enum.StudentStatus;
import Service.BedService;
import Service.Impl.BedServiceImpl;
import Service.Impl.MoneyServiceImpl;
import Service.Impl.RoomBillServiceImpl;
import Service.Impl.StudentServiceImpl;
import Service.MoneyService;
import Service.RoomBillService;
import Service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@WebServlet("/admin/dashboard")
public class Dashboard extends HttpServlet {
    private final BedService bedService = new BedServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();
    private final RoomBillService roomBillService = new RoomBillServiceImpl();
    private final MoneyService moneyService = new MoneyServiceImpl();
    private final Common common = new Common();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Bed> beds = bedService.getAll();
        List<Student> students = studentService.getAll();
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int year = date.getYear();

        String term;
        if (month < 5) {
            term = Semester.DONG.name();
            year--;
        } else if (month < 9) {
            term = Semester.XUAN.name();
        } else {
            term = Semester.HA.name();
        }
        List<RoomBill> roomBills = roomBillService.getByTermAndYear(term, year);
        Map<String, List<RoomBill>> roomBillMap = roomBills.stream().collect(Collectors.groupingBy(RoomBill::getRoomName));
        List<Money> monies = moneyService.getByListMoneyType(List.of("ELECTRIC", "WATER"));
        Map<String, Money> moneyMap = monies.stream().collect(Collectors.toMap(Money::getMoneyType, Function.identity()));
        List<RoomBillDashBoard> roomBillDashBoards = roomBillMap.entrySet().stream()
                .map(x -> {
                    long water = x.getValue().stream().mapToLong(RoomBill::getWaterNumber).sum();
                    long electric = x.getValue().stream().mapToLong(RoomBill::getElectricNumber).sum();
                    boolean checkPay = x.getValue().stream().anyMatch(v -> v.getBillStatus().equals("UNPAID"));
                    long amount = water * moneyMap.get("WATER").getAmount() + electric * moneyMap.get("ELECTRIC").getAmount();
                    Timestamp dayCreate = x.getValue().get(0).getDayCreate();
                    return RoomBillDashBoard.builder()
                            .roomName(x.getKey())
                            .electricNumber(electric)
                            .waterNumber(water)
                            .status(checkPay ? "Paid not full" : "Paid in full")
                            .year(LocalDate.now().getYear())
                            .student(x.getValue().size())
                            .amount(common.convertAmount(amount))
                            .s(amount)
                            .term(term)
                            .date(common.convertTimeToStringYYYYMMDD(dayCreate))
                            .build();
                })
                .sorted((a, b) -> Math.toIntExact(b.getS() - a.getS()))
                .limit(10)
                .toList();
        DashboardDto dto = DashboardDto.builder()
                .totalBed(beds.size())
                .freeBed(beds.stream().filter(x -> x.getBedStatus().equals(BedStatus.NOTAVAILABLE)).count())
                .usedBed(beds.stream().filter(x -> x.getBedStatus().equals(BedStatus.AVAILABLE)).count())
                .student(students.stream().filter(x -> x.getStudentStatus().equals(StudentStatus.RESIDENT)).count())
                .roomBillDashBoards(roomBillDashBoards)
                .build();
        req.setAttribute("dto", dto);
        common.setTitle(req, "Home");
        req.getRequestDispatcher("/views/admin/dashboard.jsp").forward(req, resp);
    }
}
