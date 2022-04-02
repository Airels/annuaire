<%@ include file="/WEB-INF/jsp/header.jsp"%>
<fmt:setBundle basename="person" var="persons_view" />

<div class="container">
    <%@ include file="/WEB-INF/jsp/components/header_banner.jsp"%>

    <div class="jumbotron">
        <h1><fmt:message key="person.form.title" bundle="${persons_view}" /></h1>

        <form:form method="POST" modelAttribute="person">
            <form:errors path="*" cssClass="alert alert-danger" element="div" />

            <div class="form-group">
                <label for="email"><fmt:message key="person.email" bundle="${persons_view}" />:</label>
                <form:input type="email" class="form-control" path="email" readonly="true" />
                <form:errors path="email" cssClass="alert alert-warning" element="div" />
            </div>
            <div class="form-group">
                <label for="password"><fmt:message key="person.form.password" bundle="${persons_view}" />:</label>
                <form:password class="form-control" path="password" autocomplete="new-password" />
                <form:errors path="password" cssClass="alert alert-warning" element="div" />
            </div>
            <div class="form-group">
                <label for="repeatPassword"><fmt:message key="person.form.repeat_password" bundle="${persons_view}" />:</label>
                <form:password class="form-control" path="repeatPassword" autocomplete="new-password" />
                <form:errors path="repeatPassword" cssClass="alert alert-warning" element="div" />
            </div>
            <div class="form-group">
                <label for="firstName"><fmt:message key="person.form.first_name" bundle="${persons_view}" />:</label>
                <form:input class="form-control" path="firstName" />
                <form:errors path="firstName" cssClass="alert alert-warning" element="div" />
            </div>
            <div class="form-group">
                <label for="lastName"><fmt:message key="person.form.last_name" bundle="${persons_view}" />:</label>
                <form:input class="form-control" path="lastName" />
                <form:errors path="lastName" cssClass="alert alert-warning" element="div" />
            </div>
            <div class="form-group">
                <label for="birthday"><fmt:message key="person.birthday" bundle="${persons_view}" />:</label>
                <form:input type="date" class="form-control" path="birthday" />
                <form:errors path="birthday" cssClass="alert alert-warning" element="div" />
            </div>
            <div class="form-group">
                <label for="website"><fmt:message key="person.website" bundle="${persons_view}" />:</label>
                <form:input class="form-control" path="website" pattern="${websiteURLPattern}" />
                <form:errors path="website" cssClass="alert alert-warning" element="div" />
            </div>
            <div class="form-group mt-4">
                <form:hidden path="roles" />
                <input type="submit" class="btn btn-primary w-100" value="<fmt:message key='person.form.button.submit' bundle='${persons_view}' />" />
                <a class="btn btn-secondary w-100 mt-2" href="/person/<c:out value='${sessionScope.user.id}' />"><fmt:message key='person.form.button.back' bundle='${persons_view}' /></a>
            </div>
        </form:form>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>