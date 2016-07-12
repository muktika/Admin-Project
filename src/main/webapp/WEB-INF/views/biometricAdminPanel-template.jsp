<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="b" uri="biometricLibrary" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <c:choose>
        <c:when test="${not empty title}">
            <title>${title}</title>
        </c:when>
        <c:otherwise>
            <title><tiles:insertAttribute name="title" /></title>
        </c:otherwise>
    </c:choose>
    <meta name="description" content="companyname is a powerful online assessment platform. It enables hiring managers in companies to measure and track skills of pre-hires and employees." />
    <tiles:insertAttribute name="htmlHead"  />
</head>

<body>
    <div class="navbar navbar-fixed-top" id="header">
        <div class="navbar-inner">
            <img class="logo brand" src="${b:img('csclogo.jpg')}" />
            <tiles:insertAttribute name="header" />
        </div>
    </div>
    <div class="breadcrumb">
        <div class="container-fluid">
            <c:choose>
                <c:when test="${not empty title}">
                    ${title}
                </c:when>
                <c:otherwise>
                    <tiles:insertAttribute name="title" />
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <tiles:insertAttribute name="body" />
    <tiles:insertAttribute name="footer" />
</body>

</html>