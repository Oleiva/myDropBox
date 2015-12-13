<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/docs.min.css">
<style>

    #uploadFile {
        position: relative;
        overflow: hidden;
        cursor: hand;
    }

    #uploadFile input[type=file] {
        position: absolute;
        top: 0;
        right: 0;
        min-width: 100%;
        min-height: 100%;
        font-size: 100px;
        text-align: right;
        filter: alpha(opacity=0);
        opacity: 0;
        outline: none;
        background: white;
        cursor: inherit;
        display: block;
    }

    #uploadForm {
        margin: 0;
    }

    .has-error {
        color: #a94442;
    }

    body, input, textarea, select, button, .normal {
        font-family: "Open Sans", "lucida grande", "Segoe UI", arial, verdana, "lucida sans unicode", tahoma, sans-serif;
        font-size: 13px;
        color: #3d464d;
        font-weight: normal;
    }

    .navbar-default .navbar-nav > li > a:hover, .navbar-default .navbar-nav > li > a:focus {
        background-color: #eee;
    }

    .table > thead > tr > th {
        background: #fff;
        border-bottom: 1px solid #e5e5e5;
        color: #aaa;
        font-size: 12px;
    }

    .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td,
    .table > tfoot > tr > td {
        border: 1px solid #e5e5e5;
        color: #3d464d;
        font-size: 13px;
        border-width: 1px 0;
        line-height: 32px;
    }

    table > tbody > tr {
        position: relative;
        -webkit-user-select: none;
        -khtml-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
    }

    td a {
        color: #3d464d;
    }

    td .glyphicon {
        font-size: 25px;
        vertical-align: middle;
        top: -1px;
        margin-right: 5px;
    }

    #contextMenu {
        position: absolute;
        display: none;
    }

    .table > thead > tr > td.active, .table > tbody > tr > td.active, .table > tfoot > tr > td.active, .table > thead > tr > th.active, .table > tbody > tr > th.active, .table > tfoot > tr > th.active, .table > thead > tr.active > td, .table > tbody > tr.active > td, .table > tfoot > tr.active > td, .table > thead > tr.active > th, .table > tbody > tr.active > th, .table > tfoot > tr.active > th {
        background-color: #e3f2ff !important;
        border-color: #d3e4f3 !important;
    }

    .table-hover > tbody > tr:hover > td, .table-hover > tbody > tr:hover > th {
        background: #f5fafe;
        border-color: #e1eaf1;
    }

    .dropdown-menu {
        font-size: 13px;
        -webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, .175);
        box-shadow: 0 1px 3px rgba(0, 0, 0, .175);
    }

    .span-control {
        display: block;
        width: 100%;
        height: 34px;
        padding: 6px 12px;
        font-size: 14px;
        line-height: 1.42857143;
        color: #555;
    }
</style>
