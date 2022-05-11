/*
贪心，能取最下面的就尽量取
（因为不是传统型我又不会用学习的validator所以是手动测的（……
*/
#include<bits/stdc++.h>
#define N 100010
using namespace std;

int main(){
	int n,m;
	cin>>n>>m;
	int ans = m/n;
	cout<<ans+1<<endl;
	for(int i=0;i<ans;i++){
		int offset = n-(i%n);
		printf("%c ",'a'+offset-1);
	}
	int offset = n-m%n;
	int o = n-m%n;
	printf("%c ",'a'+(offset+o)%n-1);
}