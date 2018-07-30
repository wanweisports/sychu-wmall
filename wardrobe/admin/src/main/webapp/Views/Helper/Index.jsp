<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <i class="icon-graph"></i> 工作面板
                            <small>Dashboard</small>
                            <div class="card-actions">
                                <a href="javascript:;" target="_blank">
                                    <small class="text-muted">帮助</small>
                                </a>
                            </div>
                        </div>
                        <div class="card-body card-block">
                            &nbsp;
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <i class="icon-settings"></i> 机构设置
                            <small>Venue</small>
                            <div class="card-actions">
                                <a href="javascript:;" target="_blank">
                                    <small class="text-muted">帮助</small>
                                </a>
                            </div>
                        </div>
                        <div class="card-body card-block">
                            &nbsp;
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <i class="icon-list"></i> 班级管理
                            <small>Class</small>
                            <div class="card-actions">
                                <a href="javascript:;" target="_blank">
                                    <small class="text-muted">帮助</small>
                                </a>
                            </div>
                        </div>
                        <div class="card-body card-block">
                            &nbsp;
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <i class="icon-people"></i> 学员管理
                            <small>Students</small>
                            <div class="card-actions">
                                <a href="javascript:;" target="_blank">
                                    <small class="text-muted">帮助</small>
                                </a>
                            </div>
                        </div>
                        <div class="card-body card-block">
                            &nbsp;
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <i class="icon-chart"></i> 数据统计
                            <small>Data</small>
                            <div class="card-actions">
                                <a href="javascript:;" target="_blank">
                                    <small class="text-muted">帮助</small>
                                </a>
                            </div>
                        </div>
                        <div class="card-body card-block">
                            &nbsp;
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <i class="icon-settings"></i> 系统设置
                            <small>Settings</small>
                            <div class="card-actions">
                                <a href="javascript:;" target="_blank">
                                    <small class="text-muted">帮助</small>
                                </a>
                            </div>
                        </div>
                        <div class="card-body card-block">
                            &nbsp;
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</layout:override>

<c:import url="../Shared/GeneralLayout.jsp">
    <c:param name="nav" value="dashboard"/>
</c:import>
