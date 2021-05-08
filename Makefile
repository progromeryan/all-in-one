serve:
	bundle exec jekyll serve --trace

date:
	date "+%Y-%m-%d %H:%M:%S"

# NAME="HELLO" make lc
check-lc-name:
ifndef name
	$(error name is required for the command)
endif

lc: check-lc-name
	python3 ./templates/create_file.py --name='${name}'

ood:
	python3 ./templates/create_file_ood.py

list-lc: 
	python3 ./templates/read_all_lc_files.py

sd:
	python3 ./templates/create_file_system_design.py
