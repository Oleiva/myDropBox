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
                    <h3 class="panel-title">Please sign in</h3>
                </div>
                <div class="panel-body">
                    <form accept-charset="UTF-8" action="${authenticator}" method="POST">
                        <fieldset>
                            <div class="has-error"
                                 style="font-size: small;margin-bottom: 10px;text-align: right;">${message}</div>

                            <div class="form-group">
                                <input class="form-control" placeholder="E-mail" name="j_username" type="text">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Password" name="j_password" type="password"
                                       value="">
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input name="_spring_security_remember_me" type="checkbox" value="Remember Me">
                                    Remember Me
                                </label>
                            </div>
                            <input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>