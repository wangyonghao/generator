<div class="portlet box blue-hoki">
    <div class="portlet-title">
        <div class="caption">
            <span class="caption-subject uppercase">${table.remarks}管理</span>
        </div>
        <div class="tools">
            <a href="javascript:" class="collapse"> </a>
        </div>
        <div class="actions">
            <a class="btn btn-default btn-sm ajaxify" th:href="@{/${systemName}/${moduleName}/add}"><i class="fa fa-plus"/> 添加 </a>
        </div>
    </div>
    <div class="portlet-body">
        <table class="table table-striped table-bordered table-hover table-condensed" width="100%" cellspacing="0"
               id="datatable" data-url="${systemName}/${moduleName}/page"/>
        <script type="text/javascript">
            ${"$"}(function(){
                /* 数据表配置 */
                var setting = {
                    //这个属性下的设置会应用到所有列，按顺序,没有是空
                    "columns": [
                    <#list table.baseColumns as col>
                        {"title": "${col.title}", "data": "${col.javaProperty}", "name":"${col.columnName}"},
                    </#list>
                        {"title": "", "data": null, "orderable": false,"searchable":false,
                            render:function(data,type,row){
                                var html = "";
                                html += '<a class="btn btn-default btn-sm ajaxify" href="${systemName}/${moduleName}/edit/{0}"><i class="fa fa-pencil"/> 编辑 </a>';
                                html += '<a class="btn btn-default btn-sm" href="${systemName}/${moduleName}/del/{0}" data-action="delete" data-content="确认要删除么?"><i class="fa fa-trash"/> 删除 </a>';
                                return; ${"$"}.validator.format(html,row.id);
                            }
                        }
                    ]
                };

                var table = ${"$"}("#datatable").dataTable(setting).api();

                /* 绑定删除按钮事件 */
                ${"$"}('[data-action="delete"]').confirm({
                    complete: function (res) { //调用(自定义buttons时不可用)
                        //操作成功，删除当前行
                        if (res.error == 0) this.$target.closest("tr").remove();
                    }
                });
            });
        </script>
    </div>
</div>
<script th:replace="fragments/common/notify.html"/>