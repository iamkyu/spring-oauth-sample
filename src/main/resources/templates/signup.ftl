<!DOCTYPE html>
<html>
<head>
    <title>Spring Social</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Social Login</h1>
    </div>
<#if user??>
    <form action="/signup" method="POST">
        <div>
            <div>
                Sign-up
            </div>
            <div>
                <div style="padding:5px;">email</div>
                <input style="width:100%;" name="email" value="${user.email!""}" required/><br/>

                <div style="padding:5px;">name</div>
                <input style="width:100%;" name="name" value="${user.name!""}"/><br/>

                <div style="padding:5px;">password</div>
                <input style="width:100%;" type="password" name="password" value="" required/>

            </div>
        </div>

        <div style="padding:10px;text-align: right;">
            <input type="submit" value="Sign-up" />
            <button type="button" onclick="location.href = '/';">Cancel</button>
        </div>
    </form>

<#else>
    <div>
        <div>
            Failed to get User info
        </div>
    </div>
</#if>
</div>
</body>
</html>