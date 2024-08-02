<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <h1 class="big-title">Student</h1>
        <form action="<%=request.getContextPath()%>/admin/student">
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
                        <th>RollNumber</th>
                        <th>Full name</th>
                        <th>Email</th>
                        <th>Gender</th>
                        <th>Balance</th>
                        <th>Status</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${studentList}" var="student">
                        <tr>
                            <td>${student.rollId}</td>
                            <td>${student.fullName}</td>
                            <td>${student.gmail}</td>
                            <td>${student.gender}</td>
                            <td>${student.balance}</td>
                            <td>${student.studentStatus.name()}</td>
                            <td>
                                <div class="flex justify-center">
                                    <div class="btn-action" style="width: fit-content;">
                                        <form class="chang" action="<%=request.getContextPath()%>/admin/student/status"
                                              method="post" onsubmit="return confirmSubmit(this);">
                                            <input type="hidden" name="rollId" value="${student.rollId}"/>
                                            <c:if test="${student.studentStatus == 'BANNED'}">
                                                <button type="submit" class="btn-danger rounded">ACTIVE</button>
                                            </c:if>
                                            <c:if test="${student.studentStatus != 'BANNED'}">
                                                <button type="submit" class="btn-primary rounded">BANNED</button>
                                            </c:if>
                                        </form>
                                    </div>
                                </div>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <c:if test="${totalPage > 1}">
            <div class="flex justify-center">
                <div class="pagination-container">
                    <ul class="pagination">
                        <li class="${page == 1  ? 'disabled' : ''} PagedList-skipToFirst"><a
                                href="<%=request.getContextPath()%>/admin/student?keySearch=${keySearch}&page=1">««</a>
                        </li>
                        <c:forEach begin="1" end="${totalPage}" varStatus="i">
                            <li>
                                <a class="${page == i.count ? 'active' : ''}"
                                   href="<%=request.getContextPath()%>/admin/student?keySearch=${keySearch}&page=${i.count}">${i.count}</a>
                            </li>
                        </c:forEach>
                        <li class="${page == totalPage  ? 'disabled' : ''} PagedList-skipToLast"><a
                                href="<%=request.getContextPath()%>/admin/student?keySearch=${keySearch}&page=${totalPage}">»»</a>
                        </li>
                    </ul>
                </div>
            </div>
        </c:if>
    </div>
</div>
<%@ include file="../footer.jsp" %>
<script>
    function confirmSubmit(form) {
        return confirm("Are you sure you want to change the status of this student?");
    }
</script>
</body>

</html>
