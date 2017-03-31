<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="localization" var="loc"/>
<html>
<head>
    <title>ProFilm</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/top-movie-style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<body>
<c:if test="${sessionScope.role != null}">
    <c:choose>
        <c:when test="${sessionScope.get('role') eq 'admin'}">
            <c:import url="template/admin-header.jsp"/>
        </c:when>
        <c:otherwise>
            <c:import url="template/user-header.jsp"/>
        </c:otherwise>
    </c:choose>
</c:if>
<p></p>

<div class="container">

    <div class="row">
        <div class="col-sm-9">
            <table class="table">
                <thead>
                    <tr>
                    <th></th>
                    <th><fmt:message bundle="${loc}" key="rank"/>&<fmt:message bundle="${loc}" key="title"/></th>
                    <th><fmt:message bundle="${loc}" key="rating"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="movie" items="${requestScope.movies}" varStatus="loop">
                        <tr>
                            <td class="td-img"><img class="img-top" src="./${movie.posterPath}" alt="" /></td>
                            <td>${loop.index+1}.&nbsp;
                                <a class="movie-a" href="Controller?command=view-movie&movieId=${movie.id}"> ${movie.title}</a>
                            </td>
                            <td>${movie.rating}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<c:import url="template/footer.jsp"/>
</body>
</html>
