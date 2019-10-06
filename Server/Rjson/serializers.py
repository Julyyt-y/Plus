from .models import User,Other,News,Introduced
from rest_framework import serializers

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model=User
        fields=('id','username','password','news')

class OtherSerializer(serializers.ModelSerializer):
    class Meta:
        model=Other
        fields=('id','name','word','title','content','time')

class NewsSerializer(serializers.ModelSerializer):
    class Meta:
        model=News
        fields=('id','title','new','time')

class IntroducedSerializer(serializers.ModelSerializer):
    class Meta:
        model=Introduced
        fields=('id','name','link','jianjie')