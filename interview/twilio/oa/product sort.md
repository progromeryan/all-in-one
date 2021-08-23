![img](../assets/product_sort.png)
![img](../assets/product_sort2.png)

```python
def productSort(lst: list) -> list:
    if not lst:
        return lst
    count = Counter(lst)
    sorted_list = sorted(lst, key=lambda item: (count[item], item))
    return sorted_list
```
