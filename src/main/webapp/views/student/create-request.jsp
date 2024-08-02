<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <h1 class="big-title">Create request</h1>
        <form action="<%=request.getContextPath()%>/student/create-request" method="post" style="width: 50%;">
            <div class="">
                <div class="flex-1" style="margin-bottom: 24px;">
                    <label class="SBB-input-label no-margin" for="requestType">Request type</label>
                    <div class="my-select-style">
                        <select class="SBB-input" required="" id="requestType" name="requestType"
                                onchange="showDateCheckOut()">
                            <option value="">Chọn yêu cầu</option>
                            <option value="CHECKOUT">Đăng ký check out</option>
                            <option value="OTHER">Sai số điện nước</option>
                        </select>
                    </div>
                </div>
                <div class="flex-1 hidden" id="date-check-out" style="margin-bottom: 24px;">
                    <label class="SBB-input-label no-margin" for="checkOutDate">Date check-out</label>
                    <div class="" style="width: 100%;">
                        <input class="SBB-input" type="date" name="checkOutDate" id="checkOutDate" min="">
                    </div>
                </div>
                <div class="flex-1">
                    <label class="SBB-input-label no-margin">Content</label>
                    <div class="SR-form" style="width: 100%;">
                        <textarea class="SBB-l-content" name="requestDetail"></textarea>
                    </div>
                </div>
                <div style=" margin-top: 24px;">
                    <div class="flex justify-end">
                        <input type="submit" value="Create request" class="orange-btn">
                    </div>
                </div>
            </div>
        </form>
    </div>
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
    <c:if test="${message != null}">
        <div id="myPopup" class="popup" style="display: block">
            <div class="popup-content">
                <span class="close" onclick="closePopup()">&times;</span>
                <p>${message}</p>
            </div>
        </div>
    </c:if>
</div>
<script>
    function showDateCheckOut() {
        const RequestType = document.getElementById("requestType");
        const dateCheckOut = document.getElementById("date-check-out");
        if (RequestType.value === 'CHECKOUT') {
            dateCheckOut.classList.remove("hidden");
            $("#checkOutDate").prop("required", true);
        } else if (!dateCheckOut.classList.contains("hidden")) {
            dateCheckOut.classList.add("hidden");
            $("#checkOutDate").prop("required", false);
        }
    }

    function closePopup() {
        var popup = document.getElementById("myPopup");
        popup.style.display = "none";
    }

    document.addEventListener('DOMContentLoaded', function () {
        const today = new Date().toISOString().split('T')[0];
        document.getElementById('date').setAttribute('min', today);
    });
</script>
<%@ include file="../footer.jsp" %>
</body>
</html>

