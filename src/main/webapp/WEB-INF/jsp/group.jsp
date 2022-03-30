<%@ include file="/WEB-INF/jsp/header.jsp"%>

<div class="container">
    <%@ include file="/WEB-INF/jsp/components/header_banner.jsp"%>

    <div class="jumbotron">
        <h1><c:out value="${group.name}" /></h1>
        <ul>
            <c:forEach items="${group.persons}" var="person">
                <li><a href="/person/<c:out value='${person.id}' />"><c:out value="${person.firstName}" /> <c:out value="${person.lastName}" /></a></li>
            </c:forEach>
        </ul>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>