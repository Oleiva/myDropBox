<script src="/resources/js/jquery-2.1.1.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/context-menu.js"></script>
<%--<script src="/resources/js/bootstrap-table.js"></script>--%>
<script>
    $(document).on('change', '#uploadFile :file', function () {
        $("#uploadForm").submit();
    });
</script>