serve:
	bundle exec jekyll serve --trace

date:
	date "+%Y-%m-%d %H:%M:%S"

lc:
	python3 ./templates/create_file.py

ood:
	python3 ./templates/create_file_ood.py