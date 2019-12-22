<html>
<head><title>User management. Add</title></head>
<body>
	<form action="<%=request.getContextPath()%>/add" method="post">
		First name <input type="text" name="firstName" value=""><br>
		Last name <input type="text" name="lastName" value=""><br>
		Date of birth <input type="text" name="date" value=""><br>
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