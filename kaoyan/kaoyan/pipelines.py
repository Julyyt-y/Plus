# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html
import pymysql
from kaoyan import settings
from logging import log
from kaoyan.items import KaoyanItem,otherItem,newsItem
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
        self.cursor.execute("""
            drop table if exists school
        """)
        self.cursor.execute("""
            drop table if exists classification
        """)
        self.cursor.execute("""
            drop table if exists profile
                """)
        self.cursor.execute("""
            drop table if exists info
                """)
        self.cursor.execute("""
            drop table if exists profile_news
        """)
        self.cursor.execute("""
            drop table if exists specific_news
                """)
        self.cursor.execute("""
            create table school(
            s_id smallint primary key auto_increment,
            name char(20),
            link char(50));
        """)
        self.cursor.execute("""
            create table classification(
            c_id smallint primary key auto_increment,
            name char(20));
        """)
        self.cursor.execute("""
            create table profile(
            p_id int primary key auto_increment ,
            school char not null,                  
            classification char not null,           
            title varchar(100) not null,
            time char(20));
        """) #s_id
            #c_id
        self.cursor.execute("""
        create table info(
        p_id int primary key auto_increment,
        content text);
        """)
        self.cursor.execute("""
            create table profile_news(
            n_id smallint primary key auto_increment,
            title varchar(150),
            time varchar(50));
        """)
        self.cursor.execute("""
        create table specific_news(
        n_id smallint primary key auto_increment,
        news text);
        """)
    def process_item(self, item, spider):
        try:
            # 插入数据
            if type(item) == KaoyanItem:
                name=item['name']
                link=item['link']
                self.cursor.execute(
                    """insert into school(name,link)
                    value (%s,%s)""",
                    (item['name'],
                     item['link'],
                     ))
                self.cursor.execute(
                    """insert into profile(school,classification,title,time)
                    value (%s, %s,%s,%s)""",
                    (item['name'],
                     "jianjie",
                     item['name']+"简介",
                     "",
                     ))
                self.cursor.execute(
                    """insert into info(content)
                    value (%s)""",
                    (
                     item['content'],
                     ))
            elif type(item) == otherItem:
                self.cursor.execute(
                    """insert into profile(school,classification,title,time)
                    value (%s,%s,%s,%s)""",
                    (item['name'],
                     item['classification'],
                     item['title'],
                     item['time'],
                     ))
                self.cursor.execute(
                    """insert into info(content)
                    value (%s)""",
                    (
                        item['content'],
                    ))
            elif type(item)==newsItem:
                self.cursor.execute(
                    """insert into profile_news(title,time)
                    value (%s,%s)""",
                    (
                     item['title'],
                     item['time'],
                     ))
                self.cursor.execute(
                    """insert into specific_news(content)
                    value (%s)""",
                    (
                        item['news'],
                    ))
                # 提交sql语句
            self.connect.commit()
        except Exception as error:
        # 出现错误时打印错误日志
            print("错误")

        return item

    def close_spider(self, spider):
        # 关闭游标和连接
        self.cursor.close()
        self.connect.close()
