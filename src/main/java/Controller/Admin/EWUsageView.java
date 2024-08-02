package Controller.Admin;

import Controller.General.Common;
import Entity.ElectricWaterUsage;
import Entity.Room;
import Enum.Semester;
import Service.EWUsageService;
import Service.Impl.EWUsageServiceImpl;
import Service.Impl.RoomServiceImpl;
import Service.RoomService;
import com.google.api.client.util.Strings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@WebServlet("/admin/room-detail/ew-usage")
public class EWUsageView extends HttpServlet {
    private final Common common = new Common();
    private final RoomService roomService = new RoomServiceImpl();
    private final EWUsageService ewUsageService = new EWUsageServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomName = req.getParameter("roomName");
        String message = Strings.isNullOrEmpty(req.getParameter("message")) ? null : req.getParameter("message");
        int year = LocalDate.now().getYear();
        if (LocalDate.now().getMonthValue() == 1) {
            year--;
        }
        List<ElectricWaterUsage> usages = ewUsageService.getByRoomNameAndYear(roomName, year);
        usages.sort(Comparator.comparingInt(ElectricWaterUsage::getMonth));
        req.setAttribute("usages", usages);
        req.setAttribute("message", message);
        req.setAttribute("roomName", roomName);
        common.setTitle(req, "ew");
        req.getRequestDispatcher("/views/admin/ew-usage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int month = Strings.isNullOrEmpty(req.getParameter("month")) ? LocalDate.now().getMonthValue() : Integer.valueOf(req.getParameter("month"));
        int year = Strings.isNullOrEmpty(req.getParameter("year")) ? LocalDate.now().getYear() : Integer.valueOf(req.getParameter("year"));
        long electricNumber = Strings.isNullOrEmpty(req.getParameter("electric")) ? 0 : Long.parseLong(req.getParameter("electric"));
        long waterNumber = Strings.isNullOrEmpty(req.getParameter("water")) ? 0 : Long.parseLong(req.getParameter("water"));
        String roomName = req.getParameter("roomName");

        if(month >= LocalDate.now().getMonthValue() && year >= LocalDate.now().getYear()){
            resp.sendRedirect(req.getContextPath() + "/admin/room-detail/ew-usage?message=Don't enter month more than month now!&roomName=" + roomName);
            return;
        }

        if (ewUsageService.checkBillByRoomNameAndMonthAndYear(roomName, month, LocalDate.now().getYear())) {
            resp.sendRedirect(req.getContextPath() + "/admin/room-detail/ew-usage?message=Existed already!&roomName=" + roomName);
            return;
        }
        Room room = roomService.getByRoomName(roomName);
        String term;
        if (month < 5) {
            term = Semester.XUAN.name();
        } else if (month < 9) {
            term = Semester.HA.name();
        } else {
            term = Semester.DONG.name();
        }
        ElectricWaterUsage usage = ElectricWaterUsage.builder()
                .domName("DOM" + roomName.substring(0, 1))
                .electricNumber(electricNumber)
                .waterNumber(waterNumber)
                .floor(room.getFloor())
                .term(term)
                .year(year)
                .roomName(roomName)
                .month(month)
                .build();

        ewUsageService.save(usage);
        resp.sendRedirect(req.getContextPath() + "/admin/room-detail/ew-usage?message=Success!&roomName=" + roomName);
    }
}
