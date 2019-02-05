<%--@elvariable id="client" type="tech.dev.entites.Client"--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="clientCancelUrl" value="./cancel" />

<div>
    <h1>
        <spring:message code="fiche.client.titre.consultation" arguments="${client.prenom},${client.nom}" />
    </h1>
</div>
<div>
    <table>
        <tbody>
        <tr>
            <th><spring:message code="fiche.client.nom"/></th>
            <td>${client.nom}</td>
        </tr>
        <tr>
            <th><spring:message code="fiche.client.prenom"/></th>
            <td>${client.prenom}</td>
        </tr>
        </tbody>
    </table>

    <br/>

    <button id="cancel_bouton" type="button" onclick="self.location.href='${clientCancelUrl}'"><spring:message code="fiche.bouton.annuler" /></button>
</div>

