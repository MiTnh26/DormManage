<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <div>
            <h1 class="big-title">Payment history</h1>
            <c:if test="${payments.size() > 0}">
                <div class="container-block">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-advance table-hover">
                            <thead>
                            <tr>
                                <th>Room</th>
                                <th>Create date</th>
                                <th>Description</th>
                                <th>Status</th>
                                <th>Total amount</th>
                                <th>Total amount paid</th>
                                <th>Total remaining amount</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${payments}" var="payment">
                                <tr>
                                    <td>${payment.roomName}</td>
                                    <td>${payment.createDate.toString().substring(0,10)}</td>
                                    <td>${payment.description}</td>
                                    <td>${payment.status}</td>
                                    <td>${payment.totalAmount} VND</td>
                                    <td>${payment.totalAmountPaid} VND</td>
                                    <td>${payment.totalAmountRemain} VND</td>
                                    <td>
                                        <div class="flex justify-center">
                                            <div class="btn-action" style="width: fit-content;">
                                                <a
                                                        href="<%=request.getContextPath()%>/student/invoice-detail?paymentId=${payment.paymentId}">Detail</a>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
            <c:if test="${payments.size() == 0}">
                <%@ include file="no-record.jsp" %>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>
