//����һ������,nodeitems,����nodeitem������
nodeitems = new Array() ;
//���ڵ�
tv_topnodeitem = null ;

//=============================================================================================
//����,���һ���ڵ�,
//����1:���ڵ�key,
//����2:�ڵ�key
//����3:�ڵ���ʾ����
//����4:�ڵ�ѡ��ͼƬ
function addNode(parentkey , key , lable , img){
    return new nodeitem( parentkey , key , lable , img ) ;
}
//=============================================================================================
// ��ʾ��,���ø�����show����
function showTV()  {
     tv_topnodeitem.show() ;
}
//=============================================================================================
//������:nodeitem
//lable:����
//key:key
//parent:����
//img:ͼƬ
//subitems:�ӽڵ���,������
//maxsubitem:���һ���ڵ�
//tag:��ʱ����
//status------------------------1:two-direction       0:nobox       0: disactivite
//                              2:three-0direction    1:close-box   1: activite
//                                                    2:open-box    
//��һλ:���Լ����׵����һ������,��2������.����Լ����Ǹ��׵����һ������,��3������
//�ڶ�λ:û�ж���,0,�����ǼӺŻ��߼���
//����λ:��ʱû��
//һ��function����һ���ࡣ�����д����functionʱ����ʵ���Ƕ�����һ���࣬��function�������Ĺ��캯����                
function nodeitem( parentkey , key , lable , img )  {
    this.lable = lable ;
	this.key = key ;
    this.parent = findNode( parentkey ) ;
	//����ҵ����ڵ�
	if( this.parent != null )  {
	    //�õ����׽ڵ��״̬
		aa = this.parent.status ;
		//������׽ڵ��״̬==0,Ҳ����˵�����������û�ж��ӵĻ�,
	    if( aa.substring( 1 , 2 ) == "0" )
	        //���³ɹر�״̬,100-->110
			this.parent.status = aa.substring( 0 , 1 ) + "1" + aa.substring( 2 , 3 ) ;
	    //������������һ���ڵ���,
		if( this.parent.maxsubitem != null ) 
	        //��ô���׵����һ�����Ӹĳ�3�����������,100-->200
			this.parent.maxsubitem.status = "2" + this.parent.maxsubitem.status.substring( 1 , 3 ) ;
        //�ڸ��׵��ӽڵ�������,����Լ�
		this.parent.subitems[ this.parent.subitems.length ] = this ;
        //�ø��׵����һ���ֽڵ�,ָ���Լ�
		this.parent.maxsubitem = this ; 
    }
	//���û���ҵ����ڵ�,
    else  {
	    //���ҵ�ǰ�Ѿ��и����ڵ���,����ʾ:��������������
		if( tv_topnodeitem != null )   {
		     alert( "��������������!" ) ;
			 return ;
        }
		//���û���ҵ����ڵ�,���ҵ�ǰû�и�,����Լ�����Ϊ��-->����
        tv_topnodeitem = this ;
    }

	this.img = img ;
	this.tag = null ;
	//�Լ���״̬Ĭ����:��������,û�ж���
    this.status = "100" ;
	//newһ������,׼��װ�Լ��Ķ�����
	this.subitems = new Array() ;
	//Ŀǰ,��û����ν�����һ������
	this.maxsubitem = null ;
	//��nodeitems�����м����Լ�,�������Լ���id��,
    this.id = nodeitemRegister( this ) ;
}
//=============================================================================================
//�õ����ڵ�
function findNode( key )   {
	pppp = null;
	//�������е�nodeitem,���ĳһ���ڵ㲻Ϊ�ղ���key���ڴ�������keyֵ,���ظĽڵ�
    for( i = 0 ; i < nodeitems.length ; i ++ ) {
	   if( nodeitems[ i ] != null ) {
	      if( nodeitems[ i ].key == key ) {
	         pppp = nodeitems[ i ] ;
		  }
	   }
	}
    return pppp ;
}
//=============================================================================================
//�����Javascriptʵ��OO��̣�������õķ�ʽ���ǳ������prototype(ԭ��)����
nodeitem.prototype.show = nodeitem_show ;
nodeitem.prototype.boxclick = nodeitem_boxclick ;
nodeitem.prototype.close = nodeitem_close ;
nodeitem.prototype.open = nodeitem_open ;
//=============================================================================================
//����ͼƬ
//�հ�
treeview_box_0_none = "images/4_clos.gif"  ;
//ֻ������
treeview_box_0_line = "images/4_none.gif" ;
//��������������
treeview_box_2_open = "images/2_open.gif" ;
treeview_box_2_none = "images/2_none.gif" ;
treeview_box_2_close = "images/2_clos.gif" ;
//��������������
treeview_box_1_open = "images/3_open.gif" ;
treeview_box_1_none = "images/3_none.gif" ;
treeview_box_1_close = "images/3_clos.gif" ;

//=============================================================================================
//��nodeitem��show()����
function nodeitem_show()  {
	//ÿ��Nodeһ��span,����id=preface0
	str = "<span id = \"preface" + this.id + "\"><table border='0' cellspacing='0' cellpadding='0'><tr><td>" ;
    str_f = "" ;
	//����и��ڵ�,����,������ʱ��,��Ҫ�ж�����ʾ�հ�,��������,ȡ���������������������Ļ���3�������
    for( j = this.parent ; j != null ; j = j.parent )  {
	    //������������2�������,��ʾ�հ�
		if( j.status.substring( 0 , 1 ) == 1 )
		    str_f = "<img src = '" + treeview_box_0_none + "' align='absmiddle' border=0>" + str_f ;
        //����,��ʾ����
		else
		    str_f = "<img src = '" + treeview_box_0_line + "' align='absmiddle' border=0>" + str_f ;
    }
    str = str + str_f ;
	//�ж�״̬��ͷ��λ,������ʾ�ӺŻ��Ǽ���
    str += "<img id = 'box" + this.id + "' nodeid = '" + this.id + "' src = '" ;
    switch( this.status.substring( 0 , 2 ) )   {
        case "10" : str += treeview_box_1_none ; break ;
        case "11" : str += treeview_box_1_close ; break ;
        case "12" : str += treeview_box_1_open ; break ;
        case "20" : str += treeview_box_2_none ; break ;
        case "21" : str += treeview_box_2_close ; break ;
        case "22" : str += treeview_box_2_open ; break ;
    }
	//�����"+"������"-",�������¼�����,����box_on_click,���Լ�����ȥ
    str += "' align='absmiddle' onclick='box_on_click(this)'>" ;
    //���û��ǰ��ͼƬ,�Ͳ���ʾ
	if( this.img == "" )
	    str += this.img ;
    //����,��ʾǰ��ͼƬ
	else
	    str += "<img src = '" + this.img + "' align='absmiddle' width='16' height='16'>" ;
    //��ʾlable����
	str += "</td><td><table border='0' cellspacing='1' cellpadding='1' style='font-size:9pt; color:#333333' id='lablePanel" + this.id + "'><tr><td ondblclick = 'lable_on_dblclick(" + this.id + ")' onclick='lable_on_click(" + this.id + ")' style='cursor:hand' id='f_lablePanel" + this.id + "' nowrap>" + this.lable + "</td></tr></table></td></tr></table>" ;  
	
	//��ʾ��һ��SPAN,id=tv_panel_0
	str += "</span><span id = 'tv_panel_" + this.id + "' style='display:" ;
	
	//����Ǵ�״̬,style="DISPLAY: "
	if( this.status.substring( 1 , 2 ) == '2' )
	   str += "" ;
	//����style="DISPLAY: none"
	else
	   str += "none" ;
	 
    str += "'></span>" ;
	
	//�������û�и�����,�ҵ�BODY��ǩ,��BODY��ǩ�ոտ�ʼ��λ��,�������str
	if( this.parent == null )  
  	   for(var i in document.all){
          if (document.all[i].tagName == "BODY"){
		      document.all[i].insertAdjacentHTML( "AfterBegin" , str ) ;
              break;
          }
       }
	//����и���,���뵽�Ը��׵�id��������tv_panel_��ͷ��SPAN��ǩ�Ŀ�Ҫ������λ��
	else   
		document.all( "tv_panel_" + this.parent.id ).insertAdjacentHTML( "BeforeEnd" , str ) ;

	//����ж���,ѭ��ÿһ������
    for(var m = 0 ; m < this.subitems.length ; m ++ ) {
		   if( this.subitems[ m ] != null )  {
			   this.subitems[ m ].show() ;
	   	 }
	  }
}

//=============================================================================================
//�������¼�����,���ȵ��ñ�����,������������nodeitem��boxclick()����
function box_on_click( obj )  {
	nodeitems[ obj.nodeid ].boxclick() ;
}
//=============================================================================================
//��nodeitem��boxclick()����
function nodeitem_boxclick()  {
     //���û�мӺŻ��߼���,ֱ�ӷ���
	 if( this.status.substring( 1 ,2 ) == "0" )
       return ; 
	 //����ǹرյ�״̬,���
	 if( this.status.substring( 1 ,2 ) == "1" )   
        this.open() ;
	 //����Ǵ򿪵�״̬,��ر�
	 else  
        this.close() ;
}
//=============================================================================================
//��nodeitem��close()����,�رսڵ�
function nodeitem_close()  {
	//�����Լ���״̬
	this.status = this.status.substring( 0 , 1 ) + "1" + this.status.substring( 2 , 3 ) ;
	//
	document.all( "tv_panel_" + this.id ).style.display = "none" ;
   	 eval( "document.all( 'box' + this.id ).src = treeview_box_" + this.status.substring( 0 , 1 ) +"_close" ) ;
}
//=============================================================================================
//��nodeitem��close()����,�򿪽ڵ�
function nodeitem_open()  {
     this.status = this.status.substring( 0 , 1 ) + "2" + this.status.substring( 2 , 3 ) ;
   	 document.all( "tv_panel_" + this.id ).style.display = "" ;
   	 //document.all( 'box' + this.id ).src = "treeview_box_" + this.status.substring( 0 , 1 ) +"_open";
	 //document.all( 'box' + this.id ).src = eval("treeview_box_" + this.status.substring( 0 , 1 ) +"_open");
	 eval( "document.all( 'box' + this.id ).src = treeview_box_" + this.status.substring( 0 , 1 ) +"_open" ) ;
}

//=============================================================================================
//��nodeitems�����м���obj
function nodeitemRegister( obj )   {
	//nodeitems�����м���obj
    nodeitems[ nodeitems.length ] = obj ;
	//alert(nodeitems.length - 1);
	//����id��
	return nodeitems.length - 1 ;
}

//========��=====================================================================================































/////////////////////////////////////////////////////////////////////////
//��ʱ����
///////////////////////////////////////////////////////////////////////////
//added by msb for the move up/down
nodeitem.prototype.moveUp = nodeitem_moveUp;
nodeitem.prototype.moveDown = nodeitem_moveDown;
nodeitem.prototype.refresh = nodeitem_refresh ;
nodeitem.prototype.remove = nodeitem_remove ;
nodeitem.prototype.setTag = nodeitem_setTag ;
nodeitem.prototype.getTag = nodeitem_getTag ;

//========================================
//Envrionment to hold Listeners
//========================================
tv_listeners = new Array() ;
function listener( type , handler )   {
	alert("listener");
	this.type = type ;
	this.handler = handler ;
	this.id = tv_listeners.length ;
	tv_listeners[ tv_listeners.length ] = this ;
}

function addListener( type , handler )  {
	alert("addListener");
    new listener( type , handler ) ;  
 }
//=== END =====

//added by msb for the sort and move up/down
function nodeitem_moveUp() {
	alert("nodeitem_moveUp");
	if (this == tv_topnodeitem) return; //topitem 

	ssubitems = this.parent.subitems;
	for ( i=0; i<ssubitems.length; i++ ) {
		if( ssubitems[i] == this ) {
			break;
		}
	}
	if (i==0) return;
	ssubitems[i] = ssubitems[i-1];
	ssubitems[i-1] = this;
	if (i==ssubitems.length-1) {
		ssubitems[i-1].status = "2" + ssubitems[i-1].status.substring(1, 3);
		ssubitems[i].status = "1" + ssubitems[i].status.substring(1, 3);
	}
	/*
	itemTemp = this;
	ssubitems[this.nodeIndex-1] */
/*	for ( i=0; i<ssubitems.length; i++ ) {
		if( ssubitems[i] != null && ssubitems[i].nodeIndex == (this.nodeIndex-1) )
			previousitem = ssubitems[i]
	}
	previousitem.nodeIndex = this.nodeIndex;
	this.nodeIndex = this.nodeIndex -1;
	swap(this,previousitem);
*/
	//label_on_click(this.id);
	this.parent.refresh();

	lable_on_click(this.id);
}

function nodeitem_moveDown() {
	alert(nodeitem_moveDown)
	if (this == tv_topnodeitem) return; //topitem
	
	ssubitems = this.parent.subitems;
	for ( i=0; i<ssubitems.length; i++ ) {
		if( ssubitems[i] == this ) {
			break;
		}
	}
	if (i==ssubitems.length-1) return;
	ssubitems[i] = ssubitems[i+1];
	ssubitems[i+1] = this;
	if (i==ssubitems.length-2) {
		ssubitems[i+1].status = "1" + ssubitems[i+1].status.substring(1, 3);
		ssubitems[i].status = "2" + ssubitems[i].status.substring(1, 3);
	}

	this.parent.refresh();
		
	lable_on_click(this.id);
}

/*function swap (item1, item2) {
	nodeitems[item1.id] = item2;
	nodeitems[item2.id] = item1;
	idTemp = item1.id;
	item1.id = item2.id;
	item2.id = idTemp;
}*/
function nodeitem_setTag( obj ) {
   alert("nodeitem_setTag");
    this.tag = obj ;
}

function nodeitem_getTag() {
	alert("nodeitem_getTag");
    return this.tag ;
}
//ˢ��
function nodeitem_refresh()  {
	alert("refresh");
	str = "<table border='0' cellspacing='0' cellpadding='0'><tr><td>" ;
    str_f = "" ;
    for( j = this.parent ; j != null ; j = j.parent )  {
	    if( j.status.substring( 0 , 1 ) == 1 )
		   str_f = "<img src = '" + treeview_box_0_none + "' align='absmiddle'>" + str_f ;
        else
		   str_f = "<img src = '" + treeview_box_0_line + "' align='absmiddle'>" + str_f ;
    }
	str = str + str_f ;
    str += "<img id = 'box" + this.id + "' nodeid = '" + this.id + "' src = '" ;
    switch( this.status.substring( 0 , 2 ) )   {
	        case "10" : str += treeview_box_1_none ; break ;
	        case "11" : str += treeview_box_1_close ; break ;
	        case "12" : str += treeview_box_1_open ; break ;
	        case "20" : str += treeview_box_2_none ; break ;
	        case "21" : str += treeview_box_2_close ; break ;
	        case "22" : str += treeview_box_2_open ; break ;
    }
    str += "' align='absmiddle' onclick='box_on_click(this)'>" ;
    if( this.img == "" )
	    str += this.img ;
    else
	    str += "<img src = '" + this.img + "' align='absmiddle' width='16' height='16'>" ;
    str += "</td><td><table border='0' cellspacing='1' cellpadding='1' style='font-size:9pt; color:#333333' id='lablePanel" + this.id + "'><tr><td ondblclick = 'lable_on_dblclick(" + this.id + ")' onclick='lable_on_click(" + this.id + ")' style='cursor:hand' id='f_lablePanel" + this.id + "' nowrap>" + this.lable + "</td></tr></table></td></tr></table>" ;  
	document.all( "preface" + this.id ).innerHTML = str ;
    document.all( "tv_panel_" + this.id ).innerHTML = "" ;
	for( m = 0 ; m < this.subitems.length ; m ++ )
	   if( this.subitems[ m ] != null )  {
		   userstack.put( m ) ;
		   this.subitems[ m ].show() ;
		   m = userstack.get() ;
       }
}
//ɾ��һ���ڵ�
function nodeitem_remove()  {
    pparent = this.parent ;
	if( pparent == null )   {
        removenodeitem( this.id ) ;
   	    for(var i in document.all){
           if (document.all[i].tagName == "BODY")
             {
			   document.all[i].innerHTML = "" ;
               break
             }
          }
		return ;
	}
	lastsubitem = null ;
	for( i = 0 ; i < pparent.subitems.length ; i ++ )
	   if( pparent.subitems[ i ] != null )  
		   if ( pparent.subitems[ i ] == this ) 
		       pparent.subitems[ i ] = null ;
           else
		       lastsubitem = pparent.subitems[ i ] ;

    pparent.maxsubitem = lastsubitem ; 
	if( lastsubitem == null )   
        pparent.status = pparent.status.substring( 0 , 1 ) + "0" + pparent.status.substring( 2 , 3 ) ;
    else   
	    pparent.maxsubitem.status = "1" + pparent.maxsubitem.status.substring( 1 , 3 ) ;
     removenodeitem( this.id ) ;

	//added by msb for move up/down
	arrTemp = new Array();
	j = 0;
	for ( i=0; i<pparent.subitems.length; i++ ) {
		if ( pparent.subitems[i] != null ) {
			arrTemp[j] = pparent.subitems[i];
			j++;
		}
	}
	this.parent.subitems = arrTemp;
	//end added

	 pparent.refresh() ;
	 //tv_topnodeitem.refresh() ;
}
//ɾ��һ���ڵ�
function removenodeitem( id )   {
    curitem = nodeitems[ id ] ;
	nodeitems[ id ] = null ;
	for( m = 0 ; m < curitem.subitems.length ; m ++ ) 
	     if( curitem.subitems[ m ] != null )   {
		   userstack.put( m ) ;
		   removenodeitem( curitem.subitems[ m ].id ) ;
		   m = userstack.get() ;
       }
}

//����:ɾ��һ���ڵ�
function deleteNode(  key )   {
    curNode = findNode( key ) ;
	if( curNode == null )
	  return false ;
    curNode.remove() ; 
    return true ;
}

tv_curlable = null ;
tv_curlable_f = null ;


//����(����)��ʱ��,���ñ�����
function lable_on_click( id )  {
       key = nodeitems[ id ].key ;
       if( nodeitems[ id ].parent == null )
	      parentkey = "" ;
       else
	      parentkey = nodeitems[ id ].parent.key ;
	      	   
	   if( tv_curlable != null )  {
		   tv_curlable.bgColor = "transparent" ;
		   tv_curlable.style.color = "#333333" ;
		   tv_curlable_f.bgColor = "transparent" ;
       }
	       tv_curlable = document.all("lablePanel"+id) ;
		   tv_curlable.bgColor = "#000000" ;
		   tv_curlable.style.color = "#FFFFFF" ;
	       tv_curlable_f = document.all("f_lablePanel"+id) ;
		   tv_curlable_f.bgColor = "#888888" ;

	   for( i = 0 ; i < tv_listeners.length ; i ++ )  
	      if( tv_listeners[ i ].type == "click" )  {
			   h = tv_listeners[ i ].handler ;
			   eval( h + "( '" + key + "' , '" + parentkey + "' ) ; " ) ; 
           }

}
//˫��(����)��ʱ��
function lable_on_dblclick( id ) {
		key = nodeitems[ id ].key ;
       if( nodeitems[ id ].parent == null )
	      parentkey = "" ;
       else
	      parentkey = nodeitems[ id ].parent.key ;
          
	   if( tv_curlable != null )  {
		   tv_curlable.bgColor = "transparent" ;
		   tv_curlable.style.color = "#333333" ;
		   tv_curlable_f.bgColor = "transparent" ;
       }
	       tv_curlable = document.all("lablePanel"+id) ;
		   tv_curlable.bgColor = "#000000" ;
		   tv_curlable.style.color = "#FFFFFF" ;
	       tv_curlable_f = document.all("f_lablePanel"+id) ;
		   tv_curlable_f.bgColor = "#888888" ;
	   for( i = 0 ; i < tv_listeners.length ; i ++ )  
	      if( tv_listeners[ i ].type == "dblclick" )  {
			   h = tv_listeners[ i ].handler ;
			   eval( h + "( '" + key + "' , '" + parentkey + "' ) ; " ) ; 
           }
}