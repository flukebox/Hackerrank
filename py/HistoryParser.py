import re
import sys
import csv
from os import listdir, walk
from os.path import isfile, join

mypath = sys.argv[1]

onlyfiles = [f for f in listdir(mypath) if isfile(join(mypath, f))]


fs = []
for (dirpath, dirnames, filenames) in walk(mypath):
    fs.extend(["%s/%s" % (dirpath, f) for f in filenames if not f.startswith(".") and not f.endswith(".zip")])
reg = r'[^.]*[.]'
rd = r'\d+-[a-zA-Z]+-\d+'

with open('daily_ticks.csv', 'wb') as tickfile:
    ticks = csv.writer(tickfile, delimiter=',')
    for f in fs:
        with open(f, "rb") as csvfile:
            csvreader = csv.reader(csvfile, delimiter=",")
            counter = 0
            for row in csvreader:
                if "No data available." in ",".join(row):
                    print "0@Counter=", counter, row
                    counter = 0
                    continue
                if len(row) in [1, 2, 4]:
                    counter += 1
                    if counter == 1:
                        c1 = (row[-1:][0]).split("(j")
                        #print "1@Counter=", counter, row
                    if counter == 2:
                        c2 = (row[-1:][0]).split(" ")
                        #print "2@Counter=", counter, row
                    if counter == 3:
                        c3 = (row[-1:][0]).split(":")
                        #print "3@Counter=", counter, row
                        if [len(c1), len(c2), len(c3)] == [2, 5, 2]:
                            #print c1[0][:-2], c1[1][1:-1], c2[2], c2[4], c3[1]
                            continue
                        else:
                            print "Error in =>", c1, c2, c3
                elif len(row) == 6 and [len(c1), len(c2), len(c3)] == [2, 5, 2]:
                    row.extend([c1[0][:-2].strip(), c1[1][1:-1], c2[2], c2[4], c3[1]])
                    row = [r.strip() for r in row]
                    ticks.writerow(row)
                    counter = 0
                elif len(row):
                    print len(row), row







