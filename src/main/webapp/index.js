$(function () {
    getNotice();
    $("#commit").click(function () {
        commit();
    });
});

function getNotice() {
    $.ajax({
        type: "GET",
        url: URL_ROOT + 'login/getNotice',
        success: function (result) {
            if(result.code === 0){
                $("#login").hide();
                $("#notice").show();
                $("#content").html(result.data);
            }
        },
        error: function (s, s2, s3) {
            $("#login").show();
            layer.alert('公告信息请求异常');
        }
    });
}

function commit() {
    var requestUrl, toUrl, key;
    switch ($("#comeIn").val()){
        case '0':
            requestUrl = URL_ROOT + 'login/loginEntrepreneur';
            key = entrepreneur_session;
            toUrl = URL_ROOT + key + '/projects.html';
            break;
        case '1':
            requestUrl = URL_ROOT + 'login/loginTeacher';
            key = teacher_session;
            toUrl = URL_ROOT + key + '/projects.html';
            break;
        case '2':
            requestUrl = URL_ROOT + 'login/loginManager';
            key = manager_session;
            toUrl = URL_ROOT + key + '/entrepreneurs.html';
            break;
        default: layer.alert("参数异常");return;
    }
    var data = {};
    data.account = $("#account").val();
    data.password = $("#password").val();
    request(data, requestUrl, toUrl, key);
}

function request(requestData, requestUrl, toUrl, key) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: requestUrl,
        data: {
            json: JSON.stringify(requestData)
        },
        success: function (result) {
            if(result.successful === true){
                sessionStorage.setItem(key, requestData.account);
                window.location.href = toUrl;
            }else {
                layer.alert(result.msg);
            }
        },
        error: function (s, s2, s3) {
            layer.alert('请求异常');
        }
    });
}