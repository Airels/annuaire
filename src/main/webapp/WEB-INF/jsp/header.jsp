<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:setBundle basename="jsp_view" />
<fmt:setBundle basename="password_reset" var="password_reset"  />

<c:url var="bootstrap_css"
       value="/webjars/bootstrap/4.6.0-1/css/bootstrap.min.css" />
<c:url var="bootstrap_js"
       value="/webjars/bootstrap/4.6.0-1/js/bootstrap.min.js" />
<c:url var="jquery_js" value="/webjars/jquery/3.5.1/jquery.min.js" />
<c:url var="css" value="/style.css" />

<c:set var="websiteURLPattern" value="^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&'\(\)\*\+,;=.]+$" />

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" />
    <title>Annuaire</title>
    <link rel="stylesheet" href="${css}" />
    <link rel="stylesheet" href="${bootstrap_css}" />
    <script src="${jquery_js}"></script>
    <script src="${bootstrap_js}"></script>
</head>

<body>