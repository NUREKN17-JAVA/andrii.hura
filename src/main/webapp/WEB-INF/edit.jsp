<jsp:useBean id="user" class="ua.nure.cs.hura.usermanagement.domain.User" scope="session"/>
<html>
<head><title>User management. Edit</title></head>
<body>
	<form action="<%=request.getContextPath()%>/edit" method="post">
		<input type="hidden" name="id" value="${user.id}">
		First name <input type="text" name="firstName" value="${user.firstName}"><br>
		Last name <input type="text" name="lastName" value="${user.lastName}"><br>
		Date of birth <input type="text" name="date" value="<fmt:formatDate value="${user.dateOfBirth}"type="date"dateStyle="medium"/"><br>
		<input type="submit" name="okButton" value="Ok">
		<input type="submit" name="calncelButton" value="Cancel">
	</form>
<c:if test ="${requetScope.error !=null}">
	<script>
		alert('${reqquestScope.error}');
	</script>
</c:if>
</body>
</html> 