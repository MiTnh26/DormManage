<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <div class="row">
        </div>
        <div class="mt-60">
            <h1 class="big-title"></h1>
            <div id="StudentHome">
                <div class="SHome-left flex-1">
                    <div class="Shome-news-header">News</div>
                    <c:if test="${news.size() > 0}">
                        <div class="Shome-list-news">
                            <div class="Shome-list-news-inner">
                                <c:forEach items="${news}" var="n">
                                    <div class="Shome-news-item flex-1">
                                        <a href="<%=request.getContextPath()%>/student/news-detail?id=${n.newsId}"
                                           class="text-16">${n.newsTitle}</a>
                                        <p class="Shome-news-date">By ${n.author} ${n.createdTime}</p>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                </div>
                <div id="StudentHome-info" class="flex flex-wrap gap-24">
                    <div class="SHome-right flex-1">
                        <div class="SHome-personal-info">
                            Personal information
                        </div>
                        <div class="Shome-info-container">
                            <div class="Shome-info-avatar">
                                <div
                                        style="width: 120px; height: 120px; background-color: var(--orange-color); border-radius: 100%;">
                                    <img src="<%=student.getAvatar()%>"
                                         height="312" width="235" class="img-thumbnail">
                                </div>
                            </div>
                            <div class="Shone-info">
                                <p
                                        style="font-weight: bold; font-size: 18px; line-height: 150%; color: var(--blue-color)">
                                    <%= student.getFullName()%>
                                </p>
                                <p>
                                    <span class="text-gray"> <%=student.getGender()%></span>
                                </p>
                                <p class="text-gray">
                                    <span style="color: var(--blue-color); font-weight: bold;">Bed:</span>
                                    <span>N/A</span>
                                </p>
                                <p class="text-gray"><span
                                        style="color: var(--blue-color); font-weight: bold;">Balance:</span> <%= student.getBalance()%>
                                    VND
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="SHome-right flex-1">
                        <div class="SHome-personal-info">
                            Contact
                        </div>
                        <div class="Shome-info-container">
                            <div class="Shone-info">
                                <a class="text-blue" href="tel:02466805913">
                                    <b>Security room:</b> <span class="text-gray">(024) 6680 5 913</span>
                                </a>
                                <a class="text-blue" href="tel:02466805917">
                                    <b>Health station:</b> <span class="text-gray">(024) 6680 5 917</span>
                                </a>
                                <a class="text-blue" href="tel:02473081313">
                                    <b>Dormitory management:</b> <span class="text-gray">(024) 7308 1313 </span>
                                    <br><i>(Office hours)</i>
                                </a>
                                <a href="mailto:ktx@fpt.edu.vn" class="text-blue">
                                    <b>Email:</b>
                                    <span class="text-gray">ktx@fpt.edu.vn</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <p class="text-green" style="padding-top: 24px; font-size: 12px;">
                Our design display best with 1920*1024 screen. For a better experience, you can resize your screen
                size to 80% or 90% of the original!
            </p>
            <form method="post" action="Student/Home/TryNewVersion">

                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Confirm to try new version</h5>
                            </div>
                            <div class="modal-body">
                                <h3>Try to make it easier to use and faster.</h3>
                                <br>
                                <br>
                                The system may not operate stably.
                                System error please contact IT OCD: <a
                                    href="mailto:duongnt115@fe.edu.vn">Duongnt115@fe.edu.vn</a> for help.
                            </div>
                            <div class="modal-footer">
                                <button onclick="$('#exampleModal').modal('hide');" type="button"
                                        class="btn btn-secondary" data-bs-dismiss="modal">Close
                                </button>
                                <button type="submit" class="btn btn-primary">Try now</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
</body>
</html>
