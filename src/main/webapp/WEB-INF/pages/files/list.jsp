<%@ include file="/WEB-INF/pages/partials/taglibs.jsp" %>

<html>
<head>
    <%@ include file="/WEB-INF/pages/partials/head.jsp" %>

</head>
<body>

<%@ include file="/WEB-INF/pages/partials/navigation.jsp" %>

<div class="container">
    <legend>File collection - View files</legend>

    <table id="files" class="table table-hover">
        <thead>
        <tr>
            <th>filename</th>
            <th>author</th>
            <th>size</th>
            <th>extension</th>
            <th>modified</th>
        </tr>
        </thead>

        <tbody>

        <c:forEach items="${fileList}" var="file">
            <tr data-identity="${file.id}">
                <td class="col-md-4">
                    <span class="glyphicon glyphicon-file"></span>
                    <a href="/files/view/${file.id}" target="_blank">${file.name}</a>
                </td>
                <td class="col-md-2">${file.author}</td>
                <td class="col-md-1">${file.size}</td>
                <td class="col-md-2">${file.extension}</td>
                <td class="col-md-3">${file.modificationDate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    $("#files tr").contextMenu({
        actions: {
            download: {
                text: "Download",
                link: "${filesView}",
                icon: "open"
            },
            remove: {
                text: "Remove",
                link: "${filesRemove}",
                icon: "trash"
            },
            edit: {
                text: "Rename",
                link: "${filesEdit}",
                icon: "edit"
            }
            <sec:authorize access="hasRole('ROLE_ADMIN')">,
            share: {
                text: "Share",
                link: "${filesShare}",
                icon: "th-list"
            }
            </sec:authorize>
        },
        menuSelected: function (identity, action) {
        }
    });
</script>
</body>
</html>