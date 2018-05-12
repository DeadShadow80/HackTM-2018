import nltk
from nltk.corpus import stopwords

search = ["search","find","seek","scan"]
actions =["eat","drink","movie","football","bike","sport","bike"]
places =["location","place","area","zone","neighbourhood"]

def defrag_payload(data):
    tokens = nltk.word_tokenize(data)
    return tokens


def remove_punctuation(data):
    for element in data:
        if element.isalnum() == False:
            data.remove(element)
    return data

#In testing
def find_key_word(data):
    for element in data:
        for key_word in search:
            if element == key_word:
                #Here will be returneed the command for the app
                return True
            else:
                return False


def remove_stop_word(word_list):
    processed_word_list = []
    for word in word_list:
        word = word.lower() # in case they arenet all lower cased
        if word not in stopwords.words("english"):
            processed_word_list.append(word)
        print (processed_word_list)
#def find_new_key_word(data):


def searh_for_a_searchword(data):
     for element in data:
         for word in search:
             if element == word:
                 return True
             else:
                 return element



def search_for_an_action(data):
     for element in data:
         for action in actions:
             if element == action:
                 return True
             else:
                 return element



def search_for_a_place(data):
     for element in data:
         for place in places:
             if element == place:
                 return True
             else:
                 return element


def add_data_search(data):
    global search
    if data != True:
        search.append(data)
    #print("Search values: ",search)

def add_data_place(data):
    global places
    if data != True:
        places.append(data)
    #print("Search values: ", places)

def add_data_action(data):
    global actions
    if data != True:
        actions.append(data)
    #print("Search values: ", actions)
