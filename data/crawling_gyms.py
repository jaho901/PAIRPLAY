# -*- coding: utf-8 -*-
# 참고 사이트
# 1949773
# https://beomi.github.io/2017/07/05/HowToMakeWebCrawler-with-Multiprocess/

from urllib import request
import requests
import csv
import ssl
from bs4 import BeautifulSoup as bs
import selenium
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time
from multiprocessing import Pool
from csv import writer
import os
import random
import pprint
import json
# 크롤링에서 사용하는 변수들
headers = {'user-agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36'}

def writeCSV(list):
    list_title = ['id', 'category', 'address', 'bizhour', 'homepage', 'menu', 'name', 'parkingfee', 'skyPanorama', 'streetPanorama', 'img', 'phone', 'longitude', 'latitude', 'transport', 'near', 'hashtags', 'facilities']
    # ===================================================
    if os.path.isfile("gym_chungcheongbuk.csv"):
        pass
    else:
        with open('gym_chungcheongbuk.csv', 'w', newline='', encoding='utf-8-sig') as f_object:
            writer_object = writer(f_object)
            writer_object.writerow(list_title)
            f_object.close()

    with open('gym_chungcheongbuk.csv', 'a', newline='', encoding='utf-8-sig') as f_object:
        writer_object = writer(f_object)
        for data in list:
            writer_object.writerow(data)
        f_object.close()


busan = ['부산 금정구', '부산 중구', '부산 서구', '부산 동구', '부산 영도구', '부산 진구', '부산 동래구', '부산 남구', '부산 북구', '부산 해운대구', '부산 사하구', '부산 강서구', '부산 연제구', '부산 수영구', '부산 사상구', '부산 기장군']
seoul = ['서울 강남구', '서울 강동구', '서울 강북구', '서울 강서구', '서울 관악구', '서울 광진구', '서울 구로구', '서울 금천구', '서울 노원구', '서울 도봉구', '서울 동대문구', '서울 동작구', '서울 마포구', '서울 서대문구', '서울 서초구', '서울 성동구', '서울 성북구', '서울 송파구', '서울 양천구', '서울 영등포구', '서울 용산구', '서울 은평구', '서울 종로구', '서울 중구', '서울 중랑구']
gyeonggi = ['경기도 수원시', '경기도 용인시', '경기도 성남시', '경기도 부천시', '경기도 화성시', '경기도 안산시', '경기도 안양시', '경기도 평택시', '경기도 시흥시', '경기도 김포시', '경기도 광주시', '경기도 광명시', '경기도 군포시', '경기도 하남시', '경기도 오산시', '경기도 이천시', '경기도 안성시', '경기도 의왕시', '경기도 양평군', '경기도 여주시', '경기도 과천시', '경기도 북부', '경기도 고양시', '경기도 남양주시', '경기도 파주시', '경기도 의정부시', '경기도 양주시', '경기도 구리시', '경기도 포천시', '경기도 동두천시', '경기도 가평군', '경기도 연천군']
gangwon = ['강원도 춘천시', '강원도 원주시', '강원도 강릉시', '강원도 동해시', '강원도 태백시', '강원도 속초시', '강원도 삼척시', '강원도 홍청군', '강원도 횡성군', '강원도 영월군', '강원도 평창군', '강원도 정선군', '강원도 철원군', '강원도 화천군', '강원도 양구군', '강원도 인제군', '강원도 고성군', '강원도 양양군']
gyeongnam = ['경남 창원시', '경남 진주시', '경남 통영시', '경남 사천시', '경남 김해시', '경남 밀양시', '경남 거제시', '경남 양산시', '경남 의령군', '경남 함안군', '경남 창녕군', '경남 고성군', '경남 남해군', '경남 하동군', '경남 산청군', '경남 함양군', '경남 거창군', '경남 합천군']
gyeongbuk = ['경북 포항시', '경북 경주시', '경북 김천시', '경북 안동시', '경북 구미시', '경북 영주시', '경북 영천시', '경북 상주시', '경북 문경시', '경북 경산시', '경북 군위군', '경북 의성군', '경북 청송군', '경북 영양군', '경북 영덕군', '경북 청도군', '경북 고령군', '경북 성주군', '경북 칠곡군', '경북 예천군', '경북 봉화군', '경북 울진군', '경북 울릉군']
chungcheongnam = ['충남 천안시', '충남 공주시', '충남 보령시', '충남 아산시', '충남 서산시', '충남 논산시', '충남 계룡시', '충남 당진시', '충남 금산군', '충남 부여군', '충남 서천군', '충남 청양군', '충남 홍성군', '충남 예산군', '충남 태안군']
chungcheongbuk = ['충북 충주시', '충북 제천시', '충북 보은군', '충북 옥천군', '충북 영동군', '충북 증평군', '충북 진천군', '충북 괴산군', '충북 음성군', '충북 단양군']
jeollanam = ['전남 목포시', '전남 여수시', '전남 순천시', '전남 나주시', '전남 광양시', '전남 담양군', '전남 곡성군', '전남 구례군', '전남 고흥군', '전남 보성군', '전남 화순군', '전남 장흥군', '전남 강진군', '전남 해남군', '전남 영암군', '전남 무안군', '전남 함평군', '전남 영광군', '전남 장성군', '전남 완도군', '전남 진도군', '전남 신안군']
jeollabuk = ['전북 전주시', '전북 익산시', '전북 군산시', '전북 정읍시', '전북 김제시', '전북 남원시', '전북 완주군', '전북 고창군', '전북 부안군', '전북 임실군', '전북 순창군', '전북 진안군', '전북 무주군', '전북 장수군']
category = ['축구', '풋살', '농구', '야구', '볼링', '골프', '테니스', '헬스', '필라테스', '격투기', '수영', '클라이밍', '배드민턴']
region = busan + seoul + gyeonggi + gangwon + gyeongnam + gyeongbuk + chungcheongnam + chungcheongbuk + jeollanam + jeollabuk
def get_region():
    ad = []
    for i in chungcheongbuk:
        for j in category:
            ad.append(i + ' ' + j)
    return ad

def crawling(search):
    global headers
    total_data = []
    url = "https://map.naver.com/v5/api/search?query="+search+"&type=all&page=1&displayCount=20&lang=ko"
    response = requests.get(url, headers=headers)
    html = response.text
    soup = bs(html, 'html.parser')
    time.sleep(random.uniform(1, 2))
    jsonObject = json.loads(str(soup))
    try:
        jsonData = jsonObject["result"]["place"]["list"]
        big, small, category = search.split(' ')
        for i in range(len(jsonData)):
            # 주소
            address = jsonData[i]["address"]
            if small not in address:
                break
            # 운영일
            if jsonData[i]["bizhourInfo"] == None:
                bizhour = ['매일 00:00~24:00']
            else:
                bizhour = jsonData[i]["bizhourInfo"].split(" | ")
            # id
            id = jsonData[i]["id"]
            # homePage
            if jsonData[i]["homePage"]=="":
                homepage = "-"
            else:
                homepage = jsonData[i]["homePage"]
            # menu
            if jsonData[i]["menuInfo"]:
                menu = jsonData[i]["menuInfo"].split(" | ")
            else:
                menu = []
            # 이름
            name = jsonData[i]["name"]
            # 주차비용
            if jsonData[i]["parkingPrice"] == None:
                parkingfee = '무료'
            else:
                parkingfee = jsonData[i]["parkingPrice"]
            # skyPanorama
            if jsonData[i]["skyPanorama"]:
                skyPanorama = jsonData[i]["skyPanorama"]
            else:
                skyPanorama = "-"
            # streetPanorama
            if jsonData[i]["streetPanorama"]:
                streetPanorama = jsonData[i]["streetPanorama"]
            else:
                streetPanorama = "-"
            # Thumnail
            if jsonData[i]["thumUrl"]:
                img = jsonData[i]["thumUrl"]
            else:
                img = "-"
            # 전화번호
            if jsonData[i]["virtualTel"] == "":
                phone = "-"
            else:
                phone = jsonData[i]["virtualTel"]
            longitude = jsonData[i]["x"]
            latitude = jsonData[i]["y"]
            
            new_url = "https://pcmap.place.naver.com/place/"+id+"/home"
            new_response = requests.get(new_url, headers=headers)
            new_response.encoding = 'utf-8'
            new_html = new_response.text
            time.sleep(random.uniform(1, 2))
            new_soup = bs(new_html, 'html.parser')
            # 대중교통
            if new_soup.find('div', class_="_2P6sT"):
                trans = new_soup.find('div', class_="_2P6sT")        
                if trans:
                    no = ''
                    for i in range(len(trans.find_all('span', class_="_12Coj"))):
                        no += trans.find_all('span', class_="_12Coj")[i].contents[0]
                        no += ', '
                    sub = trans.find('strong').contents[0]
                    tra = str(trans).split("<!-- -->")
                    ent = tra[1].replace("</strong>", "")
                    dist = tra[2].replace("<em>", " ")
                    transport = no + sub + ent + dist + 'm'
                else:
                    transport = '-'
            else:
                transport = '-'
            # 찾아오는 길
            if new_soup.find('span', class_="WoYOw"):
                near = new_soup.find('span', class_="WoYOw").contents[0]
                if len(near) >= 50:
                    near = []
            else:
                near = []
            # 해쉬태그
            if new_soup.find_all("span", class_="_1RUzg"):
                hashtags = []
                for i in new_soup.find_all("span", class_="_1RUzg"):
                    hashtags.append(i.contents[2])
            else:
                hashtags = []
            # 편의시설
            if new_soup.find('div', class_="_1h3B_"):
                facilities = new_soup.find('div', class_="_1h3B_")
            else:
                facilities = []
            
            data = [id, category, address, bizhour, homepage, menu, name, parkingfee, skyPanorama, streetPanorama, img, phone, longitude, latitude, transport, near, hashtags, facilities]
            total_data.append(data)
        writeCSV(total_data)
    except:
        pass
    
   

if __name__=='__main__':
    start_time = time.time()
    pool = Pool(processes=16) # 8개의 프로세스를 사용합니다.
    pool.map(crawling, get_region())  # 2~3초 소요 500개 기준
    # speed_get_content(get_links()) # 단일 프로세스 12~14초 소요 500여개 기준
    print("--- %s seconds ---" % (time.time() - start_time))