<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <h1 class="big-title">View by Dom ${domName}</h1>
    <%--        <form action="/Student/ViewBed/ViewByDom" method="get">--%>
    <%--            <div>--%>
    <%--                <input id="domId" name="domId" type="hidden" value="A">--%>
    <%--                <div class="flex flex-wrap" style="gap: 24px;">--%>
    <%--                    <div class="">--%>
    <%--                        <div class="my-select">--%>
    <%--                            <label class="SAB-label">--%>
    <%--                                Term--%>
    <%--                            </label>--%>
    <%--                            <select class="form-control" id="Term" name="Term"--%>
    <%--                                    style="max-width: 100%;  min-height: 44px;">--%>
    <%--                                <option value="1">1</option>--%>
    <%--                                <option selected="selected" value="2">2</option>--%>
    <%--                                <option value="3">3</option>--%>
    <%--                            </select>--%>
    <%--                        </div>--%>
    <%--                    </div>--%>
    <%--                    <div class="">--%>
    <%--                        <div class="my-select">--%>
    <%--                            <label  class="SAB-label">--%>
    <%--                                Year--%>
    <%--                            </label>--%>
    <%--                            <select class="form-control" id="Year" name="Year"--%>
    <%--                                    style="max-width: 100%;  min-height: 44px;">--%>
    <%--                                <option value="0">All</option>--%>
    <%--                                <option value="2025">2025</option>--%>
    <%--                                <option selected="selected" value="2024">2024</option>--%>
    <%--                                <option value="2023">2023</option>--%>
    <%--                                <option value="2022">2022</option>--%>
    <%--                                <option value="2021">2021</option>--%>
    <%--                                <option value="2020">2020</option>--%>
    <%--                                <option value="2019">2019</option>--%>
    <%--                            </select>--%>
    <%--                        </div>--%>
    <%--                    </div>--%>
    <%--                    <div class="flex items-end">--%>
    <%--                        <button type="submit" style="" class="orange-btn">--%>
    <%--                            Search--%>
    <%--                        </button>--%>
    <%--                    </div>--%>
    <%--                </div>--%>
    <%--            </div>--%>
    <%--        </form>--%>
        <div>
            <h3 style="color: var(--blue-color);" class="text-bold"> Summary: Dom ${domName}</h3>
            <div class="container-block">
                <table class="table table-striped table-bordered table-advance table-hover">
                    <thead>
                    <tr>
                        <th>Floor</th>
                        <th>Free</th>
                        <th>Used</th>
                        <th>Sum</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${bedTotalDto}" var="bed">
                        <tr>
                            <td>${bed.floor}</td>
                            <td>${bed.free}</td>
                            <td>${bed.used}</td>
                            <td>${bed.sum}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th>All</th>
                        <th>${totalFree}</th>
                        <th>${totalUsed}</th>
                        <th>${totalUsed + totalFree}</th>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div style="margin-top: 15px">
            <div class="container-block">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-advance table-hover">
                        <thead>
                        <tr>
                            <th>RoomName</th>
                            <th>Total Bed</th>
                            <th>Free Bed</th>
                            <th>Used Bed</th>
                            <th>Booking Bed</th>
                            <th>Price Per Month</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${bedDetailDto}" var="bed">
                            <tr>
                                <td>${bed.roomName}</td>
                                <td>${bed.totalBed}</td>
                                <td>${bed.freeBed}</td>
                                <td>${bed.usedBed}</td>
                                <td>${bed.bookingBed}</td>
                                <td>${bed.pricePerMonth}</td>
                            </tr>
                        </c:forEach>


                        </tbody>
                        <thead>
                        <tr>
                            <th>All</th>
                            <th>${usedBedDetail + freeBedDetail + bookingBedDetail}</th>
                            <th>${freeBedDetail}</th>
                            <th>${usedBedDetail}</th>
                            <th>${bookingBedDetail}</th>
                            <th></th>
                        </tr>
                        </thead>

                    </table>
                </div>
            </div>
            <button onclick="location.href='<%=request.getContextPath()%>/student/dom';return false;" class="SAB-back"
                    style="margin-top: 24px;">Back to list</button>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
</body>
</html>
