<%@ include file="/WEB-INF/jsp/header.jsp"%>

<div class="container">
    <%@ include file="/WEB-INF/jsp/components/header_banner.jsp"%>

    <div class="jumbotron">
        <h1><fmt:message key="jsp_view.groups.all" /></h1>

        <ul>
            <c:forEach items="${groups}" var="group">
                <li><a href="/group/<c:out value='${group.id}' />"><c:out value="${group.name}" /></a></li>
            </c:forEach>
        </ul>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>