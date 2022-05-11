/**
是谁五年之后还在用背包糊题
*/
#include<bits/stdc++.h>
#define N 1010
#define M 1000000
using namespace std;
int f[M+10],a[N],n,m;
int main(){
	int cannot = 0;
	cin>>n>>m;
	for(int i=0;i<m;i++)scanf("%d",&a[i]);
	f[0]=1;
	if(m == 1){
		cout<<(n-1)/a[0];
		return 0;
	}
	for(int i=0;i<m;i++){
		for(int j=a[i];j<=M;j++){
			if(f[j-a[i]]){
				f[j]=1;
			}
		}
	}
	for(int i=1;i<=min(n,M);i++){
		if(!f[i]){
			cannot++;
		}
	}
	cout<<n - cannot-1;
	return 0;
}