/*
贪心！！！！！！
首先按照社交距离从小到大排序，这样能保证每次一个新人进来的时候，他的社交距离比
当前任意两个相邻的人的间隔都大，考虑这个新来的人的社交距离是x，假设他插入相邻的两个人x1,x2之间，
则他坐下后和x1,x2的距离必定都为x，现周长 = 圆周长 - dist（A,B） + 2*x
可以看出这是一个最优子结构，保证dist(A,B)最小即可，即每个新上桌的人必坐在在他之前两个上桌的人的中间
*/

#include<bits/stdc++.h>
#define N 100010
using namespace std;
long long a[N];
int main(){
	int n;
	cin>>n;
	for(int i=0;i<n;i++)scanf("%lld",&a[i]);
	sort(a,a+n);
	long long ans = a[1]*2;
	for(int i=2;i<n;i++)ans = ans - a[i-1]+a[i]*2;
	cout<<ans;
}