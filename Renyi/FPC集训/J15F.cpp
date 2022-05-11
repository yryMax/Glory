#include<bits/stdc++.h>
using namespace std;
#define N 200
int f[300][210];
void cal(int a,int b,int factor){
	int c[210];
	memset(c,0,sizeof(c));	
	for(int i=0;i<=N;i++){
		c[i] += f[a][i] + f[b][i]*factor;
		c[i+1] += c[i]/10;
		c[i]%=10;
	}
	for(int i=0;i<=N;i++)f[a][i] = c[i];
}
int main(){
	int n;
	f[0][0]=1;
	cin>>n;
	n*=2;
	for(int i=1;i<=n;i++){
		if(i>=2)cal(i,i-2,3);
		for(int j=2;i>=2*j;j++)cal(i,i-2*j,2);
	}
	int t=N;
	while(f[n][t]==0)t--;
	while(t>=0){
		cout<<f[n][t];
		t--;
	}
	return 0;
}