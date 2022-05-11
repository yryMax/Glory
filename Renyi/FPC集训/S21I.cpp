/*
前缀和后排序，对于每一个人，二分查找他能化滑雪的最左边的时间，假设该日为ice【index】，则index右边的数的个数就是答案
*/
#include<bits/stdc++.h>
#define N 100010
using namespace std;
long long ice[N];
int n,k;
int check(int x){
	int l=0,r=n;
	//cout<<l<<" "<<r<<endl;
	while(l<r){
		int m = (l+r)/2;
	//	cout<<l<<" "<<r<<" "<<m<<" "<<ice[m]<<endl;
		if(ice[m]>=x)r = m;
		else l = m+1;
	}
	if(ice[l]>=x)return l;
	return n+1;
}
int main(){
	long long tmp;
	cin>>n>>k;
	for(int i=1;i<=n;i++){
		scanf("%lld",&ice[i]);
		ice[i] = -ice[i];
		ice[i]+=ice[i-1];
		if(ice[i]<0)ice[i]=0;
	}
	sort(ice+1,ice+n+1);
	//for(int i=1;i<=n;i++)cout<<ice[i]<<" ";
	while(k--){
		scanf("%lld",&tmp);
		printf("%d ",n-check(tmp*5)+1);
	}
}