<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<title></title>
<meta charset="UTF-8" />
<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	// 검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScrpt 이용  
	function fncGetUserList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	}
</script>

</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listPurchase.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37">
		</td>
	</tr>
</table>



<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >
		전체  ${resultPage.totalCount} 건수,	현재  ${resultPage.currentPage} 페이지
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">회원명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">전화번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" >정보수정</td>
		<td class="ct_line02"></td>		
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	
	<c:set var="i" value="0" />
	<c:forEach var="vo" items="${list}">
		<c:set var="i" value="${ i+1 }" />
		
	
	<tr class="ct_list_pop">
		<td align="center"><a href="/getPurchase.do?tranNo=${vo.tranNo}">${ i }</td>
		<td></td>
		<td align="left">
					
			<a href="/getUser.do?userId=${user.userId }">${user.userId }</a>
		</td>
		<td></td>
		<td align="left">${vo.receiverName}</td>
		<td></td>
		<td align="left">${vo.receiverPhone}
		</td>
		<td></td>		   
		 <td align="left"> 
		  
		<c:if test = "${vo.tranCode == '1  '}">
		 	${"현재구매완료된 상태입니다."}
		</c:if>
		
		<c:if test = "${vo.tranCode == '2  '}">
		 	${"현재 배송중 상태입니다."}
		</c:if>
		
		<c:if test = "${vo.tranCode == '3  '}">
		 	${"현재 배송완료된 상태입니다."}
		</c:if>
		
		<td></td>		
		<td align="left">
		<c:if test = "${vo.tranCode == '2  '}">
		<a href="/updateTranCode.do?tranNo=${vo.tranNo}&tranCode=3">물건도착</a>
		</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>

			<jsp:include page="../common/pageNavigator.jsp"/>
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->
</form>
</div>

</body>
</html>