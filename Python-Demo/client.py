import requests
import json
from time import sleep
from random import randrange

host = 'http://127.0.0.1:8080'
headers = {"content-type":"application/json"}
N = 5


# (long, lat)
route = [(40.3,45.2), (40.4,45.2), (40.5,45.2), (40.5,45.3), (40.5,45.4), (40.6,45.4), \
    (40.7,45.5), (40.7,45.6), (40.8,45.6), (40.8,45.7), (40.8,45.8), (40.9,45.8)]



# Register street
payload = {'streetName':'Moffet Blvd'}
r = requests.post(host + '/register', data=json.dumps(payload), headers=headers)

response = json.loads(r.text)
print response
behavior = response['behavior']
if behavior != 'sample':
    points = response['verificationPoints']

count = 0
for longitude, latitude in route:



    if behavior == 'sample':
        if count == 0:
            pointa = (longitude, latitude)
            pointb = route[1]
            payload = {'streetName':'Moffet Blvd', \
                'segment': {'a': {'longitude':pointa[0], 'latitude':pointa[1]}, \
                            'b': {'longitude':pointb[0], 'latitude':pointb[1]} \
                        }   \
                }
            r = requests.post(host + '/recommendation', data=json.dumps(payload), headers=headers)
            print "received recommendation"
            print r.text
            count += 1

        payload = {'streetName':'Moffet Blvd', \
                    'sample':{'longitude':longitude, 'latitude':latitude}}
        r = requests.post(host + '/streetsample', data=json.dumps(payload), headers=headers)
        sleep(1)
    else:
        pointa = points.pop(0)
        try:
            pointb = points[0]
        except (IndexError):
            break

        if count == 0:
            count = N
            payload = {'streetName':'Moffet Blvd', \
                'segment': {'a': {'longitude':pointa['longitude'], 'latitude':pointa['latitude']}, \
                            'b': {'longitude':pointb['longitude'], 'latitude':pointb['latitude']} \
                           }   \
                }
            r = requests.post(host + '/recommendation', data=json.dumps(payload), headers=headers)
            print "received recommendation"
            print r.text
        count -= 1

        print "Evaluating next segment"
        payload = {'streetName':'Moffet Blvd', \
                'segment': {'a': {'longitude':pointa['longitude'], 'latitude':pointa['latitude']}, \
                            'b': {'longitude':pointb['longitude'], 'latitude':pointb['latitude']} \
                           },   \
                'milliseconds': 0}
        r = requests.post(host + '/evaluationstart', data=json.dumps(payload), headers=headers)
        time_taken = randrange(1000 , 3000)
        sleep(float(time_taken) / 1000)

        payload = {'streetName':'Moffet Blvd', \
                'segment': {'a': {'longitude':pointa['longitude'], 'latitude':pointa['latitude']}, \
                            'b': {'longitude':pointb['longitude'], 'latitude':pointb['latitude']} \
                           },   \
                'milliseconds': time_taken}
        r = requests.post(host + '/evaluate', data=json.dumps(payload), headers=headers)
        print "Segment Evaluated"





