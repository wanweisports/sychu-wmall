<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/dashboard/index.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-sm-6 col-lg-3">
                    <div class="card card-inverse card-primary">
                        <div class="card-block pb-0">
                            <div class="btn-group float-right">
                                <button type="button" class="btn btn-transparent active dropdown-toggle p-0" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="icon-settings"></i>
                                </button>
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a class="dropdown-item" href="javascript:;" data-type="month">本  月</a>
                                    <a class="dropdown-item" href="javascript:;" data-type="year">本  年</a>
                                    <a class="dropdown-item" href="javascript:;" data-type="total">总  量</a>
                                </div>
                            </div>
                            <h4 class="mb-0">0/0</h4>
                            <p>未使用的区块</p>
                        </div>
                        <div class="chart-wrapper px-1" style="height:70px;">
                            <canvas id="card-chart1" class="chart" height="70"></canvas>
                        </div>
                    </div>
                </div>
                <!--/.col-->

                <div class="col-sm-6 col-lg-3 total-class">
                    <div class="card card-inverse card-info">
                        <div class="card-block pb-0">
                            <div class="btn-group float-right">
                                <button type="button" class="btn btn-transparent dropdown-toggle p-0" data-toggle="dropdown">
                                    <i class="icon-book-open"></i>
                                </button>
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a class="dropdown-item" href="javascript:;" data-type="month">本  月</a>
                                    <a class="dropdown-item" href="javascript:;" data-type="year">本  年</a>
                                    <a class="dropdown-item" href="javascript:;" data-type="total">总  量</a>
                                </div>
                            </div>
                            <h4 class="mb-0 total-class-count">0</h4>
                            <p class="total-class-name">班级数量（正上课/全部）</p>
                        </div>
                        <div class="chart-wrapper px-1" style="height: 70px;">
                            <canvas id="total_class_chart" class="chart" height="70"></canvas>
                        </div>
                    </div>
                </div>
                <!--/.col-->

                <div class="col-sm-6 col-lg-3 income-class">
                    <div class="card card-inverse card-warning">
                        <div class="card-block pb-0">
                            <div class="btn-group float-right">
                                <button type="button" class="btn btn-transparent dropdown-toggle p-0" data-toggle="dropdown">
                                    <i class="icon-settings"></i>
                                </button>
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a class="dropdown-item" href="javascript:;" data-type="month">本  月</a>
                                    <a class="dropdown-item" href="javascript:;" data-type="year">本  年</a>
                                    <a class="dropdown-item" href="javascript:;" data-type="total">总  量</a>
                                </div>
                            </div>
                            <h4 class="mb-0 income-class-count">0</h4>
                            <p class="income-class-name">入账金额（元）</p>
                        </div>
                        <div class="chart-wrapper" style="height:70px;">
                            <canvas id="income_class_chart" class="chart" height="70"></canvas>
                        </div>
                    </div>
                </div>
                <!--/.col-->

                <div class="col-sm-6 col-lg-3 expend-class">
                    <div class="card card-inverse card-danger">
                        <div class="card-block pb-0">
                            <div class="btn-group float-right">
                                <button type="button" class="btn btn-transparent dropdown-toggle p-0" data-toggle="dropdown">
                                    <i class="icon-settings"></i>
                                </button>
                                <div class="dropdown-menu dropdown-menu-right">
                                    <a class="dropdown-item" href="javascript:;" data-type="month">本  月</a>
                                    <a class="dropdown-item" href="javascript:;" data-type="year">本  年</a>
                                    <a class="dropdown-item" href="javascript:;" data-type="total">总  量</a>
                                </div>
                            </div>
                            <h4 class="mb-0 expend-class-count">0</h4>
                            <p class="expend-class-name">出账金额（元）</p>
                        </div>
                        <div class="chart-wrapper" style="height:70px;">
                            <canvas id="expend_class_chart" class="chart" height="70"></canvas>
                        </div>
                    </div>
                </div>
                <!--/.col-->
            </div>
            <!--/.row-->

            <div class="card total-students">
                <div class="card-block">
                    <div class="row">
                        <div class="col-sm-6">
                            <h4 class="card-title mb-0">学员数量（人）<span class="small text-muted total-students-date"></span></h4>
                        </div>
                        <!--/.col-->
                        <div class="col-sm-6 hidden-sm-down">
                            <div class="btn-toolbar float-right" role="toolbar">
                                <div class="btn-group" data-toggle="buttons">
                                    <label class="btn btn-outline-secondary">
                                        <input type="radio" name="total_students_type" id="total_students_type1" value="prev_day">昨 日
                                    </label>
                                    <label class="btn btn-outline-secondary mr-3">
                                        <input type="radio" name="total_students_type" id="total_students_type2" value="day">今 日
                                    </label>

                                    <%--<label class="btn btn-outline-secondary">--%>
                                        <%--<input type="radio" name="total_students_type" id="total_students_type3" value="prev_week">上 周--%>
                                    <%--</label>--%>
                                    <%--<label class="btn btn-outline-secondary mr-3">--%>
                                        <%--<input type="radio" name="total_students_type" id="total_students_type4" value="week">本 周--%>
                                    <%--</label>--%>

                                    <label class="btn btn-outline-secondary">
                                        <input type="radio" name="total_students_type" id="total_students_type5" value="prev_month">上 月
                                    </label>
                                    <label class="btn btn-outline-secondary mr-3">
                                        <input type="radio" name="total_students_type" id="total_students_type6" value="month">本 月
                                    </label>

                                    <label class="btn btn-outline-secondary">
                                        <input type="radio" name="total_students_type" id="total_students_type7" value="prev_year">去 年
                                    </label>
                                    <label class="btn btn-outline-secondary active">
                                        <input type="radio" name="total_students_type" id="total_students_type8" value="year" checked="checked">今 年
                                    </label>
                                </div>
                            </div>
                        </div>
                        <!--/.col-->
                    </div>
                    <!--/.row-->
                    <div class="chart-wrapper" style="height:300px;margin-top:40px;">
                        <canvas id="total_students_chart" class="chart" height="300"></canvas>
                    </div>
                </div>
                <div class="card-footer">
                    <ul>
                        <li>
                            <div class="text-muted">学员总量（个）</div>
                            <strong class="total-students-all"></strong>
                            <div class="progress progress-xs mt-2">
                                <div class="progress-bar bg-success" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </li>
                        <li class="hidden-sm-down">
                            <div class="text-muted">已分班学员量（个）</div>
                            <strong class="total-students-class"></strong>
                            <div class="progress progress-xs mt-2">
                                <div class="progress-bar bg-info" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </li>
                        <li>
                            <div class="text-muted">新增学员总量（个）</div>
                            <strong class="total-students-create"></strong>
                            <div class="progress progress-xs mt-2">
                                <div class="progress-bar bg-warning" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </li>
                        <li class="hidden-sm-down">
                            <div class="text-muted">新增已分班学员量（个）</div>
                            <strong class="total-students-create-class"></strong>
                            <div class="progress progress-xs mt-2">
                                <div class="progress-bar bg-danger" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <!--/.card-->
        </div>
    </div>
</layout:override>

<c:import url="../Shared/GeneralLayout.jsp">
    <c:param name="menu" value="dashboard"/>
</c:import>
