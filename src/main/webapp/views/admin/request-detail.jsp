<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <h1 class="big-title">Request details</h1>
        <div class="container-block">
            <div>
                <dl class="dl-horizontal">
                    <dt class="dt">Student ID</dt>
                    <dd class="dd">HE-178580</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Request type</dt>
                    <dd class="dd">Check-in</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Dorm</dt>
                    <dd class="dd">A</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Floor</dt>
                    <dd class="dd">1</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Term</dt>
                    <dd class="dd">1</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Room</dt>
                    <dd class="dd">N/A</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Bed</dt>
                    <dd class="dd">N/A</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Status</dt>
                    <dd class="dd">WAITING</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Date</dt>
                    <dd class="dd">1/5/2024</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt class="dt">Content</dt>
                    <dd class="dd">Đăng ký phòng mới</dd>
                </dl>
            </div>
        </div>
        <div style=" margin-top: 24px;">
            <div class="flex justify-center">
                <a class="orange-btn" style="margin-right: 15px;" href="#">Approve</a>
                <a class="orange-btn" href="#">Denied</a>
            </div>
        </div>
        <button onclick="location.href='#';return false;" class="SAB-back"
                style="margin-top: 24px;">Back to list
        </button>
    </div>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>
