package Controller.General;

import Dto.FloorAndFreeBed;
import Entity.Bed;
import Entity.Student;
import Enum.BedStatus;
import Service.BedService;
import Service.Impl.BedServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@WebServlet("/student/detail-book-room")
public class GetDetailBookRoom extends HttpServlet {
    private final BedService bedService = new BedServiceImpl();
    private final Common common = new Common();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dom = req.getParameter("dom");
        int floor = Integer.valueOf(req.getParameter("floor"));
        String roomTypeName = req.getParameter("roomType");
        Set<Integer> floors = new HashSet<>();
        Student student = common.getStudentSession(req, resp);

        List<Bed> beds = bedService.getByRoomTypeAndGender(roomTypeName, student.getGender());

        long freeBed = beds.stream()
                .peek(x -> {
                    String key = x.getRoomName().substring(0, 1);
                    if (key.equals(dom)) {
                        floors.add(x.getFloor());
                    }
                })
                .filter(x -> {
                    String key = x.getRoomName().substring(0, 1);
                    return BedStatus.NOTAVAILABLE.equals(x.getBedStatus()) && key.equals(dom)
                            && x.getFloor().equals(floors.size() < floor ? 1 : floor);
                })
                .count();

        FloorAndFreeBed floorAndFreeBed = FloorAndFreeBed.builder()
                .freeBed(freeBed)
                .floors(floors)
                .build();
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(floorAndFreeBed);
        resp.getWriter().write(jsonResponse);

    }
}



