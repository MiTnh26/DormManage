<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <div class="">
            <h1 class="big-title">Electricity water usage</h1>
            <div class="flex justify-start">
                <div class="flex justify-end" style="margin: 10px 10px">
                    <button onclick="showBlockUsage()" class="orange-btn">Block usage</button>
                </div>
                <div class="flex justify-end" style="margin: 10px 0">
                    <button onclick="hiddenBlockUsage()" class="orange-btn">Personal usage</button>
                </div>
            </div>
            <div id="block-usage">
                <c:if test="${roomBill == null}">
                    <%@ include file="no-record.jsp" %>
                </c:if>
                <c:if test="${message != null}">
                    <h3 class="text-danger"style="margin: 10px 0">${message}</h3>
                </c:if>
                <c:if test="${roomBill != null}">
                    <div class="container-block" style="margin-top: 24px">
                        <table class="table table-bordered table-advance table-hover">
                            <thead>
                            <tr>
                                <th>Block</th>
                                <th>Month - Year</th>
                                <th>Type</th>
                                <th>Your usage</th>
                                <th>Total Amount</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${roomBill.ewUsages}" var="u">
                                <tr>
                                    <td rowspan="2">${roomBill.roomName}</td>
                                    <td rowspan="2">${u.month} - ${roomBill.year}</td>
                                    <td>Electric</td>
                                    <td>${u.electricNumber}</td>
                                    <td>${u.electricMoney}</td>
                                </tr>
                                <tr>
                                    <td>Water</td>
                                    <td>${u.waterNumber}</td>
                                    <td>${u.waterMoney}</td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3">Total Electric:</td>
                                <td>${roomBill.electricNumber}</td>
                                <td>${roomBill.electricMoney}</td>
                            </tr>
                            <tr>
                                <td colspan="3">Total Water:</td>
                                <td>${roomBill.waterNumber}</td>
                                <td>${roomBill.waterMoney}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="container-block" style="margin-top: 24px">
                        <table class="table table-bordered table-advance table-hover">
                            <thead>
                            <tr>
                                <th>Roll Name</th>
                                <th>Room name</th>
                                <th>Term</th>
                                <th>Year</th>
                                <th>Amount</th>
                                <th>Status</th>
                                <th>Description</th>
                                <c:if test="${roomBill.status == 'UNPAID'}">
                                    <th></th>
                                </c:if>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>${roomBill.rollName}</td>
                                <td>${roomBill.roomName}</td>
                                <td>${roomBill.term}</td>
                                <td>${roomBill.year}</td>
                                <td>${roomBill.totalAmount}</td>
                                <td>${roomBill.status}</td>
                                <td>${roomBill.description}</td>
                                <c:if test="${roomBill.status == 'UNPAID'}">
                                    <td>
                                        <div class="flex justify-center">
                                            <div class="btn-action" style="width: fit-content;">
                                                <a href="#"
                                                   onclick="if(confirm('Are you sure you want to payment?')) submitDelete(${roomBill.billId});">Payment</a>
                                            </div>
                                        </div>
                                    </td>
                                </c:if>

                            </tr>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
            <div id="personal-usage" style="display: none">
                <div class="container-block" style="margin-top: 24px">
                    <table class="table table-bordered table-advance table-hover">
                        <thead>
                        <tr>
                            <th>Block</th>
                            <th>Month - Year</th>
                            <th>Type</th>
                            <th>Your usage</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${ewUsages}" var="u">
                            <tr>
                                <td rowspan="2">${u.roomName}</td>
                                <td rowspan="2">${u.month} - ${u.year}</td>
                                <td>Electric</td>
                                <td>${u.electricNumber}</td>
                            </tr>
                            <tr>
                                <td>Water</td>
                                <td>${u.waterNumber}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
<script>
    function submitDelete(id) {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>' + '/student/payment-ew',
            data: {
                id: id
            },
            success: function (response) {
                window.location.href = '<%=request.getContextPath()%>' + '/student/EWBedUsages?message=' + response;
            },
        });
    }
    const showBlockUsage = () => {
        $("#block-usage").show();
        $("#personal-usage").hide();
    }
    const hiddenBlockUsage = () => {
        $("#block-usage").hide();
        $("#personal-usage").show();
    }
</script>

</body>
</html>
