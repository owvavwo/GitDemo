#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

template<class T>   //����һ���࣬��Ϊ����������ʹ��
class Print
{
public:
    void operator () (const T& t) const
    {
        if(t%2 == 0)
        {
            cout<<t<<" ";
        }
    }
};

int main( )
{
    vector<int> v;
    vector<int>::const_iterator iter;
    v.push_back(1);
    v.push_back(2);
    v.push_back(3);
    v.push_back(4);
    v.push_back(5);

    cout<<"The value is ";
    for(int i=0;i<v.size();i++)
        cout<<v[i]<<" ";

    cout<<endl<<endl;

    cout<<"The value is ";
    for(iter=v.begin(); iter!=v.end(); ++iter)  //ʹ�õ���������
    {
        cout<<(*iter)<<" ";
    }
    cout<<endl;
    vector<int>::const_iterator v_begin,v_end;
    v_begin = v.begin();
    v_end = v.end();
    for_each(v_begin, v_end, Print<int>());  //��֪��Ϊʲô����

    return 0;
}
