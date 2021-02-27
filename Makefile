serve:
	bundle exec jekyll serve --trace

date:
	date "+%Y-%m-%d %H:%M:%S"

lc:
	python3 ./templates/create_file.py

ood:
	python3 ./templates/create_file_ood.py

list-lc:
	python3 ./templates/read_all_lc_files.py