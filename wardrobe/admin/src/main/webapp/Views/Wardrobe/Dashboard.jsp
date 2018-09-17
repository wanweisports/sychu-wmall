<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <style type="text/css"></style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/wardrobe/dashboard.js?v=${static_resource_version}"></script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="container-fluid">
        <div class="animated fadeIn">
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <strong>试衣间10001</strong>
                            <a href="javascript:;" class="btn btn-sm btn-primary pull-right">
                                <i class="fa fa-refresh"></i> 重新连接
                            </a>
                            <span class="badge badge-success">已连接</span>
                        </div>
                        <div class="card-block row">
                            <div class="col-md-6">
                                <div class="card">
                                    <div class="card-body p-3 d-flex align-items-center">
                                        <i class="fa fa-lock bg-info p-3 font-2xl mr-3"></i>
                                        <div class="mr-5">
                                            <div class="text-value-sm text-primary">大门</div>
                                            <div class="text-muted text-uppercase font-weight-bold small">
                                                <span class="badge badge-danger">锁定中</span>
                                            </div>
                                        </div>
                                        <div>
                                            <button class="btn btn-danger">开 锁</button>
                                        </div>
                                    </div>
                                    <div class="card-footer px-3 py-2">
                                        <a class="btn-block text-muted d-flex justify-content-between align-items-center" href="#">
                                            <span class="small font-weight-bold">查看开关锁日志</span>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="card">
                                    <div class="card-body p-3 d-flex align-items-center">
                                        <i class="fa fa-lock bg-info p-3 font-2xl mr-3"></i>
                                        <div class="mr-5">
                                            <div class="text-value-sm text-primary">柜子A</div>
                                            <div class="text-muted text-uppercase font-weight-bold small">
                                                <span class="badge badge-success">开锁中</span>
                                            </div>
                                        </div>
                                        <div>
                                            <button class="btn btn-success">关 锁</button>
                                        </div>
                                    </div>
                                    <div class="card-footer px-3 py-2">
                                        <a class="btn-block text-muted d-flex justify-content-between align-items-center" href="#">
                                            <span class="small font-weight-bold">查看开关锁日志</span>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="card">
                                    <div class="card-body p-3 d-flex align-items-center">
                                        <i class="fa fa-lock bg-info p-3 font-2xl mr-3"></i>
                                        <div class="mr-5">
                                            <div class="text-value-sm text-primary">柜子B</div>
                                            <div class="text-muted text-uppercase font-weight-bold small">
                                                <span class="badge badge-success">锁定中</span>
                                            </div>
                                        </div>
                                        <div>
                                            <button class="btn btn-danger">开 锁</button>
                                        </div>
                                    </div>
                                    <div class="card-footer px-3 py-2">
                                        <a class="btn-block text-muted d-flex justify-content-between align-items-center" href="#">
                                            <span class="small font-weight-bold">查看开关锁日志</span>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="card">
                                    <div class="card-body p-3 d-flex align-items-center">
                                        <i class="fa fa-lock bg-info p-3 font-2xl mr-3"></i>
                                        <div class="mr-5">
                                            <div class="text-value-sm text-primary">柜子C</div>
                                            <div class="text-muted text-uppercase font-weight-bold small">
                                                <span class="badge badge-success">锁定中</span>
                                            </div>
                                        </div>
                                        <div>
                                            <button class="btn btn-danger">开 锁</button>
                                        </div>
                                    </div>
                                    <div class="card-footer px-3 py-2">
                                        <a class="btn-block text-muted d-flex justify-content-between align-items-center" href="#">
                                            <span class="small font-weight-bold">查看开关锁日志</span>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="card">
                                    <div class="card-body p-3 d-flex align-items-center">
                                        <i class="fa fa-lock bg-info p-3 font-2xl mr-3"></i>
                                        <div class="mr-5">
                                            <div class="text-value-sm text-primary">柜子D</div>
                                            <div class="text-muted text-uppercase font-weight-bold small">
                                                <span class="badge badge-success">锁定中</span>
                                            </div>
                                        </div>
                                        <div>
                                            <button class="btn btn-danger">开 锁</button>
                                        </div>
                                    </div>
                                    <div class="card-footer px-3 py-2">
                                        <a class="btn-block text-muted d-flex justify-content-between align-items-center" href="#">
                                            <span class="small font-weight-bold">查看开关锁日志</span>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="card">
                                    <div class="card-body p-3 d-flex align-items-center">
                                        <i class="fa fa-lock bg-info p-3 font-2xl mr-3"></i>
                                        <div class="mr-5">
                                            <div class="text-value-sm text-primary">柜子E</div>
                                            <div class="text-muted text-uppercase font-weight-bold small">
                                                <span class="badge badge-success">锁定中</span>
                                            </div>
                                        </div>
                                        <div>
                                            <button class="btn btn-danger">开 锁</button>
                                        </div>
                                    </div>
                                    <div class="card-footer px-3 py-2">
                                        <a class="btn-block text-muted d-flex justify-content-between align-items-center" href="#">
                                            <span class="small font-weight-bold">查看开关锁日志</span>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="card">
                                    <div class="card-body p-3 d-flex align-items-center">
                                        <i class="fa fa-lock bg-info p-3 font-2xl mr-3"></i>
                                        <div class="mr-5">
                                            <div class="text-value-sm text-primary">柜子F</div>
                                            <div class="text-muted text-uppercase font-weight-bold small">
                                                <span class="badge badge-success">锁定中</span>
                                            </div>
                                        </div>
                                        <div>
                                            <button class="btn btn-danger">开 锁</button>
                                        </div>
                                    </div>
                                    <div class="card-footer px-3 py-2">
                                        <a class="btn-block text-muted d-flex justify-content-between align-items-center" href="#">
                                            <span class="small font-weight-bold">查看开关锁日志</span>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="card">
                                    <div class="card-body p-3 d-flex align-items-center">
                                        <i class="fa fa-lock bg-info p-3 font-2xl mr-3"></i>
                                        <div class="mr-5">
                                            <div class="text-value-sm text-primary">柜子G</div>
                                            <div class="text-muted text-uppercase font-weight-bold small">
                                                <span class="badge badge-success">锁定中</span>
                                            </div>
                                        </div>
                                        <div>
                                            <button class="btn btn-danger">开 锁</button>
                                        </div>
                                    </div>
                                    <div class="card-footer px-3 py-2">
                                        <a class="btn-block text-muted d-flex justify-content-between align-items-center" href="#">
                                            <span class="small font-weight-bold">查看开关锁日志</span>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="card">
                                    <div class="card-body p-3 d-flex align-items-center">
                                        <i class="fa fa-lock bg-info p-3 font-2xl mr-3"></i>
                                        <div class="mr-5">
                                            <div class="text-value-sm text-primary">柜子H</div>
                                            <div class="text-muted text-uppercase font-weight-bold small">
                                                <span class="badge badge-success">锁定中</span>
                                            </div>
                                        </div>
                                        <div>
                                            <button class="btn btn-danger">开 锁</button>
                                        </div>
                                    </div>
                                    <div class="card-footer px-3 py-2">
                                        <a class="btn-block text-muted d-flex justify-content-between align-items-center" href="#">
                                            <span class="small font-weight-bold">查看开关锁日志</span>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
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
    <c:param name="subMenu" value="dashboard"/>
</c:import>
