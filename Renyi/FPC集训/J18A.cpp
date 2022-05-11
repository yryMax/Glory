/*
签到题，没有难度……………………………………
按照题意模拟就好
*/

#include<bits/stdc++.h>
#define N 100
using namespace std;
int main(){
	string tmp,s;
	cin>>s;
	for(int i=0;i<=25;i++){
		tmp = s;
		for(int j=0;j<s.size();j++){
			int offset = (s[j] - i -'A' + 26)%26;
			tmp[j] = 'A' + offset;
		}
		cout<<tmp<<endl;
		
	}
	
}