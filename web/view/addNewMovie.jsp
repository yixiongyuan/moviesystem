<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:import url="header.jsp"><c:param name="title" value="home"></c:param></c:import>


<!-- Contact Section-->
<section class="page-section" id="contact">

    <div class="text-center" style=" margin-top: 4rem ;margin-right: 68rem">
        <a class="btn-search btn-back" href="javascript:history.back(-1)">
            Back
        </a>
    </div>

    <div class="container">
        <!-- Contact Section Heading-->
        <h2 class="page-section-heading-search text-center text-uppercase text-secondary mb-0">Add a New Movie</h2>
        <!-- Icon Divider-->
        <div class="divider-custom">
            <div class="divider-custom-line"></div>
            <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
            <div class="divider-custom-line"></div>
        </div>

        <!-- Contact Section Form-->
        <div class="row justify-content-center">
            <div class="col-lg-8 col-xl-7">
                <form:form modelAttribute="movie" action="${pageContext.request.getContextPath()}/add"   enctype="multipart/form-data" method="post">

                    <!-- Name input-->
                    <div class="form-floating mb-3">
                        <td><form:input cssClass="form-control" path="name" required="required"/></td>
                        <label for="name">Movie name</label>
                    </div>

                    <!-- Director input-->
                    <div class="form-floating mb-3">
                        <td><form:input cssClass="form-control" path="director"/></td>
                        <label for="director">Movie Director</label>
                    </div>

                    <!-- year input-->
                    <div class="form-floating mb-3">
                        <td><form:input cssClass="form-control" path="year" type = "number" min="1930" max="2030" value="2000"/></td>
                        <label for="year">Release Year</label>
                    </div>

                    <!-- label input-->
                    <div class="form-floating mb-3">
                        <td><form:input cssClass="form-control" path="label"/></td>
                        <label for="label">Category</label>
                    </div>

                    <!-- actors input-->
                    <div class="form-floating mb-3">
                        <td><form:input cssClass="form-control" path="actors"/></td>
                        <label for="actors">Main Actors</label>
                    </div>

                    <!-- cover input-->
                    <div class="form-floating mb-3">
                        <input id="cover" class="form-control" name="file" type="file">
                        <label for="cover">Cover Image Selection</label>
                    </div>


                    <tr>
                        <td colspan="3"><input type="submit"  class ="btn btn-primary btn-xl" value="Create"/></td>
                    </tr>

                </form:form>
            </div>
        </div>
    </div>
</section>



<c:import url="footer.jsp"></c:import>
