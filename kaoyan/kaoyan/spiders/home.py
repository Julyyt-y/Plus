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
        for new in allnew:
            item = otherItem()
            item['id'] = response.meta['id']
            item['word'] = response.meta['word']
            item['title'] = new.xpath('.//a/text()').extract()[0]
            item['time'] = new.xpath('.//span/text()').extract()[0]
            url= new.xpath('.//a/@href').extract()[0]
            request = scrapy.Request(url, callback=self.downlond)
            request.meta['item'] = item
            yield request

    #下载非download的链接
    def downlond(self, response):
        item=response.meta['item']
        str = ''.join(response.xpath('//text()').extract()).strip()
        if str is not  '':
            #item['content']=response.url
            if 'download' in response.url:
                partlink=response.xpath('//dl[@class="t_attachlist"]//@href').extract()[0]
                link='http://download.kaoyan.com'+partlink
                request=scrapy.Request(link,callback=self.downpdf)
                request.meta['item']=item
                yield request
            else:
                articleCon=response.xpath('//div[@class="articleCon"]')[0]
                if articleCon.xpath('.//@src'):
                    #item['content'] = ' '.join(articleCon.xpath('.//@src').extract())
                    if '.jpg' or '.png' in articleCon.xpath('.//@src').extract()[0]:
                        item['content']=articleCon.xpath('.//@src').extract()[0]
                    else:
                        item['content']= articleCon.xpath('.//img').extract()[0]
                elif articleCon.xpath('.//@href'):
                    for land in articleCon.xpath('.//@href'):
                        sf=land.extract()
                        if  ('.pdf'in sf) or ('.docx' in sf) \
                                or ('.xlsx' in sf) or ('.doc' in sf):
                            item['content']+=' '+land.extract()[0]
                else:
                    item['content'] = articleCon.extract()
                yield item

    #下载download链接
    def downpdf(self,response):
        item=response.meta['item']
        download_scan=response.xpath('//div[@class="download_scan"]//@action').extract()[0]
        item=download_scan
        yield item
