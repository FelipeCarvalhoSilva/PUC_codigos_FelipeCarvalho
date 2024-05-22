#include <stdio.h>
#include <math.h>

int main(void){
    double a,b,c;
    scanf("%lf %lf %lf",&a,&b,&c);
    if(a==0||b==0||c==0){printf("Impossivel calcular\n");
    return 0;}
    double delta;
    delta=b*b-(4*a*c);
    double r1,r2;
    r1=(b*(-1)+sqrt(delta))/(2*a);
    r2=(b*(-1)-sqrt(delta))/(2*a);
    printf("R1 = %.5lf\n",r1);
    printf("R2 = %.5lf\n",r2);
    return 0;
}
