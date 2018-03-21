<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TDTSoftwave</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	  
	  <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
	  
	  
	  
	  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	  	
	  	<link rel="shortcut icon" href="img/favicon.ico">
		<link rel="stylesheet" type="text/css" href="css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="css/demo.css" />
		<link rel="stylesheet" type="text/css" href="css/component.css" />
		<script>(function(e,t,n){var r=e.querySelectorAll("html")[0];r.className=r.className.replace(/(^|\s)no-js(\s|$)/,"$1js$2")})(document,window,0);</script>
<!-- CSS -->
	<style>
		*{
			margin: 0px;
			padding: 0px;
			font-size: 25px;
		}
		body {
		    background-image: url("<c:url value='img/Nen_Trang_Chu.jpg'/>");
		    position: relative;
    		display: table;
	  		
		    background-position-x: center;
		    background-position-y: center;
		    background-size: cover;
		    background-repeat-x: no-repeat;
		    background-repeat-y: no-repeat;
		    background-attachment: scroll;
		    background-origin: initial;
		    background-clip: initial;
		    background-color: blue;
		    height: 100%;
		    width: 100%;
		}
		.body_center{
			display: flex;
		    align-items: center;
		    justify-content: center;
		    
		}
		.body_space{
			padding-top: 5px;
			padding-bottom: 5px;
		}
		.form-border{
			border:solid 1px black;
			border-radius: 15px;
			border-collapse:separate; 
			border-spacing:2em 2em;
			background-color: white;
		}
		.center{
			
		  position: absolute;
		  margin: auto;
		  top: -350;
		  right: 0;
		  bottom: 0;
		  left: 0;
		  width: 50%;
		  height: 40%;
		  color:black;
		  border-radius: 3px;
		  /*  border: 2px solid black; /* Green */  */
		  
		}
		.button {
		    background-color:transparent;
		    border: none;
		    color: black;
		    padding: 15px 32px;
		    text-align: center;
		    text-decoration: none;
		    display: inline-block;
		    font-size: 16px;
		    margin: 4px 2px;
		    cursor: pointer;
		}
		.button1 {
		    
		    color: black;
		    border: 2px solid #4CAF50; /* Green */
		    border-radius: 20px;
		}

		.inputfile {
			width: 0.1px;
			height: 0.1px;
			opacity: 0;
			overflow: hidden;
			position: absolute;
			z-index: -1;
		}
		#btn-goto:hover{
			background-color: #007bff;
		}
	</style>
</head>
<body>

   
   
   <div class="center" style="border: 2px solid black; ">
   
   <div style="width: 100%;text-align: center;">
  
   		<h1 style="font-size: 4.5em;
    font-weight: 700;">Xếp Lịch TDT</h1>
   		
   </div>
   
    <form:form modelAttribute="myUploadForm" method="POST"
		                        action="" enctype="multipart/form-data">
   
   <div style="float: left;width: 50%;">
   		<center>
		<form:input  type="file" name="file-5" id="file-5" path="fileDatas" class="inputfile inputfile-4" data-multiple-caption="{count} files selected"/>
		<label style="color: black;" for="file-5">
			<span class="btn btn-primary glyphicon" style="padding: 50px;font-size: 20px;border-radius: 25px;"><h2 style="font-size: 2.5em;">&#xe022;</h2></span><br>
			<h2>Đính kèm tệp đăng kí môn học</h2>
		</label>
			
		</center>
	</div>
	<div style="float: left;width: 50%;">
		<center>	
		<form:input type="file" name="file-6" id="file-6" path="fileDatas" class="inputfile inputfile-4" data-multiple-caption="{count} files selected"/>
		
		<label style="color: black;" for="file-6">
			<span class="btn btn-primary glyphicon" style="padding: 50px;font-size: 20px;border-radius: 25px;"><h2 style="font-size: 2.5em;">&#xe022;</h2></span><br>
			<h2>Đính kèm tệp danh sách phòng</h2>
		</label>
		</center>
	</div>
	
	
	
	<div  style="text-align: center;clear: both;color: white;">
   <center><button id="btn-goto" type="submit" class="button button1 w3-border-blue w3-round-large w3-xlarge">Xử lý&nbsp;  <i class="fa fa-chevron-right"></i></button></center>
   </div>
      <!--  <input type="file" name="file" id="file" class="inputfile" />
<label for="file">Choose a file</label> -->
	<center><span style="color: while;"><strong>${msg}</strong></span></center>


  
	    <!-- MyUploadForm -->
	    
	    
	    
	    
		   
		         
		        
		          
		    </form:form>
	   </div> 
	  
	    
   
    <script src="js/custom-file-input.js"></script>
   
    <script src="js/jquery-v1.min.js"></script>
    
</body>
</html>