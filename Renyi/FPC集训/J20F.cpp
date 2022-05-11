/**
求求你别搞那些会爆栈的阴间数据了
*/
#include<bits/stdc++.h>
#define N 100010
using namespace std;
map<string,int>mp;
int tot,head[N],cnt,ans=1,depth[N],c[N];
struct node{
	int v,nxt;
}e[N*2];
void add(int u,int v){
	e[tot].v = v;
	e[tot].nxt = head[u];
	head[u]=tot++;
}
void bfs(int u){
	//cout<<u<<" "<<fa<<end
	int h=0,t=1,que[N];
	que[0]=u;
	depth[u] = 1;
	while(h<t){
		int cur = que[h++];
		for(int i=head[cur];i!=-1;i=e[i].nxt){

			int v= e[i].v;
			if(depth[v])continue;
			depth[v] = depth[cur] +1;
						//cout<<v<<" "<<depth[v]<<endl;
			c[depth[v]]++;
			ans = max(ans,c[depth[v]]);
			que[t++]=v;
		}
	}

}
string yry_read(){
	string ans = "";
	char c  = getchar();
	while(!isalpha(c))c = getchar();
	while(isalpha(c)){
		ans.push_back(c);
		c = getchar();
	}
	return ans;
}
int main(){
	memset(head,-1,sizeof(head));
	int root = 1,n;
	string rt,a,b,r;
	cin>>n>>rt;
	mp[rt] = ++cnt;
	for(int i=0;i<n-1;i++){
		a = yry_read();
		b = yry_read();
		//cout<<a<<" "<<b<<endl;
		if(mp[a] == 0)mp[a] = ++cnt;
		if(mp[b] == 0)mp[b] = ++cnt;
		add(mp[a],mp[b]);
		add(mp[b],mp[a]);
	}
	bfs(1);
	cout<<ans;
	return 0;
}