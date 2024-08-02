package Controller.General;

import Entity.Money;
import Enum.RoomType;
import Service.Impl.MoneyServiceImpl;
import Service.MoneyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student/get-amount")
public class GetAmount extends HttpServlet {
    private final MoneyService moneyService = new MoneyServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomType = req.getParameter("roomType");
        Money money = moneyService.getByMoneyTypeAndRoomType("ROOM", roomType);
        String amount = new Common().convertAmount(money.getAmount());
        resp.getWriter().write(amount);
    }

}
