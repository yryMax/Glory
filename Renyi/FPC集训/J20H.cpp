//stl救我狗命
#include<bits/stdc++.h>
#define N 1010
using namespace std;
string code[N];
map<string,int>var;
void store(string p){
    if(p[0]!='$') {
        int c = 0;
        int sign = 1;
        int st = 0;
        if(p[0] == '-'){
            sign = -1;
            st = 1;
        }
        for(int i=st;i<p.size();i++)c = c*10+p[i] - '0';
        var[p] = c*sign;
    }
}
int main(){
    int n;
    cin>>n;
    getline(cin,code[0]);
    var["$pc"] = 0;
    var["$acc"] = 0;
    var["$cmp"] = 0;
    var["$out"] = 0;
    for(int i=0;i<n;i++){
        getline(cin,code[i]);
    }
    while(var["$pc"]>=0&&var["$pc"]<n){
        int t =var["$pc"];
        int firstspace = code[t].find_first_of(' ');
        int secondspace = code[t].find_last_of(' ');
        string par1 = code[t].substr(firstspace+1,secondspace - firstspace -1);
        string par2 = code[t].substr(secondspace+1);
        store(par1);
        store(par2);
        bool changed = false;
        if(code[t][0] == 'm'){
            var[par2] = var[par1];
            if(par2 == "$pc")changed = true;
        }
        if(code[t][0] == 'a'){
            var["$acc"] = var[par1] + var[par2];
        }
        if(code[t][0] == 's'){
            var["$acc"] = var[par1] - var[par2];
        }
        if(code[t][0] == 'j'){
            if(var["$cmp"] == var[par1]){
                changed = true;
                var["$pc"] = var[par2];
            }
        }
        if(code[t][0] == 'h'){
            cout<<code[t]<<endl;
            cout<<var["$acc"]<<endl<<var["$cmp"]<<endl<<var["$out"]<<endl;
            return 0;
        }
        if(!changed)var["$pc"]++;
    }
    cout<<var["$out"];
    return 0;
}