 
#include <stdio.h> 

	typedef struct Node 
	{ 
		int data; 
		struct Node *next; 
	}Node; 
 
	//����ͷ���������㷨
	void reverse1(Node *&L) 
	{ 	
		// �赥����û�б�ͷ��㣬Lֱ��ָʾ����ʼ��㡣����ȫ����ת��Lָ��ԭ��β��㣬����Ϊ�µĿ�ʼ���
		if(L == NULL) return; 
		Node *p = L->next,*pr = NULL;  
		while(p)
		{ 
			L->next = pr;   //��תLָ��
			pr = L;  		
			L = p; 
			p = p->next; 	
		} 
		L->next = pr; 
	} 

	//��ͷ���ĵ����������㷨��������ͷ�巨
	void reverse2(Node *&L)
	{	
		if(L == NULL || L-next==NULL) return;
		
		Node *p = L->next, *q;
		L->next = NULL;			
		while(p)					//p���ʼ��ָ��ɵ�����Ŀ�ʼ���
		{	q = p->next;			//q�����Ϊ�����������¼p��ֱ�Ӻ�̽���λ��
			p->next = L->next;		//��p��ָ�������µ�������
			L->next = p;			
			p = q;					//��Ϊ��̽���Ѿ�����q�У����p��Ȼ�����ҵ���̽��
		}
	}
 
List reverse3(List head)
{ 		
		List newHead;
		
        if(head==NULL ||head->next==NULL)
		{ 
               return head;
        } 
        newHead = reverse3(head->next); 
        head->next->next=head; 
        head->next=NULL; 
		
		return newHead;
} 