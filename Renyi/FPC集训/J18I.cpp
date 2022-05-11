/*
01背包裸题
*/
#include<bits/stdc++.h>
using namespace std;
int f[10010];
int main(){
	int n,p,h,m;
	cin>>n>>m;
	for(int i=0;i<n;i++){
		cin>>p>>h;
		for(int j=m;j>=p;j--)f[j] = max(f[j],f[j-p]+h);
	}
	cout<<f[m];
	return 0;
}