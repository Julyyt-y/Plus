from django import forms
from django.http import HttpResponse
import json
from .models import Info,Classification,School,SpecificNews,Profile,ProfileNews
from django.contrib.auth import authenticate, login
from .forms import RegistrationForm, LoginForm,Info_from
from django.shortcuts import render,redirect
from django.contrib import auth
class UserFrom(forms.Form):
    username=forms.CharField(label='用户名',max_length=100)
    pasword=forms.CharField(label='密码',widget=forms.PasswordInput)
#注册
def register(request):
    if request.method == "POST":
        user_form = RegistrationForm(request.POST)
        new_info_form = Info_from(request.POST)
        if user_form.is_valid() and new_info_form.is_valid():
            new_user = user_form.save(commit=False)
            new_user.set_password(user_form.cleaned_data["password"])
            new_user.save()
            message = "注册成功，请您进行登录"
            new_info = new_info_form.save(commit=False)
            new_info.user = new_user
            new_info.save()
            return render(request, "register.html", {"message": message})
        else:
            message = user_form.errors
            return render(request, "register.html", {"message": message})
    else:
        user_form = RegistrationForm()
        return render(request, "register.html", {"form": user_form})
#登录
def login(request):
    if request.method == "GET":
        login_form = LoginForm()
        return render(request, "login.html")
    if request.method == "POST":
        login_form = LoginForm(request.POST)
        if login_form.is_valid():
            cd = login_form.cleaned_data
            user = authenticate(username=cd["username"], password=cd["password"])
            if user:
                login(request, user)
                return redirect('/index')  # 重定向
            else:
                message = "登录失败，用户名或密码错误，请检查您的用户名密码"
                return render(request, "login.html", {"message": message})
    return redirect('/login')


#退出
def logout(request):
    auth.logout(request)
    return render(request, 'index.html')

#资讯
def news(request):
    id = request.GET.get('id')
    news=request.GET.get('news')
    status="fail"
    data={}
    if id is None:
        renews = {
            "data": data,
            "status": status,
        }
        return HttpResponse(json.dumps(renews))
    if id and news=='True':
        id=int(id)
        try:
            new=SpecificNews.objects.get(n_id=id)
            data = {
                "id": new.id,
                "content":new.news
            }
            status = "ok"
        except SpecificNews.DoesNotExist:
            print("该链接不存在")
            data={}
            status="ok"
    elif id and news is None:
        id=int(id)
        try:
            news = ProfileNews.objects.all()[id:id + 20]
        except Exception as e:
            news = ProfileNews.objects.all()[id:]
        data = [{
                "id":new.id,
                "title":new.title,
                "time":new.time
            }for new in news]
        status="ok"
    else:
        data={}
    renews={
            "data":data,
            "status": status,
        }
    return HttpResponse(json.dumps(renews))

#院校信息
def information(request):
    id=request.GET.get('id')
    school=request.GET.get('school')
    classification=request.GET.get('classification')
    print(classification,school)
    status = "fail"
    data={}
    reinfo = {
        "data": data,
        "status": status,
    }
    if school=="all":
        reinfo["data"]=[{"id":school.id,
                 "school":school.name} for school in School.objects.all()]
        reinfo["status"]="ok"
        return HttpResponse(json.dumps(reinfo))
    if classification=="all" and school:

        reinfo["data"] = [{"id": classification.id,
                            "classification": classification.name} for classification in Classification.objects.all()]
        reinfo["status"] = "ok"
        return HttpResponse(json.dumps(reinfo))
    if school and classification:
        try:
            infos=Profile.objects.filter(school=school).filter(classification=classification)[id:id+20]
        except:
            try:
               infos =Profile.objects.filter(school=school).filter(classification=classification)[id:]
            except:
                infos=None
        if infos:
            reinfo["data"] =[{
            "id":info.id,
            "school":info.school,
            "classification":info.classification,
            "title":info.title,
            "time":info.time
        } for info in infos]
        reinfo["status"] = "ok"
        return HttpResponse(json.dumps(reinfo))
    if id:
        id=int(id)
        try:
            info=Info.objects.get(id=id)
        except:
            info=None
        if info:
            reinfo["data"]={"id":info.id,
                            "content":info.content}
        return  HttpResponse(json.dumps(reinfo))
    return HttpResponse(json.dumps(reinfo))

#查找
def search(request):
    pass




