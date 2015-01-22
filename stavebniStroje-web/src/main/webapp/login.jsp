<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
        
                
        
       <form action="<c:url value='j_spring_security_check' />" id="loginForm" method="post">
    
<!--   <c:if test="${param.error == 'true'}">
        <div class="error">Login Failed.</div>
    </c:if>-->

    <label for="username" id="username-label">Username</label><br />
    <input id="username" size="20" type="text" name="j_username" class="form-control" /><br />
     
    

    <label for="password">Password</label><br/>
    <input id="password" size="20" type="password" name="j_password" class="form-control" /><br/>

    <input type="checkbox" name="rememberMe" id="rememberMe"/>
    <label for="rememberMe" style="vertical-align: top">Remember Me</label><br/>

    <input type="submit" id="login" class="btn btn-success" value="Login"/>
    <input type="reset" id="reset" class="btn btn-default" value="Clear"/>
    
</form>
       


<script type="text/javascript">
<c:if test="${param.ajax}">
    var loginFailed = function(data, status) {
        $(".error").remove();
        $('#username-label').before('<div class="error">Login failed, please try again.</div>');
    };

    $("#login").live('click', function(e) {
        e.preventDefault();
        $.ajax({url: getHost() + "${ctx}/api/login.json",
            type: "POST",
            beforeSend: function(xhr){
                xhr.withCredentials = true;
            },
            data: $("#loginForm").serialize(),
            success: function(data, status) {
                if (data.loggedIn) {
                    // success
                    dialog.dialog('close');
                    location.href = getHost() + '${ctx}/users';
                } else {
                    loginFailed(data);
                }
            },
            error: loginFailed
        });
    });
</c:if>
    $('#username').focus();
</script>

    </s:layout-component>
</s:layout-render>
