<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="content-white">
    <h2> Assessments </h2><br/>
	<table class="table table-border">
			<tr>
				<th>Assessment Id</th>
				<th>companyname Assessment Id</th>
				<th>Agency Id</th>
				<th>companyname Assessment Name</th>
				<th>Delete Assessment</th>
			</tr>
		<c:forEach items="${assessmentsList}" var="tc">
			<tr>
				<td><a href="/admin/publishAssessments?assessmentId=${tc.assessmentId}">${tc.assessmentId}</a></td>
				<td>${tc.companynameAssessmentId}</td>
				<td>${tc.agency.agencyId}</td>
				<td>${tc.companynameAssessmentName}</td>
				<form action="/admin/deleteAssessment?assessmentId=${tc.assessmentId}" method="post" id="deleteForm" name="deleteForm">
					<td><button type="submit" class="btn btn-primary" onclick="return confirm('Are you sure you want to delete this Assessment?')">Delete</button></td>
				</form>
			</tr>
		</c:forEach>
	</table>
</div>
