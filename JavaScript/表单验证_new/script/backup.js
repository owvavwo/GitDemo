<script language=JavaScript>
function checkform() {
if( !(form.gender7605060[0].checked || form.gender7605060[1].checked) ) {
alert("\��ѡ���Ա� !!")
return false;
}

if ( form.year.value.length!=4 
  || isNumberString(form.year.value,"1234567890")!=1 
  || form.year.value<1902 || form.year.value>2004 ) {
alert("\����������ȷ���������!!")
form.year.focus()
return false;
}

if ((form.month.value==0||
form.day.value==0||
(Math.abs(Math.abs(form.month.value*2-15)-5)==2&&(form.day.value==31))||
(form.month.value==2&&((form.day.value>28&&parseInt(form.year.value)%4!=0)||(form.day.value>29&&parseInt(form.year.value)%4==0)))))
{
   alert("������д��ȷ�ĳ������ڣ�ע������ʹ�С�£�");
   form.year.focus()
   return false;
}
if (!ckidtype(form.idType.selectedIndex)){
   form.idType.focus()
   return false;
}
if ( !ckidnum(form.idType.selectedIndex,form.idCard.value)){
  form.idCard.focus()
  return false;
}
if (form.radomPass.value.length<4){
	alert("\����������ȷ��У���룬�ò��������ڷ�ֹע��� !!")
	form.radomPass.focus()
	return false;
}
var secmail=form.email.value;
if (secmail.length != 0){
	if( secmail.length<8 || secmail.length>64 || !validateEmail(secmail) ) {
		alert("\����������ȷ�������ַ !!")
		form.email.focus()
		return false;
	}
	var mymail=form.username.value+"@163.com";
	if( form.email.value == mymail) {
		alert("\�������ַ��û�п�ͨ��������һ�� !!")
		form.email.focus()
		return false;
	}
}
return true;
}
</script>