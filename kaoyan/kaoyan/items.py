# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class KaoyanItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    name=scrapy.Field()
    link=scrapy.Field()
    content=scrapy.Field()

#其他信息
class otherItem(scrapy.Item):
    school=scrapy.Field()
    classification=scrapy.Field()
    title=scrapy.Field()
    content=scrapy.Field()
    time=scrapy.Field()
#资讯
class newsItem(scrapy.Item):
    title=scrapy.Field()
    news=scrapy.Field()
    time=scrapy.Field()


#具体院校信息（文本）
class infoItem(scrapy.Item):
    p_id=scrapy.Field()
    content=scrapy.Field()
#具体院校信息（图片）
class imageItem(scrapy.Item):
    p_id=scrapy.Field()
    content=scrapy.Field()
#分类
class classification(scrapy.Item):
    c_id=scrapy.Field()
    name=scrapy.Field()
#概略
class profileItem(scrapy.Item):
    p_id=scrapy.Field()
    s_id=scrapy.Field()
    c_id=scrapy.Field()
    title=scrapy.Field()
    time=scrapy.Field()
#概略消息
class profileNewsItem(scrapy.Item):
    n_id=scrapy.Field()
    title=scrapy.Field()
    time=scrapy.Field()
#学校
class schoolItem(scrapy.Item):
    s_id=scrapy.Field()
    name=scrapy.Field()
    link=scrapy.Field()
#具体消息
class specificNewsItem(scrapy.Item):
    n_id=scrapy.Field()
    news=scrapy.Field()

