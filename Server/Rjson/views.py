from django import forms
from django.http import HttpResponse
import json
from .models import User,Other,Introduced
from django.shortcuts import render,redirect
from django.contrib import auth
class UserFrom(forms.Form):
    username=forms.CharField(label='用户名',max_length=100)
    pasword=forms.CharField(label='密码',widget=forms.PasswordInput)


#注册
def regist(request):
    if request.method=="POST":
        uf=UserFrom(request.POST)
        if uf.is_valid():
            #获取表单数据
            username=uf.cleaned_data['username']
            password=uf.cleaned_data['password']
            #添加到数据库
            registAdd=User.objects.create(username=username,password=password)
            if registAdd==False:
                return render(request,'index.html',{'registAdd':registAdd})
            else:
                return render(request,'index.html',{'registAdd':registAdd})
        else:
            uf=UserFrom()
        return render(request,'index.html',{'uf':uf})

#登录
def login(request):
    if request.method == 'POST':
        username = request.POST.get('username')
        password = request.POST.get('password')
        re = auth.authenticate(username=username, password=password)  # 用户认证
        if re is not None:  # 如果数据库里有记录（即与数据库里的数据相匹配或者对应或者符合）
            auth.login(request, re)  # 登陆成功
            return redirect('/', {'user': re})  # 跳转--redirect指从一个旧的url转到一个新的url
        else:  # 数据库里不存在与之对应的数据
            return render(request, 'login.html', {'login_error': '用户名或密码错误'})  # 注册失败
    return render(request, 'login.html')

#退出
def logout(request):
    auth.logout(request)
    return render(request, 'index.html')
#资讯
def news(request):
    id = request.GET.get('id')
    print(id)
    school=Introduced.objects.get(id=id)
    print(school.jianjie)
    data={"id":school.name,
            "word":school.link,
             "title":school.jianjie
             }
    return HttpResponse(json.dumps(data))
#院校信息
def information(request):
    id=request.GET.get('id')
    word=request.GET.get('word')
    count=int(request.GET.get('count'))
    title=Other.objects.filter(name=str(id)).filter(word=word)[count:count+5]
    print(title)
    data={}
    for t in title:
        data['title'+str(count)]=t.title
        print(t.title)
        count=count+1
    return HttpResponse(json.dumps(data))
#具体信息

def search(request):
    pass