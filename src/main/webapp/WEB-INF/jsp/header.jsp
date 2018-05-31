<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>MyUniversity</title>
    <meta name="keywords" content="MyUniversity"/>
    <meta name="description" content="test site"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="${pageContext.request.contextPath}/resources/style.css"/>"/>
</head>

<body>

<div class="container">

    <header class="header">
        <div class="logo">My University - teamOne project</div>
        <div class="logout">
            <%
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null) {
                    Object obj = auth.getPrincipal();
                    String username = "";
                    if (obj instanceof UserDetails) {
                        username = ((UserDetails) obj).getUsername();
                    } else {
                        username = obj.toString();
                    }
                    request.setAttribute("username", username);
                }
            %>
            <c:if test="${username != null}">
                <c:out value="Вы вошли как: "/>
                <c:out value="${username}"/>
                <c:out value="|   "/>
            </c:if>
            <a href="<c:url value='/j_spring_security_logout'/>">Logout</a>
        </div>
    </header>