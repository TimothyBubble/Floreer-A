const int ledPin = 8;
const int sensorPin = A0;

int dryValue = 700;
int wetValue = 190;

void setup() {
  Serial.begin(9600);
  pinMode(ledPin, OUTPUT);
}

void loop() {
  int rawValue = analogRead(sensorPin);
  int percentage = map(rawValue, dryValue, wetValue, 0, 100);
  percentage = constrain(percentage, 0, 100);

  Serial.println(percentage);

  if(percentage < 30) {
    digitalWrite(ledPin, HIGH);
  } else {
    digitalWrite(ledPin, LOW);
  }

  delay(1000);
}
