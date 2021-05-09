<script src="${pageContext.request.contextPath}/node_modules/jquery-validation/dist/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/node_modules/jquery-validation-unobtrusive/dist/jquery.validate.unobtrusive.min.js"></script>
<script>
    $.validator.addMethod("email", function (email) {
        email = email.replace(/\s+/g, "");
        return email.match(/^\b[\w.%-]+@[-.\w]+\.[A-Za-z]{2,4}\b$/);
    }, "Please specify a valid email");
</script>