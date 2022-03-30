<%@ include file="/WEB-INF/jsp/header.jsp"%>

<div class="container fill d-flex align-items-center justify-content-center">
    <div class="row">
        <div class="col-12 text-center">
            <h1>
                <fmt:message key="jsp_view.index.title" />
            </h1>
        </div>

        <div class="col-12 jumbotron">
            <div class="row mb-2">
                <div class="col-12">
                    <%@ include file="/WEB-INF/jsp/components/global_search_form.jsp"%>
                </div>
            </div>

            <div class="row mb-2">
                <div class="col-12 text-center">
                    <a class="btn btn-info w-25" href="#"><fmt:message key="jsp_view.index.button.advanced_search" /></a>
                    <a class="btn btn-primary w-25" href="#"><fmt:message key="jsp_view.index.button.login" /></a>
                    <a class="btn btn-primary w-25" href="#"><fmt:message key="jsp_view.index.button.register" /></a>
                </div>
            </div>

            <div class="row">
                <div class="col-12 text-center">
                    <a class="btn btn-secondary w-25" href="/person"><fmt:message key="jsp_view.index.button.show_all_users" /></a>
                    <a class="btn btn-secondary w-25" href="/group"><fmt:message key="jsp_view.index.button.show_all_groups" /></a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>