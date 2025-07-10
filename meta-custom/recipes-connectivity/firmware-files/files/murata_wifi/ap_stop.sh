#!/bin/bash
# Stop AP mode

INTERFACE="uap0"

echo "Stopping AP mode..."
killall hostapd
ifconfig $INTERFACE down
echo "AP mode stopped on $INTERFACE"

