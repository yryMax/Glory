#include<bits/stdc++.h>
using namespace std;
map<string,int>mp;
struct node{
	string name;
	int score;
}ans[410];
bool cmp(node x,node y){
	if(x.score == y.score)return x.name<y.name;
	return x.score>y.score;
}
int main(){
	int n,sa,sb,tot=0;
	string a,b;
	cin>>n;
	int m = n*(n-1)/2;
	while(m--){
		cin>>a>>b>>sa>>sb;
		//cout<<a<<" "<<b<<" "<<sa<<" "<<sb<<endl;
		if(sa == sb){
			mp[a]++;
			mp[b]++;
		}
		if(sa>sb){
			mp[a]+=3;
		}
		if(sb>sa)mp[b]+=3;
	}
	for(auto item: mp){
		ans[tot].name = item.first;
		ans[tot++].score = item.second;
	}
	sort(ans,ans+tot,cmp);
	for(int i=0;i<min(5,tot);i++)cout<<ans[i].name<<" "<<ans[i].score<<endl;
	return 0;
}