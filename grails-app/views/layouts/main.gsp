<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<!--<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">-->
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.min.css')}" type="text/css">
		<g:layoutHead/>
        <r:require module="jquery-ui" />
		<r:layoutResources />
	</head>
	<body>

        <div class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">${message(code: "site.title", default: "BadPractice")}</a>
                </div>
                <div class="navbar-collapse collapse">
                    <div class="navbar-form navbar-right" role="form">
                        <g:submitButton name="signin" class="btn btn-success" value="${message(code: "site.signin", default: "SignIn")}" />
                        <oauth:connect class="btn btn-success" provider="google">${message(code: "site.signup", default: "SignUp")}</oauth:connect>
                    </div>
                </div><!--/.navbar-collapse -->
            </div>
        </div>

        <div class="jumbotron">
            <h2>Get Started !</h2>
        </div>
        <div class="container">
            <g:layoutBody/>
        </div>
        <g:javascript library="application"/>
        <r:layoutResources />
	</body>
</html>
