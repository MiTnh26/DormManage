<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="header.jsp" %>
<body>
<div class="error-container">
  <img class="error-img" src="<%=request.getContextPath()%>/assets/images/FrogFixBug.png" alt="FrogFixBug">
  <p class="error-text">Sorry, something went wrong!</p>
  <a onclick="history.back()">Back</a>
  <a href="<%=request.getContextPath()%>/index.jsp">Home</a>
</div>
</body>
</html>
