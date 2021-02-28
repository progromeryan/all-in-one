import os

from datetime import datetime

cwd = os.getcwd()
lc_file_path = os.path.join(cwd, "templates", "system_design.md")

# date
today = datetime.now()
tt = today.strftime("%Y-%m-%d %H:%M:%S")

a_file = open(lc_file_path, "r")
list_of_lines = a_file.readlines()
list_of_lines[3] = "date: {date}\n".format(date=tt)

# output path
output = os.path.join(cwd, "_system_design", "system_design.md")


a_file = open(output, "w")
a_file.writelines(list_of_lines)
a_file.close()