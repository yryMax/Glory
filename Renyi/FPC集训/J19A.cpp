/**
题目很难懂。
好像是说，block number是随机给的，但是对于每个block，他的前驱必须是输入上的前驱
模拟一下题意即可
**/
#include<bits/stdc++.h>
#define N 1000010
using namespace std;
int nxt[N];
int main(){
	int n;
	cin>>n;
	int a,b,c;
	int tot = 0;
	bool isvalid = true;
	bool money = true;
	int prev = 0;
	for(int i=1;i<=n;i++){
		scanf("%d%d%d",&a,&b,&c);
		if(b!=prev)isvalid = false;
		prev = a;
		tot+=c;
		if(tot<0)money = false;
	}
	
	if(!isvalid)cout<<"INVALID";
	else if(!money)cout<<"NO_MONEY";
	else cout<<tot;
	return 0;
}