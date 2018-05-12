from functions import *
import nltk

payload = "Look for a place to eat with IT people"

#Defrag payload into a list
tokens=defrag_payload(payload)

#Remove the punctuation from the list
remove_punctuation(tokens)

#Remove the stopwords from the list
remove_stop_word(tokens)

global data_checker_search
global data_checker_action
global data_checker_place

data_checker_search = searh_for_a_searchword(tokens)

data_checker_action = search_for_an_action(tokens)

data_checker_place = search_for_a_place(tokens)

add_data_action(data_checker_place)

add_data_action(data_checker_action)

add_data_search(data_checker_search)


print(search)






#tokens = nltk.word_tokenize(sentence)


#tagged = nltk.pos_tag(tokens)

#print (tagged[0:6])
