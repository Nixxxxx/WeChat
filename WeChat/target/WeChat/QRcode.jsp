<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>二维码生成</title>

<script type="text/javascript" src="http://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>  
<script type="text/javascript" src="http://cdn.staticfile.org/jquery.qrcode/1.0/jquery.qrcode.min.js"></script> 
</head>
<body>
生成的二维码如下<br>
<div id="qrcode">
</div>
<script type="text/javascript">
	$("#qrcode").qrcode("www.imooc.com");
</script>
</body>
</html>