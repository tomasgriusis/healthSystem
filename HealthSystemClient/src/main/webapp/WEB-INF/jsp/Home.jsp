<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="resources/css/side-navigation.css" rel="stylesheet">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<title>Home</title>
	</head>
	
	<body>	
		<!--- Side navigation --->
		<div class="sidenav">
		
			<div class="active" >
				<a class="activee" href="home">Home</a>
			</div>
			
			<div class="menu-link" >
				<a href="changeInformation">Change information</a>
			</div>
			
		    <div class="menu-link" >
				<a href="uploadFile">Upload file</a>
			</div>
			
			<div class="menu-link" >
				<a href="reviewData">Review data</a>
			</div>
			
			<div class="logout" >
				<a href="logout">Log out</a>
			</div>
			
		</div>

		<!--- Page content --->
		<div class="main">
			<form modelAttribute="client">
				<p>Hello ${client.name} ${client.surname},</p>
				<p>While you are logged in you can :</p>
				<p>1. Change information - change your personal information like email, password, weight and height</p>
				<p>2. Upload file - upload *.hrm files from your device. These files will be saved in database and can be reviewed while logged in from any device</p>
				<p>3. Review Data - review data from selected file on diagram. Below diagram is shown calculated average and variance of selected file. This page is not accessible before uploading at least 1 file.</p>
				<p>4. Log out - safe way to log out. After pressing it, you will be redirected to Welcome page.</p>
			</form>
			
		</div>

	</body>
	
</html>