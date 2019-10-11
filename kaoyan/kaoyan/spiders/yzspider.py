# -*- coding: utf-8 -*-
import scrapy
import pymysql
from kaoyan.items import newsItem
class YzspiderSpider(scrapy.Spider):
    name = 'yzspider'
    allowed_domains = ['kaoyan.com']
    start_urls = ['http://www.kaoyan.com/beikao/xinlu/',
                  'http://www.kaoyan.com/beikao/xinli/',
                  'http://www.kaoyan.com/beikao/jingyan/',
                  'http://www.kaoyan.com/jingyan/']
    pages=['','index_2.html','index_3.html']
    def parse(self, response):
        for page in self.pages:
            yield  scrapy.Request(response.url+page,callback=self.news)

    def news(self,response):
        listnews=response.xpath('//ul[@class="list areaZslist"]//li')
        for news in listnews:
            new=newsItem()
            new['time']=news.xpath('.//span//text()').extract()[0]
            new['title']=news.xpath('.//a//text()').extract()[0]
            url=news.xpath('.//@href').extract()[0]
            request=scrapy.Request(url,callback=self.index)
            request.meta['new']=new
            yield request

    def index(self,response):
        new=response.meta['new']
        try:
            news=response.xpath('//div[@class="articleCon"]//p').extract()[0]
            new['news']=news
            yield new
        except:
            pass






