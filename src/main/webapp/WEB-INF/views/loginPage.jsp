<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container">
    <div id="content">
        <div class="wrapper">
            <div class="head">
                <a href="//companyname.com"><img src="//companyname.com/resources/images/companyname-logo.png?v=52.0.10.20150929.182916" alt="companyname-logo"></a>
            </div>
            <div id="form">
                <c:if test="${not empty error}">
                    <div class="error">${error}</div>
                </c:if>
                <c:if test="${not empty msg}">
                    <div class="msg">${msg}</div>
                </c:if>

                <h3><strong>Login </strong></h3>
                <form name = 'loginForm' action="j_spring_security_check" id="formLogin" method="post" autocomplete="on">

               <table>
                <tr>
                    <td>Email Id:</td>
                    <td><input type='text' name='j_username' value=''></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type='password' name='j_password' /></td>
                </tr>
                <tr>
                    <td colspan='2'>
                        <input name="submit" type="submit" value="submit" />
                    </td>
                </tr>
               </table>

                </form>
            </div>
       </div>
       <div class="copyright">
            <span class="floatright">2015 companyname.com | <a href="//companyname.com/privacy-policy">Privacy Policy</a></span>
       </div>
       <br>
       <div id="0cad398b-b070-45e8-9b4d-e30bdac0ad0f" style="margin-top: 15px;"> <script type="text/javascript" src="//privacy-policy.truste.com/privacy-seal/Induslynk-Training-Services-Private-Limited/asc?rid=0cad398b-b070-45e8-9b4d-e30bdac0ad0f"></script><a href="//privacy.truste.com/privacy-seal/Induslynk-Training-Services-Private-Limited/validation?rid=6edc17b0-4eb4-4e24-a6df-1f985995eda6" title="TRUSTe European Safe Harbor certification" target="_blank"><img style="border: none" src="//privacy-policy.truste.com/privacy-seal/Induslynk-Training-Services-Private-Limited/seal?rid=6edc17b0-4eb4-4e24-a6df-1f985995eda6" alt="TRUSTe European Safe Harbor certification"></a></div>
    </div>
</div>

<style type="text/css">

    .head {
            background-color: #004448;
            padding: 5px 0;
            border-top-left-radius: 5px;
            border-top-right-radius: 5px;
    }

    .error{
        color: red;
        margin-top: 5px;
    }

    .floatright {
        float: right;
        margin-right: 50px;
    }

    .margin-top{
        margin-top:20px;
    }

    .wrapper {
        border: 1px solid #ddd;
        padding: 0px;
        width: 480px;
        margin-left: 200px;
        text-align: center;
        border-radius: 5px;
        box-shadow: 0px 0px 10px #ddd;
        min-height: 300px;
    }

    #form {
        background: #fff;
        border-bottom-right-radius: 5px;
        border-bottom-left-radius: 5px;
        padding-top: 5px;
        padding-bottom: 35px;
    }

    h3 {
        font-size: 24px;
        padding: 5px 0;
        color: #000;
    }

    #formLogin {
        width: 360px;
        margin: 0 auto;
    }

    #content {
        font-size: 14px;
        color: #666;
        padding-top: 60px;
    }

    .copyright {
        font-size: 12px;
        width: 480px;
        margin: 10px auto;
    }

    input {
        height: 20px;
        width: 300px;
        border: 1px solid #000;
        margin-top: 10px;
        display: inline-block;
    }

</style>

