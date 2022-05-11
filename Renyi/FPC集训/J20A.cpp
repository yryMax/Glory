/*
因为直接求答案比较复杂，而检查答案是否合法比较简单，所以考虑二分答案
check可以用深搜或者广搜,判断位移有点恶心
*/
#include<bits/stdc++.h>
#define N 510
using namespace std;
struct node{
	int x,y;
}que[5000000],start;
int e[N][N],vis[N][N];
int n,m,k;
bool check(int val){//坐标从1开始
	memset(vis,0,sizeof(vis));
	start.x = k;
	start.y = k;
	int head = 0,tail = 1;
	que[head] = start;
	vis[k][k]=1;
	while(head<tail){
		node cur = que[head++];
		
		if(cur.x == n&&cur.y == m)return true;
		if(cur.x<n&&vis[cur.x+1][cur.y] == 0){//下
			bool flag = true;
			for(int yy = cur.y;yy>cur.y-k;yy--)if(e[cur.x+1][yy]>=val)flag = false;
			node nxt = cur;
			nxt.x++;
			vis[nxt.x][nxt.y] = 1;
						//cout<<cur.x<<" "<<cur.y<<" "<<flag<<endl;
			if(flag)que[tail++]=nxt;
		}
		if(cur.y<m&&vis[cur.x][cur.y+1] == 0){//右
			bool flag = true;
			node nxt = cur;
			nxt.y++;
			vis[nxt.x][nxt.y] = 1;
			for(int xx = cur.x;xx>cur.x-k;xx--)if(e[xx][cur.y+1]>=val)flag = false;
						//cout<<cur.x<<" "<<cur.y<<" "<<flag<<endl;
			if(flag)que[tail++]=nxt;
			
		}
		if(cur.y>k&&vis[cur.x][cur.y-1] == 0){//左
			bool flag = true;
			for(int xx = cur.x;xx>cur.x-k;xx--)if(e[xx][cur.y-k]>=val)flag = false;
			node nxt = cur;
			nxt.y--;
			vis[nxt.x][nxt.y] = 1;
						//cout<<cur.x<<" "<<cur.y<<" "<<flag<<endl;
			if(flag)que[tail++]=nxt;
		}
		if(cur.x>k&&vis[cur.x-1][cur.y] == 0){//上
			bool flag = true;
			for(int yy = cur.y;yy>cur.y-k;yy--)if(e[cur.x-k][yy]>=val)flag = false;
			node nxt = cur;
			nxt.x--;
			vis[nxt.x][nxt.y] = 1;
			//cout<<cur.x<<" "<<cur.y<<" "<<flag<<endl;
			if(flag)que[tail++]=nxt;
		}
	}
	return false;
}
int main(){
	cin>>n>>m>>k;
	int l = 0,r = 1e9;
	int maxx=0;
	for(int i=1;i<=n;i++){
		for(int j=1;j<=m;j++){
			scanf("%d",&e[i][j]);
			//if(e[i][j] >= 999451044)cout<<i<<" "<<j<<endl;
			//else e[i][j] = 1;
			//printf("%d ",e[i][j]);
			
		}
	}
	//cout<<check(999451044);
	//cout<<maxx<<endl;
	while(l<r){
		//cout<<l<<" "<<r<<endl;
		int mid = (l+r)/2;
		if(check(mid))r=mid;
		else l=mid+1;
	}
	
	cout<<l;
	return 0;
}