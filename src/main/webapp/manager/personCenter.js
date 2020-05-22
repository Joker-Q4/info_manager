layui.use(['form', 'layedit', 'laydate'], function(){
    var form = layui.form;

    //验证规则
    form.verify({
        managerAccount: function(value){
            if(value.length === 0){
                return '账号不能为空！';
            }
        }
        ,managerName: function(value){
            if(value.length === 0){
                return '姓名不能为空！';
            }
        }
        ,managerPassword: [
            /^[\S]{6,12}$/
            ,'密码必须6到12位，且不能出现空格'
        ]
    });

    $("#reset").click(function () {
        window.location.reload();
    });

    //监听提交
    form.on('submit(commit)', function(data){
        request(data.field);
        return false;
    });

    var managerId = sessionStorage.getItem(manager_session);
  //  var managerId = 11;
    var url = URL_ROOT +'manager/queryInformation?account=' + managerId;

    getData(url, function(result){
        var data = result.data;
        //表单赋值
        form.val('person', {
            "managerAccount": data.account
            ,"managerName": data.name
            ,"managerPassword": data.password
            ,"managerSex": data.sex
            ,"managerPhone": data.phoneNumber
            ,"managerAddress": data.address
            ,"managerResume": data.resume
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
            url: URL_ROOT + 'manager/updateManager',
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