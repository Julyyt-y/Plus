# -*- coding: utf-8 -*-
import scrapy
import pymysql

class YzspiderSpider(scrapy.Spider):
    name = 'yzspider'
    allowed_domains = ['yz.kaoyan.com']
    words=['jianjie','jianzhang','zhuanye',
           'shumu','dagang','chengji','fenshuxian',
           'luqu','zhenti','baolubi','tuimian',
           'kaochang','xuejiangzhu','lianxi','jieshao']


    start_urls = []

    def parse(self, response):
        pass
