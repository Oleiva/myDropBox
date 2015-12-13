<%@ include file="/WEB-INF/pages/partials/taglibs.jsp" %>

<html>
<head>
    <%@ include file="/WEB-INF/pages/partials/head.jsp" %>
</head>
<body>

<%@ include file="/WEB-INF/pages/partials/navigation.jsp" %>

<div class="container">
    <legend>User collection - View users</legend>

    <table id="users" class="table table-hover">
        <thead>
        <tr>
            <th>#</th>
            <th>username</th>
            <th>authority</th>
            <th>email</th>
            <th>enabled</th>
            <th>first name</th>
            <th>last name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userList}" var="userTableItem">
            <tr data-identity="${userTableItem.id}">
                <td class="col-md-1">${userTableItem.id}</td>
                <td class="col-md-1">${userTableItem.username}</td>
                <td class="col-md-1">${userTableItem.authority}</td>
                <td class="col-md-2">${userTableItem.email}</td>
                <td class="col-md-1">${userTableItem.enabled}</td>
                <td class="col-md-1">${userTableItem.firstName}</td>
                <td class="col-md-1">${userTableItem.lastName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="${usersAdd}" class="btn btn-success">Add user</a>

</div>

<script>
    $("#users tbody tr").contextMenu({
        actions: {
            edit: {
                text: "Edit",
                link: "${usersEdit}",
                icon: "edit"
            },
            remove: {
                text: "Remove",
                link: "${usersRemove}",
                icon: "trash"
            }
        },
        menuSelected: function (identity, action) {
        }
    });
</script>

</body>
</html>