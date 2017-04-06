<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ProFilm</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/admin-page-style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/admin-page.js"></script>
    <link href="${pageContext.request.contextPath}/css/morris.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/morris.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/raphael.min.js"></script>


</head>
<body>
<c:import url="template/admin-header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-sm-11">
            <label class="label label-success">User Registration</label>
            <div id="user-chart"></div>
        </div>

    </div>
    <div class="row">
        <div class="col-sm-11">
            <label class="label label-success">Most reviewed movies</label>
            <div id="review-chart"></div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-5">
            <label class="label label-success">Genre Statistics</label>
            <div id="genre-chart"></div>
        </div>
        <div class="col-sm-6">
            <ul class="list-inline list-group">
                <c:forEach items="${requestScope.genres}" var="genre">
                    <li class="list-group-item">${genre.name}
                        <p class="id-genre">${genre.id}</p>
                        <span class="btn-label btn-danger delete-genre">
                            <i class="glyphicon glyphicon-remove"></i>
                        </span>
                    </li>
                </c:forEach>
            </ul>
            <button id="add-genre" class="btn-success">Add Genre</button>
        </div>
    </div>
</div>

<c:import url="template/footer.jsp"/>
</body>
</html>
