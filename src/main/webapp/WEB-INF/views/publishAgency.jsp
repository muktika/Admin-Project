<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container-fluid">
	<h2>Biometric Agency Page</h2>
	<form:form action="publishAgency" method="post" modelAttribute="publishAgencyForm" autocomplete="off">

        <form:hidden path="agencyId" />
		<form:errors path="agencyName" cssClass="error"/>
		<div>
			<span>Agency Name</span><br/>
			<form:input path="agencyName" placeholder="Enter agency name" value="${agencyName}" maxlength="100"/><br/><br/>
		</div>

		<form:errors path="publicKey" cssClass="error"/>
		<div>
			<span>Public Key</span><br/>
			<form:input path="publicKey" placeholder="Enter public key" value="${publicKey}" readonly="${edit}" maxlength="100"/><br/><br/>
		</div>

		<form:errors path="privateKey" cssClass="error"/>
		<div>
			<span>Private Key</span><br/>
			<form:input path="privateKey" placeholder="Enter private key" value="${privateKey}" readonly="${edit}" maxlength="100"/><br/><br/>
		</div>

		<button type="submit" name="submit" id="submit" class="btn btn-primary" onclick="return confirm('Are you sure you want to publish this Agency?')">Submit</button>

	</form:form>
</div>
<style>
    .error{
        color: red;
        margin-top: 5px;
        }
</style>
