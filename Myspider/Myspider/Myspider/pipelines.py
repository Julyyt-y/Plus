# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html
import pymysql
from Myspider import settings
from logging import log
from Myspider.items import KaoyanItem,otherItem,newsItem
class MyspiderPipeline(object):
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
        self.cursor.execute("""
            drop table if exists introduced
        """)
        self.cursor.execute("""
            drop table if exists other
        """)
        self.cursor.execute("""
            drop table if exists news
        """)
        self.cursor.execute("""
            create table introduced(
            id smallint primary key auto_increment,
            name char(20),
            link varchar(50),
            jianjie text);
        """)
        self.cursor.execute("""
            create table other(
            id int primary key auto_increment ,
            name char not null,
            word char(20) not null,
            title varchar(100) not null,
            content text,
            time char(20));
        """)
        self.cursor.execute("""
            create table news(
            id smallint primary key auto_increment,
            title varchar(150),
            news text,
            time varchar(50));
        """)
    def process_item(self, item, spider):
        try:
            # 插入数据
            if type(item) == KaoyanItem:
                self.cursor.execute(
                    """insert into introduced(name,link,jianjie)
                    value (%s, %s,%s)""",
                    (item['name'],
                     item['link'],
                     item['jianjie']
                     ))
            elif type(item) == otherItem:
                self.cursor.execute(
                    """insert into other(name,word,title,content,time)
                    value (%s,%s,%s,%s,%s)""",
                    (item['name'],
                     item['word'],
                     item['title'],
                     item['content'],
                     item['time']
                     ))
            elif type(item)==newsItem:
                self.cursor.execute(
                    """insert into news(title,news,time)
                    value (%s,%s,%s)""",
                    (
                     item['title'],
                     item['news'],
                     item['time']
                     ))
                # 提交sql语句
            self.connect.commit()
        except Exception as error:
        # 出现错误时打印错误日志
            pass
        return item

    def close_spider(self, spider):
        # 关闭游标和连接
        self.cursor.close()
        self.connect.close()