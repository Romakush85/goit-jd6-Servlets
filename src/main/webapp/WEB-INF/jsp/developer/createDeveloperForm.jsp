<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>ProjectManagementSystem</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
     <c:import url="${contextPath}/WEB-INF/jsp/navbar.jsp"/>
        <form action="/developers" method="post">
                <label for="firstName"> First name:</label>
                <input type="text" id="firstName" name="firstName"><br>
                <label for="lastName"> Last name:</label>
                <input type="text" id="lastName" name="lastName"><br>
                <label for="birthDate"> Birth date, dd-MM-yyyy:</label>
                <input type="text" id="birthDate" name="birthDate"><br>
                <label for="gender"> Gender:</label>
                <input type="text" id="gender" name="gender"><br>
                <label for="salary"> Salary:</label>
                <input type="number" id="salary" name="salary"><br>
                <button type="submit" name="command" value="create">Create developer</button>
        </form>
    </body>
</html>