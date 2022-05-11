//模拟水题
#include<bits/stdc++.h>
using namespace std;
bool check(string a,string b){
	if(a.size()!=b.size())return false;
	int distinct = 0;
	for(int i=0;i<a.size();i++){
		if(a[i]!=b[i])distinct++;
		if(distinct>1)return false;
	}
	return true;
}
int main(){
	int n;
	string pwd,a,b;
	cin>>n>>pwd;
	bool ans = true;	
	while(n--){
		cin>>a>>b;
		string k;
		if(check(pwd,a))k = "ALLOWED";
		else k = "DENIED";
		if(k != b)ans = false;
	}
	if(ans)cout<<"SYSTEM SECURE";
	else cout<<"INTEGRITY OVERFLOW";
	return 0;
}