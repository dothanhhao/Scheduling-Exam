<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/W3.css" />
	  	  
	   <style type="text/css">
	  	.body{
	  		margin: 0px;
	  		padding: 0px;
	  		font-size: 1rem;
		    font-weight: 400;
		    line-height: 1.5;
		    color: #212529;
	  	}
	  	.body-left{
	  		float: left;
	  		width: 20%;
	  		
	  	}
	  	.body-right{
	  		float: left;
	  		width: 80%;
	  	}
	  	.font{
	  		font-size: 1.2em;
	  	}
	  	.border{
	  		border-style: solid;
	  	}
	  	.radius{
	  		 border-radius: 10px;
	  	}
	  	.margin-10{
	  		margin-top: 10px;
	  	}
	  	.background{
	  		background-color: #667292;
	  	}
	  	.width-max{
	  		width: 100%
	  	}
	  	.font-large{
	  		font-size:150%;
	  		
	  	}
	  	.font-medium{
	  		font-size:100%;
	  	}
	  	.padding-top-bottom{
	  		padding: 10px 0px 10px 0px;
	  	}
	  	.padding-percent{
	  		padding-left: 5%;
	  		padding-right: 5%;
	  	}
	  	.text-style{
	  		font-weight:bold;
	  		color: #337ab7;
	  	}
	  	.table > tbody > tr:hover{
	  		background-color: #92a8d1;
	  	}
	  	.text-style:hover{
	  		color: white;
	  	}
	  	.position-right{
	  		float:right;
	  	}
	  	.body-hover:hover{
	  		color: WHITE;
	  		font-size: 120%;
	  	}
	  	.header{
	  		position: relative;
    		display: table;
	  		background-image: url(<%=request.getContextPath()%>/img/Nen_Home.jpg) ;
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
		
		.text-vertical-center{
			display: table-cell;
		    text-align: center;
		    vertical-align: middle;
		  	padding-bottom: 600px;
		  	padding-top: 300px;
		}
		h1{
			  font-weight: 700;
		    font-size: 4.5em;
		    margin-bottom: 50px;
		}
		.btn-input{
			padding: 10px;
			margin-right: 0px;
			font-size: 30px;
			border-radius: 25px;
		}
		.btn-primary{
			
			padding: 10px;
			padding-right:50px;
			padding-left:50px;
			font-size: 30px;
			border-radius: 25px;
			margin-left: 0px;
		}
		@media (min-width: 1200px)
		bootstrap.min.css:6
		.container {
		    max-width: 1140px;
		}
		@media (min-width: 992px)
		bootstrap.min.css:6
		.container {
		    max-width: 960px;
		}
		@media (min-width: 768px)
		bootstrap.min.css:6
		.container {
		    max-width: 720px;
		}
		@media (min-width: 576px)
		bootstrap.min.css:6
		.container {
		    max-width: 540px;
		}
		*{
 		margin: 0px;
 		padding: 0px;
 	}
 	.none{
 		margin: 0px;
 		padding: 0px;
 	}
 	table, th, td {
   border: 1px solid grey;
   
	}
	
	#customers {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
   /* display: none; */
}

#customers td, #customers th {
    border: 1px solid #ddd;
    padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #4CAF50;
    color: white;
}

#customersFinal {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
    /* display: none; */
}

#customersFinal td, #customers th {
    border: 1px solid #ddd;
    padding: 8px;
}

#customersFinal tr:nth-child(even){background-color: #f2f2f2;}

#customersFinal tr:hover {background-color: #ddd;}

#customersFinal th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #2196F3;
    color: white;
}
		
	  </style>

	
</head>
<body class="font">
	
	
	
	
	<header>
	 <div class="header" >
	 	<center>
			<div class="text-vertical-center">
	       
	        <div style="text-align: center;">
	        	<a href='<s:url value="/Trangchu//Giaovien/exportfile"/>' class="btn btn-primary w3-btn w3-blue">Xuất file</a>	<br><br>
	        	
			</div>
			
			<span id="error"></span>
      	</div>
		</center>
	</div> 
	</header>
	
	
	

	
    
    <script src="<%=request.getContextPath()%>/js/jquery-v1.min.js"></script>

	<!--  -->
	
	
	
	
	
	<%-- <table>
		
		<c:forEach items="${danhsachsinhvien.sinhvien}" var="sinhvien"
			varStatus="status">
			<tr>
				<td>${sinhvien.key}</td>
				<td>${sinhvien.value.mssv}</td>
			</tr>
		</c:forEach>
	</table> --%>
	
	<!-- <input type="button" value="Back" onclick="javascript:history.back()" /> -->
	
</body>
</html>