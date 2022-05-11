/*
签到题，没有难度……
最大的障碍是从一堆废话里弄清题目意思（计算一个nxn网格中的正方形个数
*/

#include<bits/stdc++.h>
#define N 100
using namespace std;
int main(){
	long long n,ans = 0;
	cin>>n;
	for(int len = 0;len<n;len++){
		ans+=(n-len)*(n-len);
	}
	cout<<ans;
	
}