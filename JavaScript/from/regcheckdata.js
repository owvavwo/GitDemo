function check_empty(text) {
  marks= false;
  var nLengs=text.length;
  for(var i=0;i<nLengs;i++) {
       if(text.substring(i,i+1)!=' ' &&text.substring(i,i+1)!='��') 
       {
          marks= true;
          break;
       }
   }
  return marks;
}


function checkdata() {
	if(!check_empty(form1.from.value)){
		alert("���᲻��Ϊ��");	
		return false;
	}
	if(!check_empty(form1.username.value)){
		alert("����Ϊ��");	
		return false;
	}
	if(!check_empty(form1.password.value)){
		alert("���벻��Ϊ��");	
		return false;
	}
	if(!check_empty(form1.password1.value)){
		alert("ȷ�����벻��Ϊ��");	
		return false;
	}
	if(form1.password.value!=form1.password1.value){
		alert("��������������");	
		return false;
	}
	
	if( !(form1.gender[0].checked || form1.gender[1].checked) ) {
		alert("��ѡ���Ա�!");
		form1.gender[0].focus();
		return false;
	}
	if(!check_empty(form1.interest.value)){
		alert("��Ȥ");	
		return false;
	}
	if(!check_empty(form1.intro.value)){
		alert("���ܲ���Ϊ��");	
		return false;
	}
	flag =false;
	for(var i=0; i<form1.skill.length; i++ ) {
		if(form1.skill[i].checked){
			flag=true;
		}
	}
	if(!flag){
		alert("��ѡ����");	
		return false;
	}
	return true;
}