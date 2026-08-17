import serial
import requests
import time

SERIAL_PORT = "COM3"
BAUD_RATE = 9600
API_URL = "http://localhost:8080/api/readings"
SENSOR_ID = 4
PLANT_ID = 1

ser = serial.Serial(SERIAL_PORT, BAUD_RATE, timeout=2)
time.sleep(2)  # give the Arduino a moment to reset after opening the port

while True:
    line = ser.readline().decode("utf-8").strip()
    if line.isdigit():
        value = float(line)
        payload = {
            "value": value,
            "timeStamp": time.strftime("%Y-%m-%dT%H:%M:%SZ", time.gmtime()),
            "sensor": {"id": SENSOR_ID},
            "plant": {"id": PLANT_ID}
        }
        response = requests.post(API_URL, json=payload)
        print(f"Sent {value}% -> status {response.status_code}")
    time.sleep(1)