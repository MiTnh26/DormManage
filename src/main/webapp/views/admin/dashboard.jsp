<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <h1 class="big-title" style="margin: 20px 0">Dashboard</h1>
        <div class="flex justify-around flex-wrap" style="gap: 15px">
            <a href="<%=request.getContextPath()%>/admin/dorm" class="container-block"
               style="min-width: 250px; width: auto">
                <div class="flex justify-center">
                    <h3>Total bed</h3>
                </div>
                <div class="flex text-primary items-center">
                    <img src="<%=request.getContextPath()%>/assets/images/allbed.png" width="80px" height="100px"
                         alt="">
                    <h2>${dto.totalBed}</h2>
                </div>
            </a>
            <a href="<%=request.getContextPath()%>/admin/dorm" class="container-block"
               style="min-width: 250px; width: auto">
                <div class="flex justify-center">
                    <h3>Free bed</h3>
                </div>
                <div class="flex text-primary items-center">
                    <img src="<%=request.getContextPath()%>/assets/images/bed-empty.png" width="80px" height="100px"
                         alt="">
                    <h2>${dto.freeBed}</h2>
                </div>
            </a>
            <a href="<%=request.getContextPath()%>/admin/dorm" class="container-block"
               style="min-width: 250px; width: auto">
                <div class="flex justify-center">
                    <h3>Used bed</h3>
                </div>
                <div class="flex text-primary items-center">
                    <img src="<%=request.getContextPath()%>/assets/images/Bed.png" width="80px" height="100px" alt="">
                    <h2>${dto.usedBed}</h2>
                </div>
            </a>
            <a href="<%=request.getContextPath()%>/admin/student" class="container-block"
               style="min-width: 250px; width: auto">
                <div class="flex justify-center">
                    <h3>Total student</h3>
                </div>
                <div class="flex text-primary items-center">
                    <img src="<%=request.getContextPath()%>/assets/images/student.png" width="80px" height="100px"
                         alt="">
                    <h2>${dto.student}</h2>
                </div>
            </a>
        </div>

        <h5 style="margin: 15px 0">Top 10 room have electric money term</h5>

        <div class="container-block">
            <div class="table-responsive">
                <table class="table table-striped table-bordered table-advance table-hover">
                    <thead>
                    <tr>
                        <th>Room</th>
                        <th>Total student</th>
                        <th>Electric number</th>
                        <th>Water number</th>
                        <th>Term</th>
                        <th>Year</th>
                        <th>Total amount</th>
                        <th>Status</th>
                        <th>Date</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${dto.roomBillDashBoards}" var="r">
                        <tr>
                            <td>${r.roomName}</td>
                            <td>${r.student}</td>
                            <td>${r.electricNumber}</td>
                            <td>${r.waterNumber}</td>
                            <td>${r.term}</td>
                            <td>${r.year}</td>
                            <td>${r.amount} VND</td>
                            <td>${r.status}</td>
                            <td>${r.date}</td>
                            <td>
                                <div class="flex justify-center">
                                    <div class="btn-action" style="width: fit-content;">
                                        <a href="<%=request.getContextPath()%>/admin/room-detail?roomName=${r.roomName}">Detail</a>
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
</body>
</html>
