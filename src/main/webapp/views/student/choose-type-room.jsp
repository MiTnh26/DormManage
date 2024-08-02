<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <h1 class="big-title">Choose Type Room</h1>

        <c:if test="${roomTypes == null}">
            <div style="margin-top: 25px">
                <h4>Your current accommodation in dormitory</h4>
            </div>
        </c:if>

        <c:if test="${roomTypes != null}">
            <form action="<%=request.getContextPath()%>/student/book-room" method="get">
                <div class="">
                    <div class="flex-1">
                        <label class="SBB-input-label no-margin" for="roomTypeName">Room type</label>
                        <div class="my-select-style">
                            <select class="SBB-input" id="roomTypeName" name="roomTypeName" onchange="getAmount()">
                                <c:forEach items="${roomTypes}" var="room">
                                    <option value="${room.key}">${room.bed} beds</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="flex-1" style="margin-top: 24px;">
                        <label class="SBB-input-label no-margin">Price/Bed/Semester</label>
                        <div class="SBB-input">
                            <span id="BedPrice">${roomTypes.get(0).amount}</span> VND
                            <input name="roomAmount" id="roomAmount" type="hidden"
                                   value="${roomTypes.get(0).amount.replaceAll(',','')}"/>
                        </div>
                    </div>
                    <div style="margin-top: 24px;">
                        <div class="">
                            <input type="submit" value="Next" class="orange-btn">
                        </div>
                    </div>
                </div>
            </form>
        </c:if>

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
<%@ include file="../footer.jsp" %>
<script>
    function getAmount() {
        $.get("<%=request.getContextPath()%>" + "/student/get-amount?roomType=" + $("#roomTypeName").val(),
            data => {
                $("#BedPrice").text(data);
                $("#roomAmount").val(data.replaceAll(',', ''));
            }
        )
    }
    function closePopup() {
        var popup = document.getElementById("myPopup");
        popup.style.display = "none";
    }
</script>
</body>
</html>
