# -*- coding: utf-8 -*-
import scrapy
from kaoyan.items import KaoyanItem, otherItem,schoolItem,specificNewsItem,profileNewsItem,profileItem,infoItem,classification
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
            name = 0
            for part in waper:
                name = name + 1
                item = schoolItem()
                item['name'] = part.xpath('.//text()').extract()
                url = item['link'] = part.xpath('.//@href').extract()[0]
                yield item
                wordint=0
                for word in self.words:
                    wordint=wordint+1
                    if word == 'jianjie':
                        item_p=profileItem()
                        item_p['s_id']=name
                        item_p['c_id']=1
                        item_p['title']=item['name']+'简介'
                        item_p['time']=''
                        request=scrapy.Request(url + word + '/', callback=self.jianjie)
                        request.meta['handle_httpstatus_list']= range(300,600)
                        yield item
                        yield request
                    else:
                        for index in self.indexs:
                            request=scrapy.Request(url + word + '/'+index, callback=self.othernews)
                            request.meta['name']=name
                            request.meta['word']=wordint
                            yield request

# 简介
    def jianjie(self, response):
        item=infoItem()
        if response.status==range(300,600):
            item['content']=''
            print('cuouwu:'+str(404)+'\n')
        else:
            str = ''.join(response.xpath('//div[@class="articleCon"]/p//text()').extract())
            item['content'] = str.strip()
            #提取学校简介
        yield item

# 其他信息
    def othernews(self, response):
        allnew = response.xpath('//ul[@class="subList"]/li')
        for new in allnew:
            item = profileItem()
            item['s_id'] = response.meta['name']
            item['c_id'] = response.meta['word']
            item['title'] = new.xpath('.//a/text()').extract()[0]
            item['time'] = new.xpath('.//span/text()').extract()[0]
            url= new.xpath('.//a/@href').extract()[0]
            request = scrapy.Request(url, callback=self.downlond)
            yield item
            yield request

    #下载非download的链接
    def downlond(self, response):
        item=response.meta['item']
        str = ''.join(response.xpath('//text()').extract()).strip()
        if str is not  '':
            if 'download' in response.url:
                partlink=response.xpath('//dl[@class="t_attachlist"]//@href').extract()[0]
                link='http://download.kaoyan.com'+partlink
                request=scrapy.Request(link,callback=self.downpdf)
                yield request
            else:
                articleCon=response.xpath('//div[@class="articleCon"]')[0]
                if articleCon.xpath('.//@src'):
                    str = ''
                    for land in articleCon.xpath('.//@src'):
                        sf = land.extract()
                        if (('.jpg' in sf) or ('.png' in sf)) :
                            str += ' ' + sf
                        else:
                            str+=' '+ articleCon.xpath('.//img').extract()[0]
                    item['content'] = str
                elif articleCon.xpath('.//@href'):
                    str=''
                    for land in articleCon.xpath('.//@href'):
                        sf=land.extract()
                        if  ('.pdf'in sf) or ('.docx' in sf) \
                                or ('.xlsx' in sf) or ('.doc' in sf):
                                str+=' '+sf
                    if str !='':
                        item['content']=str
                    else:
                        item['content'] = articleCon.extract()
                yield item

    #下载download链接
    def downpdf(self,response):
        item=infoItem()
        download_scan=response.xpath('//div[@class="download_scan"]//@action').extract()[0]
        item['content']=download_scan
        yield item
