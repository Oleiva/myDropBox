<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${filesList}">Box</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li>
                        <a href="${usersList}" class="text-primary">User management</a>
                    </li>
                </sec:authorize>
                <li id="uploadFile">
                    <a class="text-primary">
                        <form:form modelAttribute="uploadForm" enctype="multipart/form-data" action="${upload}"
                                   id="uploadForm">
                            <span class="glyphicon glyphicon-cloud-upload"></span> Upload file <form:input type="file"
                                                                                                           path="multipartFile"/>
                        </form:form>
                    </a>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="#">
                        <span class="glyphicon glyphicon-user"></span>
                        <sec:authentication property="principal.username"/>
                    </a>
                </li>
                <li><a href="${logout}">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>