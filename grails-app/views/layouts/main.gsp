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
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'summernote.css')}" type="text/css">
    <!--<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">-->
    <!--<link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.min.css')}" type="text/css">-->
		<g:layoutHead/>
        <r:require modules="bootstrap"/>
        <r:require module="jquery" />
		<r:layoutResources />
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.0.1/css/font-awesome.min.css" />
    <script src="${resource(dir: 'js', file: 'summernote.min.js')}"></script>
	</head>
	<body>

        <div class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${createLink(uri: '/')}">${message(code: "site.title", default: "BadPractice")}</a>
                </div>

                <ul class="nav navbar-nav">
                    <li><g:link controller="thread" action="create">${message(code: "site.menu.ask", default: "Ask")}</g:link></li>
                </ul>

                <div style="position:absolute; left:calc(50% - 30px); top: 10px;">
                    <img src="${resource(dir: 'images', file: 'favicon.png')}" alt="" style="height:30px;" />
                </div>
                <div class="navbar-collapse collapse navbar-right">
                            <g:userConnection />
                    <!--<div class="navbar-form navbar-right" role="form">

                    </div>-->
                </div><!--/.navbar-collapse -->
            </div>
        </div>

        <div id="mainContent" class="container" style="padding-bottom:60px;">
            <g:layoutBody/>
        </div>

        <nav class="navbar navbar-default navbar-fixed-bottom" role="navigation">
            <div class="container-fluid">
                <div class="navbar-collapse collapse navbar-left">
                    <p class="navbar-text">2014 - BadPractice</p>
                </div>
                <div style="position:relative;">
                    <div style="background-image:url(${resource(dir: 'images', file: 'wrongway.png')});width:100px;height:89px;position:absolute;bottom:-50px;left:calc(50% - 50px);"></div>
                </div>
            </div>
        </nav>


        <g:javascript library="application"/>
        <r:layoutResources />
	</body>
</html>
