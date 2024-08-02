<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <div>
            <h1 class="big-title">Resident history</h1>
            <c:if test="${domResident.size() > 0}">
                <div class="container-block">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-advance table-hover">
                            <thead>
                            <tr>
                                <th>Student ID</th>
                                <th>Bed information</th>
                                <th>Check-in Date</th>
                                <th>Check-out Date</th>
                                <th>Price</th>
                                <th>Semester</th>
                                <th>Years</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${domResident}" var="resident">
                                <tr>
                                    <td>${resident.studentId}</td>
                                    <td>${resident.bedInformation}</td>
                                    <td>${resident.checkInDate}</td>
                                    <td>${resident.checkOutDate}</td>
                                    <td>${resident.price}</td>
                                    <td>${resident.semester}</td>
                                    <td>${resident.year}</td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>

            <c:if test="${domResident.size() == 0}">
                <%@ include file="no-record.jsp" %>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>
