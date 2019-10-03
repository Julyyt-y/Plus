# -*- coding: utf-8 -*-
import scrapy
from kaoyan.items import KaoyanItem,otherItem


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
                urls = item['link'] = part.xpath('.//@href').extract()
                for url in urls:
                    for word in self.words:
                        if word == 'jianjie' :
                            request=scrapy.Request(url + word + '/', callback=self.jianjie)
                            request.meta['item'] = item
                            yield request
                        else:
                            for index in self.indexs:
                                request=scrapy.Request(url + word + '/'+index, callback=self.othernews)
                                request.meta['id']=id
                                request.meta['word']=word
                                yield request



    #简介
    def jianjie(self, response):
        item=response.meta['item']
        str=''.join(response.xpath('//div[@class="articleCon"]/p//text()').extract())
        item['jianjie']=str.strip()
        yield item

    #其他信息
    def othernews(self, response):
        item = otherItem()
        item['id'] = response.meta['id']
        item['word'] = response.meta['word']
        allnew = response.xpath('//ul[@class="subList"]/li')
        item['title'] = allnew.xpath('.//a/text()').extract()
        item['time'] = allnew.xpath('.//span/text()').extract()
        url = allnew.xpath('.//a/@href').extract()[0]
        print(url)
        request = scrapy.Request(url, callback=self.downlond)
        request.meta['item'] = item
        yield request

    def downlond(self,response):
        pass