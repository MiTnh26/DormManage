package Controller.Admin;

import Controller.General.Common;
import Dto.RoomBillAdminDto;
import Entity.RoomBill;
import Enum.Semester;
import Entity.DomResident;
import Entity.ElectricWaterUsage;
import Service.DomResidentService;
import Service.EWUsageService;
import Service.Impl.DomResidentServiceImpl;
import Service.Impl.EWUsageServiceImpl;
import Service.Impl.RoomBillServiceImpl;
import Service.RoomBillService;
import com.google.api.client.util.Strings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/admin/bill")
public class BillView extends HttpServlet {
    private final DomResidentService domResidentService = new DomResidentServiceImpl();

    private final EWUsageService ewUsageService = new EWUsageServiceImpl();

    private final RoomBillService roomBillService = new RoomBillServiceImpl();
    private final Common common = new Common();
    private final int LIMIT = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keySearch = Strings.isNullOrEmpty(req.getParameter("keySearch")) ? "" : req.getParameter("keySearch");
        int page = Strings.isNullOrEmpty(req.getParameter("page")) ? 1 : Integer.valueOf(req.getParameter("page"));
        LocalDate date = LocalDate.now().minusMonths(1);
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

        List<ElectricWaterUsage> usages = ewUsageService.getByTermAndYear(term, year);
        Map<String, List<ElectricWaterUsage>> usagesMap = usages.stream().collect(Collectors.groupingBy(ElectricWaterUsage::getRoomName));
        Set<String> roomName = usagesMap.entrySet().stream().filter(x -> x.getValue().size() < 4).map(Map.Entry::getKey).collect(Collectors.toSet());

        if (roomName.size() > 0) {
            StringBuilder sb = new StringBuilder("Còn 1 số phòng chưa nhập số tiền điện và nước được sử dụng trong kỳ ").append(term).append(": ");
            sb.append(roomName.stream().collect(Collectors.joining(", "))).append(".");
            req.setAttribute("message", sb.toString());
        } else {
            String result = Strings.isNullOrEmpty(req.getParameter("result")) ? null : req.getParameter("result");
            req.setAttribute("result", result);
        }
        List<RoomBill> roomBills = roomBillService.getAll();
        List<RoomBill> roomBillList = roomBillService.getAll(keySearch, page - 1, LIMIT);
        List<RoomBillAdminDto> roomBillAdminDtos = roomBillList.stream().map(x -> RoomBillAdminDto.builder()
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
        req.setAttribute("term", term);
        req.setAttribute("page", page - 1);
        req.setAttribute("keySearch", keySearch);
        req.setAttribute("totalPage", (int) Math.ceil(roomBills.size() / LIMIT));
        common.setTitle(req, "ew");
        req.getRequestDispatcher("/views/admin/bill.jsp").forward(req, resp);
    }
}
