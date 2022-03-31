<div class="row my-4 align-items-center">
    <div class="col-3 text-center">
        <h3>
            <a href="/"><fmt:message key="jsp_view.index.title" /></a>
        </h3>
    </div>

    <div class="col-7">
        <%@ include file="/WEB-INF/jsp/components/global_search_form.jsp"%>
    </div>

    <div class="col text-center">
        <small>
            <sec:authorize access="isAnonymous()">
                <a href="/login"><fmt:message key="jsp_view.index.button.login" /></a>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <a href="/person/<c:out value='${sessionScope.user.id}' />"><c:out value="${sessionScope.user.firstName}" /> <c:out value="${sessionScope.user.lastName}" /></a>
            </sec:authorize>
        </small>
    </div>


</div>