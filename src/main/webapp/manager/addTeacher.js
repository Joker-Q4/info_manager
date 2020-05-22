layui.use(['form', 'layedit', 'laydate'], function(){
    var form = layui.form;

    //验证规则
    form.verify({
        teacherAccount: function(value){
            if(value.length === 0){
                return '账号不能为空！';
            }
        }
        ,teacherName: function(value){
            if(value.length === 0){
                return '姓名不能为空！';
            }
        }
        ,teacherPassword: [
            /^[\S]{6,12}$/
            ,'密码必须6到12位，且不能出现空格'
        ]
    });

    $("#reset").click(function () {
        location.reload();
    });

    //监听提交
    form.on('submit(commit)', function(data){
        /*layer.alert(JSON.stringify(data.field), {
            title: '最终的提交信息'
        });*/
        request(data.field);
        return false;
    });

    function request(requestData) {
        $.ajax({
            type: 'POST',
            dataType:'json',
            url: URL_ROOT + 'manager/addTeacher',
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