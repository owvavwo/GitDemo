/**
 *����������Ľ���
 **
 */

#include <iostream>
#include <stdlib.h>
using namespace std;

typedef struct node
{
    int elem;
    struct node *next;
};

/*��ʼ���б�,����һ�������������*/
void initlist(node *&l, int a[], int n)
{
    int i;
    node *tail;  //����β�巨
    for(i=0; i<n; i++)
    {
        node *p = (node *)malloc(sizeof(node));
        if(p == NULL)
        {
            cout<< "�������ݿռ�ʧ�ܣ�"<<endl;;
            break;
        }
        p->elem = a[i];
        p->next = NULL;
        if(l == NULL)
        {
            l = p;    //lָ���һ��Ԫ��
            tail = l;
        }
        else
        {
            tail->next = p;
            tail = p;
        }

    }

}

/*�󽻼�*/
void intersection(node *&La, node *Lb)
{
    node *pa,*pb,*pre,*q;
    pa = La;
    pre = NULL;

    while(pa)
    {
        pb = Lb;
        while(pb && (pa->elem != pb->elem))
              pb = pb->next;
        if(pb)  //����ͬԪ��
        {
            if(!pre)  //La�е�һ��Ԫ��Ϊ��ͬԪ��
                La = pa->next;
            else
                pre->next = pa->next;

            q = pa;      //ɾ����ͬԪ��
            pa = pa->next;
            free(q);
        }
        else             //�Ƚ�ԩ��La�ĵ�һ��Ԫ�أ��Ƚ�La����һ��Ԫ��
        {
            pre = pa;
            pa = pa->next;
        }
    }
}


int main( )
{
    int a[]= {5, 10, 20, 15, 25, 30};
    int b[] = {5, 15, 35, 25};
    node *la = NULL;
    node *lb = NULL;
    node *p;

    initlist(la, a, sizeof(a)/sizeof(int));
    initlist(lb, b, sizeof(b)/sizeof(int));


    intersection(la, lb);

    p = la;
    while(p)  //���������La
    {
        cout<<p->elem<<" ";
        p = p->next;
    }
    return 0;
}



