<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:url var="home" value="/"/>
<c:url var="login" value="/login"/>
<c:url var="upload" value="/files/upload"/>

<c:url var="filesList" value="/files/list"/>
<c:url var="filesView" value="/files/view/"/>
<c:url var="filesEdit" value="/files/edit/"/>
<c:url var="filesShare" value="/files/share/"/>
<c:url var="filesRemove" value="/files/remove/"/>

<c:url var="usersList" value="/users/list"/>
<c:url var="usersAdd" value="/users/add"/>
<c:url var="usersEdit" value="/users/edit/"/>
<c:url var="usersRemove" value="/users/remove/"/>

<c:url var="authenticator" value="/j_spring_security_check"/>
<c:url var="logout" value="/j_spring_security_logout"/>
