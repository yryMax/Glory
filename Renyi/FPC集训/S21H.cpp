/*
按照题意模拟就好…………………………………………………………………………………………
*/

#include<bits/stdc++.h>
#define N 1010
using namespace std;
int height[N];
int main(){
	int n,len,m,tmp,h=0;
	cin>>n>>len>>m;
	while(m--){
		cin>>tmp;
		int ind = (tmp-1)/len;
		height[ind]++;
		h = max(h,height[ind]);
	}
	for(int level=h;level>=1;level--,cout<<endl){
		for(int i=0;i<n;i++){
			if(height[i]>=level)putchar('#');
			else putchar('.');
		}
	}
	for(int i=0;i<n;i++)cout<<"-";
}