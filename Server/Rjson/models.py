
from django.db import models

class Classification(models.Model):
    c_id = models.SmallIntegerField(primary_key=True)
    name = models.CharField(max_length=20, blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'classification'

class Info(models.Model):
    p_id = models.AutoField(primary_key=True)
    content = models.TextField(blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'info'

class Profile(models.Model):
    p_id = models.AutoField(primary_key=True)
    school = models.CharField(max_length=1)
    classification = models.CharField(max_length=1)
    title = models.CharField(max_length=100)
    time = models.CharField(max_length=20, blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'profile'



class ProfileNews(models.Model):
    n_id = models.SmallIntegerField(primary_key=True)
    title = models.CharField(max_length=150, blank=True, null=True)
    time = models.CharField(max_length=50, blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'profile_news'


class School(models.Model):
    s_id = models.SmallIntegerField(primary_key=True)
    name = models.CharField(max_length=20, blank=True, null=True)
    link = models.CharField(max_length=50, blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'school'



class SpecificNews(models.Model):
    n_id = models.SmallIntegerField(primary_key=True)
    news = models.TextField(blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'specific_news'



class user(models.Model):
    id = models.SmallIntegerField(primary_key=True)
    username = models.CharField(max_length=50)
    password = models.CharField(max_length=50)
    email = models.CharField(max_length=50, blank=True, null=True)
    news = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'user'
