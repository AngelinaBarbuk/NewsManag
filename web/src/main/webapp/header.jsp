<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="Lang" />


<html lang="${language}">
<head>
    <meta charset="UTF-8">
    <script>
            $("#en").onclick= function () {
                ${language}="en_US";
            }
            $("#ru").onclick= function () {
                ${language}="ru_RU";
            }
    </script>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand"><fmt:message key="header.brand" /></a>
        </div>
        
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><form method="GET">
                    <input type="hidden" name="lang" value="en">
                    <input type="hidden" name="action" value="ChangeLang">
                    <input type="submit" value="En">
                </form>
                    <a href="/web/controller?lang=en" id="en">En</a></li>
                <li><a href="/web/controller?lang=ru" id="ru">Ru</a></li>
            </ul>
        </div>
    </div>
</div>
</body>


</html>