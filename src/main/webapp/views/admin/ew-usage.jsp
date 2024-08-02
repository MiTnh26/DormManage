<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <h1 class="big-title">Room name detail: ${roomName}</h1>
        <div class="flex justify-end" style="margin: 10px 0">
            <button id="btn-EW-Usage" class="orange-btn">+ EW usage</button>
        </div>
        <div class="container-block">
            <c:if test="${usages.size() > 0}">
                <div class="table-responsive" style="margin: 24px 0">
                    <table class="table table-bordered table-advance ">
                        <thead>
                        <tr>
                            <th>Block</th>
                            <th>Month - Year</th>
                            <th>Type</th>
                            <th>Your usage</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${usages}" var="u">
                            <tr>
                                <td rowspan="2">${roomName}</td>
                                <td rowspan="2">${u.month} - ${u.year}</td>
                                <td>Electric</td>
                                <td>${u.electricNumber}</td>
                            </tr>
                            <tr>
                                <td>Water</td>
                                <td>${u.waterNumber}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>

            <c:if test="${usages.size() == 0}">
                <div class="flex flex-col items-center" style="margin-top: 24px;">
                    <img class="flex justify-center" style="max-width: 50%; height: auto; min-width: 300px;"
                         src="https://ocd.fpt.edu.vn/Content/images/FrogFind.png" alt="Alternate Text">
                    <p class="no-record-found">No record found!</p>
                </div>
            </c:if>

        </div>
        <button onclick="history.back();return false;" class="SAB-back"
                style="margin-top: 24px;">Back to list
        </button>
    </div>
    <style>
        .ew-modal-container {
            position: fixed;
            z-index: 2000;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: rgba(30, 30, 30, 0.8);
            justify-content: center;
            align-items: center;
        }

        .modal-ew {
            width: 50%;
            padding: 60px 90px;
            background: #ffffff;
            border-radius: 13px;
        }

        .modal-title {
            font-weight: bold;
            font-size: 36px;
            line-height: 120%;
            text-align: center;
            color: var(--blue-color);
            margin-top: 16px;
            margin-bottom: 24px;
        }

        .close-Modal {
            position: absolute;
            top: -20px;
            right: -20px;
            background: #0803a2;
            box-shadow: 0px 12px 32px rgba(0, 0, 0, 0.09);
            border-radius: 100%;
            width: 40px;
            height: 40px;
            cursor: pointer;
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
    <form id="EWForm" action="<%=request.getContextPath()%>/admin/room-detail/ew-usage" method="post">
        <div id="EW-Modal" class="ew-modal-container hidden">
            <div class="modal-ew relative">
                <div class="close-Modal" onclick="handleCloseModal()">
                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 14 14" fill="none">
                        <path
                                d="M14 1.41L12.59 0L7 5.59L1.41 0L0 1.41L5.59 7L0 12.59L1.41 14L7 8.41L12.59 14L14 12.59L8.41 7L14 1.41Z"
                                fill="white"></path>
                    </svg>
                </div>
                <p class="modal-title">Create Elecric Water Usage</p>
                <div>
                    <h4 class="text-danger">${message}</h4>
                    <div class="relative">
                        <input type="hidden" name="roomName" value="${roomName}"/>
                        <label for="month" class="SBB-input-label no-margin">Month:</label>
                        <% int monthCheck = LocalDate.now().getMonthValue();%>
                        <select class="SBB-input" id="year" name="year" required>
                            <c:forEach begin="<%=LocalDate.now().getYear() - 1%>" end="<%=LocalDate.now().getYear()%>" var="y">
                                <option value="${y}" selected=" ${monthCheck == 1 && y == LocalDate.now().getYear() - 1}">Year: ${y}</option>
                            </c:forEach>
                        </select>
                        <span class="field-validation-valid text-danger" data-valmsg-for="Floor"
                              data-valmsg-replace="true"></span>
                    </div>
                    <div class="relative">
                        <input type="hidden" name="roomName" value="${roomName}"/>
                        <input type="hidden" name="year" value="<%=LocalDate.now().getYear()%>"/>
                        <label for="month" class="SBB-input-label no-margin">Month:</label>
                        <select class="SBB-input" id="month" name="month" required>
                            <c:forEach begin="1" end="12" var="m">
                                <option value="${m}">Month: ${m}</option>
                            </c:forEach>
                        </select>
                        <span class="field-validation-valid text-danger" data-valmsg-for="Floor"
                              data-valmsg-replace="true"></span>
                    </div>
                    <div class="relative">
                        <label for="month" class="SBB-input-label no-margin">Electric number:</label>
                        <input class="SBB-input text-box single-line" id="electric" name="electric" type="number"
                               required="">
                    </div>
                    <div class="relative">
                        <label for="month" class="SBB-input-label no-margin">Water number:</label>
                        <input class="SBB-input text-box single-line" id="water" name="water" type="number" required="">
                    </div>
                    <div class="flex justify-center" style="margin-top: 24px">
                        <input type="submit" class="orange-btn" value="Add"/>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<%@ include file="../footer.jsp" %>
<script>
    const EWModalTag = document.getElementById("EW-Modal");
    var button = document.getElementById("btn-EW-Usage");


    // Thêm sự kiện click
    button.addEventListener("click", function () {
        handleShowModal();
    });

    const handleShowModal = () => {
        EWModalTag.style.display = "flex";
        if (EWModalTag.classList.contains("hidden")) {
            EWModalTag.classList.remove("hidden")
        }
    };

    const handleCloseModal = () => {
        EWModalTag.style.display = "none";
    };

    if (${message != null}) {
        handleShowModal();
    }
</script>
</body>
</html>
