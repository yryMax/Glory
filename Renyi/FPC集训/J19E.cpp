/**
水题，解密是加密的逆过程，从后往前相减取模即可
*/
#include<bits/stdc++.h>
using namespace std;
char ans[1010];
int main(){
	int n;
	cin>>n;
	string s;
	cin>>s;
	char c;
	cin>>c;

	ans[n-1]=c;
	for(int i=n-2;i>=0;i--){
		int t = s[i+1]- ans[i+1];
		//cout<<s[i+1]<<" "<<ans[i+1]<<" "<<t<<endl;
		if(t<0)t+=26;
		ans[i]=t+'a';
	}
	cout<<ans;
	return 0;
}