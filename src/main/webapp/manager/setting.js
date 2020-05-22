layui.use(['form', 'layedit', 'laydate'], function(){
    var form = layui.form;

    $("#reset").click(function () {
        window.location.reload();
    });

    //监听提交
    form.on('submit(commit)', function(data){
        request(data.field);
        return false;
    });

    var url = URL_ROOT +'manager/getNotice';

    getData(url, function(result){
        var data = result.data;
        //表单赋值
        form.val('person', {
            "content": data.content
            ,"isShow": data.isShow
        });
    });

    function getData(url, callBack) {
        $.ajax({
            type: 'GET',
            url: url,
            success: function (data) {
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
        console.log(JSON.stringify(requestData));
        $.ajax({
            type: 'POST',
            dataType:'json',
            url: URL_ROOT + 'manager/updateNotice',
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