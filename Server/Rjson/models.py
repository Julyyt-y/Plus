# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey has `on_delete` set to the desired behavior.
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models

#院校简介
class Introduced(models.Model):
    id = models.SmallIntegerField(primary_key=True)
    name = models.CharField(max_length=20, blank=True, null=True)
    link = models.CharField(max_length=50, blank=True, null=True)
    jianjie = models.TextField(blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'introduced'


#资讯
class News(models.Model):
    id = models.SmallIntegerField(primary_key=True)
    title = models.CharField(max_length=150, blank=True, null=True)
    news = models.TextField(blank=True, null=True)
    time = models.CharField(max_length=50, blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'news'

#院校信息
class Other(models.Model):
    id = models.SmallIntegerField(primary_key=True)
    name = models.CharField(max_length=6)
    word = models.CharField(max_length=20)
    title = models.CharField(max_length=100)
    content = models.TextField(blank=True, null=True)
    time = models.CharField(max_length=20, blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'other'

#用户
class User(models.Model):
    id = models.SmallIntegerField(primary_key=True)
    username = models.CharField(max_length=50)
    password = models.CharField(max_length=50)
    email = models.CharField(max_length=50, blank=True, null=True)
    news = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'user'

    def __unicode__(self):
        # 在Python3中使用 def __str__(self):
        return self.username
