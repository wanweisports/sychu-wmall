<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <style type="text/css">
        #particles-js {
            position: absolute;
            width: 100%;
            height: 100%;
            left: 0;
            top: 0;
        }
        .d-md-down-none {
            background: url("/Content/images/demo/png12.png") no-repeat center;
            background-size: auto 100%;
            border: none;
        }
        .card-group {
            box-shadow: 2px 2px 2px 2px rgba(122, 122, 122, 0.6);
        }
        .card {
            margin-bottom: 0;
        }
        h1 {
            font-size: 2rem;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/passport/login.js?v=${static_resource_version}"></script>
    <script type="text/javascript">

        function loginIn(btn){
            $(btn).prop("disabled", true).text("登录中...");
            $.post("/operator/loginIn", $("#login_form").serialize(), function (res) {
                if(res.code == 1){
                    window.location.href = '/admin/dashboard/index';
                }else{
                    $(btn).prop("disabled", false).text("登 录");
                    alert(res.message);
                }
            });
        }

    </script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div id="particles-js"></div>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card-group mb-0">
                    <div class="card p-4">
                        <div class="card-block">
                            <h1>登 录</h1>
                            <p class="text-muted">Sign In to your account</p>
                            <form id="login_form" method="post" novalidate onclick="return false;">
                                <div class="input-group mb-3">
                                    <span class="input-group-addon">
                                        <i class="icon-user"></i>
                                    </span>
                                    <%--superadmin--%>
                                    <input type="text" class="form-control" placeholder="用户名" name="operatorAccount" autocomplete="off">
                                </div>
                                <div class="input-group mb-4">
                                    <span class="input-group-addon">
                                        <i class="icon-lock"></i>
                                    </span>
                                    <%--123456--%>
                                    <input type="password" class="form-control" placeholder="密码" name="operatorPwd" autocomplete="off">
                                </div>
                                <div class="row">
                                    <div class="col-6">
                                        <a href="javascript:;" class="btn btn-primary px-4 to-login1" onclick="loginIn(this)">登 录</a>
                                    </div>
                                    <div class="col-6 text-right">
                                        <%--<a href="/admin/dashboard/index" class="btn btn-link px-0">忘记密码?</a>--%>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <%--<div class="card py-5 d-md-down-none" style="width:44%">
                        <div class="card-block text-center">
                            <div style="width: 60%; margin: 0 auto">
                                <img src="/Content/images/logo.png?v=${static_resource_version}" style="width: 100%;">
                                <p class="text-muted mt-2">您还没有账户吗？</p>
                                &lt;%&ndash;<a href="/admin/passport/register" class="btn btn-primary active">立即注册!</a>&ndash;%&gt;
                                <p class="text-muted mt-2">请联系管理员，为您分配账号。</p>
                            </div>
                        </div>
                    </div>--%>
                </div>
            </div>
        </div>
    </div>
</layout:override>

<c:import url="../Shared/PassportLayout.jsp">
    <c:param name="nav" value="setting"/>
</c:import>
