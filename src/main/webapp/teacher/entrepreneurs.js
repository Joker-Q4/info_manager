layui.use('table', function(){
    var table = layui.table,
        form = layui.form;

    var current = 2;

    //0申请中，1已拒绝；2已通过
   // var teacherAccount = '101';
    var teacherAccount = sessionStorage.getItem(teacher_session);
    var url = URL_ROOT +'teacher/queryEntrepreneurs?applyTeacherAccount='
            + teacherAccount +
            '&currentState=' + 2;

    table.render({
        elem: '#test'
        ,url: url
        ,toolbar: '#toolbarDemo'
        ,height : 600
        ,cols: [[
        /*    {type:'checkbox'},*/
            {field:'account', width:120, title: '账号', sort: true}
            ,{field:'name', width:120, title: '姓名'}
            ,{field:'sex', width:120, title: '性别', sort: true}
            ,{field:'phoneNumber', width:150, title: '联系方式'}
            ,{field:'currentState', width:150, title: '是否通过',
                templet: function (d) {
                    // 模板的实现方式也是多种多样，这里简单返回固定的
                    return '<select name="state" lay-filter="stateSelect" lay-verify="required" data-value="' + d.currentState + '" >\n' +
                        '        <option value="0">待审批</option>\n' +
                        '        <option value="1">已退回</option>\n' +
                        '        <option value="2">通过</option>\n' +
                        '      </select>';
                }}
            ,{field:'address', width:150, title: '家庭住址'}
            ,{field:'resume', width:150, title: '个人简历'}
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
        requestData.entrepreneurAccount = parseInt(this.parentNode.parentNode.parentNode.parentNode.parentNode.childNodes[0].textContent);
        requestData.currentState = parseInt(data.value);
        console.log(JSON.stringify(requestData));
        request(requestData);
    });

    //头工具栏事件
    table.on('toolbar(test)', function(obj){
     //   var teacherAccount = '101';
        var url = URL_ROOT +'teacher/queryEntrepreneurs?applyTeacherAccount='
            + teacherAccount;
        switch(obj.event){
            case 'getBoundAlready':
                url += '&currentState=2';
                current = 2;
                break;
            case 'getBoundPending':
                url += '&currentState=0';
                current = 0;
                break;
            case 'getBoundBack':
                url += '&currentState=1';
                current = 1;
                break;
            default:
                url += '&currentState=2';
                current = 2;
                break;
        }
        reload(url);
    });

    table.on('edit(test)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
        console.log(obj.value); //得到修改后的值
        console.log(obj.field); //当前编辑的字段名
        console.log(obj.data);  //所在行的所有相关数据
        var requestData = {};
        requestData.id = parseInt(obj.data.id);
        if(obj.field === "funds"){
            requestData.funds = parseFloat(obj.value);
        }
        if(obj.field === "reasonBack"){
            requestData.reasonBack = obj.value;
        }
        request(requestData);
    });

    function request(requestData) {
        $.ajax({
            type: 'POST',
            dataType:'json',
            url: URL_ROOT + 'teacher/updateEntrepreneur',
            data: {
                json: JSON.stringify(requestData)
            },
            success: function (data) {
                console.log(data);
                if (data.successful === true) {
                    layer.msg(data.msg);
                    var url = URL_ROOT +'teacher/queryEntrepreneurs?applyTeacherAccount='
                        + teacherAccount;
                    switch(current){
                        case 2:
                            url += '&currentState=2';
                            break;
                        case 0:
                            url += '&currentState=0';
                            break;
                        case 1:
                            url += '&currentState=1';
                            break;
                        default:
                            url += '&currentState=2';
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

    function reload(url) {
        table.reload('test', {
            url: url,
            page: {
                curr: 1 //重新从第 1 页开始
            }
        }, 'data');
    }
});