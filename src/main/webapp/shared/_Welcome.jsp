<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nhatn
  Date: 2/4/2021
  Time: 10:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${null or empty sessionScope.role}">
    <div class="flex justify-center md:justify-end">
    <a href="login"
       class="btn text-primary border-primary md:border-2 hover:bg-primary hover:text-white transition ease-out duration-500">Log
        in</a>
    <a href="register"
       class="btn text-primary ml-2 border-primary md:border-2 hover:bg-primary hover:text-white transition ease-out duration-500">Sign
        up</a>
</c:if>
<c:if test="${not null and not empty sessionScope.role}">
    <div class="flex justify-center md:justify-end">
    <a href="view-history"
       class="btn text-primary border-primary md:border-2 hover:bg-primary hover:text-white transition ease-out duration-500">
            ${sessionScope.name}</a>
    <a href="log-out"
       class="btn text-primary ml-2 border-primary md:border-2 hover:bg-primary hover:text-white transition ease-out duration-500">Log
        out</a>
</c:if>
</div>