#!/usr/bin/python

t = int(raw_input())
a = []
b = []
for f in range(t):
 x,y = raw_input().split()
 a.append(int(x))
 b.append(int(y))

for f in range(t):
 output = []
 char = []
 if a[f]>0:
  start = 0 
  output = ['1']
  char = ['+1','*1','+2','*2'] 
  for i in range(1,(2*a[f]+2*b[f])/2):
   for j in range(start,len(output)):   
    if output[j].count('1') < a[f]:
     output.append(output[j] + char[0])
     output.append(output[j] + char[1])
    if b[f]>0:
     if output[j].count('2') < b[f]:
      output.append(output[j] + char[2])
      output.append(output[j] + char[3])
    start = j + 1
 output = list(set(output))
 result = output
 output = []

 if b[f]>0:
  start = 0
  output = ['2']
  char = ['+2','*2']
  for i in range(1,(2*a[f]+2*b[f])/2):
   for j in range(start,len(output)):
    if output[j].count('2') < b[f]:
     output.append(output[j] + char[0])
     output.append(output[j] + char[1])
    start = j + 1
   output = list(set(output))
 output = list(set(output))
 result = result + output
 result = [eval(i) for i in result]
 result = list(set(result))
 print len(result)
