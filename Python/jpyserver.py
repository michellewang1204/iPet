import jpysocket
import socket
import pandas as pd
import joblib
import warnings
import re
from googletrans import Translator
import keyboard

def predictResult(text): 
    warnings.filterwarnings('ignore')
    # ====== Translate =====
    translater = Translator()
    text = re.sub('https?://[^\s<>"]+|www\.[^\s<>"]+',' ',text)
    trans = translater.translate(str(text),dest='en', src='auto')
    text = trans.text
    text = re.sub('[\u4e00-\u9fa5]', '', text)

    # ====== Feature Catch =====
    features = ['WILL', 'NOT', 'DOG', '~', 'WAS', 'AT', 'HAVE', 'THIS', 'WITH', 'ON', 'SO', 'WHO', '#', 'THERE', 'HAS', 'TAKE', 'VERY', 'DO', 'ME', 'WANT', 'IF', 'GO', 'AS', '...', 'LIKE', 'HE', 'BUT', 'DOGS', 'SEE', 'YOUR', 'REALLY', 'SHARK', 'WHEN', 'AM', 'OUT', 'GOOD', 'LITTLE', 'HELP', 'PLEASE', 'BY', 'HAPPY', 'NEW', 'THANK', 'ALSO', 'EAT', 'BEEN', 'ASK', 'AFTER', 'JUST', 'TIME', 'HOME', 'NO', 'UP', 'ALL', 'BECAUSE', 'TWO', 'CARTILAGE', 'FIND', 'LOVE', 'OR', 'ABOUT', 'TOO', 'CUTE', 'BACK', 'HIM', 'ONLY', 'MA', 'EVERYONE', 'WHAT', 'SISTER', 'FROM', 'MORE', 'MOTHER', 'LONG', 'FIRST', 'DAY', 'BABY', 'COME', 'ONE', 'OWNER', 'STILL', 'EVERY', 'PEOPLE', 'WE', 'HER', 'HIS', 'GET', 'POWDER', 'FAMILY', 'HOW', 'PET', "DON'T", 'SHE', 'INTERSECTION', 'BONE', 'SAID', 'SLEEP', 'KNOW', 'HUSKY', "IT'S", 'LET', 'N’T',
            'THEY', 'YEARS', 'GIVE', 'FOUND', "CAN'T", 'ANIMAL', 'OLD', 'FRIENDS', 'YUAN', 'MAKE', 'DAD', 'PLAY', 'CHILD', 'BIG', 'OUR', 'SMALL', 'BLACK', 'MANY', 'SHOULD', 'IT.', 'BROTHER', 'DID', 'HAIR', 'DOES', 'FESTIVAL', 'SUPER', 'THREE', 'AN', '2', 'ESSENCE', 'NEED', 'CONTACT', 'LOOK', 'BUY', 'GOING', 'SAY', 'FISH', 'TODAY', 'TOOK', 'LOST', 'MAO', 'PRODUCT', 'THAN', 'AFRAID', 'CHAI', 'WOULD', 'MI', 'CARE', 'MONTHS', 'TODAY,', '4', 'NO.', 'MID', 'MUST', 'TAIPEI', 'SAW', '-AUTUMN', 'OTHER', 'IT,', '3', 'WHITE', 'YEAR', 'HOUSE', 'ROAD', 'DISTRICT,', '100', 'ANYONE', 'CHILDREN', 'WELCOME', 'HOPE', 'SUCH', 'OPEN', 'YUAN,', '1', 'ROAD,', 'BITE', 'COLLAGEN', 'WHY', 'DOWN', '5', 'LIFE', 'GROUP', 'SOME', 'MEDICAL', 'LOT', 'TIGER', 'CONTAINS', 'SHARE', 'WENT', 'LOOKING', 'SUITABLE', 'FEEL', 'US', 'SKIN', 'ANY', 'PRODUCTS', 'HOME.', 'HERE', 'TIME,', 'DAYS', 'BEFORE', 'THEIR', 'NOW', 'SLEEPING', '&', 'JI', 'SAME', 'NEXT', 'SHIBA', '-OLD', '+', 'WITHOUT', 'ADOPT', 'THEN', 'GIRL', 'THEM', "I'M", 'HAD', 'DOU', 'CITY', 'FEW', 'NATURAL', 'WALK', 'DON’T', 'CAME', 'HELLO', 'LOVES', 'PAY', 'VARIOUS', 'CAT', 'MAY', 'MOST', 'WATER', 'FOOD', 'FINALLY', 'RUN', "DOG'S", 'ALWAYS', 'HEALTH', 'SOMEONE', 'WERE', 'NIGHT', 'ADOPTION', 'HAIRY', 'EYES', 'BIRTHDAY', 'FACE', 'MEAT', 'BALL', 'DOCTOR', 'THINK', 'WORK', 'PHOTOS', 'CALL', 'WILLING', 'SEEING', 'WHETHER', 'WAIT', '￼', '-YEAR', 'FA', 'ASKED', 'EVEN', 'PRICE', 'TAKING', 'BIT']
    result = ['INTP']
    for k in features:
        target = text.upper()
        result.append(target.count(k))

    # ====== Load Model ======
    Personalities = [0 for _ in range(16)] #models
    Personal = ['INFP','INFJ','INTP','INTJ','ENTP','ENFP','ISTP','ISFP','ENTJ','ISTJ','ENFJ','ISFJ','ESTP','ESFP','ESFJ','ESTJ']
    for i in range(0,16):
        # Personalities[i] = joblib.load('~/jpysocket/default_models/random_forest'+Personal[i]+'.joblib')
        Personalities[i] = joblib.load('./default_models/random_forest'+Personal[i]+'.joblib')
    # ========== Predict ==========
    a = [[0 for _ in range(2)] for _ in range(16)]
    result.pop(0)
    x=[result]
    # y = result['type'].tolist()
    probability = [0 for _ in range(16)]
    num = 0
    for j in Personalities:
        y_pred1 = j.predict_proba(x)
        probability[num] = round(y_pred1[0][1],2)
        num = num + 1

    # ========== Sort Result ===========
    for l in range(0,16):
        a[l][0] = Personal[l]
        a[l][1] = round(result[l],3)
    a.sort(key = lambda s: s[1],reverse=True)
    ans = a[0][0]
    return ans


host='140.127.220.78' #Host Name
port=8000    #Port Number
s=socket.socket() #Create Socket
s.bind((host,port)) #Bind Port And Host
while True:
    s.listen(5) #Socket is Listening
    # print("Socket Is Listening....")
    connection,address=s.accept() #Accept the Connection
    print("Connected To ",address)

    msgsend=jpysocket.jpyencode("Thank You For Connecting.") #Encript The Msg
    connection.send(msgsend) #Send Msg

    # accept & predict
    msgrecv=connection.recv(1024)  #Recieve ms
    print(msgrecv)
    while(len(msgrecv)>0):
        msgrecv = msgrecv[1:]
        try:
            msgrecv=jpysocket.jpydecode(msgrecv)
            print("From Client: ",msgrecv)
            break
        except:
            pass

    result = predictResult(msgrecv)
    msgsend=jpysocket.jpyencode(result) #set The Msg
    connection.send(msgsend) #Send Msg
    print("To Client: ",msgsend)
    # s.close() #Close connection
    # print("Connection Closed.")
