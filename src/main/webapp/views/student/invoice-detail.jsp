<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <div>
            <h1 class="big-title">Invoice details</h1>

            <div>
                <dl class="dl-horizontal">
                    <dt class="dt">RollName</dt>
                    <dd class="dd">${payment.rollName}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Room</dt>
                    <dd class="dd">${payment.room}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Bed number</dt>
                    <dd class="dd">${payment.bed}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Payment Date</dt>
                    <dd class="dd">${payment.createDate}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Payment Status</dt>
                    <dd class="dd">${payment.status}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Description</dt>
                    <dd class="dd">${payment.description}</dd>
                </dl>
            </div>
            <div class="container-block">
                <div class="table-responsive">
                    <table class="table table-bordered table-advance table-hover">
                        <thead>
                        <tr>
                            <th>Type</th>
                            <th>Description</th>
                            <th>Amount</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${payment.money}" var="item">
                            <tr>
                                <td>${item.key}</td>
                                <td></td>
                                <td>${item.value} VND</td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td class="text-blue font-weight-m" colspan="2">Total</td>
                            <td class="text-blue font-weight-m">${payment.moneyTotal} VND</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <button onclick="location.href='<%=request.getContextPath()%>/student/payment-history';return false;" class="SAB-back"
                style="margin-top: 24px;">Back to list
        </button>
    </div>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>
