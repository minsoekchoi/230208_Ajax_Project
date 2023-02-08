<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
span {
	width: 150px;
	color: red;
}

input {
	border: 1px solid red;
}

table {
	width: 80%;
	margin: 0 auto;
}

table, th, td {
	border: 1px solid gray;
	text-align: center;
}

h2 {
	text-align: center;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		function getList() {
			$.ajax({
				/* Java는 Json을 쓴다. */
				/* 여기서 연습은 xml로 간다.  */
				url : "${pageContext.request.contextPath}/MyController01",
				dataType : "xml",
				method : "post",
				success : function(data) {
					var tbody = "";
					$(data).find("member").each(function() {
						tbody += "<tr>";
						tbody += "<td>"+ $(this).find("m_idx").text()+ "</td>";
						tbody += "<td>"+ $(this).find("m_id").text()+ "</td>";
						tbody += "<td>"+ $(this).find("m_pw").text()+ "</td>";
						tbody += "<td>"+ $(this).find("m_name").text()+ "</td>";
						tbody += "<td>"+ $(this).find("m_addr").text()+ "</td>";
						tbody += "<td>"+ $(this).find("m_reg").text()+ "</td>";
						tbody += "<td><input type='button' value='삭제' id='del' name="+ $(this).find("m_idx").text() +"></td>";
						tbody += "</tr>";
					});
					$("#tbody").append(tbody);
				},
				error : function() {
					alert("실패");
				}
			});
		};
		
		// data => 서버 주소에 갈때 같이 넘어가는 파라미터값
		$("#m_id").keyup(function() {
			$.ajax({
				url : "${pageContext.request.contextPath}/MyController06",
				dataType : "text",
				method : "post", // type이라고 타이핑 하셨는데 method가 아닌가
				// 둘 다 가능하군 차이점이 따로 있는건지
				data : "m_id="+$("#m_id").val(),
				success : function(data) {
					// $("span").text(data); // 중복 가능 여부 텍스트 변경
					if(data == "1"){
						$("#btn").removeAttr("disabled");
						$("span").text("사용가능");
					}else if (data == "0") {
						$("#btn").attr("disabled","disabled");
						$("span").text("사용불가");
					}	
				},
				error:function(){
					alert("실패");
				}
			});
		});
		// 가입하기
		// data가 여러개일때는 직렬화(serialaze())를 해야한다.
		// serialize() => 직렬화, form 태그안에 있는 요소만 가능
		$("#btn").click(function() {
			var param = $("#myform").serialize();
			$.ajax({
				url : "${pageContext.request.contextPath}/MyController07",
				dataType : "text",
				method : "get",
				data : param,
				success : function(data) {
					
				},
				error:function(){
					alert("실패");
				}
			});
		});
		
		
		
		getList();		
	});
</script>
</head>
<body>
	<h2>회원 정보 입력하기</h2>
	<!-- form으로 받는거 기억  -->
	<form name="myform" method="post" id="myform">
		<table>
			<thead>
				<tr>
					<th>아이디</th>
					<th>패스워드</th>
					<th>이름</th>
					<th>주소</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><br><input type="text" size="14" name="m_id" id="m_id" /><br><span>중복여부</span>
					</td>
					<td><input type="password" size="8" name="m_pw" /></td>
					<td><input type="text" size="14" name="m_name" /></td>
					<td><input type="text" size="25" name="m_addr" /></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="7" align="center"><button id="btn" disabled>가입하기</button></td>
				</tr>
			</tfoot>
		</table>
	</form>
	<br />
	<br />
	<br />
	<h2>회원 정보 보기</h2>
	<div>
		<table id="list">
			<thead>
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>패스워드</th>
					<th>이름</th>
					<th>주소</th>
					<th>날짜</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody id="tbody"></tbody>
		</table>
	</div>
</body>
</html>