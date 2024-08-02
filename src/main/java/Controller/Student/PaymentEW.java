package Controller.Student;

import Controller.General.Common;
import Entity.DomResident;
import Entity.Payment;
import Entity.RoomBill;
import Entity.Student;
import Service.DomResidentService;
import Service.Impl.DomResidentServiceImpl;
import Service.Impl.PaymentServiceImpl;
import Service.Impl.RoomBillServiceImpl;
import Service.Impl.StudentServiceImpl;
import Service.PaymentService;
import Service.RoomBillService;
import Service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Objects;

@WebServlet("/student/payment-ew")
public class PaymentEW extends HttpServlet {

    private final PaymentService paymentService = new PaymentServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();
    private final RoomBillService roomBillService = new RoomBillServiceImpl();
    private final DomResidentService domResidentService = new DomResidentServiceImpl();
    private final Common common = new Common();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String jsonResponse;
        Student student = common.getStudentSession(req, resp);
        RoomBill roomBill = roomBillService.getById(id);
        if(!student.getRollId().equals(roomBill.getRollName())){
            throw new RuntimeException();
        }
        long balance = student.getBalance() - roomBill.getTotalAmount();
        if (balance < 0){
            jsonResponse = "Balance not enough!";
        } else {
            student.setBalance(balance);
            studentService.updateBalance(student.getRollId(), balance);
            req.getSession().setAttribute("student", student);
            DomResident domResident = domResidentService.getByRollIdAndSemesterAndYear(roomBill.getRollName(), roomBill.getTerm(), roomBill.getYear());
            roomBill.setBillStatus("PAID");
            roomBillService.updateStatus(roomBill.getBillId(), "PAID");
            Payment payment = Payment.builder()
                    .type(2)
                    .status("Paid in full")
                    .roomName(roomBill.getRoomName())
                    .term(roomBill.getTerm())
                    .rollId(roomBill.getRollName())
                    .createDate(new Timestamp(System.currentTimeMillis()))
                    .totalAmount(roomBill.getTotalAmount())
                    .totalAmountRemain(0L)
                    .totalAmountPaid(roomBill.getTotalAmount())
                    .billId(roomBill.getBillId())
                    .description("Thanh toan tien dien nuoc ky:" + roomBill.getTerm())
                    .bed(domResident.getBedId())
                    .build();

            paymentService.savePaymentEW(payment);
            jsonResponse = "Payment success!";
        }
        resp.getWriter().write(jsonResponse);
    }
}
