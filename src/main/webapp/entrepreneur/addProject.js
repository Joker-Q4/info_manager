layui.use(['form', 'layedit', 'laydate'], function(){
    var form = layui.form;

    var entrepreneurAccount = sessionStorage.getItem(entrepreneur_session);

    form.val('project', {
        "entrepreneurAccount": entrepreneurAccount
    });

    $("#reset").click(function () {
        location.reload();
    });

    //验证规则
    form.verify({
        entrepreneurAccount: function(value){
            if(value.length === 0){
                return '账号不能为空！';
            }
        }
        ,name: function(value){
            if(value.length === 0){
                return '项目名称不能为空！';
            }
        }
        ,content: function(value){
            if(value.length === 0){
                return '项目内容不能为空！';
            }
        }
        ,funds: function(value){
            if(value.length === 0){
                return '预计经费不能为空！';
            }
        }
    });

    //监听提交
    form.on('submit(commit)', function(data){
        request(data.field);
        return false;
    });

    function request(requestData) {
        $.ajax({
            type: 'POST',
            dataType:'json',
            url: URL_ROOT + 'entrepreneur/addProject',
            data: {
                json: JSON.stringify(requestData)
            },
            success: function (data) {
                console.log(data);
                if (data.successful === true) {
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
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