# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html
import pymysql
from kaoyan import settings
from logging import log
from kaoyan.items import KaoyanItem,otherItem
class KaoyanPipeline(object):
    def process_item(self, item, spider):
        return item

class DBPipeline(object):
    def __init__(self):
        # 连接数据库
        self.connect = pymysql.connect(
            host=settings.MYSQL_HOST,
            db=settings.MYSQL_DBNAME,
            user=settings.MYSQL_USER,
            passwd=settings.MYSQL_PASSWD,
            charset='utf8',
            use_unicode=True)

        # 通过cursor执行增删查改
        self.cursor = self.connect.cursor()

    def process_item(self, item, spider):
        try:
            # 插入数据
            if type(item) == KaoyanItem:
                self.cursor.execute(
                    """insert into links(name,link,jianjie)
                    value (%s, %s,%s)""",
                    (item['name'],
                     item['link'],
                     item['jianjie']
                     ))
            elif type(item) == otherItem:
                self.cursor.execute(
                    """insert into other(id,word,title,content,time)
                    value (%s,%s,%s,%s,%s)""",
                    (item['id'],
                     item['word'],
                     item['title'],
                     item['content'],
                     item['time']
                     ))
                # 提交sql语句
            self.connect.commit()
        except Exception as error:
        # 出现错误时打印错误日志
            log(error)
        return item

    def close_spider(self, spider):
        # 关闭游标和连接
        self.cursor.close()
        self.connect.close()