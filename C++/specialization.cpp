#include <iostream>
using namespace std;

template<class T, class U>
class S
{
public:
    void f()
    {
        cout<<"��ģ��"<<endl;
    }
};

template<class T>
class S<T, int>   //�ڶ�������Ϊint
{
public:
    void f()
    {
        cout<<"U==int"<<endl;
    }
};

template<class U>
class S<double, U> //��һ������Ϊdouble
{
public:
     void f()
    {
        cout<<"T==double"<<endl;
    }
};

template<class T>
class S<T, T>   //T==U
{
public:
     void f()
    {
        cout<<"T==U"<<endl;
    }
};

template<class T, class U>  //����������Ϊָ��
class S<T*, U*>
{
public:
     void f()
    {
        cout<<"T==*T, U==*U"<<endl;
    }
};

int main( )
{
    S<int, float> s1;
    s1.f();

    S<double,float> s2;
    s2.f();

    S<float,float> s3;
    s3.f();

    S<int*, double*> s4;
    s4.f();

    S<int,int> s5;  //ģ��������������������֣����±������
    s5.f();

    return 0;
}
