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
        <div>
            <form action="/developers" method="get">
                   <label for="devId"> Developer ID <label>
                   <input type="text" id="devId" name="devId"><br>
                   <button type="submit">Find</button>
            </form>
        </div>
        <div>
            <table>
                            <thead>
                                <c:if test="${not empty developer}">
                                    <tr>
                                        <td style="text-align: center">ID</td>
                                        <td style="text-align: center">First name</td>
                                        <td style="text-align: center">Last name</td>
                                        <td style="text-align: center">Birth date</td>
                                        <td style="text-align: center">Gender</td>
                                        <td style="text-align: center">Salary</td>
                                    </tr>
                                </c:if>
                                <c:if test="${empty developer}">
                                    <p>There is no developer with such ID</p>
                                </c:if>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><c:out value="${developer.devId}"/></td>
                                    <td><c:out value="${developer.firstName}"/></td>
                                    <td><c:out value="${developer.lastName}"/></td>
                                    <td><c:out value="${developer.birthDate}"/></td>
                                    <td><c:out value="${developer.gender}"/></td>
                                    <td><c:out value="${developer.salary}"/></td>
                                </tr>
                            </tbody>
                        </table>
        </div>
    </body>
</html>