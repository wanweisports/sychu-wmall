<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <style type="text/css">
        .wardrobe-list th {
            padding: 0.75rem;
        }
        .wardrobe-list td {
            padding: 0.3rem 0.75rem;
        }
        .img-rounded {
            width: 2rem;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/wardrobe/list.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <strong>试衣间列表</strong>
                            <small>Wardrobe List</small>
                        </div>
                        <div class="card-block">
                            <button type="button" class="btn btn-primary wardrobe-add">
                                <i class="fa fa-plus"></i> 添加试衣间
                            </button>
                        </div>
                        <div class="card-footer text-right"></div>
                        <div class="card-block">
                            <table class="table table-striped table-sm wardrobe-list">
                                <thead>
                                <tr>
                                    <th>试衣间名称</th>
                                    <th>试衣间地址</th>
                                    <th>试衣间状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>试衣间XXXXXXX</td>
                                    <td>XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</td>
                                    <td>正常</td>
                                    <td>
                                        <a href="/admin/wardrobe/settings" class="btn btn-sm btn-primary">
                                            <i class="fa fa-cog"></i> 配置
                                        </a>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!--/.col-->
            </div>
            <!--/.row-->
        </div>

    </div>
</layout:override>

<c:import url="../Shared/GeneralLayout.jsp">
    <c:param name="menu" value="wardrobe"/>
    <c:param name="subMenu" value="list"/>
</c:import>
