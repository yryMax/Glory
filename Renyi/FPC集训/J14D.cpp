/*
按照题意模拟即可
*/
#include<bits/stdc++.h>
#define N 1010
using namespace std;
int contains[N],have[N];
int main(){
	int n,m,k,s,t;
	cin>>n>>m>>k;
	for(int i=0;i<k;i++){
		cin>>t;
		contains[t] = 1;
	}
	for(int i=1;i<=n;i++){
		int p;
		cin>>p;
		while(p--){
			cin>>s;
			if(contains[i])have[s]=1;
		}
	}
	bool flag = true;
	for(int i=1;i<=m;i++)if(!have[i])flag = false;
	if(flag)cout<<"yes";
	else cout<<"no";
	return 0;
}