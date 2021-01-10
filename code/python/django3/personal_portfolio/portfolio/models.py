from django.db import models


class Project(models.Model):
    # check django model field document
    title = models.CharField(max_length=100)
    description = models.CharField(max_length=250)
    image = models.ImageField()  # need to install pillow
    url = models.URLField(blank=True)

    def __str__(self):
        return self.title
