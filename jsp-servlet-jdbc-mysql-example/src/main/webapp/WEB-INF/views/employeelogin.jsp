<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Login</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
        margin: 0;
        padding: 0;
    }

    h1 {
        color: #333333;
        font-size: 28px;
        margin-bottom: 20px;
    }

    div {
        margin-top: 50px;
    }

    form {
        background-color: #ffffff;
        border-radius: 10px;
        padding: 20px;
        box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        width: 400px;
        margin: auto;
    }

    table {
        width: 100%;
        margin: 10px 0;
        border-collapse: separate;
        border-spacing: 10px;
    }

    table td {
        padding: 10px;
    }

    table td:first-child {
        text-align: right;
        font-weight: bold;
        color: #555555;
    }

    input[type="text"], input[type="password"] {
        width: 100%;
        padding: 8px;
        border-radius: 5px;
        border: 1px solid #cccccc;
        box-sizing: border-box;
    }

    input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        border: none;
        padding: 10px 20px;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
        width: 100%;
    }

    input[type="submit"]:hover {
        background-color: #45a049;
    }

    .register-link {
        margin-top: 20px;
        display: block;
        text-align: center;
    }

    .error {
        color: red;
        text-align: center;
    }
</style>
</head>
<body>
    <div align="center">
        <h1>Employee Login</h1>
        <form action="<%= request.getContextPath() %>/login" method="post">
            <table>
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="username" required /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" required /></td>
                </tr>
            </table>
            <input type="submit" value="Login" />
        </form>
        <div class="error">
            <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
        </div>
        <div class="register-link">
    <a href="<%= request.getContextPath() %>/register">Register</a>
</div>

    </div>
</body>
</html>
