<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nhatn
  Date: 2/17/2021
  Time: 1:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>History Quiz</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body class="text-gray-600 font-body">
<div class="grid md:grid-cols-5">
    <jsp:include page="shared/_Navbar.jsp"/>
    <c:set var="history" value="${requestScope.historyPage}"/>

    <main class="px-16 py-6 bg-gray-100 md:col-span-4">
        <jsp:include page="shared/_Welcome.jsp"/>

        <header>
            <h2 class="text-gray-700 text-6xl font-semibold">Let Quiz</h2>
            <h3 class="text-2xl font-semibold">Master website for education</h3>
        </header>

        <div>
            <h4 class="font-bold mt-12 pb-2 border-b border-gray-200 text-2xl">${history.takenSubject.subject.name}
                - ${history.takenSubject.takenSubjectDetails.size()} questions</h4>
            <h4 class="font-bold mt-12 pb-2 border-b border-gray-200 text-2xl">${history.point} points
                - ${history.takenSubject.startTime} - ${history.takenSubject.endTime} +7.GMT</h4>
            <div class="w-full bg-secondary-100 mt-5 p-2 rounded-xl">
                <c:forEach var="takenSubjectDetail" items="${history.takenSubject.takenSubjectDetails}">
                    <div class="font-bold text-xl">
                            ${takenSubjectDetail.question.text}
                    </div>
                    <div>
                            ${takenSubjectDetail.question.content}
                    </div>
                    <div class="mt-2 p-2">
                        <c:forEach var="option" items="${takenSubjectDetail.question.options}">
                            <c:if test="${takenSubjectDetail.question.numOfRightOption <= 1}">
                                <div <c:if test="${option.isRightAnswer}">class="text-green-500" </c:if>>
                                    <input type="radio" disabled
                                           <c:if test="${takenSubjectDetail.isChoiceOption(option.id)}">checked</c:if>>
                                    <label>
                                            ${option.content}
                                    </label>
                                </div>
                            </c:if>
                            <c:if test="${takenSubjectDetail.question.numOfRightOption > 1}">
                                <div <c:if test="${option.isRightAnswer}">class="text-green-500" </c:if>>
                                    <input type="checkbox" disabled
                                           <c:if test="${takenSubjectDetail.isChoiceOption(option.id)}">checked</c:if>>
                                    <label>
                                            ${option.content}
                                    </label>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
        </div>
</div>
</body>
</html>
