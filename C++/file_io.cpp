#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;

void openfile();
void createfile();
void test_read_write();

int main( )
{
    //���ļ�
    //open_file();

    //�����ļ�
    //create_file();

    //ʹ��read()��write()
    //test_read_write();



    return 0;
}

void open_file()
{
    fstream file;
    file.open("test.txt");
    if(file.fail())
    {
        cout<<"���ļ�ʧ��"<<endl;
    }

    char str[50];
    while(file>>str)
    {
        cout<<str<<"|";
    }
    file<<"END"<<endl;
    file.close();
}

void create_file()
{
    ofstream ou("mydata.txt");
    ifstream in;
    ou<<"hello xiao kitty"<<endl;
    in.open("mydata.txt");
    if(in.fail())          //��if(!in){}
    {
        cout<<"���ļ�ʧ��"<<endl;
    }

    char str[30];
    while(in>>str)
    {
        cout<<str<<endl;  //ÿ��ֻ�ܶ���һ���ַ����Կո�Ϊ��
    }
    int i=0;
    while(in)
    {
        in>>str[i];       //��ȡ�����ַ����������ո�
        i++;
    }

    cout<<str<<endl;
    in.close();
    ou.close();
}

void test_read_write()
{
    ofstream ofile("test2.txt");
    ifstream ifile;
    int n=12;
    char str[]="�����й���";
    while(!ofile)
    {
        cout<<"���ʧ�ܣ�"<<endl;
    }
    ofile.write((char*)&n, sizeof(int));
    ofile.write(str, strlen(str));
    ofile.close();

    ifile.open("test2.txt");
    if(!ifile)
    {
        cout<<"���ļ�ʧ�ܣ�"<<endl;
    }
    int m;
    char str2[50];
    ifile.read((char*)&m, sizeof(int));
    ifile.read(str2, strlen(str));
    cout<<"m is "<<m<<",str2 is "<<str2<<endl;
}
