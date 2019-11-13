<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
<title></title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">
<script type="text/javascript" src="/javascript/calendar.js">


</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<form name="detailForm3"  method="post" action='/updatePurchase2.do' >
<input type="hidden" name="tranNo" value="${purchase.tranNo}">
<input type="hidden" name="buyer.userId" value="${purchase.buyer.userId}">

<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매수정</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif" width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">
			구매자아이디 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle">
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="105">${purchase.buyer.userId}</td>
					<td>	</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">
			구매방법 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle">
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input type="text" name="paymentOption" value="${purchase.paymentOption}" class="ct_input_g" 
						style="width:100px; height:19px"  maxLength="50" >
						
		</td>
	</tr>
	
	
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">구매자이름</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			
			<input type="text" name="receiverName" value="${purchase.receiverName}"
						
						class="ct_input_g" style="width:100px; height:19px"  maxLength="9" >
			
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">구매자 연락처</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			
			<input type="text" name="receiverPhone" value="${purchase.receiverPhone}"
						
						class="ct_input_g" style="width:100px; height:19px"  maxLength="9" >
			
		</td>
	</tr>
	
	
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">구매자주소</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			
			<input type="text" name="divyAddr" value="${purchase.divyAddr}"
						
						class="ct_input_g" style="width:100px; height:19px"  maxLength="9" >
			
		</td>
	</tr>
	
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">구매요청사항</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			
			<input type="text" name="divyRequest" value="${purchase.divyRequest}"
						
						class="ct_input_g" style="width:100px; height:19px"  maxLength="9" >
			
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	
	<tr>
		<td width="104" class="ct_write">배송희망일자</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input 	type="text" name="divyDate" value="${purchase.divyDate}" class="ct_input_g" 
							style="width:370px; height:19px"  maxLength="100">
			<a href="javascript:show_calendar('document.detailForm3.divyDate', document.detailForm3.divyDate.value);"><img src="/images/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the manufacture_day"></a>
		</td>
	</tr>
	
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:document.detailForm3.submit();">등록</a> 
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
					<td width="30"></td>					
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="/listProduct.do?menu=manage">취소</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>	
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				
			</table>
		</td>
	</tr>
</table>

</form>

</body>
</html>
