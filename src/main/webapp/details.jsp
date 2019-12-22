<jsp:useBean id="user" class="ua.nure.cs.hura.usermanagement.domain.User" scope="session"/>
<html>
<head><title>User management. Edit</title></head>
<body>
	<form action="<%=request.getContextPath()%>/browse" method="get">
		<input type="hidden" name="id" value="${user.id}">
		First name ${user.firstName}><br>
		Last name ${user.lastName}><br>
		Date of birth <fmt:formatDate value="${user.dateOfBirth}"type="date"dateStyle="medium"/"><br>
		<input type="submit" name="calncelButton" value="Cancel">
	</form>
<c:if test ="${requetScope.error !=null}">
	<script>
		alert('${reqquestScope.error}');
	</script>
</c:if>
</body>
</html> 