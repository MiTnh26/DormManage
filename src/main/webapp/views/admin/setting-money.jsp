<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <h1 class="big-title">Setting money</h1>
        <div>
            <div class="container-block">
                <h3 class="big-title" style="margin: 24px 0">Room money</h3>
                <table class="table table-striped table-bordered table-advance table-hover">
                    <thead>
                    <tr>
                        <th>Money Type</th>
                        <th>Room type</th>
                        <th>Bed total</th>
                        <th>Amount</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${room}" var="r">
                        <tr>
                            <td>${r.moneyType}</td>
                            <td>${r.roomType}</td>
                            <td>${r.bedTotal}</td>
                            <td id="text-money-${r.moneyId}">${r.amount} VND</td>
                            <td id="input-money-${r.moneyId}" style="display: none">
                                <input class="SBB-input" style="padding: 5px" id="value-${r.moneyId}" value="${r.amount.replaceAll(',','')}" type="number">
                            </td>
                            <td>
                                <div class="flex justify-center" id="editMoney-${r.moneyId}">
                                    <div class="btn-action" style="width: fit-content;">
                                        <a href="#" onclick="editMoney(${r.moneyId})">Edit</a>
                                    </div>
                                </div>
                                <div class="flex justify-center"  id="saveMoney-${r.moneyId}" style="display: none">
                                    <div class="btn-action" style="width: fit-content; margin-right: 15px">
                                        <a href="#" onclick="saveMoney(${r.moneyId})">Save</a>
                                    </div>
                                    <div class="btn-action" style="width: fit-content;">
                                        <a href="#" onclick="cancel(${r.moneyId})">Cancel</a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
                <h3 class="big-title" style="margin: 24px 0">Electric and water money</h3>

                <table class="table table-striped table-bordered table-advance table-hover">
                    <thead>
                    <tr>
                        <th>Money Type</th>
                        <th>Amount</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${ew}" var="e">
                        <tr>
                            <td>${e.moneyType}</td>
                            <td id="text-money-${e.moneyId}">${e.amount} VND</td>
                            <td id="input-money-${e.moneyId}" style="display: none">
                                <input class="SBB-input" style="padding: 5px" id="value-${e.moneyId}" value="${e.amount.replaceAll(',','')}" type="number">
                            </td>
                            <td>
                                <div class="flex justify-center" id="editMoney-${e.moneyId}">
                                    <div class="btn-action" style="width: fit-content;">
                                        <a href="#" onclick="editMoney(${e.moneyId})">Edit</a>
                                    </div>
                                </div>
                                <div class="flex justify-center"  id="saveMoney-${e.moneyId}" style="display: none">
                                    <div class="btn-action" style="width: fit-content; margin-right: 15px">
                                        <a href="#" onclick="saveMoney(${e.moneyId})">Save</a>
                                    </div>
                                    <div class="btn-action" style="width: fit-content;">
                                        <a href="#" onclick="cancel(${e.moneyId})">Cancel</a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>
<%@ include file="../footer.jsp" %>
<script>

    function editMoney(id) {
        const textMoney = $("#text-money-" + id);
        const inputMoney = $("#input-money-" + id);
        const editMoney = $("#editMoney-" + id);
        const saveMoney = $("#saveMoney-" + id);
        textMoney.hide();
        inputMoney.show();
        editMoney.hide();
        saveMoney.show();
    }

    function saveMoney(id){
        const url = "<%=request.getContextPath()%>" + "/admin/setting-money";
        $.ajax({
            type: 'POST',
            url: url,
            data: {
                moneyId: id,
                amount: $("#value-" + id).val()
            },
            success: function(response) {
                window.location.href = url;
            },
        });
    }

    function cancel(id) {
        const textMoney = $("#text-money-" + id);
        const inputMoney = $("#input-money-" + id);
        const editMoney = $("#editMoney-" + id);
        const saveMoney = $("#saveMoney-" + id);
        const value = parseInt(textMoney.text().replaceAll(' VND','').replaceAll(',',''));
        $("#value-" + id).val(value)
        textMoney.show();
        inputMoney.hide();
        editMoney.show();
        saveMoney.hide();
    }
</script>
</body>
</html>
