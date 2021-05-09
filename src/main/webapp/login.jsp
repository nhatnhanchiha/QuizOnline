<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nhatn
  Date: 2/15/2021
  Time: 12:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="grid md:grid-cols-5">
    <jsp:include page="shared/_Navbar.jsp"/>

    <main class="px-16 py-6 md:col-span-4">

        <div class="min-h-screen flex items-center justify-center bg-blue-100">

            <div class="bg-white p-16 rounded shadow-2xl w-2/3">

                <h2 class="text-3xl font-bold text-gray-800">Login Your Let Quiz Account</h2>
                <h2 class="text-lg font-bold text-gray-800 mb-4">Or <a href="register"
                                                                       class="font-bold font-medium text-indigo-600 hover:text-indigo-500">Register</a>
                </h2>
                <span class="text-primary font-bold">${requestScope.error}</span>
                <form class="space-y-4" action="login" method="post">
                    <div>
                        <label class="block mb-1 font-bold text-gray-500" for="Input_Email">Email</label>
                        <input type="email" name="Input.Email" id="Input_Email" data-val="true"
                               class="w-full border-2 border-gray-200 p-3 rounded outline-none focus:border-purple-500"
                               value="${param['Input.Email']}">
                    </div>

                    <div>
                        <label class="block mb-1 font-bold text-gray-500" for="Input_Password">Password</label>
                        <input type="password" name="Input.Password" id="Input_Password"
                               class="w-full border-2 border-gray-200 p-3 rounded outline-none focus:border-purple-500">
                    </div>

                    <button class="block w-full bg-yellow-400 hover:bg-yellow-300 p-4 rounded text-yellow-900 hover:text-yellow-800 transition duration-300">
                        Login
                    </button>
                </form>
            </div>
        </div>
    </main>
</div>
</body>
</html>
