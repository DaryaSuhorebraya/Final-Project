<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="localization" var="loc"/>
<html>
<head>
    <title>titlee</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/movie-info.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-editable.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap-editable.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/admin-movie-info.js"></script>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.get('role') eq 'admin'}">
        <c:import url="template/admin-header.jsp"/>
    </c:when>
    <c:otherwise>
        <c:import url="template/user-header.jsp"/>
    </c:otherwise>
</c:choose>

<div class="container">
    <div class="row">
        <div class="col-md-10">
            <div class="thumbnail">
                <div class="row main">
                    <img class="img-responsive" src="./${movie.posterPath}" alt="">
                    <c:if test='${sessionScope.role eq "admin"}'>
                        <button class="btn btn-labeled edit" id="img-edit">
                            <a>
                            <span class="glyphicon glyphicon-edit"></span></a>
                        </button>
                    </c:if>
                    <p id="movie-id">${movie.id}</p>
                    <ul class="list-group">
                        <li class="list-group-item">
                            <fmt:message bundle="${loc}" key="release.year"/>:<a id="year-text"> ${movie.releaseYear}</a>
                            <c:if test='${sessionScope.role eq "admin"}'>
                                <button class="btn btn-labeled edit" id="year-edit">
                                <span class="glyphicon glyphicon-edit"></span>
                                    </button>
                                <a id='save-edit-year' type='button' class='btn btn-primary'> <span class="glyphicon glyphicon-save"></span></a>
                                <a id='cancel-edit-year' type='button' class='btn btn-primary'> <span class="glyphicon glyphicon-remove"></span></a>
                            </c:if>
                        </li>
                        <li class="list-group-item">  <fmt:message bundle="${loc}" key="country"/>:
                            <c:forEach var="country" items="${requestScope.countries}">
                                 <a href="#" id="country-text" class="editable editable-click">${country.name}</a>
                            </c:forEach>
                            <c:if test='${sessionScope.role eq "admin"}'>
                                <button class="btn btn-labeled edit">
                                    <span class="glyphicon glyphicon-edit"></span>
                                </button>
                            </c:if>
                        </li>
                        <li class="list-group-item"> <fmt:message bundle="${loc}" key="genre"/>:
                            <c:forEach var="genre" items="${requestScope.genres}">
                               <a href="#">${genre.name}
                               </a>
                            </c:forEach>
                            <c:if test='${sessionScope.role eq "admin"}'>
                                <button class="btn btn-labeled edit">
                                    <span class="glyphicon glyphicon-edit"></span>
                                </button>
                            </c:if>
                        </li>
                        <li class="list-group-item">  <fmt:message bundle="${loc}" key="actors"/>:
                            <c:forEach var="actor" items="${requestScope.actors}">
                                <a href="#">${actor.firstName} &nbsp;${actor.lastName}</a>
                            </c:forEach>
                            <c:if test='${sessionScope.role eq "admin"}'>
                                <button class="btn btn-labeled edit">
                                    <span class="glyphicon glyphicon-edit"></span>
                                </button>
                            </c:if>
                        </li>
                    </ul>
                </div>
                <div class="caption-full">
                    <h4 class="h4-title">${movie.title}</h4>
                    <c:if test='${sessionScope.role eq "admin"}'>
                        <button class="btn btn-labeled edit">
                            <span class="glyphicon glyphicon-edit"></span>
                        </button>
                    </c:if>
                    <div class="ratings">
                        <p class="p-rating">
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star-empty"></span>
                        </p>
                        <h4 class="h4-rating">${movie.rating}</h4>
                    </div>
                    <p>${movie.description}
                    <c:if test='${sessionScope.role eq "admin"}'>
                        <button class="btn btn-labeled edit">
                            <span class="glyphicon glyphicon-edit"></span>
                        </button>
                    </c:if></p>
                </div>
            </div>
            <div class="well">
                <div class="text-right">
                    <a class="btn btn-success">Leave a Review</a>
                </div>
                <hr>
                <div class="row review">
                    <div class="col-md-12">
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                        Anonymous
                        <span class="pull-right">10 days ago</span>
                        <p>This product was great in terms of quality. I would definitely buy another!</p>
                    </div>
                </div>
                <hr>
            </div>
        </div>
    </div>
</div>
</body>
</html>
