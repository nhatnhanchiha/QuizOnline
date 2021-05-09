<%@ page import="com.bac.quizonline.model.CreatingModelService" %><%--
  Created by IntelliJ IDEA.
  User: nhatn
  Date: 2/15/2021
  Time: 7:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Subject</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body class="text-gray-600font-body">
<div class="grid md:grid-cols-5">
    <jsp:include page="shared/_Navbar.jsp"/>
    <main class="px-16 py-6 bg-gray-100 md:col-span-4">
        <jsp:include page="shared/_Welcome.jsp"/>
        <header>
            <h2 class="text-gray-700 text-6xl font-semibold">Let Quiz</h2>
            <h3 class="text-2xl font-semibold">Master website for education</h3>
        </header>

        <div>
            <h4 class="font-bold mt-12 pb-2 border-b border-gray-200 text-2xl">Create New Subject</h4>
            <div class="mt-8 grid grid-cols-2 gap-10">
                <form method="post" action="add-subject">
                    <div class="mb-7">
                        <input name="Input.Name" id="Input_Name"
                               data-val="true"
                               data-val-required="The Name field is required."
                               data-val-length="The Name must be at least <%=CreatingModelService.MIN_LENGTH_OF_NAME_OF_SUBJECT%> and at max <%=CreatingModelService.MAX_LENGTH_OF_NAME_OF_SUBJECT%> characters long."
                               data-val-length-max="<%=CreatingModelService.MAX_LENGTH_OF_NAME_OF_SUBJECT%>"
                               data-val-length-min="<%=CreatingModelService.MIN_LENGTH_OF_NAME_OF_SUBJECT%>"
                               maxlength="<%=CreatingModelService.MAX_LENGTH_OF_NAME_OF_SUBJECT%>"
                               class="border-blue-400 border-b-4 w-full h-9 p-1.5 outline-none bg-gray-100 focus-within:bg-blue-50 focus-within:border-yellow-300"
                               placeholder="Input name of Subject, ex: Java Web Application">
                        <label for="Input_Name" class="font-bold text-gray-600 block">Name of subject</label>
                        <span class="text-primary field-validation-valid" data-valmsg-for="Input.Name"
                              data-valmsg-replace="true"></span>
                    </div>
                    <div class="mb-7">
                        <textarea name="Input.Description" id="Input_Description" rows="5"
                                  data-val="true"
                                  data-val-required="The Description of subject field is required."
                                  data-val-length="The Description of subject must be at least <%=CreatingModelService.MIN_LENGTH_OF_DESCRIPTION_OF_SUBJECT%> and at max <%=CreatingModelService.MAX_LENGTH_OF_DESCRIPTION_OF_SUBJECT%> characters long."
                                  data-val-length-max="<%=CreatingModelService.MAX_LENGTH_OF_DESCRIPTION_OF_SUBJECT%>"
                                  data-val-length-min="<%=CreatingModelService.MIN_LENGTH_OF_DESCRIPTION_OF_SUBJECT%>"
                                  maxlength="<%=CreatingModelService.MAX_LENGTH_OF_DESCRIPTION_OF_SUBJECT%>"
                                  class="w-full outline-none p-1.5 border-blue-400 border-b-4 bg-gray-100 focus-within:bg-blue-50 focus-within:border-yellow-300"
                                  placeholder="Input description of Subject, ex: This is the final quiz about Java Web Application"></textarea>
                        <label for="Input_Description" class="font-bold text-gray-600 block">Description of subject</label>
                        <span class="text-primary field-validation-valid" data-valmsg-for="Input.Description"
                              data-valmsg-replace="true"></span>
                    </div>
                    <div class="mb-7">
                        <input name="Input.Minutes" id="Input_Minutes"
                               data-msg-range="The field Time to taking must be between <%=CreatingModelService.MIN_VALUE_OF_MINUTES%> and <%=CreatingModelService.MAX_VALUE_OF_MINUTES%>."
                               data-msg-number="The field Time to taking must be a number."
                               data-msg-required="The Time to taking field is required."
                               data-rule-number="true"
                               data-rule-range="[<%=CreatingModelService.MIN_VALUE_OF_MINUTES%>,<%=CreatingModelService.MAX_VALUE_OF_MINUTES%>]"
                               data-rule-required="true"
                               class="border-blue-400 border-b-4 w-full h-9 p-1.5 outline-none bg-gray-100 focus-within:bg-blue-50 focus-within:border-yellow-300"
                               placeholder="Input time to taking">
                        <label for="Input_Minutes" class="font-bold text-gray-600 block">Time to taking (minutes)</label>
                        <span class="text-primary field-validation-valid" data-valmsg-for="Input.Minutes"
                              data-valmsg-replace="true"></span>
                    </div>
                    <div class="mb-7">
                        <input name="Input.NumberQuestionInQuiz" id="Input_NumberQuestionInQuiz"
                               data-rule-integer="true"
                               data-msg-range="The field Integer must be between <%=CreatingModelService.MIN_VALUE_OF_NUMBER_QUESTION_IN_QUIZ%> and <%=CreatingModelService.MAX_VALUE_OF_NUMBER_QUESTION_IN_QUIZ%>."
                               data-rule-range="[<%=CreatingModelService.MIN_VALUE_OF_NUMBER_QUESTION_IN_QUIZ%>,<%=CreatingModelService.MAX_VALUE_OF_NUMBER_QUESTION_IN_QUIZ%>]"
                               class="border-blue-400 border-b-4 w-full h-9 p-1.5 outline-none bg-gray-100 focus-within:bg-blue-50 focus-within:border-yellow-300"
                               placeholder="Ex: 50">
                        <label for="Input_NumberQuestionInQuiz" class="font-bold text-gray-600 block">Number of question in
                            quiz</label>
                        <span class="text-primary field-validation-valid" data-valmsg-for="Input.NumberQuestionInQuiz"
                              data-valmsg-replace="true"></span>
                    </div>
                    <div class="flex justify-center">
                        <button type="submit" class="btn bg-blue-400 text-white align-middle">Create</button>
                    </div>
                </form>
            </div>
        </div>
    </main>
</div>

<jsp:include page="shared/_Jquery.jsp"/>
<jsp:include page="shared/_JqueryValidation.jsp"/>
<script>
    $.validator.addMethod("integer", function (number) {
        number = number.replace(/\s+/g, "");
        return number.match(/^\d+$/);
    }, "Please specify a valid integer");
</script>
</body>
</html>
