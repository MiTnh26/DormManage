<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>

<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <div>
            <h1 class="big-title">Detail</h1>
            <div class="SND-container">
                <div class="SND-title">
                    ${news.newsTitle}
                </div>
                <div class="SND-date">
                    ${news.createdTime.toString().substring(0,19)}
                </div>
                <div>
                    ${news.newsDetail}
                </div>
            </div>
        </div>
        <p class="SN-back">
            <a onclick="history.back()" class="btn-back">Back to list</a>
        </p>
    </div>
</div>

<%@ include file="../footer.jsp" %>
</body>
</html>
