from string import ascii_uppercase
alphabet=ascii_uppercase
for _ in range(input()):
    sc  = raw_input();
    cpt = raw_input();
    scalphas=[]
    for c in sc:
        if c not in scalphas:scalphas.append(c)
    sc = "".join(scalphas)
    dis = list(set(alphabet)-set(sc));dis.sort();
    cipher = sc+"".join(dis)
    scalphas = [(x,i) for i,x in enumerate(sc)]
    scalphas.sort()
    sclen = len(sc)
    mcipher=""
    for x,idx in scalphas:
        while idx<26:
            mcipher=mcipher+cipher[idx] 
            idx=idx+sclen
    print " ".join("".join([alphabet[mcipher.index(i)] for i in word])for word in cpt.split())