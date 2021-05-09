<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nhatn
  Date: 2/16/2021
  Time: 3:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz Management</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body class="text-gray-600 font-body">
<div class="grid md:grid-cols-5">
    <jsp:include page="shared/_Navbar.jsp"/>
    <main class="px-16 py-6 bg-gray-100 md:col-span-4">
        <jsp:include page="shared/_Welcome.jsp"/>
        <header>
            <h2 class="text-gray-700 text-6xl font-semibold">Let Quiz</h2>
            <h3 class="text-2xl font-semibold">Quiz bắt đầu bằng Q và Question cũng vậy</h3>
        </header>

        <div>
            <h4 class="font-bold mt-12 pb-2 border-b border-gray-200 text-2xl">Admin thêm Question</h4>
            <div class="mb-7">
                <a href="add-question"
                   class="w-1/5 h-9 p-1.5 outline-none bg-primary rounded-br-2xl font-bold text-white">
                    Thêm Question
                </a>
            </div>

            <h4 class="font-bold mt-12 pb-2 border-b border-gray-200 text-2xl">Admin tìm Question</h4>
            <c:if test="${requestScope.subjects == null || requestScope.subjects.size() == 0}">
                <div class="text-primary text-xl mt-5">
                    Bạn chưa được phân quyền bất kì môn học nào
                </div>
            </c:if>
            <c:if test="${requestScope.subjects != null && requestScope.subjects.size() > 0}">
                <div>
                    <form class="mt-7 grid grid-cols-12 gap-4" action="search-question">
                        <div class="mb-7 col-span-5">
                            <input name="Input.ContentOfQuestion" id="Input_ContentOfQuestion"
                                   placeholder="Ex: Java Web Application"
                                   value="${param['Input.ContentOfQuestion']}"
                                   class="border-blue-400 border-b-4 w-full h-9 p-1.5 outline-none bg-gray-100 focus-within:bg-blue-50 focus-within:border-yellow-300"/>
                            <label for="Input_ContentOfQuestion" class="font-bold text-gray-600 block">Content of
                                question</label>
                        </div>
                        <div class="mb-7 col-span-2">
                            <select name="Select.Subject" id="Select_Subject"
                                    class="border-blue-400 border-b-4 w-full h-9 p-1.5 outline-none bg-gray-100 focus-within:bg-blue-50 focus-within:border-yellow-300">
                                <option value="0">All Created Subject</option>
                                <c:forEach items="${requestScope.subjects}" var="subject">
                                    <option value="${subject.id}"
                                            <c:if test="${subject.id == param['Select.Subject']}">selected</c:if>>${subject.name}</option>
                                </c:forEach>
                            </select>
                            <label for="Select_Subject" class="font-bold text-gray-600 block">Subject</label>
                        </div>
                        <div class="mb-7">
                            <select name="Select.Status" id="Select_Status"
                                    class="border-blue-400 border-b-4 w-full h-9 p-1.5 outline-none bg-gray-100 focus-within:bg-blue-50 focus-within:border-yellow-300">
                                <option>All</option>
                                <option ${param['Select.Status'] == '1' ? 'selected' : ''} value="1">Active</option>
                                <option ${param['Select.Status'] == '0' ? 'selected' : ''} value="0">Inactive</option>
                            </select>
                            <label for="Select_Status" class="font-bold text-gray-600 block">Status</label>
                        </div>
                        <div class="mb-7">
                            <button type="submit"
                                    class="w-full h-9 p-1.5 outline-none bg-yellow-400 rounded-br-2xl font-bold text-white">
                                Search
                            </button>
                        </div>
                    </form>
                </div>
                <c:if test="${requestScope.managePage.subject != null and (null or empty requestScope.managePage.subject.questions)}">
                <span class="text-primary text-2xl">
                    Không thể tìm thấy
                </span>
                </c:if>

                <c:if test="${requestScope.managePage.subject != null and not null and not empty requestScope.managePage.subject.questions}">
                    <c:set var="subject" value="${requestScope.managePage.subject}"/>
                    <div>
                        <div class="mt-8 gap-10">
                            <table class="border-separate border border-green-800 w-full">
                                <tr>
                                    <td class="p-3 font-bold uppercase bg-green-200 text-gray-600 border border-green-600 hidden lg:table-cell text-center">
                                        <c:if test="${not null and not empty subject.description}">${subject.description}</c:if>
                                        <c:if test="${null or empty subject.description}">All Created Subject</c:if>
                                    </td>
                                    <td class="p-3 font-bold uppercase bg-green-200 text-gray-600 border border-green-600 hidden lg:table-cell text-center">
                                        Action
                                    </td>
                                </tr>
                                <c:forEach items="${subject.questions}" var="question">
                                    <tr class="bg-white lg:hover:bg-gray-100 flex lg:table-row flex-row lg:flex-row flex-wrap lg:flex-no-wrap mb-10 lg:mb-0">
                                        <td class="w-full lg:w-auto p-3 text-gray-800 border border-green-600 block lg:table-cell relative lg:static">
                                            <div class="text-lg font-bold mt-2">Subject: ${question.subject.name} -
                                                Id: ${question.subject.id}</div>
                                            <hr class="mt-2 border-t-2 border-green-600">
                                            <div class="text-lg font-bold mt-2">${question.text}</div>
                                            <div class="mt-2 ml-2">
                                                    ${question.content}
                                            </div>
                                            <c:forEach items="${question.options}" var="option" varStatus="i">
                                                <div class="mt-2 ml-4 <c:if test="${option.isRightAnswer}">text-primary font-bold</c:if> ">
                                                        ${i.count}. ${option.content}
                                                </div>
                                            </c:forEach>
                                            <hr class="mt-2 border-t-2 border-green-600">
                                            <div class="mt-2 ml-2">
                                                Question status: <span
                                                    class="font-bold">${question.status ? 'Active' : 'Inactive'}</span>
                                            </div>
                                        </td>
                                        <td class="w-full lg:w-4 p-3 text-gray-800 border border-green-600 block lg:table-cell relative lg:static text-center">
                                            <a href="edit-question?id=${question.id}&page=${requestScope.managePage.currentPage}&Input.ContentOfQuestion=${param['Input.ContentOfQuestion']}&Select.Subject=${param['Select.Subject']}&Select.Status=${param['Select.Status']}"
                                               class="text-white bg-primary h-9 p-1.5 rounded-br-xl font-bold inline-block">Edit</a>
                                            <c:if test="${question.status}">
                                                <a href="delete-question?id=${question.id}&subjectId=${question.subject.id}&page=${requestScope.managePage.currentPage}&Input.ContentOfQuestion=${param['Input.ContentOfQuestion']}&Select.Subject=${param['Select.Subject']}&Select.Status=${param['Select.Status']}"
                                                   class="text-white bg-primary h-9 p-1.5 rounded-br-xl font-bold inline-block mt-2" onclick="return confirm('Are you sure?')">Delete</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <div class="flex justify-around mt-5 text-2xl">
                                <div><a
                                        <c:if test="${requestScope.managePage.currentPage > 1}">href="search-question?page=${requestScope.managePage.currentPage - 1}&Input.ContentOfQuestion=${param['Input.ContentOfQuestion']}&Select.Subject=${param['Select.Subject']}&Select.Status=${param['Select.Status']}" </c:if>
                                        class="no-underline hover:underline text-primary">Prev</a></div>
                                <div>${requestScope.managePage.currentPage}</div>
                                <div><a
                                        <c:if test="${requestScope.managePage.hasNextPage}">href="search-question?page=${requestScope.managePage.currentPage + 1}&Input.ContentOfQuestion=${param['Input.ContentOfQuestion']}&Select.Subject=${param['Select.Subject']}&Select.Status=${param['Select.Status']}" </c:if>
                                        class="no-underline hover:underline text-primary">Next</a></div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:if>
        </div>
    </main>
</div>
<script src="index.js"></script>
</body>
</html>