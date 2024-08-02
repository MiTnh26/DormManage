package Controller.Admin;

import Entity.DomResident;
import Entity.ElectricWaterUsage;
import Entity.Money;
import Entity.RoomBill;
import Enum.Semester;
import Service.DomResidentService;
import Service.EWUsageService;
import Service.Impl.DomResidentServiceImpl;
import Service.Impl.EWUsageServiceImpl;
import Service.Impl.MoneyServiceImpl;
import Service.Impl.RoomBillServiceImpl;
import Service.MoneyService;
import Service.RoomBillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/admin/create-bill")
public class CreateBill extends HttpServlet {

    private final RoomBillService roomBillService = new RoomBillServiceImpl();
    private final DomResidentService domResidentService = new DomResidentServiceImpl();
    private final EWUsageService ewUsageService = new EWUsageServiceImpl();

    private final MoneyService moneyService = new MoneyServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();
        String term;
        if (month < 5) {
            term = Semester.DONG.name();
            year--;
        } else if (month < 9) {
            term = Semester.XUAN.name();
        } else {
            term = Semester.HA.name();
        }

        List<DomResident> domResidents = domResidentService.getBySemesterAndYear(term, year);
        Set<String> roomNames = domResidents.stream().map(DomResident::getRoomName).collect(Collectors.toSet());
        List<RoomBill> roomBillList = roomBillService.getByTermAndYear(term, year);
        Set<String> roomNamesInBill = roomBillList.stream().map(RoomBill::getRoomName).collect(Collectors.toSet());
        roomNames.removeAll(roomNamesInBill);

        if (roomNames.size() == 0) {
            resp.sendRedirect(req.getContextPath()+ "/admin/bill?result=Da tao hoa don cua ky: "+ term);
        } else {
            List<ElectricWaterUsage> usages = ewUsageService.getByTermAndYear(term, year);

            Map<String, List<ElectricWaterUsage>> usagesMap = usages.stream().collect(Collectors.groupingBy(ElectricWaterUsage::getRoomName));

            Map<String, List<String>> mapRollNames = domResidents.stream()
                    .collect(Collectors.groupingBy(DomResident::getRoomName)).entrySet().stream()
                    .filter(x -> roomNames.contains(x.getKey()))
                    .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue().stream().map(DomResident::getRollId).toList()));


            List<Money> monies = moneyService.getByListMoneyType(List.of("ELECTRIC", "WATER"));
            Map<String, Long> mapMoney = monies.stream().collect(Collectors.toMap(Money::getMoneyType, Money::getAmount));
            List<RoomBill> addRoomBills = new ArrayList<>();
            int finalYear = year;
            usagesMap.entrySet().forEach(x -> {
                long totalElectricNumber = 0;
                long totalWaterNumber = 0;
                for (ElectricWaterUsage e : x.getValue()) {
                    totalWaterNumber += e.getWaterNumber();
                    totalElectricNumber += e.getElectricNumber();
                }
                long electricAmountTotal = totalElectricNumber * mapMoney.get("ELECTRIC");
                long waterAmountTotal = totalWaterNumber * mapMoney.get("WATER");
                long electricAmountOnRollName = electricAmountTotal / mapRollNames.get(x.getKey()).size();
                long waterAmountOnRollName = waterAmountTotal / mapRollNames.get(x.getKey()).size();
                long finalTotalElectricNumber = totalElectricNumber;
                long finalTotalWaterNumber = totalWaterNumber;
                List<RoomBill> roomBills =  mapRollNames.get(x.getKey()).stream().map(n ->
                    RoomBill.builder()
                            .roomName(x.getKey())
                            .rollName(n)
                            .electricMoney(electricAmountOnRollName)
                            .electricNumber(finalTotalElectricNumber)
                            .waterMoney(waterAmountOnRollName)
                            .waterNumber(finalTotalWaterNumber)
                            .billStatus("UNPAID")
                            .totalAmount(electricAmountOnRollName + waterAmountOnRollName)
                            .description("Tien dien nuoc ky: " + term)
                            .dayCreate(new Timestamp(System.currentTimeMillis()))
                            .year(finalYear)
                            .term(term)
                            .build()
                ).toList();
                addRoomBills.addAll(roomBills);
            });
            roomBillService.saveAll(addRoomBills);
            resp.sendRedirect(req.getContextPath()+ "/admin/bill?result=Tao hoa don ky " + term + " thanh cong.");
        }
    }
}
