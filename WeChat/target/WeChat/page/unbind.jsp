<%--
  Created by IntelliJ IDEA.
  User: JH
  Date: 2017/10/8
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>取消绑定 | 班务管理系统</title>
    <base href="<%=basePath%>"/>
    <link rel="stylesheet" href="//cdn.bootcss.com/weui/1.1.2/style/weui.css">
    <link rel="stylesheet" href="//cdn.bootcss.com/jquery-weui/1.0.1/css/jquery-weui.css">
</head>
<body>
<div class="weui-msg">
    <div class="weui-msg__icon-area"><i class="weui-icon-info weui-icon_msg"></i></div>
    <div class="weui-msg__text-area">
        <h2 class="weui-msg__title">取消绑定</h2>
        <p class="weui-msg__desc">您确定要取消绑定吗？取消绑定后您将不能正常使用此系统</p>
    </div>
    <div class="weui-msg__opr-area">
        <p class="weui-btn-area">
            <a href="javascript:" class="weui-btn weui-btn_warn cancel">确认取消</a>
        </p>
        <p class="weui-btn-area">
            <a href="javascript:if (typeof WeixinJSBridge === 'object') WeixinJSBridge.call('closeWindow')" class="weui-btn weui-btn_primary">不取消了</a>
        </p>
    </div>
    <div class="weui-msg__extra-area">
        <div class="weui-footer">
            <p class="weui-footer__text">Copyright &copy; 2017 班务管理系统</p>
        </div>
    </div>
</div>

</body>
<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>
<script>
    $(function () {
        $('.cancel').click(function () {
            $.confirm({
                title: '确认取消绑定',
                text: '您确实要取消绑定吗？取消绑定后您将不能正常使用该系统',
                onOK: function () {
                    //点击ok
                    $.ajax({
                        url: "<?php echo site_url('user/cancel_bind');?>",
                        type: 'POST',
                        dataType: 'json',
                        success: function (data) {
                            if (data.success) {
                                $.alert(data.msg, '取消成功', function () {
                                    if (typeof WeixinJSBridge === 'object') {
                                        WeixinJSBridge.call('closeWindow');
                                    }
                                });
                            } else {
                                $.alert(data.msg, '取消失败');
                            }
                        },
                        error: function () {
                            $.alert('请检查网络', '取消失败');
                        }
                    });
                },
                onCancel: function () {
                    //点击取消
                }
            });
        });
    })
</script>
</html>