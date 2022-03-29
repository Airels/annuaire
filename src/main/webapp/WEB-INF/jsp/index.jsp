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
                    <form method="GET" action="#">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="John Doe, AMU Information..." required />
                            <div class="input-group-append">
                                <input type="submit" class="btn btn-primary" value="<fmt:message key="jsp_view.index.button.search" />" />
                            </div>
                        </div>
                    </form>
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
                    <a class="btn btn-secondary w-25" href="#"><fmt:message key="jsp_view.index.button.show_all_users" /></a>
                    <a class="btn btn-secondary w-25" href="#"><fmt:message key="jsp_view.index.button.show_all_groups" /></a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>