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
                            <strong>试衣间运行日志</strong>
                            <small>Wardrobe Running Log</small>
                        </div>
                        <div class="card-block">
                            <form id="wardrobe_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                                <div class="form-group row">
                                    <div class="col-md-3">
                                        <input type="text" name="nickname" class="form-control" placeholder="选择试衣间">
                                    </div>
                                    <div class="col-md-3">
                                        <input type="text" name="nickname" class="form-control" placeholder="起始时间">
                                    </div>
                                    <div class="col-md-3">
                                        <input type="text" name="mobile" class="form-control" placeholder="截止时间">
                                    </div>
                                    <div class="col-md-3">
                                        <button type="submit" class="btn btn-primary members-query-btn">
                                            <i class="fa fa-search"></i> 检 索
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="card-footer text-right"></div>
                        <div class="card-block">
                            <table class="table table-striped table-sm wardrobe-list">
                                <thead>
                                <tr>
                                    <th>运行时间</th>
                                    <th>运行状态</th>
                                    <th>锁具标识</th>
                                    <th>所属试衣间</th>
                                    <th>操作人</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>2018-09-14 11:12:13</td>
                                    <td>开锁</td>
                                    <td>锁具1</td>
                                    <td>试衣间AA</td>
                                    <td>王军</td>
                                </tr>
                                <tr>
                                    <td>2018-09-14 11:12:13</td>
                                    <td>开锁</td>
                                    <td>锁具1</td>
                                    <td>试衣间AA</td>
                                    <td>王军</td>
                                </tr>
                                <tr>
                                    <td>2018-09-14 11:12:13</td>
                                    <td>开锁</td>
                                    <td>锁具1</td>
                                    <td>试衣间AA</td>
                                    <td>王军</td>
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
    <c:param name="subMenu" value="log"/>
</c:import>
