layui.use('table', function(){
    var table = layui.table,
        form = layui.form;

    var current = 2;

    var url = '/manager/queryProjects';

    table.render({
        id: 'projects',
        elem: '#projects'
        ,url: url
        ,toolbar: '#toolbarDemo'
        ,height : 600
        ,cols: [[
            {type:'radio'}
            ,{field:'id', width:120, title: '项目主键', sort: true}
            ,{field:'name', width:120, title: '项目名称'}
            ,{field:'entrepreneurName', width:120, title: '申请者', sort: true,
                templet: '<div>{{d.entrepreneur.name}}</div>'
            }
            ,{field:'content', width:150, title: '内容'}
            ,{field:'funds', width:150, title: '预计经费'}
            ,{field:'managerState', width:150, title: '管理员是否通过',
                templet: function (d) {
                    // 模板的实现方式也是多种多样，这里简单返回固定的
                    return '<select name="state" lay-filter="stateSelect" lay-verify="required" data-value="' + d.managerState + '" >\n' +
                        '        <option value="0">待审批</option>\n' +
                        '        <option value="1">退回</option>\n' +
                        '        <option value="2">通过</option>\n' +
                        '      </select>';
                }}
            ,{field:'managerBack', width:150, title: '退回原因', edit: 'text',
                templet: function (d) {
                    if(d.managerState === 1){
                        return d.managerBack;
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

    // 监听修改update到表格中
    form.on('select(stateSelect)', function (data) {
        //获取当前主键
        var requestData = {};
        requestData.id = parseInt(this.parentNode.parentNode.parentNode.parentNode.parentNode.childNodes[1].textContent);
        requestData.managerState = parseInt(data.value);
        request(requestData);
    });

    //头工具栏事件
    table.on('toolbar(projects)', function(obj){
        var url = URL_ROOT + 'manager/queryProjects';
        switch(obj.event){
            case 'getPassProjects':
                url += '?managerState=2';
                current = 2;
                break;
            case 'getUnCheckProjects':
                url += '?managerState=0';
                current = 0;
                break;
            case 'getNoPassProjects':
                url += '?managerState=1';
                current = 1;
                break;
        }
        reload(url);
    });

    table.on('edit(projects)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
        console.log(obj.value); //得到修改后的值
        console.log(obj.field); //当前编辑的字段名
        console.log(obj.data);  //所在行的所有相关数据
        var requestData = {};
        requestData.id = parseInt(obj.data.id);
        if(obj.field === "managerState"){
            requestData.managerState = obj.value;
        }
        if(obj.field === "managerBack"){
            requestData.managerBack = obj.value;
        }
        request(requestData);
    });

    function reload(url) {
        console.log(url);
        table.reload('projects', {
            url: url,
            page: {
                curr: 1 //重新从第 1 页开始
            }
        }, 'data');
    }

    function request(requestData) {
        $.ajax({
            type: 'POST',
            dataType:'json',
            url: URL_ROOT + 'manager/updateProject',
            data: {
                json: JSON.stringify(requestData)
            },
            success: function (data) {
                console.log(data);
                if (data.successful === true) {
                    layer.msg(data.msg);
                    var url = URL_ROOT + 'manager/queryProjects';
                    switch(current){
                        case 2:
                            url += '?managerState=2';
                            current = 2;
                            break;
                        case 0:
                            url += '?managerState=0';
                            current = 0;
                            break;
                        case 1:
                            url += '?managerState=1';
                            current = 1;
                            break;
                    }
                    reload(url);
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