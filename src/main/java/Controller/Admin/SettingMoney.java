package Controller.Admin;

import Controller.General.Common;
import Dto.MoneyDto;
import Entity.Money;
import Service.Impl.MoneyServiceImpl;
import Service.MoneyService;
import org.checkerframework.checker.units.qual.C;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/setting-money")
public class SettingMoney extends HttpServlet {

    private final MoneyService moneyService = new MoneyServiceImpl();

    private final Common common = new Common();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Money> monies = moneyService.getAll();
        List<MoneyDto> moneyDtos = monies.stream().map(x ->
                MoneyDto
                        .builder()
                        .moneyId(x.getMoneyId())
                        .moneyType(x.getMoneyType())
                        .amount(common.convertAmount(x.getAmount()))
                        .roomType(x.getRoomType())
                        .bedTotal(x.getBedTotal())
                        .build()).toList();
        req.setAttribute("room", moneyDtos.stream().filter(x -> "ROOM".equals(x.getMoneyType())).toList());
        req.setAttribute("ew", moneyDtos.stream().filter(x -> !"ROOM".equals(x.getMoneyType())).toList());
        common.setTitle(req, "money");
        req.getRequestDispatcher("/views/admin/setting-money.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("moneyId"));
        long amount = Long.valueOf(req.getParameter("amount"));
        Money money = moneyService.getById(id);
        money.setAmount(amount);
        moneyService.update(money);
        resp.sendRedirect(req.getContextPath() + "/admin/setting-money");
    }
}
