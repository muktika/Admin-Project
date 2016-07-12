<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid">
	<h2>Biometric Add Admin User</h2>
	<form:form action="addAdminUser" method="post" modelAttribute="addAdminUserForm" autocomplete="off">

		<form:errors path="email" cssClass="error"/>
		<div>
			<span>Admin User Email</span><br/>
			<form:input path="email" placeholder="Enter Admin User Email" value="" maxlength="100"/><br/><br/>
		</div>

		<form:errors path="password" cssClass="error"/>
		<div>
			<span>Password</span><br/>
			<form:input path="password" placeholder="Enter A Password" value="" maxlength="100"/><br/><br/>
		</div>

		<form:errors path="roleAllocations" cssClass="error"/>
		<div>
			<span>Roles:</span><br/>
			<form:checkboxes items="${roles}" path="roleAllocations"/>
		</div>
		<br/><br/>
		<button type="submit" name="submit" id="submit" class="btn btn-primary" onclick="return confirm('Are you sure you want to Add User?')">Submit</button>

	</form:form>
</div>
<style>
    .error{
        color: red;
        margin-top: 5px;
        }
</style>

