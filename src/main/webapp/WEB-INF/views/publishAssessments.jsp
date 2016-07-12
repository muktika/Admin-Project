<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container-fluid">
	<h2>Biometric Assessments Page</h2>
	<form:form action="publishAssessments" method="post" modelAttribute="publishAssessmentsForm" autocomplete="off">
		<form:hidden path="assessmentId" />
		<form:errors path="companynameAssessmentId" cssClass="error"/>
		<div>
			<span>companyname Assessment ID</span><br/>
			<form:input path="companynameAssessmentId" placeholder="Enter assessment id" value="${companynameAssessmentId}" maxlength="100"/><br/><br/>
		</div>

		<form:errors path="companynameAssessmentName" cssClass="error"/>
		<div>
			<span>Biometric Assessment Name</span><br/>
			<form:input path="companynameAssessmentName" placeholder="Enter assessment name" value="${companynameAssessmentName}" maxlength="100"/><br/><br/>
		</div>

		<form:errors path="agencyId" cssClass="error"/>
		<div>
			<span>Agency ID</span><br/>
			<form:input path="agencyId" placeholder="Enter agency Id" value="${agencyId}" maxlength="100"/><br/><br/>
		</div>

		<button type="submit" name="submit" id="submit" class="btn btn-primary" onclick="return confirm('Are you sure you want to publish this Assessment?')">Submit</button>

	</form:form>
</div>
<style>
    .error{
        color: red;
        margin-top: 5px;
        }
</style>

