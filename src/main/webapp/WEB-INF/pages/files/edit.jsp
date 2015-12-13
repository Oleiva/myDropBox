<%@ include file="/WEB-INF/pages/partials/taglibs.jsp" %>

<html>
<head>
    <%@ include file="/WEB-INF/pages/partials/head.jsp" %>
</head>
<body>

<%@ include file="/WEB-INF/pages/partials/navigation.jsp" %>

<div class="container">
    <legend>File collection - Edit file</legend>

    <form:form modelAttribute="fileEditForm" class="form-horizontal">
        <fieldset>

            <div class="form-group">
                <form:label class="col-md-4 control-label" path="filename">Filename</form:label>
                <div class="col-md-4 controls">
                    <form:input path="filename" id="filename" type="text" placeholder="" tabindex="1"
                                class="form-control input-md" value="${fileEditForm.filename}"/>
                </div>
                <form:errors path="filename" cssClass="has-error" element="small"/>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="saveButton"></label>

                <div class="col-md-8">
                    <button id="saveButton" name="saveButton" class="btn btn-success" type="submit">Save file</button>
                    <a id="cancel" name="cancel" class="btn btn-danger" href="${filesList}">Cancel</a>
                </div>
            </div>


        </fieldset>
    </form:form>


</div>


</body>
</html>