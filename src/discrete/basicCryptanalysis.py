#!/usr/bin/python
def possibleMatch(word, dictionary, givenmap):
    mapping = []
    for w in dictionary.get(len(word)):
        lmap = {}
        possible = True
        for x,y in set(zip(word, w)):
            if givenmap.get(x,y)==y and x not in lmap.keys() and y not in lmap.values():
                lmap[x]=y
            else:
                possible = False
                break

        if possible and len(lmap):
            mapping.append(lmap)
    return mapping

def getWordLengthMap(textualContent):
    wordLengthMap=dict()
    for w in textualContent.split():
        v = w.strip().lower()
        k = len(v)
        if wordLengthMap.has_key(k):
            wordLengthMap.get(k).append(v)
        else:
            wordLengthMap[k]=[v]
    return wordLengthMap

dictFileName = "dictionary.lst"
cryptText="""lhpohes gvjhe ztytwojmmtel lgsfcgver segpsltjyl vftstelc djfl rml catrroel jscvjqjyfo
             mjlesl lcjmmfqe egvj gsfyhtyq sjfgver csfaotyq lfxtyq gjywplesl lxljm dxcel mpyctyq 
             ztytwojmmtelel mfcgv spres mjm psgvty bfml ofle mjlc dtc tygfycfctjy dfsyl zpygvel
             csfao yealqsjpml atyl lgsjql qyfsotelc fseyf ojllel gjzmselltyq wpyhtelc zpltgl weygel
             afyher rstnesl aefleo rtyhes mvflel yphe rstnes qojder dtwwer lojml mfcgvel reocfl
             djzder djpygtyq gstmmoeafsel reg cpdel qspyqe mflctel csflvtyq vfcl avfghtyq vftsdfool
             mzer sfmtyq rsjye wjjol psol mplvtyq catrroe mvfqe lgseey leqzeycer wjseqsjpyrer lmjtoes 
             msjwtoel docl djpyger cjpstlcl goefy gojddesl mjrl qjddoe gjy gpdtyql lyftotyq rjayojfr 
             swgl vjle atrqec gjzmfgces frfl qotcgver gspzd zftodjzdl lyfsh"""
dictWordMap = getWordLengthMap(open(dictFileName).read())
cryptWordMap = getWordLengthMap(cryptText)
keys = cryptWordMap.keys()
keys.sort(reverse=True)

currMap={}
for k in keys:
    for w in cryptWordMap[k]:
        if len(set(w)-set(currMap.keys()))>0:
            mapping=possibleMatch(w, dictWordMap, currMap)
            if len(mapping)==1:
                currMap.update(mapping[0])
            if len(currMap)==26:break
    if len(currMap)==26: break

print " ".join("".join([currMap[l] for l in w]) for w in cryptText.split())