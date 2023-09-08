<!DOCTYPE html>
<html>
<head>
    <meta charset="utf‐8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>我的账单</title>
    <link rel="apple-touch-icon" href="icon.png">
    <link href="icon.png" sizes="114x114" rel="apple-touch-icon-precomposed">
<#--    <link rel="stylesheet" href="http://www.getuikit.net/docs/css/uikit.docs.min.css"/>-->
<style>
    li span{
        width:6rem;
        display: block;
        float: left;
    }
    body{
        font-size: 15px;
    }
</style>
</head>
<body>

<div class="uk-overflow-container" style="margin: 10px;">
    <a class="uk-button" href="add">添加</a>
    <span style="float:right">余额 ${balance}元</span>
    <#list billMonth as bill>
        <h3 align="center">
            ${bill.billDate?string('yyyy年MM月')}
            ${bill.amount}元
        </h3>
        <table class="uk-table "  >
            <tbody>
                <#list bill.billDetails as b>
                    <tr>
                        <td>${b.billDate?string('dd')}日</td>
                        <td>${b.amount}元</td>
                        <td>
                            <ul class="uk-list uk-list-line">
                                <#list b.billDetails as detail>
                                    <li><span>${detail.billName!}</span> ${detail.amount}元</li>
                                </#list>
                            </ul>
                            <span style="float:right;color: red">债 ${b.balance}元</span>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </#list>

</div>
</body>
</html>