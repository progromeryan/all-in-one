import json

f = open('problems.json')

problems = json.load(f)

print(len(problems))

# for problem in problems:
#     print(problem["stat"]["question__title"])
# frontend_question_id
