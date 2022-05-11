/**
求经过特定点的最短路
简单广搜，或魔改spfa
*/
#include<bits/stdc++.h>
using namespace std;
#define N 100010
struct node{
	int v;
	int nxt;
}e[2000010];
int head[N],tot,h[N];
void add(int u,int v){
	e[tot].v=v;
	e[tot].nxt=head[u];
	head[u]=tot++;
}
int que[N],dist[N];
int main(){
	memset(head,-1,sizeof(head));
	int n,m,k,tmp,a,b;
	cin>>n>>m>>k;
	h[1]=1,h[n]=1;
	for(int i=0;i<k-2;i++){
		cin>>tmp;
		h[tmp]=1;
	}
	while(m--){
		scanf("%d%d",&a,&b);
		add(a,b);
		add(b,a);
	}
	int start=0,tail=0;
	que[tail++]=1;
	dist[1]=1;
	while(start!=tail){
		int u = que[start++];
		if(u == n){
			cout<<dist[u];
			return 0;
		}
		for(int i=head[u];i!=-1;i=e[i].nxt){
			int v = e[i].v;
			if(dist[v]||!h[v])continue;
			dist[v] = dist[u]+1;
			que[tail++]=v;
		}
	}
	return 0;
}