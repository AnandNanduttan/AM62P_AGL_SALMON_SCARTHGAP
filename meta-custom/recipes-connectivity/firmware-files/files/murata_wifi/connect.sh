#!/bin/bash

# File containing SSID and password
SSID_PASS_FILE="sta_credentials.txt"

# Read SSID and password from file
SSID=$(sed -n '1p' "$SSID_PASS_FILE")
PASS=$(sed -n '2p' "$SSID_PASS_FILE")

# Create wpa_supplicant configuration
cat <<EOF > wpa_supplicant.conf
network={
    ssid="$SSID"
    psk="$PASS"
}
EOF

# Restart STA mode
./sta_stop.sh
./sta_start.sh

echo "Connecting to Wi-Fi network: $SSID"

