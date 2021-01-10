from django.contrib import admin
from .models import Todo


# customize admin interface
class TodoAdmin(admin.ModelAdmin):
    readonly_fields = ('created',)


admin.site.register(Todo, TodoAdmin)
