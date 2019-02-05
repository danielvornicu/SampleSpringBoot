<%--@elvariable id="clientForm" type="tech.dev.web.formulaires.ClientForm"--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="clientCancelUrl" value="/wsclient/cancel" />

<div>
    <h1>
        <c:choose>
            <c:when test="${clientForm.client.id == -1}">
                <spring:message code="fiche.client.titre.creation" />
            </c:when>
            <c:otherwise>
                <spring:message code="fiche.client.titre" arguments="${client.prenom},${client.nom}" htmlEscape="true" />
            </c:otherwise>
        </c:choose>
    </h1>
</div>

<form:form method="POST" id="clientForm" modelAttribute="clientForm" >
    <table>
        <tbody>
        <tr>
            <th><form:label path="client.nom" for="nom" ><spring:message code="fiche.client.nom"/></form:label></th>
            <td><form:input path="client.nom" id="nom" maxlength="20" size="30" /></td>
        </tr>
        <tr>
            <th><form:label path="client.prenom" for="prenom" ><spring:message code="fiche.client.prenom"/></form:label></th>
            <td><form:input path="client.prenom" id="prenom" maxlength="20" size="30" /> </td>
        </tr>
        </tbody>
    </table>

    <button id="submit_bouton" type="submit"><spring:message code="fiche.bouton.valider" /></button>
    <button id="cancel_bouton" type="button" onclick="self.location.href='${clientCancelUrl}'"><spring:message code="fiche.bouton.annuler" /></button>

</form:form>