<%@ include file="/WEB-INF/jsp/header.jsp"%>

<div class="container fill d-flex align-items-center justify-content-center">
    <div class="row">
        <div class="col-12 jumbotron w-50">
            <h1><fmt:message key="password_reset.new_password_success.title" bundle="${password_reset}" /></h1>
            <span><fmt:message key="password_reset.new_password_success.description" bundle="${password_reset}" /></span>
            <a class="btn btn-primary w-100" href="/login"><fmt:message key="password_reset.new_password_success.login_button" bundle="${password_reset}" /></a>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>