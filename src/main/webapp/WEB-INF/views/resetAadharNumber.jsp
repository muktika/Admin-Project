<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid">
    <h2>Reset Aadhar Number </h2>
    <form:form action="resetAadharNumber" method="post" modelAttribute="aadharForm" autocomplete="off">
        <form:errors path="aadharNumber" cssClass="error"/>
        <div>
            <form:input path="aadharNumber" id="aadharNumber" maxlength="100" placeholder="Enter aadhar number"/><br/><br/>
        </div>
        <div>
            <button type="submit" name="submit" id="submit" class="btn btn-primary">Submit</button>
        </div>
    </form:form>
    <c:if test="${success}">
        <p class="error">Aadhar Number Reset Successfully.</p>
    </c:if>
</div>
<style>
    .error{
        color: red;
        margin-top: 5px;
        }
</style>