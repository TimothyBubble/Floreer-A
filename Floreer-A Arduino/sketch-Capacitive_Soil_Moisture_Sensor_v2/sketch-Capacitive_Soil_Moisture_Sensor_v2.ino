void setup() {
  Serial.begin(9600);
}

void loop() {
  int rawValue = analogRead(A0);
  int percentage = map(rawValue, 700, 190, 0, 100);
  percentage = constrain(percentage, 0, 100);
  Serial.println(percentage);
  delay(1000);
}
