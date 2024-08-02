<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <h1 class="big-title">User manage</h1>
        <div class="container-block">
            <div class="table-responsive">
                <div class="flex justify-end" style="margin: 10px 0">
                    <a class="orange-btn" href="<%=request.getContextPath()%>/admin/user/new">+ New</a>
                </div>
                <table class="table table-striped table-bordered table-advance table-hover">
                    <thead>
                    <tr>
                        <th>Full name</th>
                        <th>Gmail</th>
                        <th>Gender</th>
                        <th>Role</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${usersDtoList}" var="user">
                        <tr>
                            <td>${user.fullName}</td>
                            <td>${user.gmail}</td>
                            <td>${user.gender}</td>
                            <td>${user.role}</td>
                            <td>
                                <div class="flex justify-center">
                                    <div class="btn-action" style="width: fit-content;">
                                        <a onclick="if(confirm('Are you sure you want to delete?')) submitDelete(${user.userId});" class="btn-danger">Delete</a>
                                    </div>
                                </div>
                        </tr>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
<%--            <div class="flex justify-center">--%>
<%--                <div class="pagination-container">--%>
<%--                    <ul class="pagination">--%>
<%--                        <li class="disabled PagedList-skipToFirst"><a>««</a></li>--%>
<%--                        <li class="disabled PagedList-skipToPrevious"><a rel="prev">«</a></li>--%>
<%--                        <li class="active"><a>1</a></li>--%>
<%--                        <li><a href="/Student/News?page=2">2</a></li>--%>
<%--                        <li><a href="/Student/News?page=3">3</a></li>--%>
<%--                        <li class="PagedList-skipToNext"><a href="/Student/News?page=2" rel="next">»</a></li>--%>
<%--                        <li class="PagedList-skipToLast"><a href="/Student/News?page=3">»»</a></li>--%>
<%--                    </ul>--%>
<%--                </div>--%>
<%--            </div>--%>
        </div>
    </div>
</div>
<%@ include file="../footer.jsp" %>
</body>
<script>
    function submitDelete(id) {
        $.ajax({
            type: 'POST',
            url: '<%=request.getContextPath()%>' + '/admin/user/delete',
            data: {
                id: id
            },
            success: function(response) {
                window.location.href =  '<%=request.getContextPath()%>' + '/admin/user';
            },
        });
    }
</script>
</html>