/*
素性测试模板题
*/

#include<bits/stdc++.h>
#define N 10100
using namespace std;
int main(){
	long long n;
	cin>>n;
	int c= sqrt(n);
	for(int i=2;i<=c;i++){
		if(n%i==0){
			cout<<"BROKEN";
			return 0;
		}	
	}
	if(n>1)cout<<"SAFE";
	else cout<<"BROKEN";
	return 0;
	
}