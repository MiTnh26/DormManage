<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <h1 class="big-title">Request</h1>
        <form action="<%=request.getContextPath()%>/admin/request">
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
                        <th>Student ID</th>
                        <th>Request type</th>
                        <th>Dorm</th>
                        <th>Floor</th>
                        <th>Term</th>
                        <th>Content</th>
                        <th>Status</th>
                        <th>Date</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestList}" var="request">
                        <tr>
                            <td>${request.rollId}</td>
                            <td>${request.requestType}</td>
                            <td>${request.domName}</td>
                            <td>${request.floor}</td>
                            <td>${request.term}</td>
                            <td>
                                <a onclick="handleShowModal(${request.requestId})" class="btn-action"> VIEW
                                </a>
                                <p style="display: none" id="content-${request.requestId}">${request.requestDetail}</p>
                            </td>
                            <td>${request.requestStatus}</td>
                            <td>${request.createDate.toString().substring(0,19)}</td>
                            <c:if test="${request.requestStatus != 'WAITING'}">
                                <td></td>
                            </c:if>

                            <c:if test="${request.requestStatus == 'WAITING'}">
                                <td>
                                    <div class="flex justify-center">
                                        <div class="btn-action" style="width: fit-content;">
                                            <a onclick="if(confirm('Are you sure?')) submitApprove(${request.requestId})">Approved</a>
                                        </div>
                                        <div class="btn-action" style="width: fit-content; margin-left: 30px">
                                            <a onclick="if(confirm('Are you sure?')) submitReject(${request.requestId})">Reject</a>
                                        </div>
                                    </div>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:if test="${totalPage > 1}">
                <div class="flex justify-center">
                    <div class="pagination-container">
                        <ul class="pagination">
                            <li class="${page == 1  ? 'disabled' : ''} PagedList-skipToFirst"><a
                                    href="<%=request.getContextPath()%>/admin/dom-resident?keySearch=${keySearch}&page=1">««</a>
                            </li>
                            <c:forEach begin="1" end="${totalPage}" varStatus="i">
                                <li>
                                    <a class="${page == i.count ? 'active' : ''}"
                                       href="<%=request.getContextPath()%>/admin/dom-resident?keySearch=${keySearch}&page=${i.count}">${i.count}</a>
                                </li>
                            </c:forEach>
                            <li class="${page == totalPage  ? 'disabled' : ''} PagedList-skipToLast"><a
                                    href="<%=request.getContextPath()%>/admin/dom-resident?keySearch=${keySearch}&page=${totalPage}">»»</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </c:if>

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
            <div id="content-modal" class="ew-modal-container hidden">
                <div class="modal-ew relative">
                    <div class="close-Modal" onclick="handleCloseModal()">
                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 14 14" fill="none">
                            <path
                                    d="M14 1.41L12.59 0L7 5.59L1.41 0L0 1.41L5.59 7L0 12.59L1.41 14L7 8.41L12.59 14L14 12.59L8.41 7L14 1.41Z"
                                    fill="white"></path>
                        </svg>
                    </div>
                    <div class="flex justify-center items-center">
                        <span id="title-request"></span>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        function submitApprove(id) {
            $.ajax({
                type: 'get',
                url: '<%=request.getContextPath()%>/admin/request/approved?id=' + id,
                data: {
                    id: id
                },
                success: function (response) {
                    window.location.href = '<%=request.getContextPath()%>/admin/request';
                },
            });
        }

        function submitReject(id) {
            $.ajax({
                type: 'get',
                url: '<%=request.getContextPath()%>/admin/request/reject?id=' + id,
                data: {
                    id: id
                },
                success: function (response) {
                    window.location.href = '<%=request.getContextPath()%>' + '/admin/request';
                },
            });
        }

        const EWModalTag = document.getElementById("content-modal");
        const button = document.getElementById("btn-view");
        const titleRequest = $("#title-request");


        // Thêm sự kiện click
        // button.addEventListener("click", function () {
        //     handleShowModal();
        // });

        const handleShowModal = (id) => {
            EWModalTag.style.display = "flex";
            if (EWModalTag.classList.contains("hidden")) {
                EWModalTag.classList.remove("hidden")
            }
            const content = $("#content-" + id);
            const text = content.text();
            titleRequest.empty();
            titleRequest.append(text);
        };

        const handleCloseModal = () => {
            EWModalTag.style.display = "none";
        };
    </script>
    <%@ include file="../footer.jsp" %>
</body>
</html>
