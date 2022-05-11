#include<bits/stdc++.h>
using namespace std;
int mp1[200],mp2[200];
int main(){
	int n;
	string s,t;
	cin>>n;
	cin>>s>>t;
	
	for(int i=0;i<n;i++)mp1[s[i]]++;
	for(int i=0;i<n;i++){
		mp2[t[i]]++;
	}
	int ans=0;
	for(int i='a';i<='z';i++)if(mp2[i])ans+=max(0,mp2[i]-mp1[i]);
	cout<<ans;
	return 0;
}