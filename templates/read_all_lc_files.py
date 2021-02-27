import os
from os import listdir
from os.path import isfile, join
from pathlib import Path

cwd = os.getcwd()
parent_path = Path(cwd).parent
lc_file_path = os.path.join(parent_path, "_leetcode")

files = ["[{filename_prefix}]({{{{ site.baseurl }}}}{{% link _leetcode/{filename} %}}) \n".format(
    filename_prefix=f.split(".")[0] + "." + f.split(".")[1],
    filename=f,
)
for f in listdir(lc_file_path) 
if isfile(join(lc_file_path, f)) and ".md" in f and f != "0. 前奏.md" and f != "all_files.md"]


files.sort()

# output path
output = os.path.join(parent_path, "_leetcode", "all_files.md")


a_file = open(output, "w")
a_file.writelines(files)
a_file.close()