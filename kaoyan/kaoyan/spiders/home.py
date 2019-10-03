# -*- coding: utf-8 -*-
import scrapy
from kaoyan.items import KaoyanItem, otherItem


class HomeSpider(scrapy.Spider):
    name = 'home'
    # allowed_domains = ['kaoyan.com',
    #                    'yz.kaoyan.com']
    words = ['jianjie', 'jianzhang', 'zhuanye',
             'shumu', 'dagang', 'chengji', 'fenshuxian',
             'luqu', 'zhenti', 'baolubi', 'tuimian',
             'kaochang', 'xuejiangzhu', 'jieshao']
    indexs = ['', 'index_2.html', 'index_3.html']
    start_urls = ['http://www.kaoyan.com/zhaosheng/555ecd3175112.html',
                  ]

    def parse(self, response):
        if response.url == self.start_urls[0]:
            waper = response.xpath('//div[@class="articleCon"]//tbody//tr//a')
            id = 0
            for part in waper:
                id = id + 1
                item = KaoyanItem()
                item['name'] = part.xpath('.//text()').extract()
                url = item['link'] = part.xpath('.//@href').extract()[0]
                for word in self.words:
                    if word == 'jianjie':
                        request=scrapy.Request(url + word + '/', callback=self.jianjie)
                        request.meta['item'] = item
                        request.meta['id']=id
                        request.meta['handle_httpstatus_list']= range(300,600)
                        yield request
                    else:
                        for index in self.indexs:
                            request=scrapy.Request(url + word + '/'+index, callback=self.othernews)
                            request.meta['id']=id
                            request.meta['word']=word
                            yield request


# 简介
    def jianjie(self, response):
        print(response.meta['id'])
        item = response.meta['item']
        if response.status==range(300,600):
            item['jianjie']=''
            print('cuouwu:'+str(404)+'\n')
        else:
            str = ''.join(response.xpath('//div[@class="articleCon"]/p//text()').extract())
            item['jianjie'] = str.strip()
        yield item


# 其他信息
    def othernews(self, response):
        allnew = response.xpath('//ul[@class="subList"]/li')
        title = allnew.xpath('.//a/text()').extract()
        time = allnew.xpath('.//span/text()').extract()
        content = allnew.xpath('.//a/@href').extract()
        for m in zip(title,time,content):
            item = otherItem()
            item['id'] = response.meta['id']
            item['word'] = response.meta['word']
            item['title'] =m[0]
            item['time'] = m[1]
            item['content']=m[2]
        url = allnew.xpath('.//a/@href').extract()[0]
        request = scrapy.Request(url, callback=self.downlond)
        request.meta['item'] = item
        yield request
        yield item


    def downlond(self, response):
        pass
