<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<c:if test="${page.totalCount > 0}">
    <div class="row">
        <div class="col-md-12">
            <ul class="pagination pull-right">
                <c:if test="${page.currentPage == 1}">
                    <li class="page-item disabled">
                        <a class="page-link" href="javascript:;">首页</a>
                    </li>
                    <li class="page-item disabled">
                        <a class="page-link" href="javascript:;">上一页</a>
                    </li>
                </c:if>

                <c:if test="${page.hasPreviousPage}">
                    <li class="page-item">
                        <a class="page-link" href="${pageURL}&page=1">首页</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="${pageURL}&page=${page.pre}">上一页</a>
                    </li>
                </c:if>

                <c:if test="${prefix}">
                    <li class="page-item">
                        <a class="page-link" href="javascript:;">...</a>
                    </li>
                </c:if>
                <c:forEach var="index" begin="${startPage}" end="${endPage}">
                    <c:if test="${index == page.currentPage}">
                        <li class="page-item active">
                            <a class="page-link" href="javascript:;">${index}</a>
                        </li>
                    </c:if>
                    <c:if test="${index != page.currentPage}">
                        <li class="page-item">
                            <a class="page-link" href="${pageURL}&page=${index}">${index}</a>
                        </li>
                    </c:if>
                </c:forEach>
                <c:if test="${suffix}">
                    <li class="page-item">
                        <a class="page-link" href="javascript:;">...</a>
                    </li>
                </c:if>

                <c:if test="${page.hasNextPage}">
                    <li class="page-item">
                        <a class="page-link" href="${pageURL}&page=${page.next}">下一页</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="${pageURL}&page=${page.totalPage}">末页</a>
                    </li>
                </c:if>
                <c:if test="${page.lastPage}">
                    <li class="page-item disabled">
                        <a class="page-link" href="javascript:;">下一页</a>
                    </li>
                    <li class="page-item disabled">
                        <a class="page-link" href="javascript:;">末页</a>
                    </li>
                </c:if>
                <li class="page-item disabled">
                    <a class="page-link" href="javascript:;">第${page.currentPage}页，共${page.totalCount}条</a>
                </li>
            </ul>
        </div>
    </div>
</c:if>
