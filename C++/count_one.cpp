/*
* 1.�ж�һ�����ǲ���2�ĳ˷�
* 2. ����һ������������1�ĸ���
*/

#include <iostream>
using namespace std;

/*�ж��Ƿ���2�ĳ˷�*/
int power_two(int n)
{
    if(n <= 0)
    {
        return 0;
    }
    else
    {
        return (n&(n-1))==0;
    }
}

/*��������1�ĸ��� */
int count_bin(int n)
{
    int i;
    for( i=0;n!=0;i++)
    {
        n &= (n-1);
    }

     return i;
}

int main( )
{ 
   int number;cout<<number<<(power_two(number)==1?" ��2�ĳ˷�!":" ����2�ĳ˷�!")<<endl;
   cout<<count_bin(15)<<endl;
   return 0;
}