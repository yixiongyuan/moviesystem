<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Movie" %>
<c:import url="header.jsp"><c:param name="title" value="home"></c:param></c:import>


<!-- Portfolio Section-->
<section class="page-section portfolio" id="portfolio">

    <c:choose>
        <c:when test="${!filter.equals(\"all\")}">
            <div class="text-center" style=" margin-top: 4rem ;margin-right: 68rem">
                <a class="btn-search btn-back" href=${pageContext.request.getContextPath()}/list?filter=all>
                    Reset
                </a>
            </div>
        </c:when>
    </c:choose>

    <div class="container">

        <!-- Portfolio Section Heading-->
        <c:choose>
            <c:when test="${filter.equals(\"all\")}">
                <h2 class="page-section-heading text-center text-uppercase text-secondary mb-0">Movie List</h2>
            </c:when>
            <c:otherwise>
                <h2 class="page-section-heading-search text-center text-uppercase text-secondary mb-0">Search Result</h2>
            </c:otherwise>
        </c:choose>

        <!-- Icon Divider-->
        <div class="divider-custom">
            <div class="divider-custom-line"></div>
            <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
            <div class="divider-custom-line"></div>
        </div>
        <table border="2px"  align="center" cellspacing="0" id="mainTable">
            <tr>
                <th>PreView</th>
                <th>Information</th>
                <th>Action</th>

                <th>PreView</th>
                <th>Information</th>
                <th>Action</th>
            </tr>

            <c:forEach items="${movies}" var="movie" varStatus="status">
                <c:if test="${status.count%2==1}">
                    <tr>
                </c:if>
                <td><img class="img-fluid" src="cover/${movie.cover}" alt="..." height='400' width='200'></td>


                <td>
                    <ul>
                        <div style="width:300px ;word-wrap:break-word;text-align: center">
                        <p>${movie.name}</p>
                        <p>${movie.year}</p>
                        <p> Director:${movie.director}</p>
                            <p> Category:${movie.label}</p>
                        <p> Score:${movie.avg_score}</p>
                        </div>
                    </ul>
                </td>


                <td>
                    <ul>
                        <p>
                        <button class="btn btn-primary2">
                            <a href="${pageContext.request.getContextPath()}/detail?movieId=${movie.id}&pageNow=${page.pageNow}&filter=${filter}&condition=${condition}">Detail</a>
                        </button>
                        </p>

                        <p>
                        <button class="btn btn-primary2">
                            <a href="${pageContext.request.getContextPath()}/update?movieId=${movie.id}&pageNow=${page.pageNow}&filter=${filter}&condition=${condition}">Update</a>
                        </button>
                        </p>

                        <p>
                        <button class="btn btn-primary2" >
                            <a href="${pageContext.request.getContextPath()}/rating?movieId=${movie.id}&pageNow=${page.pageNow}&filter=${filter}&condition=${condition}">Rating</a>
                        </button>
                        </p>

                        <p>
                        <button class="btn btn-primary2">
                            <a href="${pageContext.request.getContextPath()}/delete?movieId=${movie.id}&pageNow=${page.pageNow}&filter=${filter}&condition=${condition}" onclick="if(!confirm('Are you sure to delete this movie'))return false">Delete</a>
                        </button>
                        </p>

                    </ul>
                </td>

            <c:if test="${status.count%2==0}">
                </tr>
            </c:if>

            </c:forEach>



        </table>
        <div class="text-content" style="margin: auto ;width:fit-content;text-align: justify;margin-top: 2rem;background-color: sandybrown;border: 0.25rem;">
                Content:
<%--            <button class="btn btn-content">--%>
                <a href="${pageContext.request.getContextPath()}/list?pageNow=1&filter=${filter}&condition=${condition}" rel="external nofollow"  >First </a>

                <c:choose>
                    <c:when test="${page.pageNow - 1 > 0}">
                        <a  href="${pageContext.request.getContextPath()}/list?pageNow=${page.pageNow - 1}&filter=${filter}&condition=${condition}" rel="external nofollow" >Prev</a>
                    </c:when>
                    <c:when test="${page.pageNow - 1 <= 0}">
                        <a  href="${pageContext.request.getContextPath()}/list?pageNow=1&filter=${filter}&condition=${condition}" rel="external nofollow" >Prev</a>
                    </c:when>
                </c:choose>

                <c:set value="${Math.max(1,page.pageNow-5)}" var="begin"></c:set>
                <c:set value="${Math.min(page.totalPageCount.longValue(),page.pageNow+5)}" var="end"></c:set>

                <c:forEach begin="${begin}" end="${end}" var="p">
                    <c:choose>
                        <c:when test="${page.pageNow eq p}">${page.pageNow}</c:when>
                        <c:otherwise> <a href="${pageContext.request.getContextPath()}/list?pageNow=${p}&filter=${filter}&condition=${condition}" rel="external nofollow" >${p}</a></c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:choose>
                    <c:when test="${page.totalPageCount==0}">
                        <a href="${pageContext.request.getContextPath()}/list?pageNow=${page.pageNow}&filter=${filter}&condition=${condition}" rel="external nofollow" >Next</a>
                    </c:when>
                    <c:when test="${page.pageNow + 1 < page.totalPageCount}">
                        <a href="${pageContext.request.getContextPath()}/list?pageNow=${page.pageNow + 1}&filter=${filter}&condition=${condition}" rel="external nofollow" >Next</a>
                    </c:when>
                    <c:when test="${page.pageNow + 1 >= page.totalPageCount}">
                        <a href="${pageContext.request.getContextPath()}/list?pageNow=${page.totalPageCount}&filter=${filter}&condition=${condition}" rel="external nofollow"  >Next</a>
                    </c:when>
                </c:choose>

                <c:choose>
                    <c:when test="${page.totalPageCount==0}">
                        <a href="${pageContext.request.getContextPath()}/list?pageNow=${page.pageNow}&filter=${filter}&condition=${condition}" rel="external nofollow"  >Last</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.getContextPath()}/list?pageNow=${page.totalPageCount}&filter=${filter}&condition=${condition}" rel="external nofollow" >Last</a>
                    </c:otherwise>
                </c:choose>
<%--            </button>--%>
        </div>
    </div>
</section>


<c:import url="footer.jsp"></c:import>