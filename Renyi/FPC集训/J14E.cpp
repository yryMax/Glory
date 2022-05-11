/*
其实我没看懂题目什么意思，看样例猜测出来的
就是给一个图案和一个pattern，看那大图案里符合pattern的有几个
暴枚可过
*/
#include<bits/stdc++.h>
#define N 110
using namespace std;
char m[500][500],t[N][N];
bool check(int startn,int startm,int c,int d){
	for(int i=startn;i<startn+c;i++){
		for(int j=startm;j<startm+d;j++){
			if(t[i-startn][j-startm] == '*')continue;
			if(t[i-startn][j-startm] != m[i][j])return false;
		}
	}
	return true;
}
int main(){
	int a,b,c,d;
	cin>>b>>a;
	int ans=0;
	for(int i=0;i<a;i++){
		scanf("%s",m[i]);
		//cout<<m[i]<<endl;
	}
	cin>>d>>c;
	for(int i=0;i<c;i++){
		scanf("%s",t[i]);
		//cout<<t[i]<<endl;
	}
	for(int i=0;i<=a-c;i++){
		for(int j=0;j<=b-d;j++){
			ans+=check(i,j,c,d);
		}
	}
	cout<<ans;
	return 0;
}