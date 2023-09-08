<!DOCTYPE html>
<html>
<head>
    <meta charset="utf‐8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>添加账单</title>
<#--    <link rel="stylesheet" href="http://www.getuikit.net/docs/css/uikit.docs.min.css"/>-->

</head>
<body>
<div class="uk-overflow-container" style="margin: 10px;">
    <a class="uk-button" href="./">查账单</a>
    <form class="uk-form" action="add?${_csrf.parameterName}=${_csrf.token}" method="post">
        <fieldset>
            <legend>录入</legend>
            <#list labels as b>
                <a href="javascript:toName('${b}')">${b}</a>
            </#list>
            <div class="uk-form-row">
                <input type="text" name="name" id="name" placeholder="账单名称" value="余额">
                <input type="number" pattern="[0-9]*" name="amount" placeholder="金额" value="${balance?c}">
                <select name="cycle">
                    <option value="-1">天</option>
                    <option value="0">周</option>
                    <option value="1" selected>月</option>
                    <option value="2">年</option>
                </select>
            </div>
            <div class="uk-form-row">
                <input type="text" pattern="[0-9]*" name="periods" placeholder="期数" value="1">

                <input type="date" name="startDate" id="start-date" placeholder="开始日期">
            </div>
            <div class="uk-form-row">
                <button class="uk-button" type="submit">保存</button>
            </div>

        </fieldset>
    </form>

    <table class="uk-table "  >
        <tbody>
        <#list list as b>
            <tr>
                <td>${b.name}</td>
                <td>${b.amount}元</td>
                <td>${b.periods}期</td>
                <td>${(b.createDate?string('yyyy-MM-dd'))!''}</td>
                <td onclick="delBill('${b.id?string("#")}')">删除</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
</body>
<script>
    document.getElementById('start-date').valueAsDate = new Date();
    function delBill(id){
        if(confirm('删除？')){
            location.href = './delete?id='+id;
        }
    }
    function toName(name){
        document.getElementById('name').value = name;
    }
</script>
</html>