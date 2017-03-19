<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>titlee</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/image-style.css" rel="stylesheet">
</head>
<body>
<c:import url="template/admin-header.jsp"/>
<c:import url="template/carousel.jsp"/>
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

</body>
</html>
