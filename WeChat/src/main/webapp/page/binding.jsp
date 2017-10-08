<%--
  Created by IntelliJ IDEA.
  User: JH
  Date: 2017/10/7
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>绑定 | 班务管理系统</title>
    <base href="<%=basePath%>"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.2/style/weui.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.0.1/css/jquery-weui.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/light7/0.4.3/css/light7.css">
</head>
<body>
<div class="page-group">
    <div class="page page-current" id="student_page">
        <header class="bar bar-nav">
            <a class="icon icon-me pull-right" href="#admin_page"></a>
            <h1 class="title">账号绑定</h1>
        </header>
        <div class="content">
            <form id="student_form">
                <div class="list-block">
                    <ul>
                        <!-- Text inputs -->
                        <li>
                            <div class="item-content">
                                <div class="item-media"><i class="icon icon-form-settings"></i></div>
                                <div class="item-inner">
                                    <div class="item-title label">班级</div>
                                    <div class="item-input">
                                        <input type="text" name="class" id="class_picker" placeholder="请选择班级">
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="item-content">
                                <div class="item-media"><i class="icon icon-form-email"></i></div>
                                <div class="item-inner">
                                    <div class="item-title label">学号</div>
                                    <div class="item-input">
                                        <input type="text" name="number" placeholder="请输入学号" maxlength="20">
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="item-content">
                                <div class="item-media"><i class="icon icon-form-name"></i></div>
                                <div class="item-inner">
                                    <div class="item-title label">姓名</div>
                                    <div class="item-input">
                                        <input type="text" name="name" placeholder="请输入姓名" maxlength="10">
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="content-block">
                    <button type="submit" style="width: 100%;" class="button button-big button-fill button-success">立即绑定</button>
                </div>
            </form>
        </div>
    </div>

    <div class="page" id="admin_page">
        <header class="bar bar-nav">
            <a class="button button-link button-nav pull-left" href="#student_page">
                <span class="icon icon-left"></span> 返回 </a>
            <h1 class="title">系统管理员账号绑定</h1>
        </header>
        <div class="content">
            <form id="admin_form">
                <div class="list-block">
                    <ul>
                        <!-- Text inputs -->
                        <li>
                            <div class="item-content">
                                <div class="item-media"><i class="icon icon-form-name"></i></div>
                                <div class="item-inner">
                                    <div class="item-title label">用户名</div>
                                    <div class="item-input">
                                        <input type="text" name="username" placeholder="请输入用户名">
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="item-content">
                                <div class="item-media"><i class="icon icon-form-password"></i></div>
                                <div class="item-inner">
                                    <div class="item-title label">密码</div>
                                    <div class="item-input">
                                        <input type="password" name="password" placeholder="请输入密码">
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="content-block">
                    <button type="submit" style="width: 100%;" class="button button-big button-fill button-success">立即登录</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src='./static/dist/js/jquery.min.js?version=3.1.1'></script>
<script type='text/javascript' src='https://cdn.bootcss.com/light7/0.4.3/js/light7.js' charset='utf-8'></script>
<script>
    $.init();
</script>
<script>
    $(function () {
        'use strict';

        $("#class_picker").picker({
            toolbarTemplate: '<header class="bar bar-nav"><button class="button button-link pull-right close-picker">确定</button><h1 class="title">请选择班级</h1></header>',
            cols: [
                {
                    textAlign: 'center',
                    values: <?php echo $class;?>
        //如果希望显示文案和实际值不同，可以在这里加一个displayValues: [.....]
    }
    ]
    });
    })
</script>
<script>
    $(function () {
        'use strict';

        var $student_form = $('#student_form');

        $student_form.submit(function () {
            $.ajax({
                url: "<?php echo site_url('user/bind_student');?>",
                type: 'POST',
                data: $student_form.serialize(),
                dataType: 'json',
                beforeSend: function () {
                    $.showIndicator();
                },
                complete: function () {
                    $.hideIndicator();
                },
                success: function (data) {
                    if (data.success) {
                        $.alert(data.msg, '绑定成功', function () {
                            if (typeof WeixinJSBridge === "object") {
                                WeixinJSBridge.call('closeWindow');
                            }
                        });
                    } else {
                        $.alert(data.msg, '绑定失败');
                    }
                },
                error: function () {
                    $.alert('请检查网络', '绑定失败');
                }
            });
            return false;
        });


        var $admin_form = $('#admin_form');

        $admin_form.submit(function () {
            $.ajax({
                url: "<?php echo site_url('user/admin_login');?>",
                type: 'POST',
                data: $admin_form.serialize(),
                dataType: 'json',
                beforeSend: function () {
                    $.showIndicator();
                },
                complete: function () {
                    $.hideIndicator();
                },
                success: function (data) {
                    if (data.success) {
                        $.alert(data.msg, '登录成功', function () {
                            if (typeof WeixinJSBridge === "object") {
                                WeixinJSBridge.call('closeWindow');
                            }
                        });
                    } else {
                        $.alert(data.msg, '登录失败');
                    }
                },
                error: function () {
                    $.alert('请检查网络', '登录失败');
                }
            });
            return false;
        });

    })
</script>
</body>
</html>
