<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization" var="loc"/>
    <title>titlee</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
    <link href="css/style.css" rel="stylesheet">
    <link href="css/image-style.css" rel="stylesheet">
    <script src="js/ajax.js"></script>

    <link href="${pageContext.request.contextPath}/css/bootstrap-editable.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/bootstrap-editable.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-editable.js"></script>
</head>
<body>
<c:import url="WEB-INF/jsp/template/user-header.jsp"/>
<c:import url="WEB-INF/jsp/template/carousel.jsp"/>
<div class="row">
    <div class="col-md-4">
        <blockquote>
            <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.
            </p> <small>Someone famous <cite>Source Title</cite></small>
        </blockquote>
    </div>
    <div class="col-md-4">
        <blockquote>
            <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.
            </p> <small>Someone famous <cite>Source Title</cite></small>
        </blockquote>
    </div>
    <div class="col-md-4">
        <blockquote>
            <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.
            </p> <small>Someone famous <cite>Source Title</cite></small>
        </blockquote>
    </div>
</div>
<form>
    Name <input type="text" id="fullName"/>
    <input type="button" value="Hello" id="btnHello">
    <br>
    <span id="result1"></span>
</form>
<a href="#" id="forEdit">ghjghjgjh</a>
<button class="btn btn-labeled edit" id="for-edit">
    <a>
        <span class="glyphicon glyphicon-edit"></span></a>
</button>
</body>
</html>