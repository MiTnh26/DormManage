package Controller.Student;

import Controller.General.Common;
import Dto.EWUsageDto;
import Dto.RoomBillDto;
import Dto.UsagePersonalDto;
import Entity.*;
import Enum.Semester;
import Service.DomResidentService;
import Service.EWUsageService;
import Service.Impl.DomResidentServiceImpl;
import Service.Impl.EWUsageServiceImpl;
import Service.Impl.MoneyServiceImpl;
import Service.Impl.RoomBillServiceImpl;
import Service.MoneyService;
import Service.RoomBillService;
import com.google.api.client.util.Strings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/student/EWBedUsages")
public class ElectricWaterUsageView extends HttpServlet {
    private final Common common = new Common();
    private final RoomBillService roomBillService = new RoomBillServiceImpl();
    private final EWUsageService ewUsageService = new EWUsageServiceImpl();
    private final DomResidentService domResidentService = new DomResidentServiceImpl();

    private final MoneyService moneyService = new MoneyServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = common.getStudentSession(req, resp);

        LocalDate localDate = LocalDate.now();
        int month = localDate.getMonth().getValue();
        int year = localDate.getYear();
        String term;
        if (month < 5) {
            year--;
            term = Semester.DONG.name();
        } else if (month < 9) {
            term = Semester.XUAN.name();
        } else {
            term = Semester.HA.name();
        }

        List<DomResident> domResident = domResidentService.getByRollIdAndYear(student.getRollId(), LocalDate.now().getYear());
        Map<String, String> domResidentMap = domResident.stream().collect(Collectors.toMap(DomResident::getTermId, DomResident::getRoomName));

        List<EWUsageDto> ewUsages = new ArrayList<>();
        domResidentMap.forEach((key, value) -> {
            int numOfUser = domResidentService.getUsagePersonal(key, value);
            List<ElectricWaterUsage> usages = ewUsageService.getByRoomNameAndYearAndTerm(value, LocalDate.now().getYear(), key);
            usages.forEach(u -> {
                EWUsageDto ew = EWUsageDto.builder()
                        .id(u.getId())
                        .electricNumber(u.getElectricNumber() / numOfUser)
                        .waterNumber(u.getWaterNumber() / numOfUser)
                        .month(u.getMonth())
                        .year(LocalDate.now().getYear())
                        .build();
                ewUsages.add(ew);
            });
        });
        ewUsages.sort(Comparator.comparingInt(EWUsageDto::getMonth));
        req.setAttribute("ewUsages",ewUsages);

        RoomBill roomBill = roomBillService.getByRollNameAndTermAndYear(student.getRollId(), term, year);
        if (Objects.isNull(roomBill)) {
            req.setAttribute("roomBill", null);
            req.getRequestDispatcher("/views/student/ew-bed-usages.jsp").forward(req, resp);
            return;
        }
        List<ElectricWaterUsage> usages = ewUsageService.getByRoomNameAndYearAndTerm(roomBill.getRoomName(), year, roomBill.getTerm());
        int studentInRoom = domResidentService.countUserInRoomAndTermAndYear(roomBill.getRoomName(), term, year);
        List<Money> monies = moneyService.getByListMoneyType(List.of("ELECTRIC", "WATER"));
        Map<String, Long> mapMoney = monies.stream().collect(Collectors.toMap(Money::getMoneyType, Money::getAmount));
        List<EWUsageDto> ewUsageDtos = usages.stream().map(x -> {
            long electricNumber = x.getElectricNumber() / studentInRoom;
            long waterNumber = x.getWaterNumber() / studentInRoom;
            long electricMoney = electricNumber * mapMoney.get("ELECTRIC");
            long waterMoney = waterNumber * mapMoney.get("WATER");
            return EWUsageDto.builder()
                    .id(x.getId())
                    .electricNumber(x.getElectricNumber() / studentInRoom)
                    .waterNumber(x.getWaterNumber() / studentInRoom)
                    .month(x.getMonth())
                    .electricMoney(common.convertAmount(electricMoney))
                    .waterMoney(common.convertAmount(waterMoney))
                    .totalMoney(common.convertAmount(electricMoney + waterMoney))
                    .build();
        }).toList();

        RoomBillDto dto = RoomBillDto.builder()
                .billId(roomBill.getBillId())
                .roomName(roomBill.getRoomName())
                .rollName(roomBill.getRollName())
                .status(roomBill.getBillStatus())
                .description(roomBill.getDescription())
                .year(roomBill.getYear())
                .term(roomBill.getTerm())
                .electricNumber(roomBill.getElectricNumber())
                .waterNumber(roomBill.getWaterNumber())
                .electricMoney(common.convertAmount(roomBill.getElectricMoney()))
                .waterMoney(common.convertAmount(roomBill.getWaterMoney()))
                .totalAmount(common.convertAmount(roomBill.getTotalAmount()))
                .ewUsages(ewUsageDtos)
                .build();
        common.setTitle(req, "EW");
        String message = Strings.isNullOrEmpty(req.getParameter("message")) ? null : req.getParameter("message");
        req.setAttribute("roomBill", dto);
        req.setAttribute("message", message);
        req.getRequestDispatcher("/views/student/ew-bed-usages.jsp").forward(req, resp);
    }


}
