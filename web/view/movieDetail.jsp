<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Movie" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.BufferedReader" %>
<c:import url="header.jsp"><c:param name="title" value="home"></c:param></c:import>


<!-- Portfolio Section-->
<section class="page-section portfolio" id="portfolio">

    <div class="text-center" style=" margin-top: 4rem ;margin-right: 68rem">
        <a class="btn-search btn-back" href="${pageContext.request.getContextPath()}/list?pageNow=${pageNow}&filter=${filter}&condition=${condition}">
            Back
        </a>
    </div>

    <div class="container">

        <!-- Portfolio Section Heading-->
        <h2 class="page-section-heading-search text-center text-uppercase text-secondary mb-0">Movie Detail</h2>
        <!-- Icon Divider-->
        <div class="divider-custom">
            <div class="divider-custom-line"></div>
            <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
            <div class="divider-custom-line"></div>
        </div>
        <table border="1"  align="center" cellspacing="0"  id="mainTable" >
            <tr>
                <th>Cover</th>
                <th>Information</th>
                <th>Review</th>
                <th>Action</th>
            </tr>

            <tr>
                <td><img class="img-fluid" src="cover/${movie.cover}" alt="..." height='400' width='200'></td>


                <td><ul>
                    <div style="width:300px ;word-wrap:break-word;text-align: center">
                    <p>${movie.name}</p>
                    <p>${movie.year}</p>
                    <p> Director: ${movie.director}</p>
                    <p> Actors: ${movie.actors}</p>
                    <p> Category: ${movie.label}</p>
                        <p> Score: ${movie.avg_score}</p>
                    <p> Rating Times: ${movie.rating_times}</p>
                    </div>
                </ul></td>


                <td><ul>
                    <%
                        File file = new File(request.getServletContext().getRealPath("/upload/ReviewData/")+request.getAttribute("reviewFile"));
                        FileReader fr = new FileReader(file);  //字符输入流
                        BufferedReader br = new BufferedReader(fr);  //使文件可按行读取并具有缓冲功能
                        StringBuffer strB = new StringBuffer();   //strB用来存储jsp.txt文件里的内容
                        String str = br.readLine();
                        while(str!=null){
                            strB.append(str).append("<br><br>");   //将读取的内容放入strB
                            str = br.readLine();
                        }
                        br.close();
                    %>
                    <div id="reviewCell" style="overflow-y: scroll">
                        <p><%=strB%></p>
                    </div>
                </ul></td>


                <td><ul>
                    <p>
                        <button class="btn btn-primary2">
                            <a href="${pageContext.request.getContextPath()}/detail?movieId=${movie.id}&pageNow=${pageNow}&filter=${filter}&condition=${condition}">Detail</a>
                        </button>
                    </p>

                    <p>
                        <button class="btn btn-primary2">
                            <a href="${pageContext.request.getContextPath()}/update?movieId=${movie.id}&pageNow=${pageNow}&filter=${filter}&condition=${condition}">Update</a>
                        </button>
                    </p>

                    <p>
                        <button class="btn btn-primary2" >
                            <a href="${pageContext.request.getContextPath()}/rating?movieId=${movie.id}&pageNow=${pageNow}&filter=${filter}&condition=${condition}">Rating</a>
                        </button>
                    </p>


                    <p>
                        <button class="btn btn-primary2">
                            <a href="${pageContext.request.getContextPath()}/delete?movieId=${movie.id}&pageNow=${pageNow}&filter=${filter}&condition=${condition}" onclick="if(!confirm('Are you sure to delete this movie'))return false">Delete</a>
                        </button>
                    </p>
                </ul></td>
            </tr>
        </table>

    </div>
<c:import url="footer.jsp"></c:import>