<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nhatn
  Date: 2/18/2021
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Histories</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<c:set var="model" value="${requestScope.historyListPage}"/>
<div class="grid md:grid-cols-5">
    <jsp:include page="shared/_Navbar.jsp"/>
    <main class="px-16 py-6 bg-gray-100 md:col-span-4">
        <jsp:include page="shared/_Welcome.jsp"/>

        <header>
            <h2 class="text-gray-700 text-6xl font-semibold">Let Quiz</h2>
            <h3 class="text-2xl font-semibold">Master website for education</h3>
        </header>

        <div>
            <h4 class="font-bold mt-12 pb-2 border-b border-gray-200">Anh em tìm quiz đã làm</h4>
            <div class="mt-8 mb-5 w-1/2">
                <form action="view-history-list" method="get">
                    <div class="shadow flex">
                        <input class="w-4/5 rounded p-2" type="text" placeholder="Name or Id of quiz"
                               name="Input.NameOrIdSubject"
                               value="${param['Input.NameOrIdSubject']}">
                        <button class="w-1/5 justify-end items-center text-white p-2 bg-primary hover:text-blue-100">
                            Search Quiz
                        </button>
                    </div>
                </form>
            </div>

            <h4 class="font-bold mt-12 pb-2 border-b border-gray-200">History được tìm thấy</h4>
            <c:if test="${(model != null) && (null or empty model.takenSubjects)}">
                <h1 class="text-2xl text-primary mt-3">Không thể tìm thấy history nào có tên giống vậy</h1>
            </c:if>
            <c:if test="${(model != null) && (not null and not empty model.takenSubjects)}">
                <div class="mt-8 gap-10">
                    <table class="border-collapse w-full">
                        <thead>
                        <tr>
                            <th class="p-3 font-bold uppercase bg-gray-200 text-gray-600 border border-gray-300 hidden lg:table-cell">
                                Quiz ID
                            </th>
                            <th class="p-3 font-bold uppercase bg-gray-200 text-gray-600 border border-gray-300 hidden lg:table-cell">
                                Subject Name
                            </th>
                            <th class="p-3 font-bold uppercase bg-gray-200 text-gray-600 border border-gray-300 hidden lg:table-cell">
                                Id Subject
                            </th>
                            <th class="p-3 font-bold uppercase bg-gray-200 text-gray-600 border border-gray-300 hidden lg:table-cell">
                                End Time
                            </th>
                            <th class="p-3 font-bold uppercase bg-gray-200 text-gray-600 border border-gray-300 hidden lg:table-cell">
                                Action
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${model.takenSubjects}" var="takenSubject">
                            <tr class="bg-white lg:hover:bg-gray-100 flex lg:table-row flex-row lg:flex-row flex-wrap lg:flex-no-wrap mb-10 lg:mb-0">
                                <td class="w-full lg:w-auto p-3 text-gray-800 text-center border border-b block lg:table-cell relative lg:static">
                                        ${takenSubject.id}
                                </td>
                                <td class="w-full lg:w-auto p-3 text-gray-800 text-center border border-b text-center block lg:table-cell relative lg:static">
                                        ${takenSubject.subject.name}
                                </td>
                                <td class="w-full lg:w-auto p-3 text-gray-800 text-center border border-b text-center block lg:table-cell relative lg:static">
                                        ${takenSubject.idSubject}
                                </td>
                                <td class="w-full lg:w-auto p-3 text-gray-800 text-center border border-b text-center block lg:table-cell relative lg:static">
                                        ${takenSubject.endTime}
                                </td>
                                <td class="w-full lg:w-auto p-3 text-gray-800 text-center border border-b text-center block lg:table-cell relative lg:static">
                                    <a href="history_quiz?idTakenSubject=${takenSubject.id}" class="text-blue-400 hover:text-blue-600 underline">View History</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="flex justify-evenly mt-4">
                        <div>
                            <a <c:if test="${model.currentPage > 1}">href="view-history-list?Input.NameOrIdSubject=${param['Input.NameOrIdSubject']}&page=${model.currentPage - 1}" class="text-primary"</c:if>>Prev</a>
                        </div>
                        <div>
                                ${model.currentPage}
                        </div>
                        <div>
                            <a <c:if test="${model.hasNextPage}">href="view-history-list?Input.NameOrIdSubject=${param['Input.NameOrIdSubject']}&page=${model.currentPage + 1}" class="text-primary"</c:if>>Next</a>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </main>
</div>
</body>
</html>
