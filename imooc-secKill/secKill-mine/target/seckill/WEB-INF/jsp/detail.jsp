<%--
  Created by IntelliJ IDEA.
  User: storm
  Date: 2017-09-25
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀详情页</title>
    <%@include file="common/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel panel-default text-center">
        <div class="pannel-heading">
            <h1>${seckill.name}</h1>
        </div>

        <div class="panel-body">
            <h2 class="text-danger">
                <%--显示time图标--%>
                <span class="glyphicon glyphicon-time"></span>
                <%--展示倒计时--%>
                <span class="glyphicon" id="seckill-box"></span>
            </h2>
        </div>
    </div>
</div>
<%--登录弹出层 输入电话--%>
<div id="killPhoneModal" class="modal fade">

    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-center">
                    <span class="glyphicon glyphicon-phone"> </span>秒杀电话:
                </h3>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-8 col-xs-offset-2">
                        <input type="text" name="killPhone" id="killPhoneKey"
                               placeholder="填写手机号^o^" class="form-control">
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <%--验证信息--%>
                <span id="killPhoneMessage" class="glyphicon"> </span>
                <button type="button" id="killPhoneBtn" class="btn btn-success">
                    <span class="glyphicon glyphicon-phone"></span>
                    Submit
                </button>
            </div>

        </div>
    </div>

</div>

</body>
<%--jQery文件,务必在bootstrap.min.js之前引入--%>
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<%--使用CDN 获取公共js http://www.bootcdn.cn/--%>
<%--jQuery Cookie操作插件--%>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<%--jQuery countDown倒计时插件--%>
<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>

<%--<script src="../resources/script/seckill.js" type="text/javascript"></script>--%>


<script type="text/javascript">
    //存放主要交互逻辑的js代码
    // javascript 模块化(package.类.方法)
    var seckill = {
        //封装秒杀相关ajax的url
        URL: {},

        //验证手机号
        validatePhone: function (phone) {
            if (phone && phone.length == 11 && !isNaN(phone)) {
                return true;//直接判断对象会看对象是否为空,空就是undefine就是false; isNaN 非数字返回true
            } else {
                return false;
            }
        },

        //详情页秒杀逻辑
        detail: {
            //详情页初始化
            init: function (params) {
                //手机验证和登录，计时交互
                //规划交互流程
                //在cookie中查找手机号

                var killPhone = $.cookie('killPhone');
                var startTime = params['startTime'];
                var endTime = params['endTime'];
                var seckillId = params['seckillId'];
                console.log("---killPhone:"+killPhone+"---startTime:"+startTime);
                //验证手机号
                if (!seckill.validatePhone(killPhone)) {
                    //绑定phone
                    //控制输出
                    var killPhoneModal = $('#killPhoneModal');
                    //显示弹出层
                    killPhoneModal.modal({
                        show: true,//显示弹出层
                        backdrop: false,//禁止位置关闭
                        keyboard: false//关闭键盘事件
                    });
                    $('#killPhoneBtn').click(function () {
                        var inputPhone = $('#killPhoneKey').val();
                        console.log('---inputphone='+inputPhone);
                        if (seckill.validatePhone(inputPhone)) {
                            //电话写入cookie
                            $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'})
                            //刷新页面
                            window.location.reload();
                        } else {
                            $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误</label>').show(300);
                        }
                    });
                }
                //已经登录
            }
        }
    }
</script>


<script type="text/javascript">
    $(function () {
        //使用EL表达式传入参数
        seckill.detail.init({
            seckillId:${seckill.seckillId},
            startTime:${seckill.startTime.time},//毫秒
            endTime:${seckill.endTime.time}
        });
    })
</script>
</html>
