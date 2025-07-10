#!/bin/bash

# Stop wpa_supplicant and release DHCP
pkill wpa_supplicant
#dhclient -r wlan0

# Bring down the interface
ifconfig wlan0 down

echo "Station mode stopped"

