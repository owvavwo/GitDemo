#include <iostream>
using namespace std;

template <typename T, int size=100>   //Ĭ��sizeΪ100������ȷ����Ϊ��ʱ����template <class T,...>
class Stack
{
private:
    T arr[size];
    int s_count;
public:
    Stack():s_count(0)
    {

    }

    bool push(const T &e)
    {
        if(s_count <size)
        {
            arr[s_count++]=e;
            cout<<"ѹ��"<<arr[s_count-1]<<endl;
            return true;
        }
        return false;
    }
    bool pop(T &e);


};

template <typename T, int size>  //�ⲿ���庯��,ÿ����Ա�����ͱ���ֻҪ�����ⶨ�嶼��Ҫ���ϴ��д���
 bool Stack<T, size>:: pop(T &e)
    {
        if(s_count >=0)
        {
            e=arr[--s_count];
            cout<<"����"<<e<<endl;
            return true;
        }
        return false;
    }

int main( )
{

    int a=1,b=2,c=4;
    Stack<int, 20> s;
    s.push(a);s.push(b);s.push(c);

    int d,e;
    s.pop(d);s.pop(e);
    cout<<"The result is "<<d<<" and "<<e<<endl;


    return 0;
}
