<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="content-white">
	<form action="addAdminUser" method="get">
        <button class="pull-right btn btn-primary" name="AddAdmin" type="submit">Add Admin</button>
    </form>
    <h2> Admin Users </h2><br/>
	<table class="table table-border">
			<tr>
				<th>Admin Id</th>
				<th>Email Id</th>
				<th>Password</th>
				<th>Enabled</th>
				<th>User Roles</th>
			</tr>
		<c:forEach items="${AdminUserList}" var="tc">
			<tr>
				<td><a href="/superuser/admin/editUserRole?adminId=${tc.adminId}">${tc.adminId}</a></td>
				<td>${tc.email}</td>
				<td>${tc.password}</td>
				<td>${tc.enabled}</td>
				<td>${tc.rolesDisplay}</td>
			</tr>
		</c:forEach>

	</table>
</div>
