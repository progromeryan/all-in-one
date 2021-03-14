import os
import argparse

from datetime import datetime

parser = argparse.ArgumentParser(description='lc file name')

parser.add_argument('--name', action="store", dest='name', default="leetcode")

args = parser.parse_args()

filename = args.name

cwd = os.getcwd()
lc_file_path = os.path.join(cwd, "templates", "leetcode.md")

# date
today = datetime.now()
tt = today.strftime("%Y-%m-%d %H:%M:%S")

a_file = open(lc_file_path, "r")
list_of_lines = a_file.readlines()
list_of_lines[1] = "title: {title}\n".format(title=filename)
list_of_lines[2] = "order: {order}\n".format(order=filename.split(".")[0])
list_of_lines[3] = "date: {date}\n".format(date=tt)
# output
output = os.path.join(cwd, "_leetcode", filename+".md")


a_file = open(output, "w")
a_file.writelines(list_of_lines)
a_file.close()