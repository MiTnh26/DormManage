package Controller.Student;

import Controller.General.Common;
import Dto.InvoiceDetailDto;
import Entity.Bed;
import Entity.DomResident;
import Entity.Payment;
import Entity.RoomBill;
import Service.*;
import Service.Impl.*;
import com.google.api.client.util.Strings;
import org.checkerframework.checker.units.qual.C;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet("/student/invoice-detail")
public class InvoiceDetailView extends HttpServlet {
    private final PaymentService paymentService = new PaymentServiceImpl();
    private final RoomBillService roomBillService = new RoomBillServiceImpl();
    private final Common common = new Common();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int paymentId = Integer.valueOf(req.getParameter("paymentId"));
        Payment payment = paymentService.getById(paymentId);

        Map<String, String> money = new HashMap<>();
        Timestamp createDate = payment.getCreateDate();
        if (payment.getType() == 2) {
            RoomBill roomBill = roomBillService.getById(payment.getBillId());
            createDate = roomBill.getDayCreate();
            money.put("Electric", common.convertAmount(roomBill.getElectricMoney()));
            money.put("Water", common.convertAmount(roomBill.getWaterMoney()));
        } else {
            money.put("Room", common.convertAmount(payment.getTotalAmount()));
        }

        InvoiceDetailDto dto = InvoiceDetailDto.builder()
                .rollName(payment.getRollId())
                .room(payment.getRoomName())
                .createDate(common.convertTimeToStringYYYYMMDD(createDate))
                .description(payment.getDescription())
                .paymentStatus(payment.getStatus())
                .money(money)
                .moneyTotal(common.convertAmount(payment.getTotalAmount()))
                .bed(payment.getBed())
                .status(payment.getStatus())
                .build();

        req.setAttribute("payment", dto);
        common.setTitle(req, "PaymentHistory");
        req.getRequestDispatcher("/views/student/invoice-detail.jsp").forward(req, resp);
    }
}
