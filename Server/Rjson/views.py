from django.shortcuts import render, redirect
from .forms import RegistrationForm, LoginForm, Info_from
from django.contrib.auth import authenticate, login
# Create your views here.
#登录
def userlogin(request):
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