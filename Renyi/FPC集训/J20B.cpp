/**
注意到题目给了一个条件：如果把任意两条互相拮抗的点看作一条边，则任意两边不相交
所以可以把整个环分成一段一段的，取段最大的答案即可
问题转化为如何取一段的最大答案
然后通过一通骚操作证出答案只可能是2或者3，分别check即可 
*/
#include<bits/stdc++.h>
#define N 1000010
using namespace std;
int color[N],tot,head[N],que[N];
struct node{
	int nxt,v;
}e[N];
void add(int u,int v){
	e[tot].v = v;
	e[tot].nxt = head[u];
	head[u] = tot++;
}
bool bfs(int u){
	int h=0,t=1;
	que[h] = u;
	while(h<t){
		int uu = que[h++];
		for(int i=head[uu];i!=-1;i=e[i].nxt){
			int v = e[i].v;
			if(color[uu] == color[v])return false;
			if(color[v] == -1){
				color[v] = 1 - color[uu];
				que[t++] = v;
			}
		}
	}

	return true;
}
int main(){
	int a,b,m,n;
	memset(head,-1,sizeof(head));
	memset(color,-1,sizeof(color));
	cin>>n>>m;
	for(int i=0;i<m;i++){
		scanf("%d%d",&a,&b);
		add(a,b);
		add(b,a);
	}
	for(int i=1;i<=n;i++){
		if(color[i] == -1){
			color[i] = 0;
			if(!bfs(i)){
				cout<<3;
				return 0;
			}
		}
	}
	cout<<2;
	return 0;
}