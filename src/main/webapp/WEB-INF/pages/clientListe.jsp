<%--@elvariable id="listeClients" type="java.util.List<tech.dev.entites.Client>"--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="createUrl" value="client/new" />

<div>
    <h1>
        <spring:message code="liste.clients.mvc.titre" />
    </h1>
</div>

<table>
    <thead>
    <tr>
        <th><spring:message code="fiche.client.nom"/></th>
        <th><spring:message code="fiche.client.prenom"/></th>
        <th><spring:message code="liste.action.titre"/></th>
    </tr>
    </thead>

    <tbody>
        <c:forEach items="${listeClients}" var="client">
            <tr>
                <td>${client.nom}</td>
                <td>${client.prenom}</td>
                <td>
                    <a href="client/${client.id}"><spring:message code="liste.action.consulter"/></a> |
                    <a href="client/${client.id}/edit"><spring:message code="liste.action.modifier"/></a> |
                    <a href="client/${client.id}/delete"><spring:message code="liste.action.supprimer"/></a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%--<a href="client/new"><spring:message code="liste.action.ajouter"/></a>--%>
<br/>
<button id="create_bouton" type="submit" onclick="self.location.href='${createUrl}'"><spring:message code="fiche.bouton.creer" /></button>

<%--
<sec:authorize access="isAuthenticated()">
    <c:url var="logout" value="/logout" />
    <form action="${logout}" method="post">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
        <input type="submit" value="Logout">
    </form>
</sec:authorize>--%>
