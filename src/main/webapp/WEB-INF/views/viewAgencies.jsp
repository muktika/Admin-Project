<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="content-white">
    <h2> Agencies </h2><br/>
	<table class="table table-border">
			<tr>
				<th>Agency Id</th>
				<th>Agency Name</th>
				<th>Public Key</th>
				<th>Private Key</th>
				<th>Delete Agency</th>
			</tr>
		<c:forEach items="${agencyList}" var="tc">
			<tr>
				<td><a href="/admin/publishAgency?agencyId=${tc.agencyId}">${tc.agencyId}</a></td>
				<td>${tc.agencyName}</td>
				<td>${tc.publicKey}</td>
				<td>${tc.privateKey}</td>
				<form action="/admin/deleteAgency?agencyId=${tc.agencyId}" method="post" id="deleteForm" name="deleteForm">
					<td><button type="submit" class="btn btn-primary" onclick="return confirm('Are you sure you want to delete this Agency?')">Delete</button></td>
				</form>
			</tr>
		</c:forEach>
	</table>
</div>
