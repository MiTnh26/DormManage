<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="../header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>

<div id="content-body" class="content-body pl-328">
    <div class="container-fluid my-container">
        <div>
            <h1 class="big-title">News</h1>
<%--            <form action="#" method="get">--%>
<%--                <div class="flex flex-wrap SN-search-news" style="gap: 8px;">--%>
<%--                    <div class="flex-1 relative">--%>
<%--                        <div class="SN-search-icon">--%>
<%--                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"--%>
<%--                                 fill="none">--%>
<%--                                <path d="M13 6.5C13 7.93437 12.5344 9.25938 11.75 10.3344L15.7063 14.2937C16.0969 14.6844 16.0969 15.3188 15.7063 15.7094C15.3156 16.1 14.6812 16.1 14.2906 15.7094L10.3344 11.75C9.25938 12.5375 7.93437 13 6.5 13C2.90937 13 0 10.0906 0 6.5C0 2.90937 2.90937 0 6.5 0C10.0906 0 13 2.90937 13 6.5ZM6.5 11C8.98438 11 11 8.98438 11 6.5C11 4.01562 8.98438 2 6.5 2C4.01562 2 2 4.01562 2 6.5C2 8.98438 4.01562 11 6.5 11Z"--%>
<%--                                      fill="#034EA2"></path>--%>
<%--                            </svg>--%>
<%--                        </div>--%>
<%--                        <input class="SN-search-input" id="SearchString" name="SearchString"--%>
<%--                               placeholder="Type to search..." type="text" value="">--%>
<%--                    </div>--%>
<%--                    <input type="submit" value="Search" class="orange-btn">--%>
<%--                </div>--%>
<%--            </form>--%>
            <div class="flex flex-wrap SN-news-container">

                <c:forEach items="${news}" var="n">
                    <a href="<%=request.getContextPath()%>\student\news-detail?id=${n.newsId}" class="SN-news flex-1">
                        <div class="SN-date">
                            ${n.createdTime.toString().substring(0,17)}
                        </div>
                        <div class="SN-news-title">
                            ${n.newsTitle}
                        </div>
                    </a>
                </c:forEach>
            </div>
            <c:if test="${totalPage > 1}">
                <div class="flex justify-center">
                    <div class="pagination-container">
                        <ul class="pagination">
                            <li class="${page == 1  ? 'disabled' : ''} PagedList-skipToFirst"><a
                                    href="<%=request.getContextPath()%>/student/news?page=1">««</a>
                            </li>
                            <c:forEach begin="1" end="${totalPage}" varStatus="i">
                                <li>
                                    <a class="${page == i.count ? 'active' : ''}"
                                       href="<%=request.getContextPath()%>/student/news?page=${i.count}">${i.count}</a>
                                </li>
                            </c:forEach>
                            <li class="${page == totalPage  ? 'disabled' : ''} PagedList-skipToLast"><a
                                    href="<%=request.getContextPath()%>/student/news?page=${totalPage}">»»</a>
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
