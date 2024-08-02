<%@ page import="Entity.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="my-navbar">
    <div class="navbar-content flex justify-between items-center">
        <div class="flex items-center">
            <div id="navbar-menu-icon" class="hamburger" onclick="handleToggleSidebar()">
                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="24" viewBox="0 0 25 24" fill="none">
                    <line x1="2" y1="2" x2="23" y2="2" stroke="white" stroke-width="4" stroke-linecap="round">
                    </line>
                    <line x1="2" y1="12" x2="23" y2="12" stroke="white" stroke-width="4" stroke-linecap="round">
                    </line>
                    <line x1="2" y1="22" x2="23" y2="22" stroke="white" stroke-width="4" stroke-linecap="round">
                    </line>
                </svg>
            </div>
            <a href="/Student" class="logo">
                <img src="<%=request.getContextPath()%>/assets/images/logo.png" alt="logo">
            </a>
        </div>
        <div class="flex justify-end" style="margin: 10px 0">
            <a class="orange-btn" href="<%=request.getContextPath()%>/views/student/add-balance.jsp">Nạp tiền</a>
        </div>
        <div class="campus">
            <p>
                Hòa Lạc
            </p>
        </div>
    </div>
</div>
<div id="sidebar-full" class="sidebar-full">
    <div class="sidebar-top">
        <%
            HttpSession sessions = request.getSession();
            Student student = (Student) sessions.getAttribute("student");
            String title = (String) sessions.getAttribute("title");
        %>
        <div class="sidebar-infoUser flex">
            <div class="sidebar-avatar">
                <img src="<%= student.getAvatar()%>" alt="avatar-user">
            </div>
            <div class="">
                <p class="sidebar-name" style="margin-bottom: 8px;">
                    <%= student.getFullName() %>
                </p>
                <p class="sidebar-prestigeScore"><span
                        class="sidebar-prestigeScore bold">Balance:</span> <%= student.getBalance()%> VND</p>
                <p class="sidebar-prestigeScore"><span
                        class="sidebar-prestigeScore bold">RollName:</span> <%= student.getRollId() %>
                </p>
            </div>
        </div>
        <div id="sidebarLinkFull" class="sidebar-link">
            <a href="<%=request.getContextPath()%>/student/home" class="sidebar-linkItem flex items-center <%= title.equals("Home") ? "sidebar-linkItem-active sidebar-icon-active" : "" %>">
                <p>Home</p>
            </a>
            <a href="<%=request.getContextPath()%>/student/news" class="sidebar-linkItem flex items-center <%= title.equals("News") ? "sidebar-linkItem-active sidebar-icon-active" : "" %>">
                <p>News</p>
            </a>
            <a href="<%=request.getContextPath()%>/student/choose-room" class="sidebar-linkItem flex items-center  <%= title.equals("BookRoom") ? "sidebar-linkItem-active sidebar-icon-active" : "" %>">
                <p>Booking bed</p>
            </a>
            <a href="<%=request.getContextPath()%>/student/dom" class="sidebar-linkItem flex items-center  <%= title.equals("bed") ? "sidebar-linkItem-active sidebar-icon-active" : "" %>">
                <p>Available beds</p>
            </a>
            <a href="<%=request.getContextPath()%>/student/EWBedUsages" class="sidebar-linkItem flex items-center  <%= title.equals("EW") ? "sidebar-linkItem-active sidebar-icon-active" : "" %>">
                <p>Electricity water usage</p>
            </a>
            <a href="<%=request.getContextPath()%>/student/payment-history" class="sidebar-linkItem flex items-center  <%= title.equals("PaymentHistory") ? "sidebar-linkItem-active sidebar-icon-active" : "" %>">
                <p>Payment history</p>
            </a>
            <a href="<%=request.getContextPath()%>/student/resident-history" class="sidebar-linkItem flex items-center  <%= title.equals("resident") ? "sidebar-linkItem-active sidebar-icon-active" : "" %>">
                <p>Resident history</p>
            </a>
            <a href="<%=request.getContextPath()%>/student/request" class="sidebar-linkItem flex items-center  <%= title.equals("request") ? "sidebar-linkItem-active sidebar-icon-active" : "" %>">
                <p>My request</p>
            </a>
        </div>
    </div>
    <div class="sidebar-bottom">
        <div class="flex justify-center">
            <a class="btn-logout" href="<%=request.getContextPath()%>/home/account-logout">Logout</a>
        </div>
    </div>
</div>



