<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization" var="loc"/>
    <title>ProFilm</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
    <link href="css/style.css" rel="stylesheet">
    <link href="css/image-style.css" rel="stylesheet">
    <script src="js/ajax.js"></script>

    <link href="${pageContext.request.contextPath}/css/bootstrap-select.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-editable.js"></script>
    <link href="${pageContext.request.contextPath}/css/main-page-style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery.dotdotdot.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/main-page.js" type="text/javascript"></script>
</head>
<body>
<c:import url="WEB-INF/jsp/template/user-header.jsp"/>
<c:import url="WEB-INF/jsp/template/carousel.jsp"/>
<div class="container">
<section class="review-container">
    <div class="container">
        <div class="row">
            <div >
                <h3 class="title"><fmt:message bundle="${loc}" key="review"/> </h3>
                <div class="pull-right button-main">
                    <a href="Controller?command=view-all-reviews-order-by-date"><fmt:message bundle="${loc}" key="view.all.reviews"/></a>
                </div>
            </div>
            <c:forEach var="review" items="${requestScope.reviews}">
                <div class="col-md-4">
                    <blockquote class="quote">
                        <h4 class="movie-title">
                            <a href="Controller?command=view-movie&movieId=${review.idMovie}">${review.movieTitle}</a>
                        </h4>
                        <p class="review">${review.review}</p>
                    </blockquote>
                    <h4 class="pull-right user-login"> — ${review.userLogin}&nbsp;</h4>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
</div>

<c:import url="WEB-INF/jsp/template/footer.jsp"/>
</body>
</html>