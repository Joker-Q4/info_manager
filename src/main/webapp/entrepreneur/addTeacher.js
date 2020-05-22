layui.use('table', function(){
    var table = layui.table,
        form = layui.form;

    var entrepreneurId = sessionStorage.getItem(entrepreneur_session);
    var url = '/entrepreneur/queryTeachers';

    table.render({
        id: 'bindTeacher',
        elem: '#teachers'
        ,url: url
        ,toolbar: '#toolbarDemo'
        ,height : 600
        ,cols: [[
            {type:'radio'}
            ,{field:'account', width:120, title: '教师账号', sort: true}
            ,{field:'name', width:120, title: '姓名'}
            ,{field:'sex', width:120, title: '性别', sort: true}
            ,{field:'phoneNumber', width:150, title: '联系方式'}
            ,{field:'address', width:150, title: '家庭住址'}
            ,{field:'resume', width:240, title: '个人简历', sort: true}
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
    table.on('toolbar(teachers)', function(obj){
      //  console.log(JSON.stringify(table.cache))
        var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
        var data = checkStatus.data;  //获取选中行数据
        if(data.length !== 1){
            layer.alert("请选中一名教师");
            return;
        }
        if(obj.event === 'bindTeacher'){
            layer.confirm('确认绑定' + data[0].name + '老师吗？', {
                btn: ['确定','取消']
            },function(index){
                var url = URL_ROOT + 'entrepreneur/bindTeacher';
                var requestData = {};
                requestData.entrepreneurAccount = entrepreneurId;
                requestData.teacherAccount = data[0].account;
                request(requestData, url);
                layer.close(index);
            },function(index){
                layer.close(index);
            });
        }
    });

    function request(requestData, url) {
        $.ajax({
            type: 'POST',
            dataType:'json',
            url: url,
            data: {
                json: JSON.stringify(requestData)
            },
            success: function (data) {
                console.log(data);
                if (data.successful === true) {
                    parent.location.reload();
                    parent.layer.close(index);
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