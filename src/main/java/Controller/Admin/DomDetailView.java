package Controller.Admin;

import Controller.General.Common;
import Dto.BedDetailDto;
import Dto.BedTotalDto;
import Entity.Bed;
import Service.BedService;
import Service.Impl.BedServiceImpl;
import Enum.BedStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@WebServlet("/admin/dom-detail")
public class DomDetailView extends HttpServlet {
    private static final String VIEW_PATH = "/views/admin/dorm-details.jsp";
    private static final String HOME_PATH = "/views/admin/home.jsp";

    private final BedService bedService = new BedServiceImpl();
    private final Common common = new Common();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String domId = req.getParameter("domId");
        List<Bed> beds = bedService.getByDomId(domId);

        Map<Integer, List<Bed>> mapByFloor = beds.stream().collect(Collectors.groupingBy(Bed::getFloor));

        List<BedTotalDto> bedTotalDtoList = new ArrayList<>();
        mapByFloor.entrySet().forEach(entry -> {
            long used = entry.getValue().stream()
                    .filter(bed -> BedStatus.AVAILABLE.equals(bed.getBedStatus()))
                    .count();
            long free = entry.getValue().stream()
                    .filter(bed -> !BedStatus.AVAILABLE.equals(bed.getBedStatus()))
                    .count();
            BedTotalDto dto = BedTotalDto.builder()
                    .floor(entry.getKey())
                    .used(used)
                    .free(free)
                    .sum(used + free)
                    .build();
            bedTotalDtoList.add(dto);
        });



        Map<String, List<Bed>> mapByRoomName = beds.stream().collect(Collectors.groupingBy(Bed::getRoomName));

        List<BedDetailDto> bedDetailDtoList = new ArrayList<>();

        mapByRoomName.entrySet().forEach( entry -> {
            StringBuilder usedBed = new StringBuilder("(Bed No: ");
            StringBuilder freeBed = new StringBuilder("(Bed No: ");
            StringBuilder bookingBed = new StringBuilder("(Bed No: ");
            AtomicLong used = new AtomicLong();
            AtomicLong free = new AtomicLong();
            AtomicLong booking = new AtomicLong();
            entry.getValue().forEach(bed -> {
                switch (bed.getBedStatus()){
                    case AVAILABLE -> {
                        if(used.get() > 0) {
                            usedBed.append(", ");
                        }
                        usedBed.append(bed.getBedId());
                        used.getAndIncrement();
                    }
                    case NOTAVAILABLE -> {
                        if(free.get() > 0) {
                            freeBed.append(", ");
                        }
                        freeBed.append(bed.getBedId());
                        free.getAndIncrement();
                    }
                    case BOOKING -> {
                        if(booking.get() > 0) {
                            bookingBed.append(", ");
                        }
                        bookingBed.append(bed.getBedId());
                        booking.getAndIncrement();
                    }
                }
            });
            usedBed.append(" )");
            freeBed.append(" )");
            bookingBed.append(" )");

            BedDetailDto dto = BedDetailDto.builder()
                    .roomName(entry.getKey())
                    .totalBed(entry.getValue().stream().count())
                    .usedBed(used.get() > 0 ? used.get() + " " + usedBed : "0")
                    .freeBed(free.get() > 0 ? free.get() + " " + freeBed : "0")
                    .bookingBed(booking.get() > 0 ? booking.get() + " " + bookingBed : "0")
                    .pricePerMonth(0)
                    .build();
            bedDetailDtoList.add(dto);
        });

        bedDetailDtoList.sort(Comparator.comparing(BedDetailDto::getRoomName));
        long totalFreeBed = bedTotalDtoList.stream().mapToLong(BedTotalDto::getFree).sum();
        long totalUsedBed = bedTotalDtoList.stream().mapToLong(BedTotalDto::getUsed).sum();
        long usedBedDetail = bedDetailDtoList.stream()
                .filter(x -> !"0".equals(x.getUsedBed()))
                .mapToLong(x -> Long.parseLong(x.getUsedBed().substring(0,1)))
                .sum();
        long freeBedDetail = bedDetailDtoList.stream().filter(x -> !"0".equals(x.getFreeBed()))
                .mapToLong(x -> Long.parseLong(x.getFreeBed().substring(0,1)))
                .sum();;
        long bookingBedDetail = bedDetailDtoList.stream().filter(x -> !"0".equals(x.getBookingBed()))
                .mapToLong(x -> Long.parseLong(x.getBookingBed().substring(0,1)))
                .sum();;
        req.setAttribute("totalFree", totalFreeBed);
        req.setAttribute("totalUsed", totalUsedBed);
        req.setAttribute("bedTotalDto", bedTotalDtoList);
        req.setAttribute("bedDetailDto", bedDetailDtoList);
        req.setAttribute("usedBedDetail", usedBedDetail);
        req.setAttribute("freeBedDetail", freeBedDetail);
        req.setAttribute("bookingBedDetail", bookingBedDetail);
        req.setAttribute("domName",domId);
        req.setAttribute("semester", common.getSemester() + " - " + LocalDate.now().getYear());
        common.setTitle(req, "bed");
        req.getRequestDispatcher("/views/admin/dom-detail.jsp").forward(req, resp);
    }
}
