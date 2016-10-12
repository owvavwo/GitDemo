#include <iostream>
#include <deque>
using namespace std;
void show(const deque<int>& d);

int main( )
{
    deque<int> d(10,6);  //��ʼ������Ϊ10�Ķ��У����ݶ�Ϊ6
    show(d);

    d.push_back(2);
    show(d);

    d.push_front(3);
    show(d);

    d.insert(d.begin()+1,4,5);  //��indexΪ1��λ�ÿ�ʼ����4��5
    show(d);
    cout<<"��10��λ�õ���Ϊ��"<<d.at(10)<<endl<<endl;

    d.pop_front();      //�Ƴ���ǰ������Ԫ��
    d.pop_back();
    cout<<"ȥ����β��";show(d);

    d.assign(8,25);    //���¸�ֵ�ɺ���8��25�Ķ���
    show(d);

    d.push_front(2);
    d.push_front(3);
    d.erase(d.begin()+1);   //ɾ����2��Ԫ��
    show(d);

    return 0;
}

void show(const deque<int>& d)
{
    deque<int>::const_iterator iter;
    cout<<"��������Ϊ��"<<endl;
    for(iter=d.begin();iter!=d.end(); ++iter)
        cout<<*iter<<" ";
    cout<<endl<<endl;
}
