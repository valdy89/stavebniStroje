<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
        <div class="page-header">
            <h1><f:message key="index.title"/></h1>
        </div>

        <sec:authorize access="!isAuthenticated()">
            <div class=".col-md-6 .col-md-offset-3">
                <form action="<c:url value='j_spring_security_check' />" id="loginForm" method="post">

                    <c:if test="${param.error == 'true'}">
                        <div class="error">Login Failed.</div>
                    </c:if>

                    <label for="username" id="username-label"><f:message key="customer.username"/></label>
                    <input id="username" type="text" name="j_username" class="form-control" />



                    <label for="password"><f:message key="customer.password"/></label><br/>
                    <input id="password" type="password" name="j_password" class="form-control" /><br/>


                    <input type="submit" id="login" class="btn btn-success" value="<f:message key="common.login"/>"/>
                    <input type="reset" id="reset" class="btn btn-default" value="<f:message key="all.btn.cancel"/>"/>

                </form>
            </div>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <p><f:message key="common.login.message"/></p>
            <c:url value="/j_spring_security_logout" var="logoutUrl" />

            <!-- csrt for log out-->
            <form action="${logoutUrl}" method="post" id="logoutForm">
                <input type="hidden" 
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
            </form>

            <script>
                function formSubmit() {
                    document.getElementById("logoutForm").submit();
                }
            </script>

            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <h2>
                    <f:message key="customer.username"/>: ${pageContext.request.userPrincipal.name} | <a
                        href="javascript:formSubmit()"> <f:message key="common.logout"/></a>
                </h2>
            </c:if>


        </sec:authorize>
    </s:layout-component>
</s:layout-render>
