<%@ page import="com.bac.quizonline.model.CreatingModelForServletService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $addOption = $('.add-option')
    $(document).on('click', '.add-option', function () {
        const idQuestion = $(this).data('id-question');
        const optionNumber = $(this).data('option-number')
        $('#OptionsOfQuestion' + idQuestion).append('<div class="mb-4">\n' +
            '<input name="Input.Question' + idQuestion + 'Option' + optionNumber + '" id="Input_Question' + idQuestion + 'Answer' + optionNumber + '"\n' +
            'placeholder="Option Text"\n' +
            '                                       data-val="true"\n' +
            '                                       data-val-required="The Option Text field is required."\n' +
            '                                       data-val-length="The Option Text must be at least <%=CreatingModelForServletService.MIN_LENGTH_OF_CONTENT_OPTION%> and at max <%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_OPTION%> characters long."\n' +
            '                                       data-val-length-max="<%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_OPTION%>"\n' +
            '                                       data-val-length-min="<%=CreatingModelForServletService.MIN_LENGTH_OF_CONTENT_OPTION%>"\n' +
            '                                       maxlength="<%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_OPTION%>"\n' +
            'class="border-blue-400 border-b-4 w-1/2 h-9 p-1.5 outline-none bg-gray-100 focus-within:bg-blue-50 focus-within:border-yellow-300">\n' +
            '<label class="inline-flex items-center mt-3">\n' +
            '<input type="checkbox" class="form-checkbox h-5 w-5 text-orange-600 ml-4" name="CheckBox.Question' + idQuestion + 'RightOption' + optionNumber + '"><span class="ml-2 text-gray-700">Right Answer</span>\n' +
            '</label>\n' +
            '<button type="button"\n' +
            'data-id-question="' + idQuestion + '"\n' +
            'data-option-number="' + optionNumber + '"\n' +
            'class="h-9 p-1.5 outline-none bg-gray-100 bg-yellow-400 rounded-br-2xl font-bold text-white mt-3 ml-4 delete-option">\n' +
            'Delete\n' +
            '</button>\n' +
            '                                    <span class="text-primary field-validation-valid block" data-valmsg-for="Input.Question' + idQuestion + 'Option' + optionNumber + '"\n' +
            '                                          data-valmsg-replace="true"></span>\n' +
            '</div>');
        $('button').find()
        $(this).data('option-number', optionNumber + 1)
        const $countOption = $('#count-option-question-' + idQuestion)
        const numberOption = $countOption.data('count-option-question-' + idQuestion)
        $countOption.data('count-option-question-' + idQuestion, numberOption + 1)
        const $mainForm = $('#main-form')
        $mainForm.removeData('validator');
        $mainForm.removeData('unobtrusiveValidation');
        $.validator.unobtrusive.parse($mainForm);
    })


    $(document).on('click', '.delete-option', function () {
        const idQuestion = $(this).data('id-question');
        const $countOption = $('#count-option-question-' + idQuestion)
        const count = $countOption.data('count-option-question-' + idQuestion);
        if (count <= 2) {
            alert("Phải có ít nhất 2 option")
            return;
        }
        $(this).parent().remove()
        $countOption.data('count-option-question-' + idQuestion, count + -1)
    })

    $('#add-question').on('click', function () {
        const idQuestion = $(this).data('id-question')
        const numberQuestion = $(this).data('number-question')
        const $mainForm = $('#main-form')
        $mainForm.append('<div>\n' +
            '                        <div>\n' +
            '<hr class="mb-4 mt-2 border-t-4 border-blue-800">\n' +
            '                            <div class="mb-7">\n' +
            '                                <input name="Input.Question' + idQuestion + 'Text" id="Input_Question' + idQuestion + 'Text"\n' +
            '                                       placeholder="Ex: Chọn câu đúng:"\n' +
            '                                       data-val="true"\n' +
            '                                       data-val-required="The Text of Subject field is required."\n' +
            '                                       data-val-length="The Text of Subject must be at least <%=CreatingModelForServletService.MIN_LENGTH_OF_TEXT_QUESTION%> and at max <%=CreatingModelForServletService.MAX_LENGTH_OF_TEXT_QUESTION%> characters long."\n' +
            '                                       data-val-length-max="<%=CreatingModelForServletService.MAX_LENGTH_OF_TEXT_QUESTION%>"\n' +
            '                                       data-val-length-min="<%=CreatingModelForServletService.MIN_LENGTH_OF_TEXT_QUESTION%>"\n' +
            '                                       maxlength="<%=CreatingModelForServletService.MAX_LENGTH_OF_TEXT_QUESTION%>"\n' +
            '                                       class="border-blue-400 border-b-4 w-1/2 h-9 p-1.5 outline-none bg-gray-100 focus-within:bg-blue-50 focus-within:border-yellow-300">\n' +
            '                                <label for="Input_Question' + idQuestion + 'Text" class="font-bold text-gray-500 block">Text of\n' +
            '                                    Subject</label>\n' +
            '                                <span class="text-primary field-validation-valid" data-valmsg-for="Input.Question' + idQuestion + 'Text"\n' +
            '                                      data-valmsg-replace="true"></span>\n' +
            '                            </div>\n' +
            '                            <div class="mb-7">\n' +
            '                                <textarea name="Input.Question' + idQuestion + 'Content" id="Input_Question' + idQuestion + 'Content"\n' +
            '                                          placeholder="Ex: Đâu là 3 chữ cái đầu trong bảng chữ cái tiếng Anh?"\n' +
            '                                          rows="5"\n' +
            '                                       data-val="true"\n' +
            '                                       data-val-required="The Content of Subject field is required."\n' +
            '                                       data-val-length="The Content of Subject must be at least <%=CreatingModelForServletService.MIN_LENGTH_OF_CONTENT_QUESTION%> and at max <%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_QUESTION%> characters long."\n' +
            '                                       data-val-length-max="<%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_QUESTION%>"\n' +
            '                                       data-val-length-min="<%=CreatingModelForServletService.MIN_LENGTH_OF_CONTENT_QUESTION%>"\n' +
            '                                       maxlength="<%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_QUESTION%>"\n' +
            '                                          class="w-full outline-none p-1.5 border-blue-400 border-b-4 bg-gray-100 focus-within:bg-blue-50 focus-within:border-yellow-300"></textarea>\n' +
            '                                <label for="Input_Question' + idQuestion + 'Content" class="font-bold text-gray-500 block">Content of\n' +
            '                                    Subject</label>\n' +
            '                                <span class="text-primary field-validation-valid" data-valmsg-for="Input.Question' + idQuestion + 'Content"\n' +
            '                                      data-valmsg-replace="true"></span>\n' +
            '                            </div>\n' +
            '                            <div class="mb-7 ml-4" id="OptionsOfQuestion' + idQuestion + '">\n' +
            '                                <div id="count-option-question-' + idQuestion + '" data-count-option-question-' + idQuestion + '="2"></div>\n' +
            '                                <div class="mb-4">\n' +
            '                                    <input name="Input.Question' + idQuestion + 'Option1" id="Input_Question' + idQuestion + 'Option1"\n' +
            '                                           placeholder="Option Text"\n' +
            '                                       data-val="true"\n' +
            '                                       data-val-required="The Option Text field is required."\n' +
            '                                       data-val-length="The Option Text must be at least <%=CreatingModelForServletService.MIN_LENGTH_OF_CONTENT_OPTION%> and at max <%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_OPTION%> characters long."\n' +
            '                                       data-val-length-max="<%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_OPTION%>"\n' +
            '                                       data-val-length-min="<%=CreatingModelForServletService.MIN_LENGTH_OF_CONTENT_OPTION%>"\n' +
            '                                       maxlength="<%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_OPTION%>"\n' +
            '                                           class="border-blue-400 border-b-4 w-1/2 h-9 p-1.5 outline-none bg-gray-100 focus-within:bg-blue-50 focus-within:border-yellow-300">\n' +
            '                                    <label class="inline-flex items-center mt-3">\n' +
            '                                        <input type="checkbox" class="form-checkbox h-5 w-5 text-orange-600 ml-4"\n' +
            '                                               name="CheckBox.Question' + idQuestion + 'RightOption1"><span class="ml-2 text-gray-700">Right Answer</span>\n' +
            '                                    </label>\n' +
            '                                    <button type="button"\n' +
            '                                            data-id-question="' + idQuestion + '"\n' +
            '                                            data-option-number="1"\n' +
            '                                            class="h-9 p-1.5 outline-none bg-gray-100 bg-yellow-400 rounded-br-2xl font-bold text-white mt-3 ml-4 delete-option">\n' +
            '                                        Delete\n' +
            '                                    </button>\n' +
            '                                    <span class="text-primary field-validation-valid block" data-valmsg-for="Input.Question' + idQuestion + 'Option1"\n' +
            '                                          data-valmsg-replace="true"></span>\n' +
            '                                </div>\n' +
            '                                <div class="mb-4">\n' +
            '                                    <input name="Input.Question' + idQuestion + 'Option2" id="Input_Question' + idQuestion + 'Option2"\n' +
            '                                           placeholder="Option Text"\n' +
            '                                       data-val="true"\n' +
            '                                       data-val-required="The Option Text field is required."\n' +
            '                                       data-val-length="The Option Text must be at least <%=CreatingModelForServletService.MIN_LENGTH_OF_CONTENT_OPTION%> and at max <%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_OPTION%> characters long."\n' +
            '                                       data-val-length-max="<%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_OPTION%>"\n' +
            '                                       data-val-length-min="<%=CreatingModelForServletService.MIN_LENGTH_OF_CONTENT_OPTION%>"\n' +
            '                                       maxlength="<%=CreatingModelForServletService.MAX_LENGTH_OF_CONTENT_OPTION%>"\n' +
            '                                           class="border-blue-400 border-b-4 w-1/2 h-9 p-1.5 outline-none bg-gray-100 focus-within:bg-blue-50 focus-within:border-yellow-300">\n' +
            '                                    <label class="inline-flex items-center mt-3">\n' +
            '                                        <input type="checkbox" class="form-checkbox h-5 w-5 text-orange-600 ml-4"\n' +
            '                                               name="CheckBox.Question' + idQuestion + 'RightOption2"><span class="ml-2 text-gray-700">Right Answer</span>\n' +
            '                                    </label>\n' +
            '                                    <button type="button"\n' +
            '                                            data-id-question="' + idQuestion + '"\n' +
            '                                            data-option-number="2"\n' +
            '                                            class="h-9 p-1.5 outline-none bg-gray-100 bg-yellow-400 rounded-br-2xl font-bold text-white mt-3 ml-4 delete-option">\n' +
            '                                        Delete\n' +
            '                                    </button>\n' +
            '                                    <span class="text-primary field-validation-valid block" data-valmsg-for="Input.Question' + idQuestion + 'Option2"\n' +
            '                                          data-valmsg-replace="true"></span>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                            <button type="button"\n' +
            '                                    data-id-question="' + idQuestion + '"\n' +
            '                                    data-option-number="3"\n' +
            '                                    class="h-9 p-1.5 outline-none bg-gray-100 bg-primary rounded-br-2xl font-bold text-white block mt-3 ml-6 add-option">\n' +
            '                                Add More Option\n' +
            '                            </button>\n' +
            '                        </div>\n' +
            '                        <button type="button"\n' +
            '                                data-id-question="' + idQuestion + '"\n' +
            '                                class="h-9 p-1.5 outline-none bg-gray-100 bg-red-500 rounded-br-2xl font-bold text-white block mt-5 delete-question inline-block">\n' +
            '                            Delete Question\n' +
            '                        </button>' +
            '</div>');
        $(this).data('id-question', idQuestion + 1)
        $(this).data('number-question', numberQuestion + 1)
        $mainForm.removeData('validator');
        $mainForm.removeData('unobtrusiveValidation');
        $.validator.unobtrusive.parse($mainForm);
    })

    $(document).on('click', '.delete-question', function () {
        const $addButton = $('#add-question')
        const numberQuestion = $addButton.data('number-question')
        console.log(numberQuestion)
        if (numberQuestion === 2) {
            alert('Cần có ít nhất 1 question!')
            return;
        }

        $(this).parent().remove();
        $addButton.data('number-question', numberQuestion - 1)
    })

</script>