<%@ include file="/WEB-INF/pages/partials/taglibs.jsp" %>

<html>
<head>
    <%@ include file="/WEB-INF/pages/partials/head.jsp" %>
</head>
<body>

<%@ include file="/WEB-INF/pages/partials/navigation.jsp" %>

<div class="container">
    <legend>User collection - Add user</legend>


    <form:form modelAttribute="userAddForm" class="form-horizontal">
        <fieldset>

            <div class="form-group">
                <form:label class="col-md-4 control-label" path="username">Username</form:label>
                <div class="col-md-4 controls">
                    <form:input path="username" id="username" type="text" placeholder="" tabindex="1"
                                class="form-control input-md" value="${userAddForm.username}"/>
                </div>
                <form:errors path="username" cssClass="has-error" element="small"/>
            </div>

            <div class="form-group">
                <form:label class="col-md-4 control-label" path="password">Password</form:label>
                <div class="col-md-4 controls">
                    <form:input id="password" path="password" type="password" placeholder="" tabindex="2"
                                class="form-control input-md"/>
                </div>
                <form:errors path="password" cssClass="has-error" element="small"/>

            </div>

            <div class="form-group">
                <form:label class="col-md-4 control-label" path="confirmPassword">Confirm Password</form:label>
                <div class="col-md-4 controls">
                    <form:input id="confirmPassword" path="confirmPassword" type="password" placeholder="" tabindex="3"
                                class="form-control input-md"/>
                </div>
                <form:errors path="confirmPassword" cssClass="has-error" element="small"/>
            </div>

            <div class="form-group">
                <form:label class="col-md-4 control-label" path="email">Email</form:label>
                <div class="col-md-4 controls">
                    <form:input id="email" path="email" type="email" placeholder="" tabindex="4"
                                class="form-control input-md" value="${userAddForm.email}"/>
                </div>
                <form:errors path="email" cssClass="has-error" element="small"/>
            </div>

            <div class="form-group">
                <form:label class="col-md-4 control-label" path="secretQuestion">Secret question</form:label>
                <div class="col-md-4 controls">
                    <form:input path="secretQuestion" id="secretQuestion" type="text" placeholder="" tabindex="5"
                                class="
                    form-control input-md" value="${userAddForm.secretQuestion}"/>
                </div>
                <form:errors path="secretQuestion" cssClass="has-error" element="small"/>
            </div>

            <div class="form-group">
                <form:label class="col-md-4 control-label" path="secretAnswer">Username</form:label>
                <div class="col-md-4 controls">
                    <form:input path="secretAnswer" id="secretAnswer" type="text" placeholder="" tabindex="6"
                                class="form-control input-md" value="${userAddForm.secretAnswer}"/>
                </div>
                <form:errors path="secretAnswer" cssClass="has-error" element="small"/>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="addButton"></label>

                <div class="col-md-8">
                    <button id="addButton" name="addButton" class="btn btn-success" type="submit">Add user</button>
                    <a id="cancel" name="cancel" class="btn btn-danger" href="${usersList}">Cancel</a>
                </div>
            </div>


        </fieldset>
    </form:form>


</div>


</body>
</html>
