#include<bits/stdc++.h>
#define N 10020
using namespace std;
#define eps 1e-7
struct ve{
    double x,y,z;
}v,h,p,d;
ve crossproduct(ve a,ve b){
    ve c;
    c.x = a.y*b.z-a.z*b.y;
    c.y = a.z*b.x-a.x*b.z;
    c.z = a.x*b.y-a.y*b.x;
    return c;
}
bool inellipse(double lamb1,double lamb2,double a,double b){
    return lamb1*lamb1+lamb2*lamb2 <= 1.0;
}
int main(){
    int n;
    cin>>n;
    cin>>v.x>>v.y>>v.z>>h.x>>h.y>>h.z;
    ve norm = crossproduct(v,h);
    double a = v.x*v.x+v.y*v.y+v.z*v.z;
    double b = h.x*h.x+h.y*h.y+h.z*h.z;
    int ans = 0;
    while(n--){
        cin>>p.x>>p.y>>p.z>>d.x>>d.y>>d.z;
        if(d.x*norm.x+d.y*norm.y+d.z*norm.z == 0)continue;//无交点
        double k = -(norm.x*p.x+norm.y*p.y+norm.z*p.z)/(d.x*norm.x+d.y*norm.y+d.z*norm.z);
        if(k<0)continue;
        double x1 = p.x+k*d.x,x2 = p.y+k*d.y,x3 = p.z+d.z*k;
        double lamb1 = (x1*v.x+x2*v.y+x3*v.z)/a;
        double lamb2 = (x1*h.x+x2*h.y+x3*h.z)/b;
        if(inellipse(lamb1,lamb2,a,b))ans++;
    }
    cout<<ans;
    return 0;

}