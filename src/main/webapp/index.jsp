<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Let Quiz</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body class="text-gray-600 font-body">
<div class="grid md:grid-cols-5">
    <jsp:include page="shared/_Navbar.jsp"/>

    <main class="px-16 py-6 bg-gray-100 md:col-span-4">
        <jsp:include page="shared/_Welcome.jsp"/>

        <header>
            <h2 class="text-gray-700 text-6xl font-semibold">Let Quiz</h2>
            <h3 class="text-2xl font-semibold">Master website for education</h3>
        </header>

        <div>
            <h4 class="font-bold mt-12 pb-2 border-b border-gray-200">Anh em tìm quiz</h4>
            <div class="mt-8 mb-5 w-1/2">
                <form action="view-quiz-list" method="get">
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

            <div class="flex justify-center hidden">
                <div class="btn bg-secondary-100 text-secondary-200 hover:shadow-inner">Load more</div>
            </div>

            <h4 class="font-bold mt-12 pb-2 border-b border-gray-200">Quizzes vừa mới được thêm vào</h4>
            <c:if test="${null or empty requestScope.subjects}">
                <h1 class="text-2xl text-primary mt-3">Không co</h1>
            </c:if>
            <c:if test="${not null and not empty requestScope.subjects}">
                <div class="mt-8 gap-10">
                    <table class="border-collapse w-full">
                        <thead>
                        <tr>
                            <th class="p-3 font-bold uppercase bg-gray-200 text-gray-600 border border-gray-300 hidden lg:table-cell">
                                Quiz ID
                            </th>
                            <th class="p-3 font-bold uppercase bg-gray-200 text-gray-600 border border-gray-300 hidden lg:table-cell">
                                Name
                            </th>
                            <th class="p-3 font-bold uppercase bg-gray-200 text-gray-600 border border-gray-300 hidden lg:table-cell">
                                Number Of Questions
                            </th>
                            <th class="p-3 font-bold uppercase bg-gray-200 text-gray-600 border border-gray-300 hidden lg:table-cell">
                                Time
                            </th>
                            <th class="p-3 font-bold uppercase bg-gray-200 text-gray-600 border border-gray-300 hidden lg:table-cell">
                                Action
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.subjects}" var="subject">
                            <tr class="bg-white lg:hover:bg-gray-100 flex lg:table-row flex-row lg:flex-row flex-wrap lg:flex-no-wrap mb-10 lg:mb-0">
                                <td class="w-full lg:w-auto p-3 text-gray-800 text-center border border-b block lg:table-cell relative lg:static">
                                        ${subject.id}
                                </td>
                                <td class="w-full lg:w-auto p-3 text-gray-800 text-center border border-b text-center block lg:table-cell relative lg:static">
                                        ${subject.name}
                                </td>
                                <td class="w-full lg:w-auto p-3 text-gray-800 text-center border border-b text-center block lg:table-cell relative lg:static">
                                        ${subject.numberOfQuestionInQuiz}
                                </td>
                                <td class="w-full lg:w-auto p-3 text-gray-800 text-center border border-b text-center block lg:table-cell relative lg:static">
                                        ${subject.minutes} minutes
                                </td>
                                <td class="w-full lg:w-auto p-3 text-gray-800 text-center border border-b text-center block lg:table-cell relative lg:static">
                                    <a href="take-quiz?idSubject=${subject.id}" class="text-blue-400 hover:text-blue-600 underline">Take Quiz</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="flex justify-center mt-4">
                        <div class="btn bg-secondary-100 text-secondary-200 hover:shadow-inner"><a href="view-quiz-list">Load More</a></div>
                    </div>
                </div>
            </c:if>
        </div>
    </main>
</div>
<script src="index.js"></script>
</body>
</html>
