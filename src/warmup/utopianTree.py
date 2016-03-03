# Enter your code here. Read input from STDIN. Print output to STDOUT
import math
import sys
tc = input()
while tc > 0:
    n = input()
    p = int(math.ceil(n/2.0))+1
    if(n%2==0):
        print (1<<p)-1
    else:
        print  (1<<p)-2
    tc = tc - 1