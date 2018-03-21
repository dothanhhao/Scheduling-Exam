<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lịch thi sinh viên</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
 <style>
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
 </style>
 
<body>
	
	
	<div align="center" style="width: 100%">
    <span id="LichThi1_tieudelichthi" style="color: Red; font-size: 70px;
        font-family: Candara; font-weight: bold;">LỊCH THI HK1/2017-2018</span>
	</div>
	 
	 <br>
	
	 
	 
	<table class="table">
		<div>
		<div style="text-align: center;">
			
			<h1 style="font-size: 4 em;font-weight: 600;">${data_Student.infoStu.name} - ${data_Student.infoStu.MSSV}</h1>
			<h2 style="font-size: 4 em;font-weight: 600;"> Lịch thi sinh viên TDT</h2>
			
		</div>
		<div></div>
	</div>
	
		<tbody>
			<tr class="btn-primary">
				<td></td>
				<td>Thứ 2</td>
				<td>Thứ 3</td>
				<td>Thứ 4</td>
				<td>Thứ 5</td>
				<td>Thứ 6</td>
				<td>Thứ 7</td>
				<td>Chủ nhật</td>
			</tr>
			<tr id="LichThi_GiuaKy">
				<td class="btn-primary">Giữa kỳ</td>
			<c:set var = "s" value="Thứ " />
			
			<c:forEach var = "thu" begin = "2" end = "8">
				
				
				<c:if test = "${thu == 8}">
					<c:set var = "s2" value="Chủ nhật" />
					
				</c:if>
				<c:if test = "${thu != 8}">
					<c:set var = "s2" value="${s}${thu}" />
				</c:if>
				
				<td>
				<center>
				<%
					String s[] = new String[7];
					s[0] = "6f8eff";
					s[1] = "f18973";
					s[2] = "cf6fff";
					s[3] = "6f9eff";
					s[4] = "e32d5d";
					s[5] = "6db33f";
					s[6] = "e67b09";
					int i;
					while(true){
						i =  (int) (Math.random() * 10);
						if(i<5)
							break;
					}
					
				%>
				
				<span style="display:inline-block;background-color:#<% out.print(s[i]); %>;width:200px;text-align: center;vertical-align: top;border-radius:2px;">
				<c:forEach var="monhoc" items="${data_Student.perDay}">
					
					<c:if test = "${s2 == monhoc.thu}">
					
						<p style="padding:0px 0px 10px 0px; margin:0px;color: black;">
							<b><span>${monhoc.nameSub}</span></b><br>
							(${monhoc.idSub})<br>
							<b>${monhoc.group}</b><br>
							<b>${monhoc.subGroup}</b><br>
							${monhoc.ngay}<br>
							${monhoc.time}<br>
							${monhoc.room}<br>
							${monhoc.timmeExam}
						</p>
					
					
					</c:if>
				
				</c:forEach> 
				</span>
				</center>
				</td>
			</c:forEach>
				
			</tr>
			<tr id="Table-Line-Rows">
				<td valign="middle" colspan="8" class="btn-primary"></td>
			</tr>
			<tr id="LichThi-CuoiKy">
				<td class="btn-primary">Cuối kỳ</td>
				<c:forEach var = "thu" begin = "2" end = "8">
				
				
				<c:if test = "${thu == 8}">
					<c:set var = "s2" value="Chủ nhật" />
					
				</c:if>
				<c:if test = "${thu != 8}">
					<c:set var = "s2" value="${s}${thu}" />
				</c:if>
				<td>
				<center>
				<%
					String s[] = new String[7];
					s[0] = "6f8eff";
					s[1] = "f18973";
					s[2] = "cf6fff";
					s[3] = "6f9eff";
					s[4] = "e32d5d";
					s[5] = "6db33f";
					s[6] = "e67b09";
					int i;
					while(true){
						i =  (int) (Math.random() * 10);
						if(i<5)
							break;
					}
					
				%>
				<span style="display:inline-block;background-color:#<% out.print(s[i]); %>;width:200px;text-align: center;vertical-align: top;border-radius:2px;">
				<c:forEach var="monhoc" items="${data_Student.perDayFinal}">
					
					<c:if test = "${s2 == monhoc.thu}">
					
						<p style="padding:0px 0px 10px 0px; margin:0px;color: black;">
							<b><span>${monhoc.nameSub}</span></b><br>
							(${monhoc.idSub})<br>
							<b>${monhoc.group}</b><br>
							<b>${monhoc.subGroup}</b><br>
							${monhoc.ngay}<br>
							${monhoc.time}<br>
							${monhoc.room}<br>
							${monhoc.timmeExam}
						</p>
					
					
					</c:if>
				
				</c:forEach> 
				</span>
				</center>
				</td>
			</c:forEach>
			</tr>
		</tbody>
  
	
	  
	  
		
	</table>
    
   
</body>
 
 
</html>