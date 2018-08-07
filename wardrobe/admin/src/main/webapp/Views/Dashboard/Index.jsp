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
                            <h4 class="mb-0">1123</h4>
                            <p>访问人数（人）</p>
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
                            <h4 class="mb-0 total-class-count">230/10</h4>
                            <p class="total-class-name">成单数/预约数</p>
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
                            <h4 class="mb-0 income-class-count">1000.00</h4>
                            <p class="income-class-name">充值金额（元）</p>
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
                            <h4 class="mb-0 expend-class-count">12120.00</h4>
                            <p class="expend-class-name">消费金额（元）</p>
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
                            <h4 class="card-title mb-0">访问实时人流量（人）</h4>
                        </div>
                        <!--/.col-->
                    </div>
                    <!--/.row-->
                    <div class="chart-wrapper" style="height:300px;margin-top:40px;">
                        <canvas id="total_members_chart" class="chart" height="300"></canvas>
                    </div>
                </div>
            </div>
            <!--/.card-->
        </div>
    </div>
</layout:override>

<c:import url="../Shared/GeneralLayout.jsp">
    <c:param name="menu" value="dashboard"/>
</c:import>
