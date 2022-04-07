<%@ include file="/WEB-INF/jsp/header.jsp"%>

<div class="container fill d-flex align-items-center justify-content-center">
    <div class="jumbotron w-50">
        <h1><fmt:message key="password_reset.new_password_form.title" bundle="${password_reset}" /></h1>

        <form:form method="POST" modelAttribute="newPasswordResetForm">
            <form:errors path="*" cssClass="alert alert-danger" element="div" />

            <div class="form-group">
                <label for="password"><fmt:message key="password_reset.new_password_form.password" bundle="${password_reset}" /></label>
                <form:password class="form-control" path="password" autocomplete="new-password" />
                <form:errors path="password" cssClass="alert alert-warning" element="div" />
            </div>
            <div class="form-group">
                <label for="repeatPassword"><fmt:message key="password_reset.new_password_form.repeat_password" bundle="${password_reset}" /></label>
                <form:password class="form-control" path="repeatPassword" autocomplete="new-password" />
                <form:errors path="repeatPassword" cssClass="alert alert-warning" element="div" />
            </div>
            <div class="form-group mt-4">
                <input type="submit" class="btn btn-primary w-100" value="<fmt:message key="password_reset.new_password_form.submit" bundle="${password_reset}" />" />
                <a class="btn btn-secondary w-100 mt-2" href="/"><fmt:message key="password_reset.new_password_form.cancel" bundle="${password_reset}" /></a>
            </div>
        </form:form>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>