b=None;g=range;
def s(i,j,v):b[0][i][j]=v+1;b[1][i][v]=b[2][j][v]=b[3][(i/3)*3+j/3][v]=1
def o(i,j):
 if(i==9):
  i=0;j=j+1
  if(j==9):return 1
 if(b[0][i][j]):return o(i+1,j)
 for v in g(9):
  if not b[0][i][j]|b[1][i][v]|b[2][j][v]|b[3][(i/3)*3+j/3][v]:
   s(i,j,v)
   if o(i+1,j):return 1
   b[0][i][j]=b[1][i][v]=b[2][j][v]=b[3][(i/3)*3+j/3][v]=0
 return 0
for _ in g(input()):
 global b;b=[[[0]*9for i in g(9)]for i in g(4)]
 for i in g(9):
  j=0;
  for v_ in raw_input().split():
   v=int(v_);v and s(i,j,v-1);j=j+1
 o(0,0)
 print "\n".join([" ".join(str(r)for r in w)for w in b[0]])