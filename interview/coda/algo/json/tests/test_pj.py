import unittest

import pj


class TestStringMethods(unittest.TestCase):
    def test_basic(self):
        self.assertEqual(pj.from_string('1'), 1)
        self.assertEqual(pj.from_string('true'), True)
        self.assertEqual(pj.from_string('"foo"'), 'foo')
        self.assertEqual(pj.from_string('null'), None)
        self.assertEqual(pj.from_string('[]'), [])
        self.assertEqual(pj.from_string('[1,2,"three"]'), [1, 2, "three"])

    def test_empty_object(self):
        self.assertEqual(pj.from_string('{}'), {})

    def test_basic_object(self):
        self.assertEqual(pj.from_string('{"foo":"bar"}'), {"foo": "bar"})

    def test_basic_number(self):
        self.assertEqual(pj.from_string('{"foo":1}'), {"foo": 1})

    def test_empty_array(self):
        self.assertEqual(pj.from_string('{"foo": []}'), {"foo": []})

    def test_basic_array(self):
        self.assertEqual(pj.from_string('{"foo":[1,2,"three"]}'), {"foo": [1, 2, "three"]})

    def test_nested_object(self):
        self.assertEqual(pj.from_string('{"foo":{"bar":2}}'), {"foo": {"bar": 2}})

    def test_true(self):
        self.assertEqual(pj.from_string('{"foo":true}'), {"foo": True})

    def test_false(self):
        self.assertEqual(pj.from_string('{"foo":false}'), {"foo": False})

    def test_null(self):
        self.assertEqual(pj.from_string('{"foo":null}'), {"foo": None})

    def test_basic_whitespace(self):
        self.assertEqual(pj.from_string('{ "foo" : [1, 2, "three"] }'), {"foo": [1, 2, "three"]})


if __name__ == '__main__':
    unittest.main()
