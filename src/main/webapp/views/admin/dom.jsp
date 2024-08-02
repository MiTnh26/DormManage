<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <div>
            <h1 class="big-title">Available beds</h1>
            <div class="container-block" >
                <h3 style="color: var(--blue-color);" class="text-bold"> Summary: Dom in ${semester}</h3>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-advance table-hover">
                        <thead>
                        <tr>
                            <th>DomName</th>
                            <th>DomID</th>
                            <th>TotalBed</th>
                            <th>UsedBed</th>
                            <th>FreeBed</th>
                            <th>Gender</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${domTotalDto}" var="dom">
                            <tr>
                                <td>${dom.domName}</td>
                                <td>${dom.domId}</td>
                                <td>${dom.totalBed}</td>
                                <td>${dom.usedBed}</td>
                                <td>${dom.freeBed}</td>
                                <td>${dom.domGender}</td>
                                <td>
                                    <div class="flex justify-center">
                                        <div class="btn-action" style="width: fit-content;">
                                            <a
                                                    href="<%=request.getContextPath()%>/admin/dom-detail?domId=${dom.domId}">Detail</a>
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
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>
