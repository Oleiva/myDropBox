<%@ include file="/WEB-INF/pages/partials/taglibs.jsp" %>

<html>
<head>
    <%@ include file="/WEB-INF/pages/partials/head.jsp" %>
</head>
<body>

<%@ include file="/WEB-INF/pages/partials/navigation.jsp" %>

<div class="container">
    <legend>File collection - Edit file permission</legend>

    <div class="row">
        <div class="col-md-9">
            <form:form modelAttribute="fileShareEditForm" class="form-horizontal">
                <fieldset>

                    <div class="form-group">
                        <form:label class="col-md-4 control-label" path="viewList">viewList</form:label>
                        <div class="col-md-4 controls">
                            <form:input path="viewList" id="viewList" type="text" placeholder="" tabindex="1"
                                        class="form-control input-md" value="${filePermissionEditForm.viewList}"/>
                        </div>
                        <form:errors path="viewList" cssClass="has-error" element="small"/>
                    </div>

                    <div class="form-group">
                        <form:label class="col-md-4 control-label" path="editList">editList</form:label>
                        <div class="col-md-4 controls">
                            <form:input path="editList" id="editList" type="text" placeholder="" tabindex="1"
                                        class="form-control input-md" value="${filePermissionEditForm.editList}"/>
                        </div>
                        <form:errors path="editList" cssClass="has-error" element="small"/>
                    </div>

                    <div class="form-group">
                        <form:label class="col-md-4 control-label" path="removeList">removeList</form:label>
                        <div class="col-md-4 controls">
                            <form:input path="removeList" id="removeList" type="text" placeholder="" tabindex="1"
                                        class="form-control input-md" value="${filePermissionEditForm.removeList}"/>
                        </div>
                        <form:errors path="removeList" cssClass="has-error" element="small"/>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label" for="saveButton"></label>

                        <div class="col-md-8">
                            <button id="saveButton" name="saveButton" class="btn btn-success" type="submit">Save file
                                permission
                            </button>
                            <a id="cancel" name="cancel" class="btn btn-danger" href="${filesList}">Cancel</a>
                        </div>
                    </div>

                </fieldset>
            </form:form>
        </div>
        <div class="col-md-3">
            <table class="table">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Username</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userPlainViews}" var="userPlainView">
                    <tr>
                        <td class="col-md-1">${userPlainView.id}</td>
                        <td class="col-md-2">${userPlainView.username}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>


</div>


</body>
</html>