<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <h1 class="big-title">News</h1>
        <div class="container-block">
            <div class="table-responsive">
                <div class="flex justify-end" style="margin: 10px 0">
                    <a class="orange-btn" href="<%=request.getContextPath()%>/admin/news/create">+ New</a>
                </div>
                <table class="table table-striped table-bordered table-advance table-hover">
                    <thead>
                    <tr>
                        <th>New Title</th>
                        <th>Created Time</th>
                        <th>Author</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${newsDtoList}" var="news">
                        <tr>
                            <td>${news.newsTitle}</td>
                            <td>${news.createdTime.toString().substring(0,19)}</td>
                            <td>${news.author}</td>
                            <td>
                                <div class="flex justify-center">
                                    <div class="btn-action d-flex justify-content-center" style="width: fit-content;">
                                        <form class="col-lg-6" action="<%=request.getContextPath()%>/admin/news/detail"
                                              method="get">
                                            <input type="hidden" name="id" value="${news.newsId}">
                                            <button class="btn-primary">Details</button>
                                        </form>
                                        <form class="col-lg-6" action="<%=request.getContextPath()%>/admin/news/delete"
                                              method="get">
                                            <input type="hidden" name="id" value="${news.newsId}">
                                            <button class="btn-danger">Delete</button>
                                        </form>
                                    </div>
                                </div>
                            </td>
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
                                    href="<%=request.getContextPath()%>/admin/news?page=1">««</a>
                            </li>
                            <c:forEach begin="1" end="${totalPage}" varStatus="i">
                                <li>
                                    <a class="${page == i.count ? 'active' : ''}"
                                       href="<%=request.getContextPath()%>/admin/news?page=${i.count}">${i.count}</a>
                                </li>
                            </c:forEach>
                            <li class="${page == totalPage  ? 'disabled' : ''} PagedList-skipToLast"><a
                                    href="<%=request.getContextPath()%>/admin/news?page=${totalPage}">»»</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>

</body>
</html>
