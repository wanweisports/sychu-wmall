<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="clearfix">
                    <h1 class="float-left display-3 mr-4">404</h1>
                    <h4 class="pt-3">Oops! You're lost.</h4>
                    <p class="text-muted">The page you are looking for was not found.</p>
                </div>
                <div class="input-prepend input-group">
                    <span class="input-group-addon"><i class="fa fa-search"></i>
                    </span>
                    <input id="prependedInput" class="form-control" size="16" type="text" placeholder="What are you looking for?">
                    <span class="input-group-btn">
                        <button class="btn btn-info" type="button">Search</button>
                    </span>
                </div>
            </div>
        </div>
    </div>
</layout:override>

<c:import url="../Shared/ErrorLayout.jsp">
    <c:param name="nav" value="setting"/>
</c:import>
