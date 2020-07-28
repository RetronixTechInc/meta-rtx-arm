#!/bin/sh

logger "starting initscript"

# do some work here. Mount rootfs as rw if needed.

logger "initscript work done"

echo ds2482 0x18 > /sys/bus/i2c/devices/i2c-2/new_device
