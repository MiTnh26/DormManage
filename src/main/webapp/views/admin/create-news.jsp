<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <h1 class="big-title">Create request</h1>
        <form action="<%=request.getContextPath()%>/admin/news/create" method="post" style="width: 50%;">
            <h5 class="text-danger">${message}</h5>
            <div class="">
                <div class="flex-1" style="margin-bottom: 24px;">
                    <label class="SBB-input-label no-margin" for="newsTitle">News Title:</label>
                    <div class="SR-form" style="width: 100%;">
                        <input class="SBB-input text-box single-line" type="text" name="newsTitle" id="newsTitle">
                    </div>
                </div>
                <div class="flex-1">
                    <label class="SBB-input-label no-margin">News Detail</label>
                    <div class="SR-form" style="width: 100%;">
                        <textarea class="SBB-l-content" name="newsDetail"></textarea>
                    </div>
                </div>
                <div style=" margin-top: 24px;">
                    <div class="flex justify-end">
                        <input type="submit" value="Create news" class="orange-btn">
                    </div>
                </div>
            </div>
        </form>
    </div>
    <button onclick="location.href='<%=request.getContextPath()%>/admin/user';return false;" class="SAB-back"
            style="margin-top: 24px;">Back to list</button>
    <style>
        /* CSS để ẩn popup ban đầu */
        .popup {
            display: none;
            position: fixed;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5); /* Màu nền đục */
            z-index: 9999; /* Lớp z-index để nằm trên các phần tử khác */
            overflow: auto; /* Cho phép cuộn khi nội dung dài hơn */
        }

        .popup-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            width: 50%;
            text-align: center;
            border: 2px solid #003eff;
            border-radius: 4px;
            color: #003eff;
        }

        .close {
            color: #003eff;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
    <c:if test="${popup != null}">
        <div id="myPopup" class="popup" style="display: block">
            <div class="popup-content">
                <span class="close" onclick="closePopup()">&times;</span>
                <p>${popup}</p>
            </div>
        </div>
    </c:if>
</div>
<%@ include file="../footer.jsp" %>
<script>
    function closePopup() {
        var popup = document.getElementById("myPopup");
        popup.style.display = "none";
    }
</script>
</body>
</html>

