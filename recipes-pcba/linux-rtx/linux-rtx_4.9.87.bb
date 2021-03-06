# Copyright (C) 2013-2016 Freescale Semiconductor
# Copyright 2017-2018 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Linux Kernel provided and supported by NXP"
DESCRIPTION = "Linux Kernel provided and supported by NXP with focus on \
i.MX Family Reference Boards. It includes support for many IPs such as GPU, VPU and IPU."

require recipes-kernel/linux/linux-imx.inc

DEPENDS += "lzop-native bc-native"

SRCBRANCH = "RTX_4.9.87_1.0.x"
SRC_URI = "git://github.com/RetronixTechInc/linux-rtx.git;protocol=git;branch=${SRCBRANCH} \
	"
DEFAULT_PREFERENCE = "1"

SRCREV = "RTX_4.9.87_1.0.x"
#SRCREV = "7cee0b9c75504b53c86d6adfcc1dd89af77efa17"

DO_CONFIG_V7_COPY = "no"
DO_CONFIG_V7_COPY_mx6 = "yes"
DO_CONFIG_V7_COPY_mx7 = "yes"
DO_CONFIG_V7_COPY_mx8 = "no"

addtask copy_defconfig after do_unpack before do_preconfigure
do_copy_defconfig () {
    install -d ${B}
    if [ ${DO_CONFIG_V7_COPY} = "yes" ]; then
        # copy latest rtx-imx6q-pitx-b21_defconfig to use for mx6, mx6ul and mx7
        mkdir -p ${B}
        cp ${S}/rtx/configs/rtx-imx6q-pitx-b21_defconfig ${B}/.config
        cp ${S}/rtx/configs/rtx-imx6q-pitx-b21_defconfig ${B}/../defconfig
    else
        # copy latest defconfig to use for mx8
        mkdir -p ${B}
        cp ${S}/arch/arm64/configs/defconfig ${B}/.config
        cp ${S}/arch/arm64/configs/defconfig ${B}/../defconfig
    fi
}

COMPATIBLE_MACHINE = "(mx6|mx7|mx8)"
EXTRA_OEMAKE_append_mx6 = " ARCH=arm"
EXTRA_OEMAKE_append_mx7 = " ARCH=arm"
EXTRA_OEMAKE_append_mx8 = " ARCH=arm64"
