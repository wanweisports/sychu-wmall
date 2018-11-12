<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.wardrobe.layout.Blocks" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- JSTL表达式（判断，循环，输出） --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- 方法表达式（字符串截取，替换） --%>
<%@ taglib uri="http://www.sychu.com/tags/tag" prefix="layout" %>

<layout:override name="<%=Blocks.BLOCK_HEADER_CSS%>">
    <style type="text/css">
        .form-control-label {
            text-align: right;
        }
        .wardrobe-list th {
            padding: 0.75rem;
        }
        .wardrobe-list td {
            padding: 0.3rem 0.75rem;
        }
        .form-check-input {
            margin-left: 0;
        }
    </style>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_HEADER_SCRIPTS%>">
    <script type="text/javascript" src="Content/js/require.js?v=${static_resource_version}"
            data-main="Content/js/app/wardrobe/list.js?v=${static_resource_version}"></script>

    <script type="text/javascript">
        function wardrobeSettings(did, name, address, doorIp, doorPort, lockIp, lockPort, status){
            $("#did").val(did ? did : '');
            $("#wardrobe_name").val(name ? name : '');
            $("#wardrobe_address").val(address ? address : '');
            $("#wardrobe_doorIp").val(doorIp ? doorIp : '');
            $("#wardrobe_doorPort").val(doorPort ? doorPort : '');
            $("#wardrobe_cabinetIp").val(lockIp ? lockIp : '');
            $("#wardrobe_cabinetPort").val(lockPort ? lockPort : '');
            status = status ? status : 1;
            $("input[name='status'][value='" + status + "']").prop('checked', true);
        }

        function saveSysDeviceInfo(){
            if(window.confirm("确认保存吗？")) {
                $.post("/admin/wardrobe/saveDeviceInfo", $("#wardrobe_form").serialize(), function (res) {
                    alert(res.message);
                    if(res.code == 1){
                        window.location.reload();
                    }
                });
            }
        }
    </script>
</layout:override>

<layout:override name="<%=Blocks.BLOCK_BODY%>">
    <div class="modal fade" id="wardrobe_settings" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-default" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <form id="wardrobe_form" method="post" class="form-horizontal" novalidate onsubmit="return false;">
                        <input type="hidden" id="did" name="did">
                        <div class="form-group row">
                            <label class="col-md-3 form-control-label" for="wardrobe_name">
                                <span class="text-danger">*</span> 试衣间名称
                            </label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="wardrobe_name" placeholder="请输入试衣间名称" name="name"
                                       data-val="true" data-val-required="试衣间名称不能为空" autocomplete="off"
                                       data-val-length-max="30" data-val-length-min="2" data-val-length="试衣间名称必须包含 2~30 个字符">
                                <div data-valmsg-for="name" data-valmsg-replace="true"></div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 form-control-label" for="wardrobe_address">
                                <span class="text-danger">*</span> 所在地址
                            </label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="wardrobe_address" placeholder="请输入所在地址" name="address"
                                       data-val="true" data-val-required="所在地址不能为空" autocomplete="off"
                                       data-val-length-max="30" data-val-length-min="2" data-val-length="所在地址必须包含 2~30 个字符">
                                <div data-valmsg-for="address" data-valmsg-replace="true"></div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 form-control-label" for="wardrobe_doorIp">
                                <span class="text-danger">*</span> 大门地址
                            </label>
                            <div class="col-md-5">
                                <input type="text" class="form-control" id="wardrobe_doorIp" placeholder="大门IP地址" name="doorIp"
                                       data-val="true" data-val-required="大门IP地址不能为空" autocomplete="off">
                                <div data-valmsg-for="doorIp" data-valmsg-replace="true"></div>
                            </div>
                            <div class="col-md-4">
                                <input type="text" class="form-control" id="wardrobe_doorPort" placeholder="大门IP端口" name="doorPort"
                                       data-val="true" data-val-required="大门IP端口不能为空" autocomplete="off">
                                <div data-valmsg-for="doorPort" data-valmsg-replace="true"></div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 form-control-label" for="wardrobe_cabinetIp">
                                <span class="text-danger">*</span> 柜子地址
                            </label>
                            <div class="col-md-5">
                                <input type="text" class="form-control" id="wardrobe_cabinetIp" placeholder="柜子IP地址" name="lockIp"
                                       data-val="true" data-val-required="柜子IP地址不能为空" autocomplete="off">
                                <div data-valmsg-for="cabinetIp" data-valmsg-replace="true"></div>
                            </div>
                            <div class="col-md-4">
                                <input type="text" class="form-control" id="wardrobe_cabinetPort" placeholder="柜子IP端口" name="lockPort"
                                       data-val="true" data-val-required="柜子IP端口不能为空" autocomplete="off">
                                <div data-valmsg-for="cabinetPort" data-valmsg-replace="true"></div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 form-control-label" for="wardrobe_status1">
                                <span class="text-danger">*</span> 试衣间状态
                            </label>
                            <div class="col-md-9 col-form-label">
                                <div class="form-check form-check-inline mr-1">
                                    <input class="form-check-input" id="wardrobe_status1" type="radio" value="1" name="status" checked>
                                    <label class="form-check-label" for="wardrobe_status1">开放</label>
                                </div>
                                <div class="form-check form-check-inline mr-1">
                                    <input class="form-check-input" id="wardrobe_status2" type="radio" value="3" name="status">
                                    <label class="form-check-label" for="wardrobe_status2">关闭</label>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">
                        <i class="fa fa-close"></i> 取 消
                    </button>
                    <button type="button" class="btn btn-sm btn-primary save-wardrobe" onclick="saveSysDeviceInfo()">
                        <i class="fa fa-check"></i> 保 存
                    </button>
                </div>
            </div>
        </div>
    </div>

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
                            <button type="button" class="btn btn-primary wardrobe-add" data-target="#wardrobe_settings" onclick="wardrobeSettings()"
                                    data-toggle="modal">
                                <i class="fa fa-plus"></i> 添加试衣间
                            </button>
                        </div>
                        <div class="card-footer text-right"></div>
                        <div class="card-block">
                            <table class="table table-striped table-bordered table-sm wardrobe-list">
                                <thead>
                                <tr>
                                    <th>试衣间名称</th>
                                    <th>所在地址</th>
                                    <th>大门地址</th>
                                    <th>柜子地址</th>
                                    <th>试衣间状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="d" items="${page.list}">
                                    <tr data-id="123">
                                        <td>${d.name}</td>
                                        <td>${d.address}</td>
                                        <td>${d.doorIp}:${d.doorPort}</td>
                                        <td>${d.lockIp}:${d.lockPort}</td>

                                        <c:if test="${d.status == '1'}">
                                            <td class="text-success">已开放</td>
                                        </c:if>
                                        <c:if test="${d.status == '2'}">
                                            <td class="text-danger">被占用</td>
                                        </c:if>
                                        <c:if test="${d.status == '3'}">
                                            <td class="text-danger">已下线</td>
                                        </c:if>

                                        <td>
                                            <a href="#wardrobe_settings" class="btn btn-sm btn-primary wardrobe-set" data-toggle="modal" onclick="wardrobeSettings('${d.did}', '${d.name}', '${d.address}', '${d.doorIp}', '${d.doorPort}', '${d.lockIp}', '${d.lockPort}', '${d.status}')">
                                                <i class="fa fa-cog"></i> 配置
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
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
