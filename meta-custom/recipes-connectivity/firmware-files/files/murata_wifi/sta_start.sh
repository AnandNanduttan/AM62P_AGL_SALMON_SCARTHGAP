#!/bin/bash

# Bring up wlan0 interface
ifconfig wlan0 up

# Start wpa_supplicant for STA mode
wpa_supplicant -B -i wlan0 -c wpa_supplicant.conf

echo "Station mode started and attempting to connect to Wi-Fi"

