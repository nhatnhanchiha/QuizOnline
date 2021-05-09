<%@ page import="com.bac.quizonline.model.CreatingModelForServletService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="grid md:grid-cols-5">
    <jsp:include page="shared/_Navbar.jsp"/>

    <main class="px-16 py-6 md:col-span-4">
        <div class="min-h-screen flex items-center justify-center bg-gray-100">

            <div class="bg-white p-16 rounded shadow-2xl w-2/3">

                <h2 class="text-3xl font-bold text-gray-800">Create Your Let Quiz Account</h2>
                <h2 class="text-lg font-bold mb-10 text-gray-800">Or <a href="login"
                                                                        class="font-bold font-medium text-indigo-600 hover:text-indigo-500">Login</a>
                </h2>

                <form class="space-y-4" action="register" method="post">
                    <div>
                        <label class="block mb-1 font-bold text-gray-500" for="Input_Email">Email</label>
                        <input type="email" name="Input.Email" id="Input_Email" data-val="true"
                               class="w-full border-2 border-gray-200 p-3 rounded outline-none focus:border-purple-500"
                               data-val-required="The Email field is required."
                               data-val-length="The Email must be at least <%=CreatingModelForServletService.MIN_LENGTH_OF_EMAIL%> and at max <%=CreatingModelForServletService.MAX_LENGTH_OF_EMAIL%> characters long."
                               data-val-length-max="<%=CreatingModelForServletService.MAX_LENGTH_OF_EMAIL%>"
                               data-val-length-min="<%=CreatingModelForServletService.MIN_LENGTH_OF_EMAIL%>"
                               maxlength="<%=CreatingModelForServletService.MAX_LENGTH_OF_EMAIL%>"
                               data-rule-email="true"
                               value="${param['Input.Email']}"
                               data-msg-email="Must input an email">
                        <span class="text-primary field-validation-valid" data-valmsg-for="Input.Email"
                              data-valmsg-replace="true">${requestScope.error}</span>
                    </div>

                    <div>
                        <label class="block mb-1 font-bold text-gray-500" for="Input_Name">Name</label>
                        <input type="text" name="Input.Name" id="Input_Name"
                               data-val="true"
                               class="w-full border-2 border-gray-200 p-3 rounded outline-none focus:border-purple-500"
                               data-val-required="The Name field is required."
                               data-val-length="The Name must be at least <%=CreatingModelForServletService.MIN_LENGTH_OF_NAME%> and at max <%=CreatingModelForServletService.MAX_LENGTH_OF_NAME%> characters long."
                               data-val-length-max="<%=CreatingModelForServletService.MAX_LENGTH_OF_NAME%>"
                               data-val-length-min="<%=CreatingModelForServletService.MIN_LENGTH_OF_NAME%>"
                               value="${param['Input.Name']}"
                               maxlength="<%=CreatingModelForServletService.MAX_LENGTH_OF_NAME%>">
                        <span class="text-primary field-validation-valid" data-valmsg-for="Input.Name"
                              data-valmsg-replace="true"></span>
                    </div>

                    <div>
                        <label class="block mb-1 font-bold text-gray-500" for="Input_Password">Password</label>
                        <input type="password" name="Input.Password" id="Input_Password"
                               data-val="true"
                               class="w-full border-2 border-gray-200 p-3 rounded outline-none focus:border-purple-500"
                               data-val-length="The Password must be at least <%=CreatingModelForServletService.MIN_LENGTH_OF_PASSWORD%> and at max <%=CreatingModelForServletService.MAX_LENGTH_OF_PASSWORD%> characters long."
                               data-val-length-max="<%=CreatingModelForServletService.MAX_LENGTH_OF_PASSWORD%>"
                               data-val-length-min="<%=CreatingModelForServletService.MIN_LENGTH_OF_PASSWORD%>"
                               data-val-required="The Password field is required."
                               maxlength="<%=CreatingModelForServletService.MAX_LENGTH_OF_PASSWORD%>">
                        <span class="text-primary field-validation-valid" data-valmsg-for="Input.Password"
                              data-valmsg-replace="true"></span>
                    </div>

                    <div>
                        <label class="block mb-1 font-bold text-gray-500" for="Input_ConfirmPassword">Confirm password</label>
                        <input type="password" name="Input.ConfirmPassword" id="Input_ConfirmPassword" data-val="true"
                               class="w-full border-2 border-gray-200 p-3 rounded outline-none focus:border-purple-500"
                               data-val-equalto="The password and confirmation password do not match."
                               maxlength="<%=CreatingModelForServletService.MAX_LENGTH_OF_PASSWORD%>"
                               data-val-equalto-other="*.Password"/>
                        <span class="text-primary field-validation-valid" data-valmsg-for="Input.ConfirmPassword"
                              data-valmsg-replace="true"></span>
                    </div>

                    <div>
                        <label class="block mb-1 font-bold text-gray-500" for="Select_Role">Role</label>
                        <select name="Select.Role" id="Select_Role"
                                class="w-full border-2 border-gray-200 p-3 rounded outline-none focus:border-purple-500">
                            <c:forEach items="${requestScope.roles}" var="role">
                                <option value="${role.id}" <c:if test="${role.id == param['Select.Role']}">selected</c:if>>${role.name}</option>
                            </c:forEach>
                        </select>

                    </div>
                    <button class="block w-full bg-yellow-400 hover:bg-yellow-300 p-4 rounded text-yellow-900 hover:text-yellow-800 transition duration-300">
                        Sign Up
                    </button>
                </form>
            </div>
        </div>
    </main>
</div>
<jsp:include page="shared/_Jquery.jsp"/>
<jsp:include page="shared/_JqueryValidation.jsp"/>
</body>
</html>
