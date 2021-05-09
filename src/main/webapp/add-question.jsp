<%@ page import="com.bac.quizonline.model.CreatingModelForServletService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nhatn
  Date: 2/16/2021
  Time: 10:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Question</title>
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
            <h4 class="font-bold mt-12 pb-2 border-b border-gray-200 text-2xl">Admin thêm Question</h4>
            <c:if test="${requestScope.subjects == null || requestScope.subjects.size() == 0}">
                <div class="text-primary text-xl mt-5">
                    Bạn chưa được phân quyền bất kì môn học nào
                </div>
            </c:if>
            <c:if test="${requestScope.subjects != null && requestScope.subjects.size() > 0}">
                <div>
                    <form class="mt-7" id="main-form" action="add-question" method="post">
                        <div class="grid grid-cols-12 gap-4">
                            <div class="mb-7 col-span-5">
                                <select name="Select.Subject" id="Select_Subject"
                                        class="border-blue-400 border-b-4 w-full h-9 p-1.5 outline-none bg-gray-100 focus-within:bg-blue-50 focus-within:border-yellow-300">
                                    <c:forEach items="${requestScope.subjects}" var="subject">
                                        <option value="${subject.id}">${subject.name}</option>
                                    </c:forEach>
                                </select>
                                <label for="Select_Subject" class="font-bold text-gray-500 block">Name of
                                    Subject</label>
                            </div>
                            <div class="mb-7">
                                <button type="submit"
                                        class="w-full h-9 p-1.5 outline-none bg-gray-100 bg-primary rounded-br-2xl font-bold text-white">
                                    Save
                                </button>
                            </div>
                        </div>
                        <hr>
                        <h4 class="font-bold mt-6 pb-2">List Question admin muốn thêm</h4>
                        <div>
                            <div>
                                <hr class="mb-4 mt-2 border-t-4 border-blue-800">
                                <div class="mb-7">
                                    <input name="Input.Question1Text" id="Input_Question1Text"
                                           placeholder="Ex: Chọn câu đúng:"
                                           data-val="true"
                                           data-val-required="The Text of Subject field is required."
                                           data-val-length="The Text of Subject must be at least <%=CreatingModelForServletService.MIN_LENGTH_OF_TEXT_QUESTION%> and at max <%=CreatingModelForServletService.MAX_LENGTH_OF_TEXT_QUESTION%> characters long."
                                           data-val-length-max="<%=CreatingModelForServletService.MAX_LENGTH_OF_TEXT_QUESTION%>"
                                           data-val-length-min="<%=CreatingModelForServletService.MIN_LENGTH_OF_TEXT_QUESTION%>"
                                           maxlength="<%=CreatingModelForServletService.MAX_LENGTH_OF_TEXT_QUESTION%>"
                                           class="border-blue-400 border-b-4 w-1/2 h-9 p-1.5 outline-none bg-gray-100 focus-within:bg-blue-50 focus-within:border-yellow-300">
                                    <label for="Input_Question1Text" class="font-bold text-gray-500 block">Text of
                                        Subject</label>
                                    <span class="text-primary field-validation-valid"
                                          data-valmsg-for="Input.Question1Text"
                                          data-valmsg-replace="true"></span>
                                </div>
                                <div class="mb-7">
                                <textarea name="Input.Question1Content" id="Input_Question1Content"
                                          placeholder="Ex: Đâu là 3 chữ cái đầu trong bảng chữ cái tiếng Anh?"
                                          rows="5"
                                          data-val="true"
                                          data-val-required="The Content of Subject field is required."
                                          data-val-length="The Content of Subject must be at least <%=CreatingModelForServletService.MIN_LENGTH_OF_CONTENT_QUESTION%> and at max <%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_QUESTION%> characters long."
                                          data-val-length-max="<%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_QUESTION%>"
                                          data-val-length-min="<%=CreatingModelForServletService.MIN_LENGTH_OF_CONTENT_QUESTION%>"
                                          maxlength="<%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_QUESTION%>"
                                          class="w-full outline-none p-1.5 border-blue-400 border-b-4 bg-gray-100 focus-within:bg-blue-50 focus-within:border-yellow-300"></textarea>
                                    <label for="Input_Question1Content" class="font-bold text-gray-500 block">Content of
                                        Question</label>
                                    <span class="text-primary field-validation-valid"
                                          data-valmsg-for="Input.Question1Content"
                                          data-valmsg-replace="true"></span>
                                </div>
                                <div class="mb-7 ml-4" id="OptionsOfQuestion1">
                                    <input type="hidden" id="count-option-question-1" data-count-option-question-1="2"
                                           value="2">
                                    <div class="mb-4">
                                        <input name="Input.Question1Option1" id="Input_Question1Option1"
                                               placeholder="Option Text"
                                               data-val="true"
                                               data-val-required="The Option Text field is required."
                                               data-val-length="The Option Text must be at least <%=CreatingModelForServletService.MIN_LENGTH_OF_CONTENT_OPTION%> and at max <%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_OPTION%> characters long."
                                               data-val-length-max="<%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_OPTION%>"
                                               data-val-length-min="<%=CreatingModelForServletService.MIN_LENGTH_OF_CONTENT_OPTION%>"
                                               maxlength="<%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_OPTION%>"
                                               class="border-blue-400 border-b-4 w-1/2 h-9 p-1.5 outline-none bg-gray-100 focus-within:bg-blue-50 focus-within:border-yellow-300">
                                        <label class="inline-flex items-center mt-3">
                                            <input type="checkbox" class="form-checkbox h-5 w-5 text-orange-600 ml-4"
                                                   name="CheckBox.Question1RightOption1"><span
                                                class="ml-2 text-gray-700">Right Answer</span>
                                        </label>
                                        <button type="button"
                                                data-id-question="1"
                                                data-option-number="1"
                                                class="h-9 p-1.5 outline-none bg-gray-100 bg-yellow-400 rounded-br-2xl font-bold text-white mt-3 ml-4 delete-option">
                                            Delete
                                        </button>
                                        <span class="text-primary field-validation-valid block"
                                              data-valmsg-for="Input.Question1Option1"
                                              data-valmsg-replace="true"></span>
                                    </div>
                                    <div class="mb-4">
                                        <input name="Input.Question1Option2" id="Input_Question1Option2"
                                               placeholder="Option Text"
                                               data-val="true"
                                               data-val-required="The Option Text field is required."
                                               data-val-length="The Option Text must be at least <%=CreatingModelForServletService.MIN_LENGTH_OF_CONTENT_OPTION%> and at max <%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_OPTION%> characters long."
                                               data-val-length-max="<%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_OPTION%>"
                                               data-val-length-min="<%=CreatingModelForServletService.MIN_LENGTH_OF_CONTENT_OPTION%>"
                                               maxlength="<%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_OPTION%>"
                                               class="border-blue-400 border-b-4 w-1/2 h-9 p-1.5 outline-none bg-gray-100 focus-within:bg-blue-50 focus-within:border-yellow-300">
                                        <label class="inline-flex items-center mt-3">
                                            <input type="checkbox" class="form-checkbox h-5 w-5 text-orange-600 ml-4"
                                                   name="CheckBox.Question1RightOption2"><span
                                                class="ml-2 text-gray-700">Right Answer</span>
                                        </label>
                                        <button type="button"
                                                data-id-question="1"
                                                data-option-number="2"
                                                class="h-9 p-1.5 outline-none bg-gray-100 bg-yellow-400 rounded-br-2xl font-bold text-white mt-3 ml-4 delete-option">
                                            Delete
                                        </button>
                                        <span class="text-primary field-validation-valid block"
                                              data-valmsg-for="Input.Question1Option2"
                                              data-valmsg-replace="true"></span>
                                    </div>
                                </div>
                                <button type="button"
                                        data-id-question="1"
                                        data-option-number="3"
                                        class="h-9 p-1.5 outline-none bg-gray-100 bg-primary rounded-br-2xl font-bold text-white block mt-3 ml-6 add-option inline-block">
                                    Add More Option
                                </button>
                            </div>
                            <button type="button"
                                    data-id-question="1"
                                    class="h-9 p-1.5 outline-none bg-gray-100 bg-red-500 rounded-br-2xl font-bold text-white block mt-5 delete-question inline-block">
                                Delete Question
                            </button>
                        </div>
                    </form>
                    <hr class="mt-7 border-t-4 border-red-300">
                    <div class="mt-4">
                        <button type="button"
                                id="add-question"
                                data-id-question="2"
                                data-number-question="2"
                                class="h-9 p-1.5 outline-none bg-gray-100 bg-blue-400 rounded-br-2xl font-bold text-white block mt-3">
                            Add More Question
                        </button>
                    </div>
                </div>
            </c:if>
        </div>
    </main>
</div>
<jsp:include page="shared/_Jquery.jsp"/>
<jsp:include page="shared/_JqueryValidation.jsp"/>
<jsp:include page="shared/add-questionJs.jsp"/>
</body>
</html>
