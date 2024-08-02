package Controller.Student;

import Controller.General.Common;
import Dto.PaymentDto;
import Entity.Payment;
import Entity.Student;
import Service.Impl.PaymentServiceImpl;
import Service.PaymentService;
import com.google.api.client.util.Strings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/student/payment-history")
public class PaymentHistoryView extends HttpServlet {
    private final PaymentService paymentService = new PaymentServiceImpl();
    private final Common common = new Common();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = common.getStudentSession(req, resp);
        if(Objects.isNull(student)){
            resp.sendRedirect("/views/error.jsp");
            return;
        }
        List<Payment> payments = paymentService.getByRollId(student.getRollId());

        List<PaymentDto> paymentDtoList = payments.stream().map( x->
            PaymentDto.builder().paymentId(x.getPaymentId())
                    .status(x.getStatus())
                    .rollId(x.getRollId())
                    .roomName(x.getRoomName())
                    .description(x.getDescription())
                    .createDate(common.convertTimeToStringYYYYMMDD(x.getCreateDate()))
                    .totalAmountRemain(common.convertAmount(x.getTotalAmountRemain()))
                    .totalAmountPaid(common.convertAmount(x.getTotalAmountPaid()))
                    .totalAmount(common.convertAmount(x.getTotalAmount()))
                    .build()
        ).toList();
        req.setAttribute("payments", paymentDtoList);
        common.setTitle(req, "PaymentHistory");
        req.getRequestDispatcher("/views/student/payment-history.jsp").forward(req, resp);
    }
}
