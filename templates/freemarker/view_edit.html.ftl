<h3 class="page-title"></h3>
<div class="row">
    <div class="portlet box blue-hoki">
        <div class="portlet-title">
            <div class="caption"><span class="caption-subject uppercase">${table.remarks}编辑</span></div>
            <div class="tools">
                <a href="javascript:" class="collapse"> </a>
            </div>
            <div class="actions">
                <a class="btn btn-default btn-sm ajaxify"
                   th:href="@{/${systemName}/${moduleName}}"><i class="fa fa-arrow-left"/> 返回 </a>
            </div>
        </div><!--/ portlet-title -->
        <div class="portlet-body form">
            <form class="form-horizontal" th:action="@{/${systemName}/${moduleName}/save}" method="post"
                  data-validate="true" data-target=".page-content-body" role="form">
                <div class="form-body">
                <#list table.primaryColumns as col>
                    <input type="hidden" name="${col.javaProperty}" th:if="${"#"}{!#strings.isEmpty(${moduleName}.${col.javaProperty})}" th:value="${"#"}{${moduleName}.${col.javaProperty}}"/>
                </#list>
                <#list table.baseColumns as col>
                    <div class="form-group">
                        <label class="control-label col-sm-2">${col.title}</label>
                        <div class="col-sm-10">
                            <input class="form-control input-large" name="${col.javaProperty}" type="text"
                                   maxlength="${col.size}" <#if col.nullable>data-rule-required="true"</#if> <#if (col.size!0 > 0)>data-rule-maxlength="${col.size}"</#if>
                                   th:value="${"$"}{${moduleName}.${col.javaProperty}}">
                        </div>
                    </div>
                </#list>
                </div>
                <div class="form-actions">
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button class="btn btn-primary" type="submit">保存</button>
                        </div>
                    </div>
                </div>
            </form>
        </div><!--/ portlet-body -->
    </div><!--/ portlet -->
</div>
<script th:replace="fragments/common/notify.html"/>