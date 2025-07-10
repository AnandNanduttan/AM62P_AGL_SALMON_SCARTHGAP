#!/bin/bash
# Start AP mode

INTERFACE="uap0"
AP_CONF="hostapd.conf"
CREDENTIALS_FILE="ap_credentials.txt"

# Read SSID and password from the credentials file
if [[ -f "$CREDENTIALS_FILE" ]]; then
    SSID=$(sed -n '1p' $CREDENTIALS_FILE)
    PASS=$(sed -n '2p' $CREDENTIALS_FILE)
else
    echo "Error: Credentials file $CREDENTIALS_FILE not found!"
    exit 1
fi

# Ensure SSID and password are not empty
if [[ -z "$SSID" || -z "$PASS" ]]; then
    echo "Error: SSID or Password is empty!"
    exit 1
fi

# Generate hostapd configuration file
cat <<EOF > $AP_CONF
interface=$INTERFACE
driver=nl80211
ssid=$SSID
hw_mode=g
channel=6
wpa=2
wpa_passphrase=$PASS
wpa_key_mgmt=WPA-PSK
wpa_pairwise=TKIP
rsn_pairwise=CCMP
EOF

echo "Starting AP mode..."
sudo iw dev wlan0 interface add $INTERFACE type __ap
ifconfig $INTERFACE up
hostapd $AP_CONF -B
ifconfig $INTERFACE 192.168.1.1   # Assign an IP for the AP interface

echo "AP mode started on $INTERFACE with SSID $SSID"

