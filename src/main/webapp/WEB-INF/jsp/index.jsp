<%@ include file="/WEB-INF/jsp/header.jsp"%>

<div class="container fill d-flex align-items-center justify-content-center">
    <div class="row">
        <div class="col-12 text-center">
            <h1>
                <fmt:message key="jsp_view.index.title" />
            </h1>
        </div>

        <div class="col-12 jumbotron">
            <div class="row mb-4">
                <div class="col-12">
                    <%@ include file="/WEB-INF/jsp/components/global_search_form.jsp"%>
                </div>
            </div>

            <div class="row mb-2">
                <div class="col-12 text-center">
                    <%-- <a class="btn btn-info w-25 disabled" href="#"><fmt:message key="jsp_view.index.button.advanced_search" /></a> --%>
                    <sec:authorize access="isAnonymous()">
                        <a class="btn btn-primary w-40" href="/login"><fmt:message key="jsp_view.index.button.login" /></a>
                        <a class="btn btn-primary w-40" href="/account/resetPassword"><fmt:message key="jsp_view.index.button.reset_password" /></a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <a class="btn btn-primary w-40" href="/person/<c:out value='${sessionScope.user.id}' />"><fmt:message key="jsp_view.index.button.user_profile" /></a>
                        <a class="btn btn-primary w-40" href="/logout"><fmt:message key="jsp_view.index.button.logout" /></a>
                    </sec:authorize>
                </div>
            </div>

            <div class="row">
                <div class="col-12 text-center">
                    <a class="btn btn-secondary w-40" href="/person"><fmt:message key="jsp_view.index.button.show_all_users" /></a>
                    <a class="btn btn-secondary w-40" href="/group"><fmt:message key="jsp_view.index.button.show_all_groups" /></a>
                </div>
            </div>

            <sec:authorize access="isAuthenticated()">
                <div class="row mt-4 justify-content-center">
                    <small><fmt:message key="jsp_view.index.label.connected_as" /> <c:out value="${sessionScope.user.firstName}" /> <c:out value="${sessionScope.user.lastName}" /></small>
                </div>
            </sec:authorize>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>