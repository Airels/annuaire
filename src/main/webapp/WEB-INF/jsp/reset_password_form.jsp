<%@ include file="/WEB-INF/jsp/header.jsp"%>

<div class="container fill d-flex align-items-center justify-content-center">
    <div class="jumbotron w-50">
        <h1><fmt:message key="password_reset.form.title" bundle="${password_reset}" /></h1>

        <p><fmt:message key="password_reset.form.description" bundle="${password_reset}" /></p>

        <form:form method="POST" modelAttribute="user">
            <div class="form-group">
                <label for="email"><fmt:message key="password_reset.form.email" bundle="${password_reset}" /></label>
                <form:input type="email" class="form-control" path="email" id="email" />
                <form:errors path="email" cssClass="alert alert-warning" element="div" />
            </div>

            <div class="form-group mt-4">
                <input type="submit" class="btn btn-primary w-100" value="<fmt:message key="password_reset.form.submit" bundle="${password_reset}" />" />
                <a class="btn btn-secondary w-100 mt-2" href="/"><fmt:message key="password_reset.form.cancel" bundle="${password_reset}" /></a>
            </div>
        </form:form>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>