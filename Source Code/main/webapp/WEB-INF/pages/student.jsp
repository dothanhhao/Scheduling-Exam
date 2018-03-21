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
	  <style type="text/css">
	  	tr,td{
	  		padding: 15px;
	  	}
	  	.about .btn{
	  		font-size: 25px;
	  	}
	  	body {
		    background-image: url(<%=request.getContextPath()%>/img/Nen_Home.jpg);
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
		.btn-primary {
		    background-color: #f05f40;
		    border-color: #f05f40;
		    padding: 20px;
		    padding-left: 100px;
		    padding-right: 100px;
		   	border-radius: 25px;
		}
		.btn-info{
			padding: 20px;
		    padding-left: 100px;
		    padding-right: 100px;
		   	border-radius: 25px;
		}
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
	  		font-size: 2.0em;
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
	  	
		
		.text-vertical-center{
			display: table-cell;
		    text-align: center;
		    vertical-align: middle;
		  	padding-bottom: 450px;
		  	padding-top: 450px;
		}
		h1{
			  font-weight: 500;
		    font-size: 3em;
		}
		.btn-input{
			padding: 25px;
			border-radius: 50px 0px 0px 50px;
		}
		.btn-primary{
			padding: 25px;
			border-radius: 0px 50px 50px 0px;
		}
	  </style>
	  
</head>
<body class="font">
	
	
	<header>
	 <div class="header" >
	 	<center>
		<div class="text-vertical-center">
			 <%-- <s:url value="/Home/Student" var="url"></s:url> --%>
	       <form:form modelAttribute="form_sinhvien" method="POST"
		                        action="">
		     <form:input type="text" class="btn btn-input" path="MSSV" placeholder="Nhập mã sinh viên ở đây..." size="30" maxlength="8" style="font-size:25px;"/>
			<button type="submit" class="btn btn-primary" style="margin: 0px;font-size: 25px;">Tìm kiếm</button>
		                        
	        </form:form>
	        <center><span style="color: while;"><strong>${msg}</strong></span></center>
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