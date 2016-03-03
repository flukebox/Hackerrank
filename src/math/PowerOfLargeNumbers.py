import sys

M=10**9+7
M1=M-1

def modpower(base, pow):
    t = 1;
    p = base;
    while pow >= 1:
      if (pow & 1) == 1:
           t = (t * p) % M;
      p = (p * p) % M;
      pow >>= 1;
    return t
    
for _ in range(input()):
      ar=raw_input().split()
      print modpower(int(ar[0])%M, int(ar[1])%M1)