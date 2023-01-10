<%--
  Created by IntelliJ IDEA.
  User: zacharyuan
  Date: 2022/12/11
  Time: 2:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Movie" %>
<c:import url="header.jsp"><c:param name="title" value="home"></c:param></c:import>

<!-- Masthead-->
<header class="page-section bg-primary2 text-white text-center">

    <div class="text-center" style=" margin-top: 4rem ;margin-right: 68rem">
        <a class="btn-search btn-back" href="javascript:history.back(-1)">
            Back
        </a>
    </div>

    <div class="container d-flex align-items-center flex-column">

        <h2 class="page-section-heading-search text-center text-secondary text-uppercase mb-0">Search Movie</h2>
        <!-- Icon Divider-->
        <div class="divider-custom">
            <div class="divider-custom-line"></div>
            <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
            <div class="divider-custom-line"></div>
        </div>

            <% if(request.getAttribute("alert")!=null){%>
        <script type="text/javascript">
            alert("We didn't find the related result, you can try again or add a new one");
        </script><% } %>

        <div class="form-floating mb-6">
        <form:form  action="${pageContext.request.getContextPath()}/list"   method="post">

            <h5 class="text-secondary">
                Movie Name:  <input  name="condition" type="text" required = required>
            <input type="submit"  class =" btn-primary3" value="Start Search"/>
            </h5>

            <input type="hidden" name="filter" value="name">

        </form:form>
        </div>

        <div class="form-floating mb-6">
        <form:form  action="${pageContext.request.getContextPath()}/list" method="post">
                <h5 class="text-secondary">
                    Category:  <input  name="condition" type="text" required = required>
                <input type="submit"  class ="btn-primary3" value="Start Search"/>
                </h5>

            <input type="hidden" name="filter" value="label">
        </form:form>

        </div>
</header>


<c:import url="footer.jsp"></c:import>





