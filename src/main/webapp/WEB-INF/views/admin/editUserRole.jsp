<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="content-white">
    <h2> Admin Users </h2><br/>
	<table class="table table-border">
			<tr>
				<th>User Role Allocation Id</th>
				<th>User Role</th>
			</tr>
		<c:forEach items="${rolesList}" var="tc">
			<tr>
				<td>${tc.userRoleAllocationId}</td>
				<td>${tc.userRole.name}</td>
			</tr>
		</c:forEach>
	</table>
</div>
