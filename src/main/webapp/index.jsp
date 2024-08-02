<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 9/12/2023
  Time: 4:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>KTX</title>
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/assets/images/favicon.png">
    <style>
        <%@ include file="assets/css/index.css" %>
    </style>
</head>
<body>
<div id="myLanding" class="relative">
    <div class="landing-header">
        <a href="/" class="landing-logo">
            <img src="assets/images/logo.png" alt="logo">
        </a>
        <div class="flex items-center landing-action">
            <div id="btnLogin" class="landing-btn-login">
                Login
            </div>
        </div>
    </div>
    <div class="my-slide">
        <div class="slideshow-container">
            <div class="mySlides myFade" style="display: block;">
                <div class="text-slider">
                    <div class="flex justify-center">
                        <p class="text_KTX">
                            KTX ĐẠI HỌC FPT
                        </p>
                    </div>
                    <p class="text2_KTX">
                        Không gian sống xanh
                    </p>
                </div>
                <img src="assets/images/bg1.jpg">
            </div>
            <div class="mySlides myFade" style="display: none;">
                <div class="text-slider">
                    <div class="flex justify-center">
                        <p class="text_KTX">
                            KTX ĐẠI HỌC FPT
                        </p>
                    </div>
                    <p class="text2_KTX">
                        Kiến trúc hiện đại
                    </p>
                </div>
                <img src="assets/images/bg2.jpg">
            </div>
            <div class="mySlides myFade" style="display: none;">
                <div class="text-slider">
                    <div class="flex justify-center">
                        <p class="text_KTX">
                            KTX ĐẠI HỌC FPT
                        </p>
                    </div>
                    <p class="text2_KTX">
                        Đảm bảo an ninh
                    </p>
                </div>
                <img src="assets/images/bg3.jpg">
            </div>
            <div class="mySlides myFade" style="display: none;">
                <div class="text-slider">
                    <div class="flex justify-center">
                        <p class="text_KTX">
                            KTX ĐẠI HỌC FPT
                        </p>
                    </div>
                    <p class="text2_KTX">
                        Đầy đủ điện nghi
                    </p>
                </div>
                <img src="assets/images/bg4.jpg">
            </div>
            <div class="mySlides myFade 3" style="display: none;">
                <div class="text-slider">
                    <div class="flex justify-center">
                        <p class="text_KTX">
                            KTX ĐẠI HỌC FPT
                        </p>
                    </div>
                    <p class="text2_KTX">
                        Văn minh lịch sự
                    </p>
                </div>
                <img src="assets/images/bg5.jpg">
            </div>
            <a class="prev flex justify-center items-center" onclick="plusSlides(-1)">
                <svg xmlns="http://www.w3.org/2000/svg" width="23" height="38" viewBox="0 0 23 38" fill="none">
                    <path
                            d="M22.2188 33.4473L7.77208 19.0007L22.2188 4.55399L18.3321 0.66732L-0.00124771 19.0007L18.3321 37.334L22.2188 33.4473Z"
                            fill="white"></path>
                </svg>
            </a>
            <a class="next flex justify-center items-center" onclick="plusSlides(1)">
                <svg xmlns="http://www.w3.org/2000/svg" width="23" height="38" viewBox="0 0 23 38" fill="none">
                    <path
                            d="M0.78125 4.55268L15.2279 18.9993L0.78125 33.446L4.66792 37.3327L23.0012 18.9993L4.66792 0.666016L0.78125 4.55268Z"
                            fill="#FFFDFD"></path>
                </svg>
            </a>
        </div>
        <br>
    </div>
    <div class="layout-center">
        <div class="lading-container">
            <div class="lading-intro">
                <div class="flex justify-between">
                    <div>
                        <p class="intro-title">Kênh thông tin</p>
                        <p class="intro-title2"> Ký túc xá Đại Học FPT</p>
                    </div>
                </div>
            </div>
            <div class="landing-list-navigate">
                <a href="#infoKTX" class="intro-navigate">
                    <p class="intro-navigate-title">Thông tin KTX Đại học FPT</p>
                    <p class="intro-navigate-text">
                        Thông tin
                        <span>
                                <svg xmlns="http://www.w3.org/2000/svg" width="22" height="16" viewBox="0 0 22 16"
                                     fill="none">
                                    <path d="M13.6667 1L21 8M21 8L13.6667 15M21 8H1" stroke="white" stroke-width="2"
                                          stroke-linecap="round" stroke-linejoin="round"></path>
                                </svg>
                            </span>
                    </p>
                </a>
                <div class="intro-navigate" onclick="handleShowModal('booking')" style="cursor: pointer;">
                    <p class="intro-navigate-title">Đăng ký sử dụng KTX</p>
                    <p class="intro-navigate-text">
                        Đăng ký
                        <span>
                                <svg xmlns="http://www.w3.org/2000/svg" width="22" height="16" viewBox="0 0 22 16"
                                     fill="none">
                                    <path d="M13.6667 1L21 8M21 8L13.6667 15M21 8H1" stroke="white" stroke-width="2"
                                          stroke-linecap="round" stroke-linejoin="round"></path>
                                </svg>
                            </span>
                    </p>
                </div>
                <a href="#QA" class="intro-navigate">
                    <p class="intro-navigate-title">Các câu hỏi thường gặp</p>
                    <p class="intro-navigate-text">
                        FAQ
                        <span>
                                <svg xmlns="http://www.w3.org/2000/svg" width="22" height="16" viewBox="0 0 22 16"
                                     fill="none">
                                    <path d="M13.6667 1L21 8M21 8L13.6667 15M21 8H1" stroke="white" stroke-width="2"
                                          stroke-linecap="round" stroke-linejoin="round"></path>
                                </svg>
                            </span>
                    </p>
                </a>
            </div>
            <section id="infoKTX" class="info-KTX">
                <div class="relative">
                    <div class="landing-line"></div>
                    <div class="flex justify-center">
                        <p class="info-KTX-title">Thông tin Ký túc xá Đại Học FPT</p>
                    </div>
                </div>
                <div class="info-1-layout">
                    <div class="info-1-text flex-1">
                        <p style="line-height: 1.8;">
                             Trường Đại học FPT là một trong những ngôi trường nổi tiếng đào tạo đa ngành, với chất
                            lượng đào
                            tạo đạt chuẩn quốc tế. Trường không chỉ quan tâm đến chất lượng đào tạo, công tác tuyển
                            sinh mà còn chăm
                            lo cho đời sống sinh viên.
                            <br>
                             Bằng việc đầu tư, xây dựng khu <b style="color: #F36F21;">Ký túc xá</b> xịn sò. Đầy đủ
                            trang thiết
                            bị cần thiết, không gian
                            thoáng mát,
                            sạch sẽ.
                            Để đáp ứng nhu cầu và tạo không gian học tập, sinh hoạt thoải mái nhất cho sinh viên. <b
                                style="color: #F36F21;">KTX</b>
                            cũng được xem như ngôi nhà thứ 2 của nhiều sinh viên.
                        </p>
                    </div>
                    <img class="landing-info-img" src="assets/images/dom1.png" alt="img-1">
                </div>
                <div class="info-2-layout">
                    <img class="landing-info-img-2" src="assets/images/dom2.png" alt="img-2">
                    <div class="info-1-text flex-1">
                        <div style="line-height: 1.8;">
                            <p style="font-weight: bold; color: #F36F21;">
                                 Ký túc xá của trường Đại học FPT là chỗ ở dành riêng
                                cho
                                sinh
                                viên của Đại học FPT.
                            </p>
                             Hiện nay, một vấn đề
                            các bạn tân sinh viên sau khi biết kết quả trúng tuyển Đại học. Đó là tìm kiếm cho mình
                            một chỗ ở phù
                            hợp, vừa tiết kiệm vừa đảm bảo an ninh, môi trường học tập. Không chỉ các tân sinh viên
                            mà các bạn sinh
                            viên các khóa trước hầu hết cũng đều mong muốn ở tại <b style="color: #F36F21;">KTX</b>
                            trường để thuận
                            lợi cho việc di chuyển. Và để
                            tiết kiệm chi phí, có một trường trường để học tập và sinh hoạt.
                        </div>
                    </div>
                </div>
                <div>
                    <img class="landing-info-img-3" src="assets/images/dom3.png" alt="img-3">
                    <div style="margin-top: 24px; line-height: 1.8; text-align: justify;">
                        <p style="font-weight: bold; color: #F36F21; margin-bottom: 8px;">
                             Ký túc xá trường Đại học FPT được xây dựng với thiết kế hiện đại, thoáng mát và đầy đủ
                            tiện nghi.
                        </p>
                         Khu <b style="color: #F36F21;">KTX</b> gồm các tòa nhà. Mỗi tòa <b
                            style="color: #F36F21;">KTX</b>
                        có các tầng rộng rãi, sạch sẽ, có cả wifi, máy bán nước tự động, máy giặt sấy tự động...
                        Xung quanh còn là cây cối xanh mướt trong lành, dễ chịu, thoáng mát. Phòng ở được thiết kế
                        hiện đại, không
                        gian thoải mái, thiết kế phù hợp cho từng loại phòng
                        3-4-6-8 người. Mỗi phòng sẽ được trang bị các thiết bị cần thiết, đầy đủ phục vụ cho những
                        nhu cầu thiết
                        yếu của sinh viên như giường tầng, bàn học, giá phơi quần áo, bình nóng lạnh, điều hòa, tủ
                        để giày,
                        nhà vệ sinh riêng cho mỗi phòng… giúp sinh viên an tâm học tập trong quãng thời gian gắn bó
                        với đại học
                        FPT, đem đến cho sinh viên cảm giác thoải mái tiện nghi như ở nhà.
                    </div>
                </div>
            </section>
            <section id="QA" class="QA-layout">
                <div class="relative">
                    <div class="landing-line"></div>
                    <div class="flex justify-center">
                        <p class="QA-title">FAQ</p>
                    </div>
                </div>
                <div id="QA-content" class="QA-content">
                    <div>
                        <div id="QA-1" class="QA-item" onclick="showQA(0)">
                            <p>1. Khi ở KTX cần lưu ý điều gì?</p>
                        </div>
                        <div id="Des-QA-1" class="QA-item-des hidden">
                            <p style="margin-bottom: 8px;"><b> Ký túc xá có một số điều cần lưu ý khi ở như sau:</b>
                            </p>
                            <ul class="Des-QA-list-item">
                                <li>Không được nuôi vật nuôi, thú cưng (chó, mèo,...).</li>
                                <li>Không được uống rượu, bia, chơi cờ bạc, sử dụng các chất kích thích và chất cấm.
                                </li>
                                <li>Không được nấu ăn trong ký túc xá.</li>
                                <li>Không được đưa người lạ không ở trong ký túc xá vào phòng sau giờ giới nghiêm.
                                </li>
                                <li>Giờ giới nghiêm trong ký túc xá là sau 10 giờ 30 phút tối.</li>
                                <li>Giữ gìn vệ sinh chung và đổ rác trước 9 giờ sáng.</li>
                            </ul>
                            <b style="margin-top: 4px;">
                                Tất cả các lỗi vi phạm đều bị trừ dựa trên điểm uy tín dựa trên mức độ lỗi
                                vi phạm.
                            </b>
                        </div>
                    </div>
                    <div>
                        <div id="QA-2" class="QA-item" onclick="showQA(1)">
                            <p>2. Điểm uy tín là gì?</p>
                        </div>
                        <div id="Des-QA-2" class="QA-item-des hidden">
                            <p style="margin-bottom: 18px; font-weight: bold;">
                                Điểm uy tín (Credibility in FPT Dormitory - CFD score) là một trong những yếu tố để
                                tạo ra môi trường
                                KTX
                                văn minh và lành mạnh hơn
                            </p>
                            <ul class=" Des-QA-list-item">
                                <li>
                                    Điểm uy tín là tiêu chí để đánh giá ý thức của sinh viên khi sử dụng dịch vụ ký
                                    túc xá.
                                </li>
                                <li>
                                    Điểm uy tín thay đổi dựa theo những hành vi, hoạt động và sự đóng góp của sinh
                                    viên trong suốt thời
                                    gian ở ký túc xá.
                                </li>
                                <li>
                                    Điểm uy tín sẽ được tăng, giảm tương ứng theo các quy định đã được đề ra trong
                                    nội quy KTX.
                                </li>
                                <li>
                                    Điểm uy tín là một trong những tiêu chí được dùng để xét duyệt xem sinh viên có
                                    được sử dụng ký túc
                                    xá trong kỳ hay không.
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="">
                        <div id="QA-3" class="QA-item" onclick="showQA(2)">
                            <p>3. Làm thế nào để gửi yêu cầu tới Ban Quản lý KTX?</p>
                        </div>
                        <div id="Des-QA-3" class="QA-item-des hidden">
                            <p>
                                Bước 1: Vào chức năng <b>My request</b>.
                            </p>
                            <p>
                                Bước 2: Bấm vào nút <b>Create new request</b> -&gt; Chọn <b>loại yêu cầu (Type
                                request)</b> thích hợp.
                            </p>
                            <p>
                                Bước 3: Điền nội dung của yêu cầu ở phần <b>Content</b>.
                            </p>
                            <p>
                                Bước 4: Bấm vào nút <b>Create request</b>.
                            </p>
                        </div>
                    </div>
                    <div>
                        <div id="QA-4" class="QA-item" onclick="showQA(3)">
                            <p>4. Làm thế nào để báo cáo sửa chữa đồ dùng trong phòng?</p>
                        </div>
                        <div id="Des-QA-4" class="QA-item-des hidden">
                            <p>
                                Bước 1: Vào chức năng <b>My request</b>
                            </p>
                            <p>
                                Bước 2: Bấm vào nút <b>Create new request</b> -&gt; Chọn <b>Báo cáo vấn đề kỹ
                                thuật</b> ở mục <b>
                                Type
                                request
                            </b>
                            </p>
                            <p> Bước 3: Hệ thống sẽ dẫn tới trang https://cim.fpt.edu.vn/</p>
                            <p> Bước 4: Điền những thông tin cần thiết và gửi ảnh tình trạng thiết bị (trên hệ thống
                                CIM)</p>
                            <p> Bước 5: Bấm vào nút <b>Create</b> (trên hệ thống CIM)</p>
                            <p></p>
                        </div>
                    </div>
                    <div>
                        <div id="QA-5" class="QA-item" onclick="showQA(4)">
                            <p>5. Thông tin liên lạc của bảo vệ và y tế là gì?</p>
                        </div>
                        <div id="Des-QA-5" class="QA-item-des hidden">
                            <b>Thông tin liên lạc của phòng bảo vệ và phòng y tế (24/7):</b>
                            <ul class="Des-QA-list-item">
                                <li>
                                    <a style="color: black;" href="tel:02466805913">
                                        <b>Phòng bảo vệ:</b><i> (024) 668 05913</i>
                                    </a>
                                </li>
                                <li>
                                    <a style="color: black;" href="tel:02466805917">
                                        <b>Phòng y tế:</b><i> (024) 668 05917</i>
                                    </a>
                                </li>
                            </ul>
                            <p style="margin-top: 8px;">
                                <i>
                                    Thông tin chi tiết và cụ thể hơn, sinh viên cần <b>Đăng nhập</b> và xem thêm ở
                                    trang <b>Home</b>
                                </i>
                            </p>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
    <form id="loginForm" action="<%=request.getContextPath()%>/account-login" method="post">
        <div id="loginModal" class="login-modal-container hidden">
            <div class="modal-login relative">
                <div class="close-Modal" onclick="handleCloseModal()">
                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 14 14" fill="none">
                        <path
                                d="M14 1.41L12.59 0L7 5.59L1.41 0L0 1.41L5.59 7L0 12.59L1.41 14L7 8.41L12.59 14L14 12.59L8.41 7L14 1.41Z"
                                fill="white"></path>
                    </svg>
                </div>
                <div class="modal-logo">
                    <img class="responsive-img" src="assets/images/logo.png" alt="Alternate Text">
                </div>
                <p class="login-title">Login</p>
                <div>
                    <div>
                        <div class="role-input">
                            <div class="relative" id="fieldUsername">
                                <label for="UserName" class="icon-input">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="22" height="18"
                                         viewBox="0 0 22 18" fill="none">
                                        <path
                                                d="M3.25 0H18.75C20.483 0 21.8992 1.35646 21.9949 3.06558L22 3.25V14.75C22 16.483 20.6435 17.8992 18.9344 17.9949L18.75 18H3.25C1.51697 18 0.100754 16.6435 0.00514483 14.9344L0 14.75V3.25C0 1.51697 1.35645 0.100754 3.06558 0.0051446L3.25 0H18.75H3.25ZM20.5 5.905L11.346 10.6654C11.1653 10.7594 10.9558 10.7751 10.7655 10.7124L10.654 10.6654L1.5 5.905V14.75C1.5 15.6682 2.20711 16.4212 3.10647 16.4942L3.25 16.5H18.75C19.6682 16.5 20.4212 15.7929 20.4942 14.8935L20.5 14.75V5.905ZM18.75 1.5H3.25C2.33183 1.5 1.57881 2.20711 1.5058 3.10647L1.5 3.25V4.214L11 9.15466L20.5 4.214V3.25C20.5 2.33183 19.7929 1.57881 18.8935 1.5058L18.75 1.5Z"
                                                fill="#034EA2"></path>
                                    </svg>
                                </label>
                                <input class="form-control login-input" id="UserName" name="UserName" type="text"
                                       value="" placeholder="Username" required="">
                            </div>
                            <div class="relative" id="fieldPassword">
                                <label for="Password" class="icon-input">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="18" height="25"
                                         viewBox="0 0 18 25" fill="none">
                                        <path
                                                d="M9.28781 0.00489807L9.49965 0C11.8748 0 13.8368 1.84564 13.9901 4.20451C14.0169 4.61786 13.7036 4.97471 13.2903 5.00157C12.8769 5.02843 12.5201 4.71513 12.4932 4.30178C12.3911 2.73068 11.0828 1.5 9.49965 1.5C7.90197 1.5 6.59599 2.74892 6.50474 4.32373L6.49965 4.5L6.49905 7.499L15.7501 7.5C16.9927 7.5 18.0001 8.50736 18.0001 9.75V21.7518C18.0001 22.9945 16.9927 24.0018 15.7501 24.0018H3.24805C2.00541 24.0018 0.998047 22.9945 0.998047 21.7518V9.75C0.998047 8.50736 2.00541 7.5 3.24805 7.5L4.99905 7.499L4.99965 4.5C4.99965 2.08573 6.90088 0.115511 9.28781 0.00489807L9.49965 0L9.28781 0.00489807ZM15.7501 9H3.24805C2.83383 9 2.49805 9.33579 2.49805 9.75V21.7518C2.49805 22.166 2.83383 22.5018 3.24805 22.5018H15.7501C16.1643 22.5018 16.5001 22.166 16.5001 21.7518V9.75C16.5001 9.33579 16.1643 9 15.7501 9ZM9.49965 14.25C10.3281 14.25 10.9996 14.9216 10.9996 15.75C10.9996 16.5784 10.3281 17.25 9.49965 17.25C8.67122 17.25 7.99965 16.5784 7.99965 15.75C7.99965 14.9216 8.67122 14.25 9.49965 14.25Z"
                                                fill="#034EA2"></path>
                                    </svg>
                                </label>
                                <input class="form-control login-input" id="Password" name="Password"
                                       type="password" placeholder="Password" required="">
                            </div>
                            <div id="login-account">
                                <button type="submit" class="btn-login relative">
                                    Login
                                </button>
                            </div>
                            <div style="text-align:center">
                                <span class="line-title">Or</span>
                            </div>
                            <div id="login-google" class="">
                                <a id="google_login" class="btn-google-login"
                                   href="<%=request.getContextPath()%>/google-login">
                                    <img src="assets/images/btn_google.png">
                                    <p>Sign in with Google</p>
                                </a>
                            </div>
                            <div class="login-error">
                                <div class="validation-summary-valid text-danger" data-valmsg-summary="true">
                                    <ul>
                                        <c:if test="${message != null}">
                                            <li>${message}</li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <script>


    </script>
    <div onclick="smoothScroll()" id="scrollTop" style="display: none;">
        <div class="flex justify-center items-center" style="height: 100%;">
            <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" viewBox="0 0 50 50" fill="none">
                <path
                        d="M24.5 25.4508L31.5436 32.3789C32.5173 33.3366 34.0792 33.3357 35.0518 32.377L36.3306 31.1164C37.3238 30.1373 37.3238 28.5347 36.3305 27.5556L26.255 17.6238C25.2817 16.6643 23.7183 16.6643 22.745 17.6238L12.6695 27.5556C11.6762 28.5348 11.6762 30.1373 12.6695 31.1164L13.9482 32.377C14.9208 33.3357 16.4827 33.3366 17.4564 32.3789L24.5 25.4508Z"
                        stroke="#F36F21" stroke-width="3" stroke-linejoin="round"></path>
                <rect x="1.5" y="1.5" width="47" height="47" rx="11.5" stroke="#F36F21" stroke-width="3"></rect>
            </svg>
        </div>
    </div>

    <footer class="my-footer">

        <div class="footer-contact-container">
            <div class="flex-1">
                <p class="address-title">Hà Nội</p>
                <p class="footer-contact">
                    Khu Giáo dục và Đào tạo - Khu Công nghệ cao Hòa Lạc - KM29 Đại Lộ Thăng Long, H. Thạch Thất, TP.
                    Hà Nội
                    <br>
                    <br>
                    Điện thoại: 024 7300 1866
                    <br>
                    <br>
                    Email: daihocfpt@fpt.edu.vn
                </p>
            </div>
            <div class="flex-1">
                <p class="address-title">
                    Đà Nẵng
                </p>
                <p class="footer-contact">
                    Khu đô thị công nghệ FPT Đà Nẵng, P. Hoà Hải, Q. Ngũ Hành Sơn, TP. Đà Nẵng
                    <br>
                    <br>
                    Điện thoại: 024 7300 1866
                    <br>
                    <br>
                    Email: daihocfpt@fpt.edu.vn
                </p>
            </div>
            <div class="flex-1">
                <p class="address-title">Cần Thơ</p>
                <p class="footer-contact">
                    Số 600 Đường Nguyễn Văn Cừ (nối dài), P. An Bình, Q. Ninh Kiều, TP. Cần Thơ
                    <br>
                    <br>
                    Điện thoại: 029 2360 1995
                    <br>
                    <br>
                    Email: sro.ct@fe.edu.vn
                </p>
            </div>
            <div class="flex-1">
                <p class="address-title">Quy nhơn</p>
                <p class="footer-contact">
                    Khu đô thị mới An Phú Thịnh, Phường Nhơn Bình &amp; Phường Đống Đa, TP. Quy Nhơn, Bình Định
                    <br>
                    <br>
                    Điện thoại: 024 7300 1866/ 0256 7300 999
                    <br>
                    <br>
                    Email: daihocfpt@fpt.edu.vn
                </p>
            </div>
        </div>
    </footer>
</div>
<script>
    <%@ include file="assets/js/jquery.js" %>
    <%@ include file="assets/js/index.js" %>
    const loginModalTag = document.getElementById("loginModal");
    var button = document.getElementById("btnLogin");


    // Thêm sự kiện click
    button.addEventListener("click", function () {
        handleShowModal();
    });

    const handleShowModal = () => {
        loginModalTag.style.display = "flex";
    };

    const handleCloseModal = () => {
        loginModalTag.style.display = "none";
    };

    if(${message != null}){
        handleShowModal();
    }
</script>

</body>
</html>


