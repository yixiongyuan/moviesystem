<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="header.jsp"><c:param name="title" value="home"></c:param></c:import>


<!-- Contact Section-->
<section class="page-section" id="contact">
    <div class="container">
        <!-- Contact Section Heading-->
        <div class="text-center" style=" margin-top: 4rem ;margin-right: 68rem">
            <a class="btn-search btn-back" href="javascript:history.back(-1)">
                Back
            </a>
        </div>
        <h2 class="page-section-heading-search text-center text-uppercase text-secondary mb-0">Grade and Review</h2>
        <!-- Icon Divider-->
        <div class="divider-custom">
            <div class="divider-custom-line"></div>
            <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
            <div class="divider-custom-line"></div>
        </div>

        <!-- Contact Section Form-->
        <div class="row justify-content-center">
            <div class="col-lg-8 col-xl-7">
                <form action="${pageContext.request.getContextPath()}/rating?movieId=${movieId}" method="post">

                    <!-- Name input-->
                    <div class="form-floating mb-3">
                        Score
                        <td><input class="form-control" name="score" type="number" min="1" max="10"/></td>
                    </div>

                    <!-- Director input-->
                    <div class="form-floating mb-3">
                        Review
                        <td><input class="form-control" name="review"  type="text"/></td>
                    </div>

                        <input type="hidden" name="pageNow" value="${pageNow}">
                        <input type="hidden" name="filter" value="${filter}">
                        <input type="hidden" name="condition" value="${condition}">

                    <tr>
                        <td colspan="3"><input type="submit"  class ="btn btn-primary btn-xl" value="Submit"/></td>
                    </tr>

                </form>
            </div>
        </div>
    </div>
</section>



<c:import url="footer.jsp"></c:import>

