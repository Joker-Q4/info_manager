layui.use('table', function(){
    var table = layui.table,
        form = layui.form;

    var entrepreneurId = sessionStorage.getItem(entrepreneur_session);
    var url = '/entrepreneur/queryProjects?entrepreneurId=' + entrepreneurId;

    table.render({
        elem: '#test'
        ,url: url
        ,toolbar: '#toolbarDemo'
        ,height : 600
        ,cols: [[
            {type:'radio'}
            ,{field:'id', width:120, title: '项目主键', sort: true}
            ,{field:'name', width:120, title: '项目名称', edit: 'text'}
            ,{field:'entrepreneurName', width:120, title: '申请者', sort: true,
                templet: '<div>{{d.entrepreneur.name}}</div>'
            }
            ,{field:'content', width:150, edit: 'text', title: '内容'}
            ,{field:'funds', width:150, edit: 'text', title: '预计经费'}
            ,{field:'managerState', width:150, title: '管理员是否通过',
                templet: function (d) {
                    switch (d.managerState) {
                        case 0: return '待审批';
                        case 1: return '退回';
                        case 2: return '通过';
                    }
                }}
            ,{field:'managerBack', width:150, title: '退回原因',
                templet: function (d) {
                    if(d.managerState === 1){
                        return d.managerBack;
                    }
                    return '';
                }}
            ,{field:'teacherState', width:150, title: '指导教师是否通过',
                templet: function (d) {
                    switch (d.teacherState) {
                        case 0: return '待审批';
                        case 1: return '退回';
                        case 2: return '通过';
                    }
                }}
            ,{field:'teacherBack', width:150, title: '退回原因',
                templet: function (d) {
                    if(d.teacherState === 1){
                        return d.teacherBack;
                    }
                    return '';
                }}
            ,{field:'createTime', width:240, title: '创建时间'}
            ,{field:'updateTime', width:240, title: '修改时间', sort: true}
        ]]
        ,done: function (res, curr, count) {
            var tableElem = this.elem.next('.layui-table-view');
            count || tableElem.find('.layui-table-header').css('overflow', 'auto');
            layui.each(tableElem.find('select'), function (index, item) {
                var elem = $(item);
                elem.val(elem.data('value')).parents('div.layui-table-cell').css('overflow', 'visible');
            });
            form.render();
        }
        ,page: true
    });

    //头工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
        switch(obj.event){
            case 'addProject':
                var url = 'addProject.html';
                openPopup(url);
                break;
            case 'getCheckData':
                var data = checkStatus.data;  //获取选中行数据
                layer.alert(JSON.stringify(data));
                break;
        }
    });

    table.on('edit(test)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
        console.log(obj.value); //得到修改后的值
        console.log(obj.field); //当前编辑的字段名
        console.log(obj.data);  //所在行的所有相关数据
        var requestData = {};
        requestData.id = parseInt(obj.data.id);
        if(obj.field === "name"){
            requestData.name = obj.value;
        }
        if(obj.field === "content"){
            requestData.content = obj.value;
        }
        if(obj.field === "funds"){
            requestData.funds = parseFloat(obj.value);
        }
        request(requestData);
    });

    function openPopup(url){
        layer.open({
            type: 2,
            title: '添加项目',
            closeBtn: 1,
            maxmin: true,
            shadeClose: true,
            area: ['80%', '80%'],
            content: url
        });
    }

    function request(requestData) {
        $.ajax({
            type: 'POST',
            dataType:'json',
            url: URL_ROOT + 'entrepreneur/updateProject',
            data: {
                json: JSON.stringify(requestData)
            },
            success: function (data) {
                console.log(data);
                if (data.successful === true) {
                    layer.msg(data.msg);
                } else {
                    layer.msg(data.msg);
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                layer.msg("请求失败");
            }
        });
    }

});