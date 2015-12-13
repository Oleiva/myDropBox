<%@ page import="io.github.oleiva.myDropBox.users.authentication.support.Authorities" %>
<%@ include file="/WEB-INF/pages/partials/taglibs.jsp" %>

<html>
<head>
    <%@ include file="/WEB-INF/pages/partials/head.jsp" %>
</head>
<body>

<%@ include file="/WEB-INF/pages/partials/navigation.jsp" %>

<div class="container">
    <legend>User collection - Edit user</legend>


    <form:form modelAttribute="userEditForm" class="form-horizontal">
        <fieldset>

            <div class="form-group">
                <form:label class="col-md-4 control-label" path="username">Username</form:label>
                <div class="col-md-4 controls">
                    <form:input path="username" id="username" type="text" placeholder="" tabindex="1"
                                class="form-control input-md" value="${userEditForm.username}"/>
                </div>
                <form:errors path="username" cssClass="has-error" element="small"/>
            </div>

            <div class="form-group">
                <form:label class="col-md-4 control-label" path="email">Email</form:label>
                <div class="col-md-4 controls">
                    <form:input id="email" path="email" type="email" placeholder="" tabindex="2"
                                class="form-control input-md" value="${userEditForm.email}"/>
                </div>
                <form:errors path="email" cssClass="has-error" element="small"/>
            </div>

            <div class="form-group">
                <form:label class="col-md-4 control-label" path="firstName">First name</form:label>
                <div class="col-md-4 controls">
                    <form:input path="firstName" id="firstName" type="text" placeholder="" tabindex="3"
                                class="form-control input-md" value="${userEditForm.firstName}"/>
                </div>
                <form:errors path="firstName" cssClass="has-error" element="small"/>
            </div>

            <div class="form-group">
                <form:label class="col-md-4 control-label" path="lastName">Last name</form:label>
                <div class="col-md-4 controls">
                    <form:input path="lastName" id="lastName" type="text" placeholder="" tabindex="4"
                                class="form-control input-md" value="${userEditForm.lastName}"/>
                </div>
                <form:errors path="lastName" cssClass="has-error" element="small"/>
            </div>

            <div class="form-group">
                <form:label class="col-md-4 control-label" path="authority">Authority</form:label>
                <div class="col-md-4 controls">
                    <form:select path='authority' class="form-control" tabindex="5">
                        <c:set var="authorities" value="<%=Authorities.values()%>"/>
                        <c:forEach items="${authorities}" var="authority">
                            <option value="${authority.value}" ${userEditForm.authority == authority.value ? 'selected' : ''}>${authority}</option>
                        </c:forEach>
                    </form:select>
                </div>
                <form:errors path="authority" cssClass="has-error" element="small"/>
            </div>

            <div class="form-group">
                <form:label class="col-md-4 control-label" path="secretQuestion">Secret Question</form:label>
                <div class="col-md-4 controls">
                    <form:input path="secretQuestion" id="secretQuestion" type="text" placeholder="" tabindex="6"
                                class="form-control input-md" value="${userEditForm.secretQuestion}"/>
                </div>
                <form:errors path="secretQuestion" cssClass="has-error" element="small"/>
            </div>

            <div class="form-group">
                <form:label class="col-md-4 control-label" path="secretAnswer">Secret Answer</form:label>
                <div class="col-md-4 controls">
                    <form:input path="secretAnswer" id="secretAnswer" type="text" placeholder="" tabindex="7"
                                class="form-control input-md" value="${userEditForm.secretAnswer}"/>
                </div>
                <form:errors path="secretAnswer" cssClass="has-error" element="small"/>
            </div>


            <div class="form-group">
                <form:label class="col-md-4 control-label" path="enabled">Enabled</form:label>
                <div class="col-md-4 controls">
                    <form:select path='enabled' tabindex="8" class="form-control">
                        <option value="1" ${userEditForm.enabled == 1 ? 'selected' : ''}>true</option>
                        <option value="0" ${userEditForm.enabled == 0 ? 'selected' : ''}>false</option>
                    </form:select>
                </div>
                <form:errors path="enabled" cssClass="has-error" element="small"/>
            </div>

            <div class="form-group">
                <form:label class="col-md-4 control-label" path="accountNonExpired">Account Non Expired</form:label>
                <div class="col-md-4 controls">
                    <form:select path='accountNonExpired' tabindex="9" class="form-control">
                        <option value="1" ${userEditForm.accountNonExpired == 1 ? 'selected' : ''}>true</option>
                        <option value="0" ${userEditForm.accountNonExpired == 0 ? 'selected' : ''}>false</option>
                    </form:select>
                </div>
                <form:errors path="accountNonExpired" cssClass="has-error" element="small"/>
            </div>

            <div class="form-group">
                <form:label class="col-md-4 control-label" path="accountNonLocked">Account Non Locked</form:label>
                <div class="col-md-4 controls">
                    <form:select path='accountNonLocked' tabindex="10" class="form-control">
                        <option value="1" ${userEditForm.accountNonLocked == 1 ? 'selected' : ''}>true</option>
                        <option value="0" ${userEditForm.accountNonLocked == 0 ? 'selected' : ''}>false</option>
                    </form:select>
                </div>
                <form:errors path="accountNonLocked" cssClass="has-error" element="small"/>
            </div>

            <div class="form-group">
                <form:label class="col-md-4 control-label"
                            path="credentialsNonExpired">Credentials Non Expired</form:label>
                <div class="col-md-4 controls">
                    <form:select path='credentialsNonExpired' tabindex="11" class="form-control">
                        <option value="1" ${userEditForm.credentialsNonExpired == 1 ? 'selected' : ''}>true</option>
                        <option value="0" ${userEditForm.credentialsNonExpired == 0 ? 'selected' : ''}>false</option>
                    </form:select>
                </div>
                <form:errors path="credentialsNonExpired" cssClass="has-error" element="small"/>
            </div>


            <div class="form-group">
                <form:label class="col-md-4 control-label" path="oldPassword">Password</form:label>
                <div class="col-md-4 controls">
                    <form:input id="oldPassword" path="oldPassword" type="password" placeholder="" tabindex="12"
                                class="form-control input-md"/>
                    <span class="help-block">Required to commit changes</span>
                </div>
                <form:errors path="oldPassword" cssClass="has-error" element="small"/>
            </div>

            <div class="form-group">
                <form:label class="col-md-4 control-label" path="newPassword">Confirm Password</form:label>
                <div class="col-md-4 controls">
                    <form:input id="newPassword" path="newPassword" type="password" placeholder="" tabindex="13"
                                class="form-control input-md"/>
                    <span class="help-block">Confirm or Enter New Password</span>
                </div>
                <form:errors path="newPassword" cssClass="has-error" element="small"/>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label">Last update</label>

                <div class="col-md-4 controls">
                    <span class="span-control">${userEditForm.lastUpdate}</span>
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-4 control-label" for="saveButton"></label>

                <div class="col-md-8">
                    <button id="saveButton" name="saveButton" class="btn btn-success" type="submit">Save user</button>
                    <a id="cancel" name="cancel" class="btn btn-danger" href="${usersList}">Cancel</a>
                </div>
            </div>

        </fieldset>
    </form:form>

</div>


</body>
</html>