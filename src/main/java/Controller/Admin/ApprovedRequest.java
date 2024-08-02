package Controller.Admin;

import Entity.Bed;
import Entity.DomResident;
import Entity.Money;
import Entity.Payment;
import Entity.Request;
import Entity.Student;
import Enum.RequestType;
import Enum.BedStatus;
import Service.BedService;
import Service.DomResidentService;
import Service.Impl.BedServiceImpl;
import Service.Impl.DomResidentServiceImpl;
import Service.Impl.MoneyServiceImpl;
import Service.Impl.PaymentServiceImpl;
import Service.Impl.RequestServiceImpl;
import Service.Impl.StudentServiceImpl;
import Service.MoneyService;
import Service.PaymentService;
import Service.RequestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import Enum.RequestStatus;
import Service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@WebServlet("/admin/request/approved")
public class ApprovedRequest extends HttpServlet {
    private final RequestService requestService = new RequestServiceImpl();
    private final MoneyService moneyService = new MoneyServiceImpl();

    private final BedService bedService = new BedServiceImpl();

    private final DomResidentService domResidentService = new DomResidentServiceImpl();

    private final StudentService studentService = new StudentServiceImpl();

    private final PaymentService paymentService = new PaymentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        Request request = requestService.getById(id);
        request.setRequestStatus(RequestStatus.ACCEPTED.name());
        requestService.updateStatus(request);
        System.out.println(request.toString());
        if (request.getRequestType().equals(RequestType.CHECKIN.name())) {
            Money money = moneyService.getByMoneyTypeAndRoomType("ROOM", request.getRoomType());

            Bed bed = bedService.randomBedByFloorAndDomNameAndRoomType(request.getFloor(), request.getDomName(), request.getRoomType());

            LocalDateTime newDateTime = request.getCreateDate().toLocalDateTime().plusMonths(4);

            Timestamp checkOutDate = Timestamp.valueOf(newDateTime);

            DomResident domResident = DomResident.builder()
                    .balance(money.getAmount())
                    .rollId(request.getRollId())
                    .floor(request.getFloor())
                    .bedId(bed.getBedId())
                    .checkInDate(request.getCreateDate())
                    .checkOutDate(checkOutDate)
                    .roomName(bed.getRoomName())
                    .termId(request.getTerm())
                    .build();
            domResidentService.save(domResident);
            bed.setBedStatus(BedStatus.valueOf(BedStatus.AVAILABLE.name()));
            bedService.updateStatus(bed);
            Student student = studentService.getByRollId(request.getRollId());
            long balance = student.getBalance() - money.getAmount() * 4;
            student.setBalance(balance);
            studentService.updateBalance(student.getRollId(), balance);
            studentService.updateStatus(student.getRollId(), "RESIDENT");
            Payment payment = Payment.builder()
                    .rollId(student.getRollId())
                    .term(domResident.getTermId())
                    .description("Thanh toan tien phong ky: " + domResident.getTermId())
                    .totalAmount(money.getAmount() * 4)
                    .totalAmountPaid(money.getAmount() * 4)
                    .totalAmountRemain(0L)
                    .bed(domResident.getBedId())
                    .createDate(new Timestamp(System.currentTimeMillis()))
                    .type(1)
                    .status("Paid in full")
                    .roomName(domResident.getRoomName())
                    .build();

            paymentService.savePaymentRoom(payment);
            resp.sendRedirect(req.getContextPath() + "/admin/request");
        }

        if (request.getRequestType().equals(RequestType.CHECKOUT.name())) {
            Money money = moneyService.getByMoneyTypeAndRoomType("ROOM", request.getRoomType());
            Bed bed = bedService.randomBedByFloorAndDomNameAndRoomType(request.getFloor(), request.getDomName(), request.getRoomType());
            DomResident domResident = DomResident.builder()
                    .balance(money.getAmount())
                    .rollId(request.getRollId())
                    .floor(request.getFloor())
                    .bedId(bed.getBedId())
                    .checkInDate(request.getCreateDate())
                    .checkOutDate(request.getCheckOutDate())
                    .roomName(bed.getRoomName())
                    .termId(request.getTerm())
                    .build();
            domResidentService.save(domResident);
            resp.sendRedirect(req.getContextPath() + "/admin/request");
        }

    }

}
