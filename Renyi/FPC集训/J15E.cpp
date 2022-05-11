#include<bits/stdc++.h>
#define N 100
using namespace std;
double score[N],weight[N];
int main(){
 // freopen("sample2.in", "r", stdin);
 // freopen("J15E.out", "w", stdout);
  int n;
	cin>>weight[0]>>n;
  double sumscore = 0,sumweight = weight[0];
  bool ans = true;
  for(int i=1;i<=n;i++){
  	cin>>score[i]>>weight[i];
  	sumscore+=score[i]*weight[i];
  	sumweight+=weight[i];
  	if(score[i]<5.8)ans=false;
  }
  if(!ans||8.0*sumweight>sumscore+10.0*weight[0]){
  	cout<<"IMPOSSIBLE";
  }
  else {
  	double a = max(5.8,(8.0*sumweight-sumscore)/weight[0]);
  	int aa = ceil(a*10);
  	printf("%.1f",(double)aa/10.0);
  }
	return 0;
}