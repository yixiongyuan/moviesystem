<%--
  Created by IntelliJ IDEA.
  User: zacharyuan
  Date: 2022/12/11
  Time: 2:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Movie" %>
<c:import url="header.jsp"><c:param name="title" value="home"></c:param></c:import>


<!-- About Section-->
<section class="masthead bg-primary text-white mb-0" id="about">
    <div class="container" >
        <!-- About Section Heading-->
        <h2 class="page-section-heading text-secondary text-center text-uppercase text-white" style="font-size: 3rem;">Introduction</h2>
        <!-- Icon Divider-->
        <div class="divider-custom divider-light">
            <div class="divider-custom-line"></div>
            <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
            <div class="divider-custom-line"></div>
        </div>
        <!-- About Section Content-->
        <div class="row">
            <div class="col-lg-4 ms-auto"><p class="lead">Welcome to the movie world! In this website
                you can create your own movie database and preserve all the data </p></div>
            <div class="col-lg-4 me-auto"><p class="lead">The Three main functions are listed at the right corner, they will
            guide you to use this movie data manage system.</p></div>
        </div>
        <!-- About Section Button-->
        <div class="text-center mt-4">
            <a class="btn btn-xl btn-outline-light" href=${pageContext.request.getContextPath()}/list?filter=all>
                Get Started
            </a>
        </div>
    </div>
</section>

<c:import url="footer.jsp"></c:import>




