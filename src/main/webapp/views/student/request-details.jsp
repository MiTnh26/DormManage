<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <div>
            <h1 class="big-title">Request details</h1>

            <div>
                <dl class="dl-horizontal">
                    <dt class="dt">Roll name</dt>
                    <dd class="dd">${request.rollId}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Request type</dt>
                    <dd class="dd">${request.requestType}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Request Detail</dt>
                    <dd class="dd">${request.requestDetail}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Dom</dt>
                    <dd class="dd">${request.domName}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Floor</dt>
                    <dd class="dd">${request.floor}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Room Name</dt>
                    <dd class="dd">${request.roomName}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Bed number</dt>
                    <dd class="dd">${request.bed == 0 ? "N/A" : request.bed}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Semester</dt>
                    <dd class="dd">${request.term}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Create Date</dt>
                    <dd class="dd">${request.createDate.toString().substring(0,10)}</dd>
                </dl>
            </div>
        </div>
        <button onclick="location.href='<%=request.getContextPath()%>/student/request';return false;" class="SAB-back"
                style="margin-top: 24px;">Back to list
        </button>
    </div>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>
