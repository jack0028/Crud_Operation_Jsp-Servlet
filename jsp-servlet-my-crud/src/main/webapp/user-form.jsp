<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <title>User Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
            <div>
                <a href="https://www.javaguides.net" class="navbar-brand">User Management App</a>
            </div>
            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/userform/list" class="nav-link">Users</a></li>
            </ul>
        </nav>
    </header>
    <br>
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <c:choose>
                    <c:when test="${user != null}">
                        <form action="<%=request.getContextPath()%>/userform/update" method="post">
                    </c:when>
                    <c:otherwise>
                        <form action="<%=request.getContextPath()%>/userform/insert" method="post">
                    </c:otherwise>
                </c:choose>

                <h2>
                    <c:choose>
                        <c:when test="${user != null}">
                            Edit User
                        </c:when>
                        <c:otherwise>
                            Add New User
                        </c:otherwise>
                    </c:choose>
                </h2>
                <form>
                <c:if test="${user != null}">
                    <input type="hidden" name="id" value="<c:out value='${user.id}' />" />
                </c:if>

                <fieldset class="form-group">
                    <label>User Name</label>
                    <input type="text" value="<c:out value='${user.name}' default=''/>'" class="form-control" name="name" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>User Email</label>
                    <input type="email" value="<c:out value='${user.email}' default=''/>'" class="form-control" name="email">
                </fieldset>
                 <fieldset class="form-group">
                    <label>User Contact</label>
                    <input type="text" value="<c:out value='${user.country}' default=''/>'" class="form-control" name="country">
                </fieldset>

                <fieldset class="form-group">
                    <label>User Otp</label>
                    <input type="number" value="<c:out value='${user.country}' default=''/>'" class="form-control" name="country">
                </fieldset>

                <button type="submit" class="btn btn-success">Save</button>
                </form>
            </div>
        </div>
    </div>
</body>

</html>
