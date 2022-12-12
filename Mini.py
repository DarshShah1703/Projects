from asyncio import sleep, wait_for
import ctypes
from gettext import gettext
from imp import source_from_cache
from logging.config import listen
from multiprocessing.connection import Listener, wait
from pydoc import plain
import re
from socket import timeout
import subprocess
from click import command
from pip import main

from setuptools import Command
import speech_recognition as sr
import pyttsx3
import pywhatkit
import flask
import datetime
import wikipedia
import pyjokes
import webbrowser
import os
import random
import smtplib
import winshell
# import yagmail

engine = pyttsx3.init('sapi5')
voices = engine.getProperty('voices')
rate = engine.getProperty('rate')
engine.setProperty('voice', voices[1].id)


def talk(voice):
    engine.say(voice)
    engine.runAndWait()
def wishMe():
    hour = int(datetime.datetime.now().hour)
    if hour >= 0 and hour < 12:
        talk('Good morning sir!')
    elif hour >= 12 and hour < 16:
        talk('Good Afternoon sir!')
    else:
        talk('Goog evening sir!')
    talk('tell me sir....,How can i help you.')


def take_command():
    listener = sr.Recognizer()
    with sr.Microphone() as source:
        print('listening....')
        listener.adjust_for_ambient_noise(source, duration=1);
        listener.pause_threshold = 1
        listener.energy_threshold = 300
        voice = listener.listen(source)
    try:
        print('Recognizing')
        command = listener.recognize_google(voice, language='en-in')
        print(f'You said:{voice}\n')
        if 'mini' in command:
            command = command.replace('mini', '')
            talk(command)
    except Exception as e:
        # print(e)
        talk('Sir ,can you say that again')
        return 'None'

    return command

def sendEmail(to, content):
    server = smtplib.SMTP('smtp.gmail.com', 587)
    server.ehlo()
    server.starttls()
    server.login('20dcs107@charusat.edu.in', 'D$_170803')
    server.sendmail('20dcs107@charusat.edu.in', to, content)
    server.close()


if __name__ == '__main__':
    clear = lambda: os.system('cls')
    clear()
    wishMe()
    while True:
        command = take_command().lower()
        print(Command)
        if 'time' in command:
            time = datetime.datetime.now().strftime('%I:%M%p')
            # print(time)
            talk('Current time is' + time)
        elif 'today' in command:
            date=datetime.datetime.now().strftime('%m-%d-%Y')
            talk(f'Sir, today is {date}')
        elif 'how are you' in command:
            talk('I am fine, Thank you')
            talk('How are you, Sir')
        elif 'fine' in command or 'good' in command:
            talk('Its good to know that your fine')
        elif 'not good' in command or 'sad' in command:
            talk('Ohhh!, so what I can do to make you happy?')
        elif 'tell me about you' in command:
            talk('I am Mini. A virtual voice assistant. I can do every thing which you want')
            talk('what you want?')
        elif 'are you single' in command:
            talk('I am in a relationship with someone other')
        elif 'love' in command:
            talk('Its hard to understand')
        elif 'who i am' in command:
            talk('If you talk then definitely your human.')
        elif 'why you come to world' in command:
            talk('Thanks to Darsh Shah and Dhruv Rangras. Its better to not know further.You cant stand it.')
        elif 'play music' in command:
            music_dir='C:\\My music'
            songs = os.listdir(music_dir)
            # num=random. randint(0,len(songs))
            print(songs)
            random=os.startfile(os.path.join(music_dir,songs[0]))
            sleep(30)            
        elif 'stop music' in command:
            os.system('TASKKILL /F /IM Media Player.exe')
        elif 'horror story' in command:
            engine.setProperty('rate',150)
            talk('We bought an old house, my boyfriend and I.... He is in charge of the new construction – converting the kitchen in to the master bedroom for instance, while I am on wallpaper removal duty. The previous owner papered EVERY wall and CEILING! Removing it is brutal, but oddly satisfying. The best feeling is getting a long peel, similar to your skin when you are peeling from a sunburn... I do not know about you but I kinda make a game of peeling, on the hunt for the longest piece before it rips...Under a corner section of paper in every room is a person’s name and a date.... Curiosity got the best of me one night when I Googled one of the names and discovered the person was actually a missing person, the missing date matching the date under the wallpaper! The next day, I made a list of all the names and dates... Sure enough each name was for a missing person with dates to match... We notified the police who naturally sent out the crime scene team... I overhead one tech say "yup, it is human..." Human? What is human? "Maam, where is the material you removed from the walls already? This is not wallpaper you were removing..."')
        elif 'date' in command:
            talk('sorry,I have a headache... but no problem we can go next sunday')
        elif 'are you single' in command:
            talk('I am in a relationship with someone other. you can find some one other')
        elif 'joke' in command:
            talk(pyjokes.get_joke())
            print(pyjokes.get_joke())
        elif 'lock window' in command:
            talk('locking the device')
            ctypes.windll.user32.LockWorkStation()
        elif 'empty recycle bin' in command:
            winshell.recycle_bin().empty(confirm = False, show_progress = False, sound = True)
            talk('Recycle Bin Recycled')
        elif 'restart' in command:
            subprocess.call(['shutdown', '/r'])
        elif 'hibernate' in command or 'sleep' in command:
            talk('Hibernating')
            subprocess.call('shutdown / h')
        elif 'log off' in command or 'sign out' in command:
            talk('Make sure all the application are closed before sign-out')
            time.sleep(5)
            subprocess.call(['shutdown', '/l'])
        elif 'write a note' in command:
            talk('What should i write, sir')
            note = take_command()
            file = open('Mini.txt', 'w')
            talk('Sir, Should i include date and time')
            snfm = take_command()
            if 'yes' in snfm or 'sure' in snfm:
                strTime = datetime.datetime.now().strftime('%m-%d-%Y %T:%M%p')
                file.write(strTime)
                file.write(' :- ')
                file.write(note)
            else:
                file.write(note)
        elif 'show note' in command:
            talk('Showing Notes')
            file = open('Marco.txt', 'r')
            print(file.read())
            talk(file.read(6))
        elif 'open code' in command:
            vspath = 'C:\\Users\\darsh\\AppData\\Local\\Programs\\Microsoft VS Code\\Code.exe'
            os.startfile(vspath)
        elif 'open whatsapp' in command:
            wpath = 'C:\\Users\\darsh\\AppData\\Local\\WhatsApp\\WhatsApp.exe'
            os.startfile(wpath)
        elif 'mail' in command:
            try:
                talk('What should i say?')
                content=take_command()
                to='shahsaumya47@gmail.com'
                sendEmail(to,content)
                talk('email has been sent!')
            except Exception as e:
                print(e)
                talk('sorry dear Darsh, I am not able to sent that emil') 
        elif 'whatsapp' in command:
            pywhatkit.sendwhatmsg('+918200690759','How are you',23,38)
            talk('sending massage')      
        elif 'wikipedia' in command:
            talk('Serching Wikipedia...')
            query = command.replace('wikipedia', '')
            info = wikipedia.summary(query, 1)
            talk('According to wikipedia')
            print(info)
            talk(info)
        elif 'open youtube' in command:
            webbrowser.open('youtube.com')
        elif 'on youtube' in command:
            video = command.replace('on youtube', '')
            talk('playing'+video)
            pywhatkit.playonyt(video)
            sleep(20)
        elif 'open google' in command:
            webbrowser.open('google.com')
        elif 'open yahoo' in command:
            webbrowser.open('yahoo.com')
        elif 'open facebook' in command:
            webbrowser.open('facebook.com')
        elif 'open gmail' in command:
            webbrowser.open('gmail.com')
        elif 'open youtube' in command:
            webbrowser.open('facebook.com')   
        if 'nice to talk you' in command:
            talk('okay Sir,if you need my help call me again...good by')
            break

        
