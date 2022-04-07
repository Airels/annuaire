<%@ include file="/WEB-INF/jsp/header.jsp"%>

<div class="container fill d-flex align-items-center justify-content-center">
    <div class="row">
        <div class="col-12 jumbotron w-50">
            <h1><fmt:message key="password_reset.success.title" bundle="${password_reset}" /></h1>
            <span><fmt:message key="password_reset.success.description" bundle="${password_reset}" /></span>
        </div>

        <div class="col-12 jumbotron w-50">
            <h1 class="text-info">A l'attention des enseignants</h1>
            <p>
                �tant donn� que des E-Mails bidons sont g�n�r�s afin de pouvoir r�cup�rer son mot de passe, <br />
                il est donc impossible de cliquer sur le lien demand� par l'application. <br />
                Le lien permettant de r�initialiser le mot de passe est donc le suivant : <br />
            </p>
            <a class="btn btn-info" href="/account/resetPasswordConfirm?token=<c:out value='${token}' />">R�initialiser mot de passe</a>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>