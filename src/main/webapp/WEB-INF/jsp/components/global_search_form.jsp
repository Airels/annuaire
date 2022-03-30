<form method="GET" action="/search">
    <div class="input-group">
        <input type="text" class="form-control" id="q" name="q" placeholder="John Doe, AMU Information..." value="<c:out value='${query}' />" required />
        <div class="input-group-append">
            <input type="submit" class="btn btn-primary" value="<fmt:message key="jsp_view.index.button.search" />" />
        </div>
    </div>
</form>