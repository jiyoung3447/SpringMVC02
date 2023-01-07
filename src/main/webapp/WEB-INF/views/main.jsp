<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 <script type="text/javascript">
	$(document).ready(function(){
		loadList();
	});
	 function loadList(){
     	// 서버와 통신 : 게시판 리스트 가져오기
     	$.ajax({
     		url : "myboardList.do",
     		type : "get",
     		dataType : "json",
     		success : makeView,
     		error : function(){ alert("error");  }    		
     	});
      }
	function makeView(data){
		//alert(data);
		var listHtml= "<table class='table table-bordered'>";
		listHtml += "<tr>";
		listHtml += "<th>번호</th>";
		listHtml += "<th>제목</th>";
		listHtml += "<th>작성자</th>";
		listHtml += "<th>작성일</th>";
		listHtml += "<th>조회수</th>";
		listHtml += "</tr>";
		
		//each 제이쿼리에서 반복문
		$.each(data, function(index, obj){ //obj={"idx" : 5, "title" : "게시판", ~~}
			listHtml += "<tr>";
			listHtml += "<td>"+ obj.idx +"</td>";
			listHtml += "<td id='t"+ obj.idx +"'><a href='javascript:goContent("+ obj.idx +")'>"+ obj.title +"</a></td>";
			listHtml += "<td>"+ obj.writer +"</td>";
			listHtml += "<td>"+ obj.indate +"</td>";
			listHtml += "<td>"+ obj.count +"</td>";
			listHtml += "</tr>";
			
			//위에까진 전체리스트들. 아래는 각 리스트아래 공간만들어 주기
			listHtml += "<tr id='c"+ obj.idx+"' style='display:none'>";
			listHtml += "<td>내용</td>";
			listHtml += "<td colspan='4'>";
			listHtml += "<textarea id='t_a"+ obj.idx +"'readonly rows='7' class='form-control'>"+ obj.content +"</textarea>";
			listHtml += "<br/>";
			listHtml += "<span id='update_btn"+ obj.idx +"'><button class='btn btn-success btn-sm' onclick='goUpdateForm("+ obj.idx +")'>수정</button></span>&nbsp";
			listHtml += "<button class='btn btn-warning btn-sm' onclick='goDelete("+ obj.idx +")'>삭제</button>";
			
			listHtml += "</td>";
			listHtml += "</tr>";
			
		} );
		listHtml += "<tr>";
		listHtml += "<td colspan='5'>";
		listHtml +="<button class='btn btn-primary btn-sm' onclick='goForm()'>글쓰기</button>"
		listHtml += "</td>";
		listHtml += "</tr>";
		 
		listHtml += "</table>";
		$("#view").html(listHtml);
		
		//글 등록한 뒤 리스트는 보이게하고 글쓰기 화면은 숨기 위해
		$("#view").css("display","block"); 
		$("#write_view").css("display","none"); 
	}
	function goForm(){
		$("#view").css("display","none"); 
		$("#write_view").css("display","block"); 
	}
	function goList(){
		$("#view").css("display","block"); 
		$("#write_view").css("display","none"); 
	} 
	function goInsert(){
		//var title = $("#title").val();
		//var writer = $("#writer").val();
		//var content = $("#content").val();
		
		var formData = $("#frm").serialize(); //폼 안에있는 모든 파라미터 직렬화시켜서 한꺼번에 가져옴
		//alert(formData);
 
		$.ajax({
     		url : "boardInsert.do",
     		type : "post",
     		data : formData,
     		success : loadList,
     		error : function(){ alert("error");  }    		
     	});
		//폼 초기화. 글등록후 글쓰기 누르면 기존 내용이 살아있어서 초기화시켜줌
		/* $("#title").val("");
		 $("#writer").val("");
		 $("#content").val(""); */
		 $("#form_clear").trigger("click"); //트리거가 form_clear 버튼찾아가서 이벤트 발생시킴
		 
	}
	//상세보기 화면임
	function goContent(idx){
		//$("#c"+idx).css("display", "block");  //block으로 해버리면 colsplan이 tr에걸려있기 때문에 안먹힘.
		if($("#c"+idx).css("display")=="none"){  //상세보기가 펼쳐질때 아래 ajax로 보여지기
			
			$("#c"+idx).css("display", "table-row"); //보이기
			$("#t_a"+idx).attr("readonly",true); //수정하기 처음에만 readonly가 false여야 하므로 이부분 추가해주기
			
		}else{
			$("#c"+idx).css("display", "none"); //감추기
		}
	}
	
	//상세보기에서 삭제버튼
	function goDelete(idx){
		$.ajax({
			url : "boardDel.do",
     		type : "get",
     		data : {"idx" : idx},
     		success : loadList,
     		error : function(){ alert("삭제가 실패됐습니다.");  }  
			
		});	
	}
	
	//상세보기에서 수정하기
	 function goUpdateForm(idx){ //textarea를 수정해야 하니 textarea에 id 넣어주기
		$("#t_a"+ idx).attr("readonly", false); //속성값을 가져오거나 설정(readonly는 스타일이 아니니까)
		
		var title = $("#t"+idx).text();  //원래 썼던 제목을 가져옴
		var newInput = "<input type='text' id='new_title" +idx+ "' class='form-control' value='"+ title +"'/>";
		
		$("#t"+idx).html(newInput);
		
		var newButton="<button class='btn btn-primary btn-sm' onclick='goUpdate("+ idx +")'>저장</button>"; //버튼 바꿔주기
		$("#update_btn"+idx).html(newButton);
		
	} 
	//수정화면에서 저장버튼 눌렀을 때
	   function goUpdate(idx){
		var title= $("#new_title"+idx).val();
		var content = $("#t_a"+idx).val();
		   $.ajax({
			url : "boardModify.do",
			type : "post",
			data :{ "idx" : idx, "title" : title, "content" : content},  // key : value   
			success : loadList,
			error : function(){alert("수정할 수 없습니다."); }
		}); 
	}    
</script> 

      

</head>
<body>
 
<div class="container">
  <h2>Spring MVC02</h2>
  <div class="panel panel-default">
    <div class="panel-heading">BOARD</div>
    <div class="panel-body" id="view">Panel Content</div>
    <div class="panel-body" id="write_view" style="display:none">
    <form id=frm>
					<table class="table">
						<tr>
							<th>제목</th>
							<td><input id="title" name="title" class="form-control" /></td>
						</tr>
						<th>작성자</th>
						<td><input type="text" id="writer" name="writer" class="form-control" /></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea rows="7" id="content" name="content" class="form-control"></textarea></td>
						</tr>
						</tr>

						<tr>
							<td colspan="2" align="right">
								<button type="button" class="btn btn-info btn-sm" onclick="goInsert()">등록</button>
								<button type="reset" class="btn btn-warning btn-sm" id="form_clear">내용지우기</button>
								<button type="reset" class="btn btn-sm" onclick="goList()">취소</button>
							</td>
					</table>
				</form>
    </div>
    <div class="panel-footer">인프런_스프1단 푸터</div>
  </div>
</div>

</body>
</html>