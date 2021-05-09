<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nhatn
  Date: 2/19/2021
  Time: 7:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.bac.quizonline.model.component.QuizPage" %>
<html>
<head>
    <title>Take Quiz</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body class="text-gray-600 font-body">
<div class="grid md:grid-cols-5">
    <jsp:include page="shared/_Navbar.jsp"/>
    <c:set var="takenSubject" value="${requestScope.quizPage.takenSubject}"/>

    <main class="px-16 py-6 bg-gray-100 md:col-span-4">
        <jsp:include page="shared/_Welcome.jsp"/>

        <header>
            <h2 class="text-gray-700 text-6xl font-semibold">Let Quiz</h2>
            <h3 class="text-2xl font-semibold">Master website for education</h3>
        </header>

        <div>
            <h4 class="font-bold mt-12 pb-2 border-b border-gray-200 text-2xl">${requestScope.quizPage.takenSubject.subject.name} - ${requestScope.quizPage.takenSubject.subject.numberOfQuestionInQuiz} questions</h4>
            <div class="flex justify-between mt-12">
                <c:if test="${requestScope.quizPage.currentPage > 1}">
                    <div class="border-2 border-yellow-400 cursor-pointer px-10 py-2 rounded bg-primary text-white hover:bg-red-700"
                         id="btn-prev"
                         data-page="${requestScope.quizPage.currentPage - 1}">
                        <<
                    </div>
                </c:if>
                <c:forEach items="${takenSubject.takenSubjectDetails}" varStatus="i" var="takenSubjectDetail">
                    <div class="btn-change-question cursor-pointer border-2 border-yellow-400 px-10 py-2 rounded text-white hover:bg-red-700 ${i.count == 1? 'bg-blue-500' : takenSubjectDetail.answerDetails.size() > 0 ? 'bg-green-500' : 'bg-primary'}"
                         data-btn-question-for="${i.count}" id="btn-q-${i.count}">
                            ${i.count + (requestScope.quizPage.currentPage - 1) * QuizPage.MAX_QUESTION_OF_A_PAGE}
                    </div>
                </c:forEach>
                <c:if test="${requestScope.quizPage.hasNextPage}">
                    <div class="border-2 border-yellow-400 cursor-pointer px-10 py-2 rounded bg-primary text-white hover:bg-red-700"
                         id="btn-next"
                         data-page="${requestScope.quizPage.currentPage + 1}">
                        >>
                    </div>
                </c:if>
            </div>
            <div class="w-full bg-secondary-100 mt-5 p-2 rounded-xl">
                <form method="post" id="mainForm">
                    <input type="hidden" name="idSubject" value="${param['idSubject']}">
                    <input type="hidden" name="idTakenSubject" value="${requestScope.quizPage.takenSubject.id}">
                    <div id="current-question" data-current-question="1"></div>
                    <c:forEach varStatus="i" items="${requestScope.quizPage.takenSubject.takenSubjectDetails}"
                               var="takenSubjectDetail">
                        <div id="q-${i.count}" class="${i.count != 1? 'hidden' : ''}">
                            <div class="font-bold text-xl">
                                    ${takenSubjectDetail.question.text}
                            </div>
                            <div>
                                    ${takenSubjectDetail.question.content}
                            </div>
                            <div class="mt-2 p-2">
                                <div id="count-option-q-${i.count}"
                                     data-count-option-q-${i.count}="${takenSubjectDetail.answerDetails.size()}"></div>
                                <c:forEach items="${takenSubjectDetail.question.options}" var="option" varStatus="j">
                                    <c:if test="${takenSubjectDetail.question.numOfRightOption <= 1}">
                                        <div>
                                            <input type="radio" name="as-${takenSubjectDetail.question.id}"
                                                   value="${option.id}"
                                                   <c:if test="${takenSubjectDetail.isChoiceOption(option.id)}">checked</c:if>
                                                   id="op${i.count}-${j.count}" class="select-option"
                                                   data-option-for-q="${i.count}">
                                            <label for="op${i.count}-${j.count}">
                                                    ${option.content}
                                            </label>
                                        </div>
                                    </c:if>
                                    <c:if test="${takenSubjectDetail.question.numOfRightOption > 1}">
                                        <div>
                                            <input type="checkbox" name="as-${takenSubjectDetail.question.id}"
                                                   value="${option.id}"
                                                   <c:if test="${takenSubjectDetail.isChoiceOption(option.id)}">checked</c:if>
                                                   id="op${i.count}-${j.count}" class="select-option"
                                                   data-option-for-q="${i.count}">
                                            <label for="op${i.count}-${j.count}">
                                                    ${option.content}
                                            </label>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </c:forEach>
                </form>
            </div>
            <div class="p-3">
                <div class="text-primary font-bold inline-block" id="timer">
                </div>
                <button id="btn-submit"
                        class="bg-primary hover:bg-blue-300 text-white font-bold py-2 px-4 rounded text-right">Submit
                </button>
            </div>
        </div>
    </main>
</div>
<jsp:include page="shared/_Jquery.jsp"/>
<script>
    $('.btn-change-question').on('click', function () {
        const questionId = $(this).data('btn-question-for')
        let isDone = $(this).hasClass('bg-green-500')
        if (isDone) {
            $(this).removeClass('bg-green-500')
        } else {
            $(this).removeClass('bg-primary')
        }
        $(this).addClass('bg-blue-500')


        const $currentQuestion = $('#current-question')
        const idCurrentQuestion = $currentQuestion.data('current-question')
        const $countOption = $('#count-option-q-' + idCurrentQuestion)
        const numberOfCheckedOption = $countOption.data('count-option-q-' + idCurrentQuestion);
        const $btnCurrentQuestion = $('#btn-q-' + idCurrentQuestion)

        if (numberOfCheckedOption > 0) {
            $btnCurrentQuestion.addClass('bg-green-500')
        }

        isDone = $btnCurrentQuestion.hasClass('bg-green-500')
        $btnCurrentQuestion.removeClass('bg-blue-500')
        if (!isDone) {
            $btnCurrentQuestion.addClass('bg-primary')
        }
        $('#q-' + idCurrentQuestion).addClass('hidden')
        $currentQuestion.data('current-question', questionId)
        $('#q-' + questionId).removeClass('hidden')
    })


    $('#btn-submit').on('click', function () {
        if (confirm('Are you sure?')) {
            const $form = $('#mainForm')
            $form.attr('action', 'submit_quiz')
            $form.submit()
        }
    })

    $('.select-option').on('change', function () {
        const idQuestion = $(this).data("option-for-q");
        const $countOption = $('#count-option-q-' + idQuestion)
        let numberOfCheckedOption = $countOption.data('count-option-q-' + idQuestion);
        if ($(this).is(':checked')) {
            numberOfCheckedOption = numberOfCheckedOption + 1;
            $countOption.data('count-option-q-' + idQuestion, numberOfCheckedOption);
            const $btn = $('#btn-q-' + idQuestion)
            $btn.removeClass('bg-primary')
            $btn.addClass('bg-green-500')
        } else {
            numberOfCheckedOption = numberOfCheckedOption - 1;
            $countOption.data('count-option-q-' + idQuestion, numberOfCheckedOption);
            const $btn = $('#btn-q-' + idQuestion)
            if (numberOfCheckedOption === 0) {
                $btn.removeClass('bg-green')
                $btn.addClass('bg-blue-500')
            }
        }
    })

    $('#btn-prev').on('click', function () {
        const $form = $('#mainForm')
        $form.attr('action', 'save_current_page')
        const page = $(this).data('page')

        $form.append('<input type="hidden" name="page" value="' + page + '">')
        $form.submit()
    })

    $('#btn-next').on('click', function () {
        const $form = $('#mainForm')
        $form.attr('action', 'save_current_page')
        const page = $(this).data('page')

        $form.append('<input type="hidden" name="page" value="' + page + '">')
        $form.submit()
    })
</script>
<script>
    const endTime = ${requestScope.quizPage.endTimeInMilliseconds};
    setInterval(function () {
        const currentTime = new Date().getTime();
        const timeRemaining = Math.ceil((endTime - currentTime) / 1000);
        $('#timer').text('Time remaining : ' + timeRemaining + ' seconds')
        if (timeRemaining <= 0) {
            const $form = $('#mainForm')
            $form.attr('action', 'submit_quiz')
            $form.submit()
        }
    }, 1000)
</script>
</body>
</html>
