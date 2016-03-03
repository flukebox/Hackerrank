for tc in range(input()):
    n = input()
    for m in range(0, n/5+1): 
        i = 5*m
        j = n-i
        if j%3==0:
            print "5"*j+"3"*i
            break
        if m==n/5:print -1
