layui.use(['form', 'layedit', 'laydate'], function(){
    var form = layui.form;

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
        ,entrepreneurPassword: [
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

    var entrepreneurId = sessionStorage.getItem(entrepreneur_session);
    var url = URL_ROOT +'entrepreneur/queryInformation?account=' + entrepreneurId;

    getData(url, function(result){
        console.log(result);
        var data = result.data;
        //表单赋值
        form.val('person', {
            "entrepreneurAccount": data.account
            ,"entrepreneurName": data.name
            ,"entrepreneurPassword": data.password
            ,"entrepreneurSex": data.sex
            ,"entrepreneurPhone": data.phoneNumber
            ,"entrepreneurAddress": data.address
            ,"entrepreneurResume": data.resume
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
            url: URL_ROOT + 'entrepreneur/updateEntrepreneur',
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