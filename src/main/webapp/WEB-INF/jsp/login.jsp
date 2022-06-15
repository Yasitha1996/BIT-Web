<%--
  Created by IntelliJ IDEA.
  User: Yasitha
  Date: 1/14/2022
  Time: 9:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.css">

    <script>
        function disablePaste() {
            var input = document.getElementById("password");
            if (input)
                input.onpaste = function () {
                    return false;
                };
        }

        function checkUser() {
            let data = $('form[name=loginform]').serialize();
            $.ajax({
                type: 'POST',
                url: '/checkuser',
                data: data,
                success: function (res) {
                    $('#responseMsgLogin').addClass('error-response').text(res.errorMessage);
                    $('#responseMsgLogin').show();

                    if (res.flag) {
                        //success
                        toastr.success(res.successMessage);
                         window.location.href='product';

                    } else {
                        //error
                        toastr.error(res.errorMessage);
                    }
                },
                error: function (jqXHR) {
                   window.location = "${pageContext.request.contextPath}/logout";
                }
            });
        }

    </script>

</head>
<body id="kt_body"
      class="header-fixed header-mobile-fixed subheader-enabled subheader-fixed aside-enabled aside-fixed aside-minimize-hoverable page-loading">

<div class="login-body">
    <div class="login-container">
        <div class="left-content">
            <div class="top-content">
                <div class="login-content login-section">
                    <div class="logo">
                        <img src="../images/coop.png" alt="COOP Logo">
                    </div>

                    <h1>Login</h1>
                    <p class="login-desc">Please login to your account</p>

                    <!--begin::Form-->
                    <form method="post" class="form" id="loginform" name="loginform">
                        <!-- Error Message -->
                        <div class="col-sm-4">
                            <div class="form-group"><span id="responseMsgLogin"></span></div>
                            <div class="form-group">
                                <input name="username"
                                       class="form-control form-control-solid h-auto py-5 px-6" placeholder="Username"
                                       type="text" autocomplete="off"/>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <input name="password" id="password"
                                       class="form-control form-control-solid h-auto py-5 px-6" placeholder="Password"
                                       type="password" autocomplete="off" onfocus="disablePaste()"/>
                            </div>
                        </div>

                        <!--begin::Action-->
                        <div class="form-group d-flex flex-wrap justify-content-between align-items-center">
                            <button type="button" id="kt_login_signin_submit"
                                    class="btn btn-primary font-weight-bold px-9 py-4 my-3" onclick="checkUser()">LOGIN
                            </button>
                        </div>
                        <!--end::Action-->
                    </form>
                    <!--end::Form-->
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
