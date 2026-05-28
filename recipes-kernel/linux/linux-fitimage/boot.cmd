echo 'Retronix auto-boot Ver. 0.1'
echo --- Check J1 ---;
i2c dev 1
if i2c probe 0x10; then
    echo IMX219 is detected;
    setenv j1_conf '#j1-imx219';
else
    setenv j1_conf '';    
fi

echo --- Check J2 ---;
i2c dev 2
if i2c probe 0x10; then
    echo  IMX219 is detected;
    setenv j2_conf '#j2-imx219';
else
    setenv j2_conf '';
fi

echo --- Check J4 ---;
i2c dev 0 && i2c mw 0x71 0x0 0x7 && i2c speed 100000
if i2c probe 0x45; then
    # Power on I2C display
    i2c mw 0x45 0x02 0x00 && sleep 0.1 && i2c mw 0x45 0x02 0x03 && sleep 0.1

    # Raspberry Pi Touch Display Case
    if i2c probe 0x5d; then
        i2c read 0x5d 0x8047.2 0x1 0x48000000
        setexpr.b config_version *0x48000000
        if test 0x${config_version} -eq 0x41; then
            echo Raspberry Pi Touch Display 2 7inch model is detected;
            setenv j4_conf '#rpi-display-2-7in';
        else
            setenv j4_conf '';
        fi
    else
        setenv j4_conf '';
    fi
else
    setenv j4_conf '';
fi

if test -z "${cma_size}"; then setenv cma_size "900M"; fi
if test -z "${pcie_option}"; then setenv pcie_option "pci=pcie_bus_perf"; fi
setenv bootargs "rw root=/dev/mmcblk0p1 rootfstype=ext4 rootwait cma=${cma_size} clk_ignore_unused ${pcie_option}"

echo --- Check Boot device ---;
echo Boot device is MMC (Forced).
setenv initramfs_conf '#default'

echo --- Booting ---;
setenv conf "${initramfs_conf}${j1_conf}${j2_conf}${j4_conf}"
echo bootcmd: bootm ${loadaddr}${conf}
bootm ${loadaddr}${conf}

