import requests
import json

# json.dumps() -> Python object to JSON String
# json.loads() -> JSON string to Python object

def jprint(obj):
    # create a formatted string of the Python JSON object
    text = json.dumps(obj, sort_keys=True, indent=4)
    print(text)

response = requests.get(
    "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22")
#print (response.status_code)
jprint(response.json())

response = requests.get("http://api.open-notify.org/iss-now.json")
print(response.status_code)
print(response.json())


# New York
parameters = {
    "lat": 40.71,
    "lon": -74
}

# params => Query String
response = requests.get(
    "http://api.open-notify.org/iss-pass.json", params=parameters)
jprint(response.json())

# Extracting parameters
pass_times = response.json()['response']
jprint(pass_times)

risetimes = []
for d in pass_times:
    time = d['risetime'] #key
    risetimes.append(time)

print(risetimes)

from datetime import datetime

times = []

for rt in risetimes:
    time = datetime.fromtimestamp(rt)
    times.append(time)
    print(time)
