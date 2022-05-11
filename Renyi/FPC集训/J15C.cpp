/*
魔改克鲁斯卡尔,本来是把最小的边放前面，改为把质数边放前面
*/

#include<bits/stdc++.h>
#define N 10010
using namespace std;
bool isprime[N];
struct node{
	int u,v,w;
}e[N];
bool cmp(node a, node b){return isprime[a.w]>isprime[b.w];}
int fa[N];
int find(int x){return x==fa[x]?x:fa[x]=find(fa[x]);}
int main(){
	memset(isprime,true,sizeof(isprime));
	isprime[1]=0;
	for(int i=2;i<N;i++){
		if(!isprime[i])continue;
		for(int j=i*i;j<N;j+=i)isprime[j]=0;
	}
	int n,m;
	cin>>n>>m;
	for(int i=0;i<m;i++){
		int tmp;
		cin>>e[i].u>>e[i].v>>e[i].w;
	}
	for(int i=1;i<=n;i++)fa[i]=i;
	sort(e,e+m,cmp);
	int totp=0;
	for(int i=0;i<m;i++){
		int u=e[i].u;
		int v=e[i].v;
		if(find(u)==find(v))continue;
		fa[find(u)]=find(v);
		//cout<<u<<" "<<v<<" "<<e[i].w<<" "<<isprime[e[i].w]<<endl;
		if(isprime[e[i].w])totp++;
	}
	cout<<totp<<" "<<n-1-totp;
	return 0;
	
}