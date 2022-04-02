<%@ include file="/WEB-INF/jsp/header.jsp"%>
<fmt:setBundle basename="person" var="persons_view" />

<div class="container">
    <%@ include file="/WEB-INF/jsp/components/header_banner.jsp"%>

    <div class="jumbotron">
        <h1><c:out value="${person.firstName}" /> <c:out value="${person.lastName}" /></h1>

        <div class="row my-4">
            <sec:authorize access="isAuthenticated()">
                <span class="col"><fmt:message key="person.email" bundle="${persons_view}" />: <a href="mailto:<c:out value='${person.email}' />"><c:out value="${person.email}" /></a></span>

                <c:if test="${person.birthday != null}">
                    <span class="col"><fmt:message key="person.birthday" bundle="${persons_view}" />: <c:out value="${person.birthday}" /></span>
                </c:if>
            </sec:authorize>

            <c:if test="${person.website != null}">
                <span class="col"><fmt:message key="person.website" bundle="${persons_view}" />: <a href="<c:out value='${person.website}' />"><c:out value='${person.website}' /></a></span>
            </c:if>
        </div>

        <details open>
            <summary><fmt:message key="person.group_list" bundle="${persons_view}" /></summary>

            <ul>
                <c:forEach items="${groups}" var="group">
                    <li><a href="/group/<c:out value='${group.id}' />"><c:out value="${group.name}" /></a></li>
                </c:forEach>
            </ul>
        </details>

        <sec:authorize access="isAuthenticated() && #id == principal.person.id">
            <a class="btn btn-primary mt-4 w-100" href="/person/<c:out value='${person.id}' />/edit"><fmt:message key="person.button.update" bundle="${persons_view}" /></a>
        </sec:authorize>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>