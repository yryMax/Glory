/**
大法师维护包含每个节点的最优解和次优解
*/
#include<bits/stdc++.h>
#define N 1e6 + 10
#define INF 1e18
using namespace std;
long long n,answer = -INF;
bool leaf = false;
pair<long long,long long> dfs(){
	int val,num;
	scanf("%d%d",&val,&num);
	pair<long long,long long>ans(0,0);
	for(int i=0;i<num;i++){
		leaf = false;
		pair<long long,long long>child = dfs();
		
		if(child.first>ans.first){
			ans.second = ans.first;
			ans.first = child.first;
		}
		else if(child.first>ans.second)ans.second = child.first;
		if(leaf == false && child.second>ans.second)ans.second = child.second;
		//cout<<child.first<<" "<<child.second<<" "<<ans.first<<" "<<ans.second<<endl;
	}
	ans.first+=val;
	ans.second+=val;
	//cout<<ans.first<<" "<<ans.second<<endl;
	answer = max(answer,ans.first+ans.second-val);
	if(num == 0)leaf = true;
	return ans;
}
int main(){
	cin>>n;
	dfs();
	cout<<answer;
	return 0;
}