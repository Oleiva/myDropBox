<%@ include file="/WEB-INF/pages/partials/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Login Page</title>
    <%@ include file="/WEB-INF/pages/partials/head.jsp" %>
    <style type="text/css">
        body {
            background-color: #eee;
        }

        .vertical-offset-100 {
            padding-top: 100px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row vertical-offset-100">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please answer a secret question</h3>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="keywordForm">
                        <fieldset>
                            <div class="form-group">
                                <span>${keywordForm.question}</span>
                            </div>
                            <div class="form-group">
                                <form:input id="answer" path="answer" type="answer" placeholder="answer" tabindex="1"
                                            class="form-control input-md"/>
                            </div>
                            <input class="btn btn-lg btn-success btn-block" type="submit" value="Confirm">
                        </fieldset>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>