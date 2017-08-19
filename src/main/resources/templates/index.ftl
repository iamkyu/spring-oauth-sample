<!DOCTYPE html>
<html>
<head>
    <title>Spring Social</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <style>
        .login-btn {
            margin-bottom: 10px;
            width:254px;
            height:40px;
            display:block;
        }

        .facebook-btn {
            background: url('/image/facebook-btn.png') center top no-repeat;
            background-size: 254px 40px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Social Login</h1>
    </div>
    <div>
        <div>
            Login
        </div>
        <div>
            <form action="/login/authenticate" method="POST">
                <input name="username" value="" />
                <div style="height:5px"></div>
                <input type="password" name="password" value="" />
                <input type="submit" value="Login" />
            </form>
        </div>
    </div>

    <br/>

    <h3>Sign in with</h3>

    <ul>
        <div im
        <a class="login-btn facebook-btn" onclick="document.facebook.submit();" />

    </ul>

    <!-- /.container -->
    <form action="/auth/facebook" name="facebook">
        <input type="hidden" name="scope" value="email,user_friends"/>
    </form>
</div>
</body>
</html>
