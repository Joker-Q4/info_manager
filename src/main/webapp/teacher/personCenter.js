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

  //  var teacherAccount = '101';
    var teacherAccount = sessionStorage.getItem(teacher_session);
    var url = URL_ROOT +'teacher/queryInformation?account=' + teacherAccount;
    getData(url, function(result){
        console.log(result);
        var data = result.data;
        //表单赋值
        form.val('person', {
            "teacherAccount": data.account
            ,"teacherName": data.name
            ,"teacherPassword": data.password
            ,"teacherSex": data.sex
            ,"teacherPhone": data.phoneNumber
            ,"teacherAddress": data.address
            ,"teacherResume": data.resume
        });
    });

    function getData(url, callBack) {
        $.ajax({
            type: 'GET',
            url: url,
            success: function (data) {
                console.log(data);
                if (data.code === 0) {
                    callBack(data);
                } else {
                    layer.msg(data.msg);
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                layer.msg("请求失败");
            }
        });
    }

    function request(requestData) {
        $.ajax({
            type: 'POST',
            dataType:'json',
            url: URL_ROOT + 'teacher/updateTeacher',
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