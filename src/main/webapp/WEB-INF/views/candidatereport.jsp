<%@ taglib uri="/birt.tld" prefix="birt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form:form name="candidateReport" action="candidateReportData" method="post" modelAttribute="aadharForm" autocomplete="off">
    <div class="container-fluid">
        <h2> Enter Aadhar number: </h2>
        <form:input path="aadharNumber" id="aadharNumber" maxlength="100" placeholder="Enter aadhar number" value="${aadharNumber}"/><br/><br/>
        <form:errors path="aadharNumber" cssClass="error"/><br/>
        <button type="submit" name="submit" id="submit" class="btn btn-primary">Submit</button>
    </div>
</form:form><br/><br/>
<c:if test="${success}">
    <birt:viewer id="birtViewer" reportDesign="candidate.rptdesign" pattern="frameset" height="900" width="1600" format="html">
        <birt:param name="CandidateAadhar" value="${aadharNumber}"></birt:param>
    </birt:viewer>
</c:if>
<style>
    .error{
        color: red;
        margin-top: 5px;
        }
</style>
