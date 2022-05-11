//lcm(a,b,c) = lcm(lcm(a,b),c)
#include<bits/stdc++.h>
using namespace std;
int main(){
	int n;
	cin>>n;
	long long ans = 1;
	while(n--){
		long long tmp;
		cin>>tmp;
		ans = ans/(__gcd(tmp,ans))*tmp;
	}
	cout<<ans;
	return 0;
}