<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <h1 class="big-title">Dorm Risedent</h1>
        <form action="<%=request.getContextPath()%>/admin/dom-resident">
            <div class="SBB-layout" style="margin-bottom: 24px;">
                <div class="flex items-center justify-between">
                    <div class="flex-1" style="margin-right: 10px;">
                        <input class="SBB-input text-box single-line" placeholder="Search..." id="keySearch"
                               name="keySearch"
                               type="text" value="${keySearch}">
                        <span class="field-validation-valid text-danger" data-valmsg-for="Note"
                              data-valmsg-replace="true"></span>
                    </div>
                    <div>
                        <input type="submit" value="Search" class="orange-btn">
                    </div>
                </div>
            </div>
        </form>
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
                        <th>Description</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${domResidentList}" var="r">
                        <tr>
                            <td>${r.studentId}</td>
                            <td>${r.bedInformation}</td>
                            <td>${r.checkInDate}</td>
                            <td>${r.checkOutDate}</td>
                            <td>${r.price}</td>
                            <td>${r.semester}</td>
                            <td>${r.year}</td>
                            <td>Keep current bed</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:if test="${totalPage > 1}">
                <div class="flex justify-center">
                    <div class="pagination-container">
                        <ul class="pagination">
                            <li class="${page == 1  ? 'disabled' : ''} PagedList-skipToFirst"><a
                                    href="<%=request.getContextPath()%>/admin/dom-resident?keySearch=${keySearch}&page=1">««</a>
                            </li>
                            <c:forEach begin="1" end="${totalPage}" varStatus="i">
                                <li>
                                    <a class="${page == i.count ? 'active' : ''}"
                                       href="<%=request.getContextPath()%>/admin/dom-resident?keySearch=${keySearch}&page=${i.count}">${i.count}</a>
                                </li>
                            </c:forEach>
                            <li class="${page == totalPage  ? 'disabled' : ''} PagedList-skipToLast"><a
                                    href="<%=request.getContextPath()%>/admin/dom-resident?keySearch=${keySearch}&page=${totalPage}">»»</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>
