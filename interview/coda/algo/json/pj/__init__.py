from .pre import pre_parse
from .parser import parse


def from_string(string):
    tokens = pre_parse(string)
    if len(tokens) == 0:
        raise Exception("Invalid")
    if len(tokens) == 1:
        return tokens[0]
    if tokens[0] == '[':
        res = []
        for token in tokens:
            if token == '[' or token == ',' or token == ']':
                continue
            else:
                res.append(token)
        return res
    return parse(tokens, is_root=True)[0]


def to_string(json):
    json_type = type(json)
    if json_type is dict:
        string = '{'
        dict_len = len(json)

        for i, (key, val) in enumerate(json.items()):
            string += '"{}": {}'.format(key, to_string(val))

            if i < dict_len - 1:
                string += ', '
            else:
                string += '}'

        return string
    elif json_type is list:
        string = '['
        list_len = len(json)

        for i, val in enumerate(json):
            string += to_string(val)

            if i < list_len - 1:
                string += ', '
            else:
                string += ']'

        return string
    elif json_type is str:
        return '"{}"'.format(json)
    elif json_type is bool:
        return 'true' if json else 'false'
    elif json_type is None:
        return 'null'

    return str(json)
