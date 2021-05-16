# **dict**

A dictionary or other mapping object used to store an object's (writable) attributes

```python
class MyClass(object):
    class_var = 1

    def __init__(self, i_var):
        self.i_var = i_var

foo = MyClass(2)
bar = MyClass(3)

print MyClass.__dict__
print foo.__dict__
print bar.__dict__
```

```
{'__module__': '__main__', 'class_var': 1, '__dict__': <attribute '__dict__' of 'MyClass' objects>, '__weakref__': <attribute '__weakref__' of 'MyClass' objects>, '__doc__': None, '__init__': <function __init__ at 0x0000000004E55CF8>}

{'i_var': 2}

{'i_var': 3}
```
