import serial
import requests
import time

# TO-DO: Create SENSOR_ID automatically through env_id because ID's are not always the same
# Low priority right now because of low number of sensors
SERIAL_PORT = "/dev/ttyACM0" # Linux/Pi serial port or "COMx" on Windows
BAUD_RATE = 9600
API_URL = "http://<your-ip>:8080/api/readings" # Input your machine's actual local IP
SENSOR_ID = 1
PLANT_ID = 1

ser = serial.Serial(SERIAL_PORT, BAUD_RATE, timeout=2)
time.sleep(2)  # gives the Arduino a moment to reset after opening the port

while True:
    ser.reset_input_buffer()
    line = ser.readline().decode("utf-8").strip()
    if line.isdigit():
        value = float(line)
        payload = {
            "value": value,
            # Uses UTC time instead of local to keep timestamps consistent
            # time.strftime for easier to read time/date
            "timeStamp": time.strftime("%Y-%m-%dT%H:%M:%SZ", time.gmtime()),
            "sensor": {"id": SENSOR_ID},
            "plant": {"id": PLANT_ID}
        }
        response = requests.post(API_URL, json=payload)
        print(f"Sent {value}% -> status {response.status_code}")
    time.sleep(1)