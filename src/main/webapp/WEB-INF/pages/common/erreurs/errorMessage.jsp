<%--@elvariable id="errorMessage" type="java.lang.String"--%>
<%--@elvariable id="resourcesPath" type="java.lang.String"--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="resourcesPath" value="${pageContext.request.contextPath}/resources" />
<!-- CSS -->
<link type="text/css" rel="stylesheet" href="${resourcesPath}/css/app.css" />
<link type="text/css" rel="stylesheet" href="${resourcesPath}/js/jquery/ui/css/jquery-ui-1.9.2.custom.min.css" />
<!-- Javascript -->
<script type="text/javascript" src="${resourcesPath}/js/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${resourcesPath}/js/jquery/ui/js/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript" src="${resourcesPath}/js/app.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        setTimeout(function() {
            if ($("div.errorContent").is(":visible")) {
                showHideError();
            }
        }, 5000);
    });
</script>

<c:if test="${errorMessage != null}">
    <div class="errorPanel" onclick="showHideError()">
        <div class="errorHeader ui-state-error ui-widget-header ui-corner-top no-bottom-border">

            <img class="hideError" src="${resourcesPath}/images/bouton_fleche_haut.png" title='<spring:message code="error.bouton.haut" htmlEscape="true" />'
                 name="showErr" border="0" width="22" height="22" alt="<spring:message code="error.bouton.haut" htmlEscape="true" />" />

            <img class="showError" src="${resourcesPath}/images/bouton_fleche_bas.png" title='<spring:message code="error.bouton.bas" htmlEscape="true" />'
                 name="hideErr" border="0" width="22" height="22" alt="<spring:message code="error.bouton.bas" htmlEscape="true" />" />
        </div>

        <div class="errorContent ui-state-error ui-corner-bottom">${errorMessage}</div>
    </div>
</c:if>