<%@ page import="Entity.Users" %>
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
            <a href="#" class="logo">
                <img src="<%=request.getContextPath()%>/assets/images/logo.png" alt="logo">
            </a>
        </div>
        <h1 class="big-title flex items-center" style="margin: 0;">
            ADMIN
        </h1>
        <div class="campus">
            <p>
                Hòa Lạc
            </p>
        </div>
    </div>
</div>
<div id="sidebar-full" class="sidebar-full">
    <div class="sidebar-top">
        <div class="sidebar-infoUser flex items-center">
            <div class="sidebar-avatar">
                <img src="https://ocd.fpt.edu.vn/Content/images/FrogSleep.png" alt="avatar-user">
            </div>
            <%
                HttpSession sessions = request.getSession();
                Users users = (Users) sessions.getAttribute("admin");
                String title = (String) sessions.getAttribute("title");
            %>
            <div class="">
                <p class="sidebar-name" style="margin-bottom: 8px;"><%= users.getFullName()%></p>
            </div>
        </div>
        <div id="sidebarLinkFull" class="sidebar-link">
            <a href="<%=request.getContextPath()%>/admin/dashboard" class="sidebar-linkItem flex items-center  <%= title.equals("Home") ? "sidebar-linkItem-active sidebar-icon-active" : "" %>">
                <p>Dashboard</p>
            </a>
            <a href="<%=request.getContextPath()%>/admin/dorm" class="sidebar-linkItem flex items-center  <%= title.equals("bed") ? "sidebar-linkItem-active sidebar-icon-active" : "" %>">
                <p>Dorm</p>
            </a>
            <a href="<%=request.getContextPath()%>/admin/setting-money" class="sidebar-linkItem flex items-center  <%= title.equals("money") ? "sidebar-linkItem-active sidebar-icon-active" : "" %>">
                <p>Money Setting</p>
            </a>
            <a href="<%=request.getContextPath()%>/admin/bill" class="sidebar-linkItem flex items-center  <%= title.equals("ew") ? "sidebar-linkItem-active sidebar-icon-active" : "" %>">
                <p>Bill electric and water</p>
            </a>
            <a href="<%=request.getContextPath()%>/admin/user" class="sidebar-linkItem flex items-center  <%= title.equals("user") ? "sidebar-linkItem-active sidebar-icon-active" : "" %>">
                <p>User</p>
            </a>
            <a href="<%=request.getContextPath()%>/admin/student" class="sidebar-linkItem flex items-center  <%= title.equals("student") ? "sidebar-linkItem-active sidebar-icon-active" : "" %>">
                <p>Student</p>
            </a>
            <a href="<%=request.getContextPath()%>/admin/request" class="sidebar-linkItem flex items-center  <%= title.equals("request") ? "sidebar-linkItem-active sidebar-icon-active" : "" %>">
                <p>Request</p>
            </a>
            <a href="<%=request.getContextPath()%>/admin/dom-resident" class="sidebar-linkItem flex items-center  <%= title.equals("domResident") ? "sidebar-linkItem-active sidebar-icon-active" : "" %>">
                <p>Dorm Risedent</p>
            </a>
            <a href="<%=request.getContextPath()%>/admin/news" class="sidebar-linkItem flex items-center <%= title.equals("new") ? "sidebar-linkItem-active sidebar-icon-active" : "" %>">
                <p>News</p>
            </a>
        </div>
    </div>
    <div class="sidebar-bottom">
        <div class="flex justify-center">
            <a class="btn-logout" href="<%=request.getContextPath()%>/home/google-logout">Logout</a>
        </div>
    </div>
</div>