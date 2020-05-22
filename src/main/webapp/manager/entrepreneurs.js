layui.use('table', function(){
    var table = layui.table,
        form = layui.form;

    var url = URL_ROOT +'manager/queryEntrepreneurs';

    table.render({
        id: 'entrepreneurs',
        elem: '#entrepreneurs'
        ,url: url
        ,toolbar: '#toolbarDemo'
        ,height : 600
        ,cols: [[
            {type:'checkbox'}
            ,{field:'account', width:120, title: '创业者账号', sort: true}
            ,{field:'name', width:120, title: '姓名'}
            ,{field:'password', width:120, title: '密码', edit: 'text'}
            ,{field:'sex', width:120, title: '性别', sort: true, edit: 'text'}
            ,{field:'phoneNumber', width:150, title: '联系方式', edit: 'text'}
            ,{field:'address', width:150, title: '家庭住址', edit: 'text'}
            ,{field:'resume', width:240, title: '个人简历', sort: true, edit: 'text'}/*
            ,{field:'teacherName', width:240, title: '绑定教师', sort: true,templet: function (d) {
                    return d.teacher.name;
                }}*/
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
    table.on('toolbar(entrepreneurs)', function(obj){
        var teacherAccount = '101';
        var requestData = {};
        requestData.entrepreneurAccount = parseInt(teacherAccount);
        switch(obj.event){
            case 'addEntrepreneurs':
                var url = 'addEntrepreneurs.html';
                openPopup(url);
        }
    });

    table.on('edit(entrepreneurs)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
        console.log(obj.value); //得到修改后的值
        console.log(obj.field); //当前编辑的字段名
        console.log(obj.data);  //所在行的所有相关数据
        var requestData = {};
        requestData.entrepreneurAccount = parseInt(obj.data.account);
        if(obj.field === "password"){
            requestData.entrepreneurPassword = obj.value;
        }
        if(obj.field === "sex"){
            requestData.entrepreneurSex = obj.value;
        }
        if(obj.field === "phoneNumber"){
            requestData.entrepreneurPhone = obj.value;
        }
        if(obj.field === "address"){
            requestData.entrepreneurAddress = obj.value;
        }
        if(obj.field === "resume"){
            requestData.entrepreneurResume = obj.value;
        }
        console.log(JSON.stringify(requestData));
        request(requestData);
    });

    function reloadTable() {
        table.reload('entrepreneurs', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
        }, 'data');
    }

    function openPopup(url){
        layer.open({
            type: 2,
            title: '添加创业者',
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
            url: URL_ROOT + 'manager/updateEntrepreneur',
            data: {
                json: JSON.stringify(requestData)
            },
            success: function (data) {
                console.log(data);
                if (data.successful === true) {
                    layer.msg(data.msg);
                    reloadTable();
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