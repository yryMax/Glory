//给圆的面积求周长,向上取整到0.1
#include<bits/stdc++.h>
#define N 100
#define PI 3.14159265358479267
using namespace std;
double score[N],weight[N];
int main(){
	long long n;
	cin>>n;
	double ans = 2*sqrt(n*PI);
	long long aa = ceil(ans*10);
	printf("%.1f",(double)aa/10);
	return 0;
}