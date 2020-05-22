layui.use(['form', 'layedit', 'laydate'], function(){
    var form = layui.form;

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
        ,entrepreneurName: function(value){
            if(value.length === 0){
                return '姓名不能为空！';
            }
        }
        ,entrepreneurPassword: function(value){
            if(value.length === 0){
                return '密码不能为空！';
            }
        }
        ,entrepreneurPhone: function(value){
            if(value.length === 0){
                return '联系方式不能为空！';
            }
        }
    });

    //监听提交
    form.on('submit(commit)', function(data){
        request(data.field);
        return false;
    });

    function request(requestData) {
        console.log(JSON.stringify(requestData));
        $.ajax({
            type: 'POST',
            dataType:'json',
            url: URL_ROOT + 'manager/addEntrepreneur',
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