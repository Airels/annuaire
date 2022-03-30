<%@ include file="/WEB-INF/jsp/header.jsp"%>

<div class="container align-items-center justify-content-center">
    <%@ include file="/WEB-INF/jsp/components/header_banner.jsp"%>

    <div class="row jumbotron fill">
        <div class="col-6 vertical-separated">
            <h2 class="text-center"><fmt:message key="jsp_view.search.global.label.list.persons" /></h2>

            <ul>
                <c:forEach items="${persons}" var="person">
                    <li><a href="/person/<c:out value='${person.id}' />"><c:out value="${person.firstName}" /> <c:out value="${person.lastName}" /></a></li>
                </c:forEach>
            </ul>
        </div>

        <div class="col-6">
            <h2 class="text-center"><fmt:message key="jsp_view.search.global.label.list.groups" /></h2>

            <ul>
                <c:forEach items="${groups}" var="group">
                    <li><a href="/group/<c:out value='${group.id}' />"><c:out value="${group.name}" /></a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>