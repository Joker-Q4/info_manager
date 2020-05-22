layui.use('table', function(){
    var table = layui.table,
        form = layui.form;

    var entrepreneurId = sessionStorage.getItem(entrepreneur_session);
    var url = '/entrepreneur/queryTeacher?entrepreneurId=' + entrepreneurId;

    table.render({
        id: 'bindTeacher',
        elem: '#test'
        ,url: url
        ,toolbar: '#toolbarDemo'
        ,height : 600
        ,cols: [[
         //   {type:'radio'}
           /* ,*/
            {field:'account', width:120, title: '教师账号', sort: true, templet: function (d) {
                    return d.teacher.account;
                }}
            ,{field:'name', width:120, title: '姓名', templet: function (d) {
                    return d.teacher.name;
                }}
            ,{field:'sex', width:120, title: '性别', sort: true, templet: function (d) {
                    return d.teacher.sex;
                }}
            ,{field:'phoneNumber', width:150, title: '联系方式', templet: function (d) {
                    return d.teacher.phoneNumber;
                }}
            ,{field:'address', width:150, title: '家庭住址', templet: function (d) {
                    return d.teacher.address;
                }}
            ,{field:'resume', width:240, title: '个人简历', sort: true, templet: function (d) {
                    return d.teacher.resume;
                }}
            ,{field:'currentState', width:240, title: '状态', sort: true,
                templet: function (d) {
                    switch (d.currentState) {
                        case 0: return '申请中';
                        case 1: return '退回';
                        case 2: return '通过';
                    }
                }}
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
        var allData = table.cache.bindTeacher;
        switch(obj.event){
            case 'bindTeacher':
                if(allData.length !== 0 && allData[0].currentState === 2){
                    layer.alert("您已绑定教师，请勿重复绑定！");
                    return;
                }
                var url = 'addTeacher.html';
                openPopup(url);
                break;
            case 'cancelTeacher':
                if(allData.length === 0){
                    layer.alert("您尚未绑定教师");
                    return;
                }
                if(allData[0].currentState !== 0){
                    layer.alert("只能取消绑定正在申请中的老师");
                    return;
                }
                layer.confirm('确定取消申请绑定' + allData[0].teacher.name + '老师吗？', {
                    btn: ['确定','取消']
                },function(index){
                    var url = URL_ROOT + 'entrepreneur/unBindTeacher';
                    var requestData = {};
                    requestData.entrepreneurAccount = entrepreneurId;
                    request(requestData, url);
                    layer.close(index);
                },function(index){
                    layer.close(index);
                });
                break;
            case 'unBindTeacher':
              //  var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
             //   var data = checkStatus.data;  //获取选中行数据
                if(allData.length === 0){
                    layer.alert("您尚未绑定教师");
                    return;
                }
                if(allData[0].currentState !== 2){
                    layer.alert("只能取消已绑定的老师");
                    return;
                }
                layer.confirm('确定解除绑定' + allData[0].teacher.name + '老师吗？', {
                        btn: ['确定','取消']
                    },function(index){
                    var url = URL_ROOT + 'entrepreneur/unBindTeacher';
                    var requestData = {};
                    requestData.entrepreneurAccount = entrepreneurId;
                    request(requestData, url);
                    layer.close(index);
                    },function(index){
                    layer.close(index);
                });
                break;
        }
    });


    function reloadTable() {
        table.reload('bindTeacher', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
        }, 'data');
    }

    function openPopup(url){
        layer.open({
            type: 2,
            title: '申请绑定教师',
            closeBtn: 1,
            maxmin: true,
            shadeClose: true,
            area: ['80%', '80%'],
            content: url
        });
    }

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
                    reloadTable();
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