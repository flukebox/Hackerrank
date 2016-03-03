/** C code here **/
main(t){scanf("%d",&t);while(t--){double m=1,d=1,s=1,n;scanf("%lf",&n);while(--n)s*=-1,d+=2,m+=s/d;printf("%.15f\n",m);}}

/** Python here **/
exec"print'%.15f'%sum((-1.)**i/(i-~i)for i in range(input()));"*input()

/** Sudoku **/
def r(a):i=a.find('0');~i or exit(a);[m
in[(i-j)%9*(i/9^j/9)*(i/27^j/27|i%9/3^j%9/3)or a[j]for
j in range(81)]or r(a[:i]+m+a[i+1:])for m in'%d'%5**18]
from sys import*;r(argv[1])

/** fizzbuzz Python 2.0 **/
for i in range(1,101):print"Fizz"[i%3*4:]+"Buzz"[i%5*4:]or i
